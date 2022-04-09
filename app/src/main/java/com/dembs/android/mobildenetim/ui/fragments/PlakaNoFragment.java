package com.dembs.android.mobildenetim.ui.fragments;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
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


public class PlakaNoFragment extends Fragment {

    ProgressDialogManager progressDialogManager;
    Api api;
    int denetimTurId;
    private MaterialButton btnPlakaNoAra;
    private ImageView ivQrCode;
    private EditText etPlakaNo;
    private  ArrayList<Ruhsat> ruhsatList=new ArrayList<>();
    private static final int REQUEST_CAMERA = 1;

    public PlakaNoFragment(int denetimTurId2) {
        this.denetimTurId = denetimTurId2;
    }

    public PlakaNoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plaka_no, container, false);
        progressDialogManager = new ProgressDialogManager(getContext(), "");
        etPlakaNo = view.findViewById(R.id.etPlakaNo);
        etPlakaNo.setText("10 T 11111");
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        btnPlakaNoAra = view.findViewById(R.id.btnPlakaNoAra);
        btnPlakaNoAra.setOnClickListener(view1 -> {
            Log.d("PlakaNoSorgula", "arama basıldı: ");
            String plakaNo = etPlakaNo.getText().toString();
            if (plakaNo.equals("")) {
                Toasty.warning(Objects.requireNonNull(getActivity()), "Bu Alan Boş Geçilemez !", Toasty.LENGTH_LONG, true).show();

            } else {

                api = ClientConfigs.createRetrofit(getActivity()).create(Api.class);
                Call<ArrayList<Ruhsat>> call = api.getRuhsat(plakaNo);
                call.enqueue(new Callback<ArrayList<Ruhsat>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Ruhsat>> call, Response<ArrayList<Ruhsat>> response) {
                        if (response.isSuccessful()) {
                            Log.d("PlakaNoSorgula", "arama basıldı: "+response.message());
                            ruhsatList = response.body();
                            if (ruhsatList ==null||ruhsatList.size()<1) {
                                Toasty.error(Objects.requireNonNull(getActivity()), "Ruhsat Bulunamadı..", Toasty.LENGTH_LONG, true).show();
                            } else {
                                setRuhsatList(ruhsatList);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Ruhsat>> call, Throwable t) {

                        Toast.makeText(getActivity(), "Hata" + t, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        ivQrCode = view.findViewById(R.id.ivQrCode);
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
    public void onResume() {
        super.onResume();
        progressDialogManager.dismissDialog();
    }

    @Override
    public void onStop() {
        super.onStop();
        progressDialogManager.dismissDialog();
    }

    private void setRuhsatList(ArrayList<Ruhsat> ruhsatList) {
        Intent intent = new Intent(getActivity(), RuhsatListActivity.class);
        intent.putExtra("ruhsatList", ruhsatList);
        startActivity(intent);
        // getActivity().finish();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() != null) {
                String temp = getPlakaNoByQrCode(result.getContents());
                etPlakaNo.setText(temp);
            }

        } else {

            Log.d("QRCode", "Hata  :  " + result.getContents());
            Toasty.error(getActivity(), "Hata: QR Kod Okunamadı..", Toasty.LENGTH_LONG, true).show();

        }


    }

    private String getPlakaNoByQrCode(String contents) {
        int begin = contents.indexOf("PLAKA:");
        int end = begin + 16;
        begin = begin + 6;
        return contents.substring(begin, end);
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

