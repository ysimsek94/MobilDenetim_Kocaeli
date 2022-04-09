package com.dembs.android.mobildenetim.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.AracLine;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.utils.ToolbarInit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AracBilgisiActivity extends AppCompatActivity {
    private Api api;
    private SharedPreferences sharedPreferences;
    private List<AracLine> aracList = new ArrayList<>();
    private TextView tvPlaka, tvTescilTarihi, tvAracCinsi, tvAracMarkasi, tvAracModeli, tvYolcuAdedi, tvAyaktaYolcuAdedi,
            tvEngelliDurumu, tvMotorNo, tvSaseNo, tvIslemTarihi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_bilgisi);
        sharedPreferences = getApplicationContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        String plaka = sharedPreferences.getString("plaka", "Kayıt Yok");

        Toolbar toolbar = findViewById(R.id.toolbarAracBilgisi);
        ToolbarInit toolbarInit = new ToolbarInit(toolbar, null);
        toolbarInit.getToolbar("Araç Bilgileri", "", Color.WHITE, Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        createLayoutElements();



        api = ClientConfigs.createRetrofit(this).create(Api.class);
        Call<ArrayList<AracLine>> call = api.getAracBilgisi(plaka);
        call.enqueue(new Callback<ArrayList<AracLine>>() {

            @Override
            public void onResponse(Call<ArrayList<AracLine>> call, Response<ArrayList<AracLine>> response) {
                if (response.isSuccessful()) {
                    aracList = response.body();
                    if (aracList!=null){
                        setLayoutElements(aracList);
                    }
                else
                        Toasty.error(getApplicationContext(), "Bu Plakaya ait araç bilgisi bulunamadı..", Toasty.LENGTH_LONG, true).show();

                }else
                    Toasty.error(getApplicationContext(), "Geçersiz parametre..", Toasty.LENGTH_LONG, true).show();
            }

            @Override
            public void onFailure(Call<ArrayList<AracLine>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "HATA :"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createLayoutElements() {
        tvPlaka = findViewById(R.id.tvPlakaNo);
        tvPlaka.setEnabled(false);
        tvTescilTarihi = findViewById(R.id.tvTescilTarihi);
        tvAracCinsi = findViewById(R.id.tvAracCinsi);
        tvAracMarkasi = findViewById(R.id.tvAracMarkasi);
        tvAracModeli = findViewById(R.id.tvAracModeli);
        tvYolcuAdedi = findViewById(R.id.tvYolcuAdedi);
        tvAyaktaYolcuAdedi = findViewById(R.id.tvAyaktaYolcuA);
        tvEngelliDurumu = findViewById(R.id.tvEngelliDurumu);
        tvMotorNo = findViewById(R.id.tvMotorNo);
        tvSaseNo = findViewById(R.id.tvSaseNo);
        tvIslemTarihi = findViewById(R.id.tvIslemTarihi);
    }

    public void setLayoutElements(List<AracLine> aracLineList) {
if (aracLineList.size()>0) {

    tvPlaka.setText(aracLineList.get(0).getArac().getPlaka());
    tvTescilTarihi.setText(aracLineList.get(0).getArac().getTescilTarihi());
    tvAracCinsi.setText(aracLineList.get(0).getAracCinsi());
    tvAracMarkasi.setText(aracLineList.get(0).getMarkasi());
    tvAracModeli.setText(String.valueOf(aracLineList.get(0).getArac().getModel()));
    tvYolcuAdedi.setText(String.valueOf(aracLineList.get(0).getArac().getYolcuAdet()));
    tvAyaktaYolcuAdedi.setText(String.valueOf(aracLineList.get(0).getArac().getYolcuAdetArti()));
    //todo engelli durum objede yok
   // tvEngelliDurumu.setText(arac.isEngelliDurum()?"Engelli":"-");
    tvMotorNo.setText(aracLineList.get(0).getArac().getMotorNo());
    tvSaseNo.setText(aracLineList.get(0).getArac().getSaseNo()==null||aracLineList.get(0).getArac().getSaseNo().isEmpty()?
            "-":(aracLineList.get(0).getArac().getSaseNo()));
     tvIslemTarihi.setText(aracLineList.get(0).getArac().getIslemTarihi());
}else
    Toasty.error(getApplicationContext(), "Bu Plakaya ait araç bilgisi bulunamadı..", Toasty.LENGTH_LONG, true).show();
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
