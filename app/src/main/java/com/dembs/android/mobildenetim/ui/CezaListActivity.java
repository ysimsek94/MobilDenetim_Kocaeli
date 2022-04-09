package com.dembs.android.mobildenetim.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.CezaListAdapter;
import com.dembs.android.mobildenetim.models.CezaLine;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.utils.ToolbarInit;

import java.util.ArrayList;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CezaListActivity extends AppCompatActivity {
    public ProgressBar progressBar;
    Toolbar toolbar;
    Api api;
    String plaka;
    ArrayList<CezaLine> cezaList = new ArrayList<>();
    RecyclerView rvCezaList;
    TextView tvEmpytList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceza_list);
        api = ClientConfigs.createRetrofit(CezaListActivity.this).create(Api.class);
        initActivity();
        getCezaList();
    }

    private void getCezaList() {
        if (plaka !=null||!plaka.equals("")) {
            Call<ArrayList<CezaLine>> call = api.getCezaList(plaka);
            call.enqueue(new Callback<ArrayList<CezaLine>>() {
                @Override
                public void onResponse(Call<ArrayList<CezaLine>> call, Response<ArrayList<CezaLine>> response) {
                    if (response.isSuccessful()) {

                        cezaList = response.body();
                        if (cezaList==null||cezaList.size()<1)
                        {
                            progressBar.setVisibility(View.GONE);
                            tvEmpytList.setVisibility(View.VISIBLE);
                            tvEmpytList.setText("Ceza Bulunamadı..");
                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            setRecyclerView(cezaList);
                        }
                    }else
                    {
                        progressBar.setVisibility(View.GONE);
                        tvEmpytList.setVisibility(View.VISIBLE);
                        tvEmpytList.setText("Hata Oluştu..");
                    }

                }
                @Override
                public void onFailure(Call<ArrayList<CezaLine>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toasty.error(getApplicationContext(),"Hata :"+t.toString(),Toasty.LENGTH_LONG,true).show();
                }
            });
        }

    }

    private void initActivity() {
        toolbar = findViewById(R.id.toolbarCezaList);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        rvCezaList = findViewById(R.id.rvCezaList);
        tvEmpytList = findViewById(R.id.tvEmptyList);
        ToolbarInit toolbarInit = new ToolbarInit(toolbar, null);
        toolbarInit.getToolbar("Ceza Listesi", "", Color.WHITE, Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent i=getIntent();
        plaka=i.getStringExtra("plaka");
    }

    void setRecyclerView(ArrayList<CezaLine> cezaLineArrayList) {

        if (cezaLineArrayList != null && !cezaLineArrayList.isEmpty()) {

            rvCezaList.setLayoutManager(new LinearLayoutManager(CezaListActivity.this));
            CezaListAdapter belgeSorgulaAdapter = new CezaListAdapter(CezaListActivity.this, cezaLineArrayList);
            rvCezaList.setAdapter(belgeSorgulaAdapter);

        } else {
            rvCezaList.setVisibility(View.GONE);
            tvEmpytList.setVisibility(View.VISIBLE);
            tvEmpytList.setText("Liste Boş..");
        }
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