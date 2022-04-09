package com.dembs.android.mobildenetim.ui.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.Ruhsat;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.ui.RuhsatListActivity;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;
import com.google.android.material.button.MaterialButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CAMERA;

public class RuhsatNoFragment extends Fragment {
    ProgressDialogManager progressDialogManager;
    private MaterialButton btnRuhsatNo;
    private ImageView ivQrCode;
    private EditText etRuhsatNo;
    Api api;
    private static final int REQUEST_CAMERA = 1;
    private IntentIntegrator qrScan;
    int denetimTurId;
    private ArrayList<Ruhsat> ruhsatList;
    public RuhsatNoFragment() {
        // Required empty public constructor
    }
    public RuhsatNoFragment(int denetimTurId)
    {
        this.denetimTurId=denetimTurId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ruhsat_no, container, false);
        progressDialogManager = new ProgressDialogManager(getContext(),"");
        etRuhsatNo = view.findViewById(R.id.etRuhsatNo);
        etRuhsatNo.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(7) });
        etRuhsatNo.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if( s.length() == 2 && !etRuhsatNo.getText().toString().contains("-") )
                {
                    String  t =  etRuhsatNo.getText().toString().trim();
                    t=t.substring(0,1) + "-" + t.substring(1);
                    etRuhsatNo.setText(t);
                    etRuhsatNo.setSelection(etRuhsatNo.getText().length());
                }


            }
        });
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        btnRuhsatNo = view.findViewById(R.id.btnRuhsatAra);
        btnRuhsatNo.setOnClickListener(view1 -> {

            String ruhsatNo = etRuhsatNo.getText().toString();
            if (ruhsatNo.equals("")) {
                Toasty.warning(Objects.requireNonNull(getActivity()), "Bu Alan Boş Geçilemez !", Toasty.LENGTH_LONG, true).show();
            }else {

                api = ClientConfigs.createRetrofit(getActivity()).create(Api.class);

                Call<ArrayList<Ruhsat>> call = api.getRuhsat(ruhsatNo);
                call.enqueue(new Callback<ArrayList<Ruhsat>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Ruhsat>> call, Response<ArrayList<Ruhsat>> response) {
                        if (response.isSuccessful()) {
                            ruhsatList = response.body();
                            if (ruhsatList ==null) {
                                Toasty.error(Objects.requireNonNull(getActivity()), "Ruhsat Bulunamadı..", Toasty.LENGTH_LONG, true).show();
                            }
                            else
                            {

                                setRuhsatList(ruhsatList);
                            }
                        } else
                        {
                            Toasty.error(Objects.requireNonNull(getActivity()), "Ruhsat Bulunamadı..", Toasty.LENGTH_LONG, true).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Ruhsat>> call, Throwable t) {

                        Toast.makeText(getActivity(), "Hata" + String.valueOf(t), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        ivQrCode=view.findViewById(R.id.ivQrCode);
        ivQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                //şeklindede sadece qr code taratabilirsiniz.
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                //Kamera açıldığında aşağıda yazı gösterecek
                integrator.setPrompt("QR Kod Taranıyor..");
                //telefonun kendi kamerasını kullandırıcaz
                integrator.setCameraId(0);
                //okuduğunda 'beep' sesi çıkarır
                integrator.setBeepEnabled(true);
                //okunan barkodun image dosyasını kaydediyor
                integrator.setBarcodeImageEnabled(false);
                //scan başlatılıyor
                integrator.initiateScan();
            }
        });


        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        progressDialogManager.dismissDialog();
    }
    private void setRuhsatList(ArrayList<Ruhsat> ruhsatList){
        Intent intent = new Intent(getActivity(), RuhsatListActivity.class);
        intent.putParcelableArrayListExtra("ruhsatList",ruhsatList);
        progressDialogManager.createProgressBar();
        startActivity(intent);
      // getActivity().finish();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            if(result != null) {
                if(result.getContents() != null) {

                    etRuhsatNo.setText(result.getContents());
                }

            } else {

                Log.d("QRCode", "Hata  :  "+result.getContents());
                Toasty.error(getActivity(), "Hata: QR Kod Okunamadı..", Toasty.LENGTH_LONG,true).show();

            }


        }

    @SuppressLint("NewApi")
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted) {
                        Toast.makeText(getActivity(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();

                        if (shouldShowRequestPermissionRationale(CAMERA)) {
                            showMessageOKCancel("You need to allow access to both the permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermissions(new String[]{CAMERA},
                                                    REQUEST_CAMERA);
                                        }
                                    });
                            return;
                        }

                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton("Tamam", okListener)
                .setNegativeButton("İptal", null)
                .create()
                .show();
    }

}
