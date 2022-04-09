package com.dembs.android.mobildenetim.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.Ruhsat;
import com.dembs.android.mobildenetim.utils.ToolbarInit;

import java.io.Serializable;
import java.util.Objects;

public class IslemMenuActivity extends AppCompatActivity {
    CardView cvYeniDenetim, cvDenetimListesi, cvAracUygunluk, cvReklamList, cvBelgeList,cvCezaSorgula;
    Toolbar toolbar;
    ToolbarInit toolbarInit;
    Ruhsat ruhsatBilgisi = null;
    String ruhsatNo;
    String tip;
    String denetimTipAdi;
    int denetimTipId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_islem_menu);
        toolbar = findViewById(R.id.toolbarIslemMenu);
        toolbarInit = new ToolbarInit(toolbar, null);
        toolbarInit.getToolbar("Ruhsat İşlemleri", "", Color.WHITE, Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initActivity();

        cvYeniDenetim.setOnClickListener(v -> {
            switch (ruhsatBilgisi.getRuhsatTipId()) { //Denetim Tipi Belirtir..
                case 1:
                    tip = "SERVİS ARACI";
                    denetimTipAdi ="Servis Araç Denetimi";
                    denetimTipId =1;

                    break;
                case 2:
                    tip = "TİCARİ TAKSİ";
                    denetimTipAdi ="Ticari Taksi Denetimi";
                    denetimTipId =2;
                    break;
                case 3:
                    tip = "TOPLU TAŞIMA ";
                    denetimTipAdi ="Toplu Taşıma Denetimi";
                    denetimTipId =3;
                    break;
                default:
                    break;
            }
            Intent i = new Intent(getApplicationContext(), DenetimGirisActivity.class);
            i.putExtra("detayId", ruhsatBilgisi.getDetayId());
            i.putExtra("denetimTurId", ruhsatBilgisi.getRuhsatTipId());
            i.putExtra("denetimTipId", denetimTipId);
            i.putExtra("aracId", ruhsatBilgisi.getAracId());
            i.putExtra("tip", tip);
            i.putExtra("ruhsatNo", ruhsatBilgisi.getRuhsatNo());
            i.putExtra("Plaka", ruhsatBilgisi.getPlaka());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        });

        cvDenetimListesi.setOnClickListener(v -> {


            switch (ruhsatBilgisi.getRuhsatTipId()) { //Denetim Tipi Belirtir..
                case 1:
                    tip = "SERVİS ARACI";
                    denetimTipAdi ="Servis Araç Denetimi";
                    denetimTipId =1;

                    break;
                case 2:
                    tip = "TİCARİ TAKSİ";
                    denetimTipAdi ="Ticari Taksi Denetimi";
                    denetimTipId =2;
                    break;
                case 3:
                    tip = "TOPLU TAŞIMA ";
                    denetimTipAdi ="Toplu Taşıma Denetimi";
                    denetimTipId =3;
                    break;
                default:
                    break;
            }
            // progressDialogManager = new ProgressDialogManager(getApplicationContext(), "");
            // progressDialogManager.createProgressBar();

            Intent intent = new Intent(getApplicationContext(), DenetimListActivity.class);
            intent.putExtra("Ruhsat", (Serializable) ruhsatBilgisi);
            intent.putExtra("denetimTipId", denetimTipId);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//            progressDialogManager.dismissDialog();
            startActivity(intent);
        });

        cvBelgeList.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), BelgeListActivity.class);
            i.putExtra("plaka", (Serializable) ruhsatBilgisi.getPlaka());
            startActivity(i);
        });

        cvReklamList.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ReklamListActivity.class);
            i.putExtra("plaka", (Serializable) ruhsatBilgisi.getPlaka());
            startActivity(i);
        });

        cvAracUygunluk.setOnClickListener(v -> {

            switch (ruhsatBilgisi.getRuhsatTipId()) { //Denetim Tipi Belirtir..
                case 1:
                    tip = "SERVİS ARACI";
                    denetimTipAdi ="Servis Araç Uygunluk Denetimi";
                    denetimTipId =4;

                    break;
                case 2:
                    tip = "TİCARİ TAKSİ";
                    denetimTipAdi ="Ticari Taksi Araç Uygunluk";
                    denetimTipId =5;
                    break;
                case 3:
                    tip = "TOPLU TAŞIMA ";
                    denetimTipAdi ="Toplu Taşıma Araç Uygunluk";
                    denetimTipId =6;
                    break;
                default:
                    break;
            }


            Intent i = new Intent(getApplicationContext(), DenetimGirisActivity.class);
            i.putExtra("detayId", ruhsatBilgisi.getDetayId());
            i.putExtra("denetimTurId", ruhsatBilgisi.getRuhsatTipId());//Ruhsat Türunu Belittir.
            i.putExtra("denetimTipId", denetimTipId);
            // i.putExtra("denetimTipAdi", denetimTipAdi);
            i.putExtra("tip",tip);
            i.putExtra("ruhsatNo", ruhsatBilgisi.getRuhsatNo());
            i.putExtra("isAracUygunluk" ,true);
            i.putExtra("Plaka", ruhsatBilgisi.getPlaka());
            startActivity(i);


//            Intent i = new Intent(getApplicationContext(), ReklamListActivity.class);
//            i.putExtra("plaka", (Serializable) ruhsatBilgisi.getPlaka());
//            startActivity(i);
        });

        cvCezaSorgula.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), CezaListActivity.class);
            i.putExtra("plaka", ruhsatBilgisi.getPlaka());
            startActivity(i);
        });
    }

    private void initActivity() {
        cvYeniDenetim = findViewById(R.id.cvYeniDenetim);
        cvDenetimListesi = findViewById(R.id.cvDenetimListesi);
        cvAracUygunluk = findViewById(R.id.cvAracUygunluk);
        cvReklamList = findViewById(R.id.cvReklamlar);
        cvBelgeList = findViewById(R.id.cvBelgeler);
        cvCezaSorgula = findViewById(R.id.cvCezaSorgula);

        Intent i = getIntent();
        ruhsatBilgisi = (Ruhsat) i.getSerializableExtra("Ruhsat");
//        if (ruhsatBilgisi != null) {
//            ruhsatNo = ruhsatBilgisi.getRuhsatNo();
//        } else {
//            ruhsatNo = i.getStringExtra("ruhsatNo");
//        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}