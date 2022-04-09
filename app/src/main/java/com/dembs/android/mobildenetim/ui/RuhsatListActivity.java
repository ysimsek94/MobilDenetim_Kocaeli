package com.dembs.android.mobildenetim.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.RuhsatBilgisiAdapter;
import com.dembs.android.mobildenetim.models.DuzeltmeTalebi;
import com.dembs.android.mobildenetim.models.Ruhsat;
import com.dembs.android.mobildenetim.models.VarakaBekleyen;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.utils.ToolbarInit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RuhsatListActivity extends AppCompatActivity {
    ArrayList<Ruhsat> ruhsatList=new ArrayList<>();
    Api api;
    Toolbar toolbar;
    String plakaNo;
    RecyclerView recyclerView;
    ToolbarInit toolbarInit;
    ArrayList<VarakaBekleyen> varakaBekleyenList = new ArrayList<>();
    ArrayList<DuzeltmeTalebi> duzeltmeTalebiBekleyenArrayList = new ArrayList<>();
    int varakaBekleyenSize = 0;
    int duzeltmeTalebiBekleyenSize=0;
    private List<String> itemList;
    int counter=0;
    MenuItem menuItem;
    TextView tvEmpytList;
    TextView badgeCounter;
   ProgressBar progressBar;

    int pendingNotifications = 0;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruhsat_bilgisi);

        Intent i=getIntent();
        plakaNo=i.getStringExtra("plaka");
        tvEmpytList = findViewById(R.id.tvEmptyList);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        getRuhsatList();

        itemList = new ArrayList<>();
        toolbar = findViewById(R.id.toolbarRuhsatBilgisi);
        toolbarInit = new ToolbarInit(toolbar, itemList);
        toolbarInit.getToolbar("Ruhsat Listesi", "", Color.WHITE, Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setRecyclerView(ruhsatList);
    }

    private void getRuhsatList() {
        api = ClientConfigs.createRetrofit(getApplicationContext()).create(Api.class);
        Call<ArrayList<Ruhsat>> call = api.getRuhsat(plakaNo);
        call.enqueue(new Callback<ArrayList<Ruhsat>>() {
            @Override
            public void onResponse(Call<ArrayList<Ruhsat>> call, Response<ArrayList<Ruhsat>> response) {
                if (response.isSuccessful()) {
                    Log.d("PlakaNoSorgula", "arama basıldı: "+response.message());
                    ruhsatList = response.body();
                    if (ruhsatList ==null||ruhsatList.size()<1) {
                        progressBar.setVisibility(View.GONE);
                        tvEmpytList.setVisibility(View.VISIBLE);
                        tvEmpytList.setText("Ruhsat Bulunamadı..");
                        Toasty.error(Objects.requireNonNull(getApplicationContext()), "Ruhsat Bulunamadı..", Toasty.LENGTH_LONG, true).show();
                    } else {
                        progressBar.setVisibility(View.GONE);
                        setRecyclerView(ruhsatList);
                        loadData();
                    }
                }else{
                    progressBar.setVisibility(View.GONE);
                    tvEmpytList.setVisibility(View.VISIBLE);
                    tvEmpytList.setText("Hata Oluştu..");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Ruhsat>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Hata" + t, Toast.LENGTH_LONG).show();
            }
        });
    }

    void setRecyclerView(ArrayList<Ruhsat> ruhsatList) {
        RuhsatListActivity denetimListele_activity = new RuhsatListActivity();
        recyclerView = findViewById(R.id.rvRuhsatList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(denetimListele_activity));
        RuhsatBilgisiAdapter ruhsatBilgisiAdapter = new RuhsatBilgisiAdapter(RuhsatListActivity.this, ruhsatList, varakaBekleyenList,varakaBekleyenSize,duzeltmeTalebiBekleyenArrayList,duzeltmeTalebiBekleyenSize);
        recyclerView.setAdapter(ruhsatBilgisiAdapter);

    }

    @SuppressLint("CheckResult")
    public void loadData() {
        api = ClientConfigs.createRetrofitRxJava(this).create(Api.class);
        Observable<ArrayList<VarakaBekleyen>> varakaBekleyenlerCallBack = api.varakaBekleyenler2(plakaNo);
        Observable<ArrayList<DuzeltmeTalebi>> duzeltmeTalebiBekleyenCallBack = api.getDuzeltmeTalebiBekleyen(plakaNo);

        Observable.merge( varakaBekleyenlerCallBack, duzeltmeTalebiBekleyenCallBack)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);
    }

    private void handleResults(ArrayList<? extends Serializable> serializables) {
        counter++;

        if (counter == 1) {
            this.varakaBekleyenList = (ArrayList<VarakaBekleyen>) serializables;
            this.varakaBekleyenSize= varakaBekleyenList.size();


        } else if (counter == 2) {
            this.duzeltmeTalebiBekleyenArrayList = (ArrayList<DuzeltmeTalebi>) serializables;
            this.duzeltmeTalebiBekleyenSize=duzeltmeTalebiBekleyenArrayList.size();
            setupBadge();

            setRecyclerView(ruhsatList);

          //  progressDialogManager.dismissDialog();

        }

    }

    private void handleError(Throwable t) {

        if (counter == 1) {
            Toasty.error(this, "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
        } else if (counter == 2) {
            Toasty.error(this, "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.notification_item);

        View actionView = menuItem.getActionView();
        badgeCounter = (TextView) actionView.findViewById(R.id.badge_counter);
        setupBadge();
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }else if(item.getItemId()==R.id.notification_item){
            if (ruhsatList.size()>0)
            {
                Intent i = new Intent(getApplicationContext(), RuhsatDetayActivity.class);
                i.putExtra("ruhsatNo", ruhsatList.get(0).getRuhsatNo());
                i.putExtra("plaka", ruhsatList.get(0).getPlaka());
                i.putExtra("denetimTurId", ruhsatList.get(0).getRuhsatTipId());
                i.putExtra("varakaBekleyenList",varakaBekleyenList);
                i.putExtra("duzeltmeTalebiBekleyenList",duzeltmeTalebiBekleyenArrayList);
                i.putExtra("varakaBekleyenListSize",varakaBekleyenSize);
                i.putExtra("duzeltmeTalebiBekleyenSize",duzeltmeTalebiBekleyenSize);
                startActivity(i);
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {

        if (badgeCounter != null) {
            if (varakaBekleyenSize == 0&&duzeltmeTalebiBekleyenSize==0) {
                if (badgeCounter.getVisibility() != View.GONE) {
                    badgeCounter.setVisibility(View.GONE);
                }
            } else {
                if (varakaBekleyenSize>0)
                { badgeCounter.setText(String.valueOf(Math.min(varakaBekleyenSize, 99)));}
                else if(duzeltmeTalebiBekleyenSize>0)
                { badgeCounter.setText(String.valueOf(Math.min(duzeltmeTalebiBekleyenSize, 99)));}
                if (badgeCounter.getVisibility() != View.VISIBLE) {
                    badgeCounter.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
