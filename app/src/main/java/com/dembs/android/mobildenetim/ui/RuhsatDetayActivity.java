package com.dembs.android.mobildenetim.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.DuzeltmeTalebi;
import com.dembs.android.mobildenetim.models.RuhsatBandrol;
import com.dembs.android.mobildenetim.models.SoforBilgisi;
import com.dembs.android.mobildenetim.models.VarakaBekleyen;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.ui.fragments.FragmentMain;
import com.dembs.android.mobildenetim.utils.AlertDialogManager;
import com.dembs.android.mobildenetim.utils.ToolbarInit;
import com.google.android.material.button.MaterialButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class RuhsatDetayActivity extends AppCompatActivity implements View.OnClickListener {

    int denetimTurId;
    MaterialButton btnBekleyenVaraka, btnGuncellemeTalebi, btnBandrolBilgisi, btnSoforEgitim;
    FrameLayout flBekleyenVaraka, flGuncellemeTalebi, flBandrolBilgisi, flSoforEgitim;
    Api api;
    String ruhsatNo,plaka;
    ArrayList<VarakaBekleyen> varakaBekleyenList = new ArrayList<>();
    ArrayList<RuhsatBandrol> ruhsatBandrolList = new ArrayList<>();
    ArrayList<SoforBilgisi> soforBilgisiArrayList = new ArrayList<>();
    ArrayList<DuzeltmeTalebi> duzeltmeTalebiBekleyenList = new ArrayList<>();
    int varakaBekleyenSize, duzeltmeTalebiBekleyenSize;
    AlertDialogManager alertDialogManager;
    int counter = 0;
    private Toolbar toolbar;
    private ToolbarInit toolbarInit;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruhsat_detay);

        Intent i = getIntent();
        varakaBekleyenList=i.getParcelableArrayListExtra("varakaBekleyenList");
        duzeltmeTalebiBekleyenList=i.getParcelableArrayListExtra("duzeltmeTalebiBekleyenList");
        duzeltmeTalebiBekleyenSize=i.getIntExtra("duzeltmeTalebiBekleyenSize",0);
        varakaBekleyenSize=i.getIntExtra("varakaBekleyenListSize",0);
        ruhsatNo = i.getStringExtra("ruhsatNo");
        plaka = i.getStringExtra("plaka");
        denetimTurId = i.getIntExtra("denetimTurId", 0);
        api = ClientConfigs.createRetrofitRxJava(this).create(Api.class);
        createLayoutElements();
        createFragment();
        //loadData(api);
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                //finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createLayoutElements() {

        toolbar = findViewById(R.id.toolbarRuhsatDetay);
        toolbarInit = new ToolbarInit(toolbar, null);
        toolbarInit.getToolbar("Ruhsat Detay", "", Color.WHITE, Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void handleResults(ArrayList<? extends Serializable> serializables) {
        counter++;

        if (counter == 1) {
            this.soforBilgisiArrayList = (ArrayList<SoforBilgisi>) serializables;
        }   else if (counter == 2) {
            this.ruhsatBandrolList = (ArrayList<RuhsatBandrol>) serializables;
            createFragment();
          //  progressDialogManager.dismissDialog();

        }
    }

    private void handleError(Throwable t) {

        if (counter == 1) {
            Toasty.error(this, "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
        } else if (counter == 2) {
            Toasty.error(this, "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
        } else if (counter == 3) {
            Toasty.error(this, "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
        } else if (counter == 4) {
            Toasty.error(this, "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
           // progressDialogManager.dismissDialog();

        }


    }

    private void createFragment() {

        Bundle main = new Bundle();
        main.putInt("denetimTurId",denetimTurId);
        main.putString("ruhsatNo",ruhsatNo);
        main.putString("plaka",plaka);
        main.putInt("varakaBekleyenListSize",varakaBekleyenSize);
        main.putInt("duzeltmeTalebiBekleyenSize",duzeltmeTalebiBekleyenSize);
      //  main.putParcelableArrayList("soforList", soforBilgisiArrayList);
        main.putParcelableArrayList("varakaBekleyen", varakaBekleyenList);
        main.putParcelableArrayList("duzeltmeTalebi", duzeltmeTalebiBekleyenList);
        //main.putParcelableArrayList("bandrolList", bandrolList);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentMain fragmentMain = new FragmentMain();
        fragmentMain.setArguments(main);
        fragmentTransaction.replace(R.id.frameLayoutMain, fragmentMain).commit();
        fragmentTransaction.addToBackStack(null);


    }

    @Override
    public void onBackPressed(){
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
               finish();
            //additional code
        } else {
            super.onBackPressed();
        }
    }


}
