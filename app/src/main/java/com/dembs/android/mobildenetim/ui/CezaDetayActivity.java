package com.dembs.android.mobildenetim.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.Ceza;
import com.dembs.android.mobildenetim.models.CezaLine;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;
import com.dembs.android.mobildenetim.utils.ToolbarInit;
import com.dembs.android.mobildenetim.utils.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import static com.dembs.android.mobildenetim.utils.DateTimeInit.getFormattedTime;

public class CezaDetayActivity extends AppCompatActivity {
    Toolbar toolbar;
    ProgressDialogManager progressDialogManager = new ProgressDialogManager(CezaDetayActivity.this, "");
    TextInputEditText  etPlakaNo, etCezaTuru, etKayitTipi, etVarakaNo, etIhlalMaddesi, etIhlalBendi, etIhlalTarihi, etIhlalAdresi,
           etAciklama, etEncKarTarih, etCezaTutar, etIslemTarihi, etKaydeden;
     ToolbarInit toolbarInit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceza_detay);

        createLayoutElements();

        CezaLine cezaLine = getIntent().getExtras().getParcelable("cezaLine");
        setElementsValue(cezaLine);
    }

    private void createLayoutElements() {
        progressDialogManager.createProgressBar();
        toolbar = findViewById(R.id.toolbarCezaDetay);
        toolbarInit = new ToolbarInit(toolbar, null);
        toolbarInit.getToolbar("Ceza Detay", "", Color.WHITE, Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        etRuhsatNo = findViewById(R.id.etRuhsatNo);
//        Util.setDisableEditText(etRuhsatNo);
        etPlakaNo = findViewById(R.id.etPlaka);
        Util.setDisableEditText(etPlakaNo);
        etCezaTuru = findViewById(R.id.etCezaTur);
        Util.setDisableEditText(etCezaTuru);
        etKayitTipi = findViewById(R.id.etKayitTipi);
        Util.setDisableEditText(etKayitTipi);
        etVarakaNo = findViewById(R.id.etVarakaNo);
        Util.setDisableEditText(etVarakaNo);
        etIhlalMaddesi = findViewById(R.id.etIhlalMaddesi);
        Util.setDisableEditText(etIhlalMaddesi);
        etIhlalBendi = findViewById(R.id.etIhlalBendi);
        Util.setDisableEditText(etIhlalBendi);
        etIhlalTarihi = findViewById(R.id.etIhlalTarih);
        Util.setDisableEditText(etIhlalTarihi);
        etIhlalAdresi = findViewById(R.id.etIhlalAdresi);
        Util.setDisableEditText(etIhlalAdresi);
        etAciklama = findViewById(R.id.etAciklama);
        Util.setDisableEditText(etAciklama);
        etEncKarTarih = findViewById(R.id.etEncKararTarihi);
        Util.setDisableEditText(etEncKarTarih);
        etCezaTutar = findViewById(R.id.etCezaTutar);
        Util.setDisableEditText(etCezaTutar);
        etIslemTarihi = findViewById(R.id.etIslemTarihi);
        Util.setDisableEditText(etIslemTarihi);
        etKaydeden = findViewById(R.id.etKaydeden);
        Util.setDisableEditText(etKaydeden);


    }

    @SuppressLint("SetTextI18n")
    private void setElementsValue(CezaLine cezaLine) {
        if (cezaLine!=null) {
            Ceza ceza=cezaLine.getCeza();
           // etRuhsatNo.setText(ceza.getRuhsatNo()==null?" - ":ceza.getRuhsatNo());

            etPlakaNo.setText(ceza.getPlaka()==null?" - ":ceza.getPlaka());

            etCezaTuru.setText(cezaLine.getDenetimTuru()==null?" - ":cezaLine.getDenetimTuru());

            etKayitTipi.setText(cezaLine.getKayitTipi()==null?" - ":cezaLine.getKayitTipi());

            etVarakaNo.setText(String.valueOf(ceza.getVarakaId()));

            etIhlalMaddesi.setText(cezaLine.getIhlalMaddeAdi()==null ? " - " : cezaLine.getIhlalMaddeAdi());

            etIhlalBendi.setText(cezaLine.getIhlalBendAdi()==null ? " - " : cezaLine.getIhlalBendAdi());

            etIhlalTarihi.setText((getFormattedTime(ceza.getIhlalTarihi())));

            etIhlalAdresi.setText(ceza.getIhlalAdres()==null ? " - " :ceza.getIhlalAdres());

            etAciklama.setText(ceza.getAciklama()==null ? " - " :ceza.getAciklama());

            etEncKarTarih.setText(ceza.getEncumenKararTarihi()==null ? " - " :ceza.getEncumenKararTarihi());

            etCezaTutar.setText(ceza.getEncumenCezaTutar() +"0 TL");

            etIslemTarihi.setText(ceza.getIslemTarihi()==null ? " - " :getFormattedTime(ceza.getIslemTarihi()));

            etKaydeden.setText(ceza.getKayitEden()==null ? " - " :ceza.getKayitEden());

            progressDialogManager.dismissDialog();


        }
        progressDialogManager.dismissDialog();
    }
    public String convertNullToString(String value){
        if (!value.equals("null"))
        {
            return value;
        }

        return "-";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
