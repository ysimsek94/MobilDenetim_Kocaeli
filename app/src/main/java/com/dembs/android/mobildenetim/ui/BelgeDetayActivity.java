package com.dembs.android.mobildenetim.ui;

import static com.dembs.android.mobildenetim.utils.DateTimeInit.getFormattedTimeWithoutSaat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.BelgeLine;
import com.dembs.android.mobildenetim.models.BelgeLineResult;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.utils.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class BelgeDetayActivity extends AppCompatActivity {

    TextInputEditText etPlaka, etSayisi, etAracSahibi, etGecerlilikTarihi,
            etIslemTarihi, etKaydeden,etBelgeAdi;
    TextView tvEmptyList;
    BelgeLine belgeLine;
    BelgeLineResult belgeLineResult;
    Toolbar toolbar;
    Api api;
    private CardView cvRehberPersonel;
    private CardView cvTasinanKurum;
    private CardView cvGuzergah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belge_detay);

        belgeLineResult = getIntent().getParcelableExtra("belgeLineResult");
        belgeLine = belgeLineResult.getBelgeLine();
        initActivity();
        createApiRetrofit();
        if (belgeLine != null)
            setBelgeBilgileri();

        cvTasinanKurum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TasinanKurumListActivity.class);
                i.putExtra("belgeId", belgeLine.getBelge().getId());
                startActivity(i);
            }
        });
        cvRehberPersonel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RehberPersonelListActivity.class);
                i.putExtra("belgeId", belgeLine.getBelge().getId());
                startActivity(i);
            }
        });

        cvGuzergah.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), GuzergahActivity.class);
//            i.putExtra("belgeId", belgeLine.getBelge().getId());
            startActivity(i);
        });
    }

    private void setBelgeBilgileri() {

        String surucu = belgeLineResult.getKisi().getAdi() + " " + belgeLineResult.getKisi().getSoyadi();
        etPlaka.setText(belgeLineResult.getArac().getPlaka() == null ? " - " : belgeLineResult.getArac().getPlaka());
        etBelgeAdi.setText(belgeLineResult.getBelgeLine().getBelgeTuru() == null ? " - " : belgeLineResult.getBelgeLine().getBelgeTuru());
        etSayisi.setText(belgeLine.getBelge().getSayisi() == null ? " - " : belgeLine.getBelge().getSayisi());
        etAracSahibi.setText(surucu);
        etGecerlilikTarihi.setText((belgeLine.getBelge().getGecerlilikTarihi() == null) || (belgeLine.getBelge().getGecerlilikTarihi().equals("")) ? "-" : getFormattedTimeWithoutSaat(belgeLine.getBelge().getGecerlilikTarihi()));
        etIslemTarihi.setText((belgeLine.getBelge().getIslemTarihi() == null) || (belgeLine.getBelge().getIslemTarihi().equals("")) ? "-" : getFormattedTimeWithoutSaat(belgeLine.getBelge().getIslemTarihi()));
        etKaydeden.setText((belgeLine.getBelge().getKayitEden() == null) || (belgeLine.getBelge().getKayitEden().equals("")) ? "-" : belgeLine.getBelge().getKayitEden());

    }

    private void createApiRetrofit() {

        api = ClientConfigs.createRetrofit(this).create(Api.class);
    }

    private void initActivity() {
        toolbar = findViewById(R.id.toolbarBelgeDetay);
        toolbar.setTitle("Belge Detay");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etPlaka = findViewById(R.id.etPlaka);
        Util.setDisableEditText(etPlaka);
        etBelgeAdi = findViewById(R.id.etBelgeAdi);
        Util.setDisableEditText(etBelgeAdi);
        etSayisi = findViewById(R.id.etSayisi);
        Util.setDisableEditText(etSayisi);
        etAracSahibi = findViewById(R.id.etAracSahibi);
        Util.setDisableEditText(etAracSahibi);
        etGecerlilikTarihi = findViewById(R.id.etGecerlilikTarihi);
        Util.setDisableEditText(etGecerlilikTarihi);
        etIslemTarihi = findViewById(R.id.etIslemTarihi);
        Util.setDisableEditText(etIslemTarihi);
        etKaydeden = findViewById(R.id.etKaydeden);
        Util.setDisableEditText(etKaydeden);
        tvEmptyList = findViewById(R.id.tvEmptyList);
        cvRehberPersonel = findViewById(R.id.cvRehberPersonel);
        cvTasinanKurum = findViewById(R.id.cvTasinanKurum);
        cvGuzergah = findViewById(R.id.cvGuzergah);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
