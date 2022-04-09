package com.dembs.android.mobildenetim.ui;

import static com.dembs.android.mobildenetim.utils.DateTimeInit.getFormattedTimeWithoutSaat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.AracLine;
import com.dembs.android.mobildenetim.models.SoforBilgisi;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;
import com.dembs.android.mobildenetim.utils.ToolbarInit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AracSoforBilgisiActivity extends AppCompatActivity {
    ProgressDialogManager progressDialogManager = new ProgressDialogManager(AracSoforBilgisiActivity.this, "");
    Toolbar toolbar;
    ToolbarInit toolbarInit;
    Api api;
    String plakaNo,tcKimlikNo;
    int denetimId,denetimTurId,aracId;
    Retrofit retrofit;
    TextView tvPlaka, tvTescilTarihi, tvAracCinsi, tvAracMarkasi, tvAracModeli, tvYolcuAdedi, tvAyaktaYolcuA, tvEngelliDurumu, tvMotorNo, tvSaseNo, tvIslemTarihi;
    TextView tvSoforAdi, tvSoforSoyadi, tvTcNo, tvEgitimProgrami, tvEgitimDonemi, tvEgitimTarihi, tvGecerlilikTarihi;
    private AracLine aracLine=new AracLine();
    private List<SoforBilgisi> soforBilgisiArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_sofor_bilgisi);

        progressDialogManager.createProgressBar();
        createLayoutElements();
        Intent intent = getIntent();
        tcKimlikNo=intent.getStringExtra("tcKimlikNo");
        denetimTurId=intent.getIntExtra("denetimTurId",0);
        plakaNo = getIntent().getStringExtra("plakaNo");
        denetimId = getIntent().getIntExtra("denetimId", 0);
        aracId = getIntent().getIntExtra("aracId", 0);
    }

    private void createLayoutElements() {


        toolbar = findViewById(R.id.toolbarAracSoforBilgisi);
        toolbarInit = new ToolbarInit(toolbar, null);
        toolbarInit.getToolbar("Araç Şöför Bilgisi", "", Color.WHITE, Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //AraçBilgisi CardView Elements
        tvPlaka = findViewById(R.id.tvPlaka);
        tvTescilTarihi = findViewById(R.id.tvTescilTarihi);
        tvAracCinsi = findViewById(R.id.tvAracCinsi);
        tvAracMarkasi = findViewById(R.id.tvAracMarkasi);
        tvAracModeli = findViewById(R.id.tvAracModeli);
        tvYolcuAdedi = findViewById(R.id.tvYolcuAdedi);
        tvAyaktaYolcuA = findViewById(R.id.tvAyaktaYolcuA);
        tvEngelliDurumu = findViewById(R.id.tvEngelliDurumu);
        tvMotorNo = findViewById(R.id.tvMotorNo);
        tvSaseNo = findViewById(R.id.tvSaseNo);
        tvIslemTarihi = findViewById(R.id.tvIslemTarihi);
        //Şöför Bilgisi Cardciew Elements
        tvSoforAdi = findViewById(R.id.tvSoforAdi);
        tvSoforSoyadi = findViewById(R.id.tvSoforSoyadi);
        tvTcNo = findViewById(R.id.tvTcNo);
        tvEgitimProgrami = findViewById(R.id.tvEgitimProgrami);
        tvEgitimDonemi = findViewById(R.id.tvEgitimDonemi);
        tvEgitimTarihi = findViewById(R.id.tvEgitimTarihi);
        tvGecerlilikTarihi = findViewById(R.id.tvGecerlilikTarihi);


    }


    @Override
    protected void onStart() {
        super.onStart();



        api =  ClientConfigs.createRetrofit(this).create(Api.class);
        getAracBilgisi();
        if (tcKimlikNo!=null&&tcKimlikNo.length()==11)
        getSoforBilgisi(tcKimlikNo,denetimTurId);

    }

    private void getAracBilgisi() {

        Call<AracLine> call = api.getAracBilgisi(aracId);
        call.enqueue(new Callback<AracLine>() {

            @Override
            public void onResponse(Call<AracLine> call, Response<AracLine> response) {

                if (response.isSuccessful()) {

                    aracLine = response.body();
                    if (aracLine != null) {
                        setAracBilgisiValues(aracLine);
                    }
                    progressDialogManager.dismissDialog();

                } else
                    Toasty.error(getApplicationContext(), "Geçersiz Parametre..", Toasty.LENGTH_LONG, true).show();

            }

            @Override
            public void onFailure(Call<AracLine> call, Throwable t) {

                Toasty.error(getApplicationContext(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
                progressDialogManager.dismissDialog();
            }
        });
    }

    private void setAracBilgisiValues(AracLine aracLine) {

        if (aracLine!=null) {

            tvPlaka.setText(aracLine.getArac().getPlaka());

            tvTescilTarihi.setText(getFormattedTimeWithoutSaat(aracLine.getArac().getTescilTarihi()));

            tvAracCinsi.setText(aracLine.getAracCinsi());

            tvAracMarkasi.setText(aracLine.getMarkasi());
            tvAracModeli.setText(String.valueOf(aracLine.getArac().getModel()));
            tvYolcuAdedi.setText(String.valueOf(aracLine.getArac().getYolcuAdet()));
            tvAyaktaYolcuA.setText(String.valueOf(aracLine.getArac().getYolcuAdetArti()));

            //  tvEngelliDurumu.setText(aracList.get(0).isEngelliDurum()?"Engelli":"-");
            tvMotorNo.setText(aracLine.getArac().getMotorNo());
            tvSaseNo.setText(aracLine.getArac().getSaseNo());

            tvIslemTarihi.setText(getFormattedTimeWithoutSaat(aracLine.getArac().getIslemTarihi()));

        }else
            Toasty.warning(getApplicationContext(),"Bu plakaya ait  aracbilgisi bulunamadı..",Toasty.LENGTH_LONG,true).show();

    }

    private void getSoforBilgisi(String tcKimlikNo,int denetimTurId) {
        Call<ArrayList<SoforBilgisi>> call = api.getSoforBilgisi(tcKimlikNo,denetimTurId);
        call.enqueue(new Callback<ArrayList<SoforBilgisi>>() {
            @Override
            public void onResponse(Call<ArrayList<SoforBilgisi>> call, Response<ArrayList<SoforBilgisi>> response) {

                if (response.isSuccessful()) {

                    soforBilgisiArrayList =response.body();
                    if(soforBilgisiArrayList !=null){
                        setSoforBilgisiValues(soforBilgisiArrayList);
                    }

                    progressDialogManager.dismissDialog();
                } else
                    Toasty.error(getApplicationContext(), "Geçersiz Parametre..", Toasty.LENGTH_LONG, true).show();
            }

            @Override
            public void onFailure(Call<ArrayList<SoforBilgisi>> call, Throwable t) {
                Toasty.error(getApplicationContext(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
                progressDialogManager.dismissDialog();
            }
        });
    }

    private void setSoforBilgisiValues(List<SoforBilgisi> soforBilgisi) {
if (soforBilgisi.size()>0) {
    tvSoforAdi.setText(checkStringNull(soforBilgisi.get(0).getAdi()));
    tvSoforSoyadi.setText(checkStringNull(soforBilgisi.get(0).getSoyadi()));
    tvTcNo.setText(checkStringNull(soforBilgisi.get(0).getTcKimlikNo()));
    tvEgitimProgrami.setText(checkStringNull(soforBilgisi.get(0).getEgitimProgrami()));
    tvEgitimDonemi.setText(checkStringNull(soforBilgisi.get(0).getDonemi()));
    tvEgitimTarihi.setText(checkStringNull(getFormattedTimeWithoutSaat(soforBilgisi.get(0).getBitisTarihi())));
    tvGecerlilikTarihi.setText(checkStringNull(getFormattedTimeWithoutSaat(soforBilgisi.get(0).getGecerlilikTarihi())));
}
}
    private String getFormattedTime(String getDateNow) {

        String s = getDateNow.substring(8, 10) + "-" + getDateNow.substring(5, 7) + "-" + getDateNow.substring(0, 4) + " " + getDateNow.substring(11, 16);
        return s;
    }
private String checkStringNull(String value)
{
    return value==null?"-":value;
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
