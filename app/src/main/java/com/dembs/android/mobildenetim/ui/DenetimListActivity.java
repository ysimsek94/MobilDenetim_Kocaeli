package com.dembs.android.mobildenetim.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.DenetimListAdapter;
import com.dembs.android.mobildenetim.models.DenetimLine;
import com.dembs.android.mobildenetim.models.Ruhsat;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.utils.ToolbarInit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DenetimListActivity extends AppCompatActivity {

    ArrayList<DenetimLine> denetimLineArrayList;
    ToolbarInit toolbarInit;
    Api api;
    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView tvEmpytList;
    Ruhsat ruhsatBilgisi=null;
    String tip;
    int denetimTipId;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denetim_list);

        initActivty();
    }

    private void getDenetimList() {
        api = ClientConfigs.createRetrofit(this).create(Api.class);

        Call<ArrayList<DenetimLine>> call = api.getDenetimler(ruhsatBilgisi.getPlaka());
        call.enqueue(new Callback<ArrayList<DenetimLine>>() {
            @Override
            public void onResponse(Call<ArrayList<DenetimLine>> call, Response<ArrayList<DenetimLine>> response) {

                if (response.isSuccessful()) {

                    denetimLineArrayList= response.body();

                    if (denetimLineArrayList==null||denetimLineArrayList.size()<1)
                    {
                        progressBar.setVisibility(View.GONE);
                        tvEmpytList.setVisibility(View.VISIBLE);
                        tvEmpytList.setText("Denetim Bulunamadı..");

                    }else{

                        progressBar.setVisibility(View.GONE);
                        Collections.sort(denetimLineArrayList, new Comparator<DenetimLine>() {
                            public int compare(DenetimLine o1, DenetimLine o2) {
                                if (o1.getDenetim().getDenetimTarihi() == null || o2.getDenetim().getDenetimTarihi() == null)
                                    return 0;
                                return o1.getDenetim().getDenetimTarihi().compareTo(o2.getDenetim().getDenetimTarihi());
                            }
                        });
                       Collections.reverse(denetimLineArrayList);
                        setRecyclerView(denetimLineArrayList);
                    }

                }else {
                    progressBar.setVisibility(View.GONE);
                    tvEmpytList.setVisibility(View.VISIBLE);
                    tvEmpytList.setText("Hata Oluştu..");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DenetimLine>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toasty.error(getApplicationContext(), String.valueOf(t), Toasty.LENGTH_LONG,true).show();
            }
        });
    }

    void setRecyclerView(ArrayList<DenetimLine> denetimList) {

        if (denetimList != null && !denetimList.isEmpty()) {

            recyclerView.setLayoutManager(new LinearLayoutManager(DenetimListActivity.this));
            DenetimListAdapter denetimListAdapter = new DenetimListAdapter(DenetimListActivity.this, denetimList,ruhsatBilgisi.getAracId());
            recyclerView.setAdapter(denetimListAdapter);

        }
        else
        {
            recyclerView.setVisibility(View.GONE);
            tvEmpytList.setVisibility(View.VISIBLE);
            tvEmpytList.setText("Liste Boş..");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        else if(item.getItemId()==R.id.add_denetim_item)
        {
            Intent i = new Intent(getApplicationContext(), DenetimGirisActivity.class);

            i.putExtra("detayId", ruhsatBilgisi.getDetayId());
            i.putExtra("denetimTurId", ruhsatBilgisi.getRuhsatTipId());
            i.putExtra("denetimTipId", denetimTipId);
            i.putExtra("tip", tip);
            i.putExtra("ruhsatNo", ruhsatBilgisi.getRuhsatNo());
            i.putExtra("Plaka", ruhsatBilgisi.getPlaka());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_denetim_menu, menu);

        // MenuItem menuItem = menu.findItem(R.id.add_denetim_item);

        return true;
    }

    public  void initActivty() {

        Intent i = getIntent();
        ruhsatBilgisi = (Ruhsat) i.getSerializableExtra("Ruhsat");
        denetimTipId=i.getIntExtra("denetimTipId",0);
        int ruhsatTipId =(ruhsatBilgisi.getRuhsatTipId());

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

        List<String> listItem=null;
        toolbar = findViewById(R.id.toolbarDenetimList);
        toolbarInit=new ToolbarInit(toolbar,listItem);
        toolbarInit.getToolbar("Denetim Listesi","",Color.WHITE,Color.WHITE);
        recyclerView = findViewById(R.id.rvDenetimList);
        tvEmpytList = findViewById(R.id.tvEmptyList);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getDenetimList();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initActivty();
    }


}

