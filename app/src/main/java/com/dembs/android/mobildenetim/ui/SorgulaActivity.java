package com.dembs.android.mobildenetim.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.AracLine;
import com.dembs.android.mobildenetim.models.AracReklamResult;
import com.dembs.android.mobildenetim.models.BelgeLineResult;
import com.dembs.android.mobildenetim.models.BelgeOzetLine;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.ui.fragments.AracBilgisiSorgulaFragment;
import com.dembs.android.mobildenetim.ui.fragments.BelgeSorgulamaFragment;
import com.dembs.android.mobildenetim.ui.fragments.ReklamBilgisiFragment2;
import com.dembs.android.mobildenetim.ui.fragments.ReklamSorgulamaFragment;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;
import com.dembs.android.mobildenetim.utils.ToolbarInit;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SorgulaActivity extends AppCompatActivity {
    Api api, apiObservable;
    LinearLayout linearLayout;
    int counter = 0;
    EditText etPlaka;
    TextInputLayout tipPlaka;
    ToolbarInit toolbarInit;
    Toolbar toolbar;
    ProgressDialogManager progressDialogManager = new ProgressDialogManager(SorgulaActivity.this, "");
    String header;
    private ArrayList<AracLine> aracList = new ArrayList<>();
    private ArrayList<AracReklamResult> reklamAracArrayList = new ArrayList<>();
    private ArrayList<BelgeOzetLine> belgeList = new ArrayList<>();
    String plaka;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorgula);

        toolbar = findViewById(R.id.toolbarSorgula2);
        Intent i = getIntent();
        header = i.getStringExtra("title");
        toolbarInit = new ToolbarInit(toolbar, null);
        toolbarInit.getToolbar(header, "", Color.WHITE, Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        linearLayout = findViewById(R.id.linearLayout);
        etPlaka = findViewById(R.id.etPlaka);
        etPlaka.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        tipPlaka = findViewById(R.id.tipPlaka);
        // etPlaka.addTextChangedListener(new MaskWatcher("## # ####"));


        createApiRetrofit();

        tipPlaka.setEndIconActivated(true);
        tipPlaka.setEndIconOnClickListener(v ->{



            counter = 0;
            plaka= etPlaka.getText().toString();
            if (!plaka.equals("")) {
                progressDialogManager.createProgressBar();
                switch (header) {
                    case "Araç Bilgi Sorgulama": {
                        Observable<ArrayList<AracLine>> aracBilgisiObservable = apiObservable.getAracBilgisiObservable(plaka);

                        Observable<ArrayList<AracReklamResult>> reklamListObservable = apiObservable.getReklamList(plaka,0 );
                        Observable.merge(aracBilgisiObservable, reklamListObservable)
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(this::handleAracBilgisiResult, this::handleAracBilgisiError);

                        break;
                    }
                    case "Belge Sorgulama": {

                        Call<ArrayList<BelgeOzetLine>> call = api.getBelgeList(plaka);
                        call.enqueue(new Callback<ArrayList<BelgeOzetLine>>() {
                            @Override
                            public void onResponse(Call<ArrayList<BelgeOzetLine>> call, Response<ArrayList<BelgeOzetLine>> response) {
                                if (response.isSuccessful()) {

                                    progressDialogManager.dismissDialog();
                                    belgeList = response.body();

                                    setBelgeSorgulaFragment();


                                } else
                                    progressDialogManager.dismissDialog();
                            }

                            @Override
                            public void onFailure(Call<ArrayList<BelgeOzetLine>> call, Throwable t) {
                                progressDialogManager.dismissDialog();
                                Toasty.error(getApplicationContext(), "Hata :" + t.toString(), Toast.LENGTH_LONG, true).show();

                            }
                        });
                        break;
                    }
                    default: {

                        Observable<ArrayList<AracLine>> aracBilgisiObservable = apiObservable.getAracBilgisiReklam(plaka);
                        Observable<ArrayList<AracReklamResult>> reklamListObservable = apiObservable.getReklamList(plaka,0 );
                        Observable.merge(aracBilgisiObservable, reklamListObservable)
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(this::handleReklamBilgisiResults, this::handleReklamBilgisiError);
                        //progressDialogManager.dismissDialog();

                        break;
                    }
                }
            } else {
                Toasty.warning(getApplicationContext(), "Plaka alanı boş geçilemez..!", Toasty.LENGTH_LONG, true).show();
            }
        });

    }

    private void handleAracBilgisiResult(ArrayList<? extends Parcelable> parcelables) {
        counter++;
        if (counter == 1) {
            this.aracList = (ArrayList<AracLine>) parcelables;
        } else if (counter == 2) {
            this.reklamAracArrayList = (ArrayList<AracReklamResult>) parcelables;
            setAracBilgisiFragment();
            setReklamBilgisiFragment();
        }
        progressDialogManager.dismissDialog();
    }

    private void handleAracBilgisiError(Throwable throwable) {
        progressDialogManager.dismissDialog();
        Toasty.error(this, "Hata  :" + throwable.toString(),
                Toasty.LENGTH_LONG, true).show();
    }

    private void createApiRetrofit() {


        api = ClientConfigs.createRetrofit(this).create(Api.class);
        apiObservable = ClientConfigs.createRetrofitRxJava(this).create(Api.class);


    }

    private void handleReklamBilgisiResults(ArrayList<? extends Parcelable> parcelables) {
        counter++;
        if (counter == 1) {
            this.aracList = (ArrayList<AracLine>) parcelables;
        } else if (counter == 2) {
            this.reklamAracArrayList = (ArrayList<AracReklamResult>) parcelables;
            setReklamSorgulamaFragment();
            setReklamBilgisiFragment();
        }
        progressDialogManager.dismissDialog();
    }

    private void handleReklamBilgisiError(Throwable t) {
        progressDialogManager.dismissDialog();
        Toasty.error(this, "Hata  :" + t.toString(),
                Toasty.LENGTH_LONG, true).show();
    }

    private void setAracBilgisiFragment() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout1);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("aracBilgisi", aracList);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AracBilgisiSorgulaFragment aracBilgisiSorgula2Fragment = new AracBilgisiSorgulaFragment();
        aracBilgisiSorgula2Fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frameLayout1, aracBilgisiSorgula2Fragment).commit();
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().
                    remove(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.frameLayout1))).commit();
        }


    }

    private void setReklamBilgisiFragment() {
        Bundle bundleReklamList = new Bundle();
        bundleReklamList.putParcelableArrayList("reklamList", reklamAracArrayList);
        bundleReklamList.putString("plaka", etPlaka.getText().toString());
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout2);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ReklamBilgisiFragment2 reklamBilgisiFragment = new ReklamBilgisiFragment2();

        reklamBilgisiFragment.setArguments(bundleReklamList);

        fragmentTransaction.replace(R.id.frameLayout2, reklamBilgisiFragment).commit();


        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().
                    remove(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.frameLayout2))).commit();
        }
    }

    private void setBelgeSorgulaFragment() {
        Bundle bundleBelgeList = new Bundle();
        bundleBelgeList.putParcelableArrayList("belgeList", belgeList);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BelgeSorgulamaFragment belgeSorgulamaFragment = new BelgeSorgulamaFragment();
        belgeSorgulamaFragment.setArguments(bundleBelgeList);
        fragmentTransaction.replace(R.id.frameLayout1, belgeSorgulamaFragment).commit();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 2.0f);
        linearLayout.setLayoutParams(layoutParams);
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().
                    remove(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.frameLayout1))).commit();
        }
    }

    private void setReklamSorgulamaFragment() {
        Bundle bundleAracList = new Bundle();
        bundleAracList.putParcelableArrayList("aracList", aracList);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ReklamSorgulamaFragment reklamSorgulamaFragment = new ReklamSorgulamaFragment();
        reklamSorgulamaFragment.setArguments(bundleAracList);

        fragmentTransaction.replace(R.id.frameLayout1, reklamSorgulamaFragment).commit();

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().
                    remove(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.frameLayout1))).commit();
        }
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onStart() {
        super.onStart();
        counter = 0;
        String plaka = etPlaka.getText().toString();
        if (!plaka.equals("")) {
            switch (header) {
                case "Araç Bilgi Sorgulama": {

                    Observable<ArrayList<AracLine>> aracBilgisiObservable = apiObservable.getAracBilgisiObservable(etPlaka.getText().toString());
                    Observable<ArrayList<AracReklamResult>> reklamListObservable = apiObservable.getReklamList(etPlaka.getText().toString(),0 );

                    Observable.merge(aracBilgisiObservable, reklamListObservable)
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(this::handleAracBilgisiResult, this::handleAracBilgisiError);
                    //  progressDialogManager.dismissDialog();

                    break;
                }
                case "Belge Sorgulama": {

                    Call<ArrayList<BelgeOzetLine>> call = api.getBelgeList(etPlaka.getText().toString());
                    call.enqueue(new Callback<ArrayList<BelgeOzetLine>>() {

                        @Override
                        public void onResponse(Call<ArrayList<BelgeOzetLine>> call, Response<ArrayList<BelgeOzetLine>> response) {
                            if (response.isSuccessful()) {

                                belgeList = response.body();
                                if (belgeList.size() > 0) {

                                    setBelgeSorgulaFragment();
                                    progressDialogManager.dismissDialog();
                                } else {
                                    setBelgeSorgulaFragment();
                                    progressDialogManager.dismissDialog();
                                    // alertDialogManager.createAlertDialog();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<BelgeOzetLine>> call, Throwable t) {
                            progressDialogManager.dismissDialog();
                            Toasty.error(getApplicationContext(), "Hata :" + t.toString(), Toast.LENGTH_LONG, true).show();

                        }
                    });
                    progressDialogManager.dismissDialog();
                    break;
                }
                default: {


                    Observable<ArrayList<AracLine>> aracBilgisiObservable = apiObservable.getAracBilgisiReklam(etPlaka.getText().toString());
                    Observable<ArrayList<AracReklamResult>> reklamListObservable = apiObservable.getReklamList(plaka,0);

                    Observable.merge(aracBilgisiObservable, reklamListObservable)
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(this::handleReklamBilgisiResults, this::handleReklamBilgisiError);
                    //progressDialogManager.dismissDialog();

                    break;
                }
            }
        }
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
