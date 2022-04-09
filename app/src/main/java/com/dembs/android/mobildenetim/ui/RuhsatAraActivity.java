package com.dembs.android.mobildenetim.ui;

import static android.Manifest.permission.CAMERA;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.Ruhsat;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;
import com.dembs.android.mobildenetim.utils.ToolbarInit;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class RuhsatAraActivity extends AppCompatActivity {

    ProgressDialogManager progressDialogManager;
    private MaterialButton btnPlakaNoAra;
    private TextInputLayout tipPlaka;
    private EditText etPlakaNo;
    private  ArrayList<Ruhsat> ruhsatList=new ArrayList<>();
    private static final int REQUEST_CAMERA = 1;
    private Toolbar toolbar;
    private ToolbarInit toolbarInit;
    int ruhsatTipId;
    String tip;
    private List<String> itemList;
    SharedPreferences.Editor editor;
    SharedPreferences  sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruhsat_ara);

//        Bundle extras;
//        extras=getIntent().getExtras();
        // String subtitle=Objects.requireNonNull(extras.getCharSequence("subtitle")).toString();
        //  denetimTurId=extras.getInt("denetimTurId");

        itemList = new ArrayList<>();
        itemList.add(0, "Araç Bilgi Sorgulama");
        itemList.add(1, "Belge Sorgulama");
        itemList.add(2, "Reklam Sorgulama");
        itemList.add(3, "Denetlenen Rapor");
        toolbar = findViewById(R.id.toolbarRuhsatAra);
        toolbarInit=new ToolbarInit(toolbar,itemList);
        toolbarInit.getToolbarWithoutSubtitle("Ruhsat Arama",Color.WHITE,Color.WHITE);
        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        viewPager = (ViewPager) findViewById(R.id.viewpager);
//
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new PlakaNoFragment(denetimTurId), "Plaka");
//        adapter.addFragment(new RuhsatNoFragment(denetimTurId), "Ruhsat");
//
//        viewPager.setAdapter(adapter);
//
//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
//        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#D81B60"));
//        tabLayout.setupWithViewPager(viewPager);
        sharedPreferences = this.getSharedPreferences("sharedpref",Context.MODE_PRIVATE);
        ruhsatTipId = sharedPreferences.getInt("ruhsatTipId", 0);

        switch (ruhsatTipId) {
            case 1:
                tip="SERVİS ARACI";
                break;
            case 2:
                tip="TİCARİ TAKSİ";
                break;
            case 3:
                tip="TOPLU TAŞIMA";
                break;
            default:
                break;
        }

        progressDialogManager = new ProgressDialogManager(getApplicationContext(), "");
        etPlakaNo = findViewById(R.id.etPlakaNo);
       // etPlakaNo.setText("10 T 11111");
        etPlakaNo.setFilters(new InputFilter[]{new InputFilter.AllCaps()});



        IntentIntegrator integrator = new IntentIntegrator(RuhsatAraActivity.this);
        btnPlakaNoAra = findViewById(R.id.btnPlakaNoAra);
        btnPlakaNoAra.setOnClickListener(view1 -> {
            String plakaNo = etPlakaNo.getText().toString();
            if (plakaNo.equals("")) {
                Toasty.warning(Objects.requireNonNull(getApplicationContext()), "Bu Alan Boş Geçilemez !", Toasty.LENGTH_LONG, true).show();

            } else {

                Intent i =new Intent(getApplicationContext(), RuhsatListActivity.class);
                i.putExtra("plaka",plakaNo);
                startActivity(i);
            }
        });

        tipPlaka = findViewById(R.id.tipPlaka);
        tipPlaka.setEndIconActivated(true);
        tipPlaka.setEndIconOnClickListener(new View.OnClickListener() {
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


    }

    private void setRuhsatList(ArrayList<Ruhsat> ruhsatList) {
        Intent intent = new Intent(getApplicationContext(), RuhsatListActivity.class);
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
            Toasty.error(getApplicationContext(), "Hata: QR Kod Okunamadı..", Toasty.LENGTH_LONG, true).show();

        }


    }

    private String getPlakaNoByQrCode(String contents) {
        int begin = contents.toUpperCase().indexOf("PLAKA:") +6;
        int end = contents.toUpperCase().indexOf("MODEL:");
        String plaka = contents.substring(begin, end);
        plaka=plaka.replace("\n", "");
        plaka=plaka.replace("\r", "");

        return plaka;
    }

    @SuppressLint("NewApi")
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted) {
                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();

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
        new AlertDialog.Builder(getApplicationContext())
                .setMessage(message)
                .setPositiveButton("Tamam", okListener)
                .setNegativeButton("İptal", null)
                .create()
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbarInit.onCreateOptionsMenu(menu);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.add_denetim_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getTitle().equals("YeniDenetim"))
//        {
//            Intent i = new Intent(RuhsatAraActivity.this, DenetimGirisActivity.class);
//            i.putExtra("detayId", -1);
//            //i.putExtra("denetimTurId", ruhsatTipId);
//            // i.putExtra("tip",tip);
//            i.putExtra("isRuhsatsızDenetim" ,true);
//            i.putExtra("ruhsatNo","");
//            i.putExtra("Plaka", "");
//            //  i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(i);
//        }
//        else
            if (item.getItemId() == 3) {
            Intent i = new Intent(getApplicationContext(), DenetlenenRaporActivity.class);
            //  i.putExtra("title", item.getTitle());
            startActivity(i);
        }
        else {
            Intent i = new Intent(RuhsatAraActivity.this, SorgulaActivity.class);
            i.putExtra("title", item.getTitle());
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }



}