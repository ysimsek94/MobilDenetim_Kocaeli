package com.dembs.android.mobildenetim.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.dembs.android.mobildenetim.adapters.BelgeSorgulaAdapter;
import com.dembs.android.mobildenetim.models.BelgeLineResult;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.utils.ToolbarInit;

import java.util.ArrayList;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BelgeListActivity extends AppCompatActivity {
    public ProgressBar progressBar;
    Toolbar toolbar;
    Api api;
    String plaka;
    ArrayList<BelgeLineResult> belgeLineResultArrayList = new ArrayList<>();
    RecyclerView rvBelgeList;
    TextView tvEmpytList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belge_list);
        api = ClientConfigs.createRetrofit(BelgeListActivity.this).create(Api.class);
        initActivity();
        getBelgeList();
    }

    private void getBelgeList() {
        Call<ArrayList<BelgeLineResult>> call = api.getBelgeList(plaka);
        call.enqueue(new Callback<ArrayList<BelgeLineResult>>() {
            @Override
            public void onResponse(Call<ArrayList<BelgeLineResult>> call, Response<ArrayList<BelgeLineResult>> response) {
                if (response.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);
                    belgeLineResultArrayList = response.body();
                    setRecyclerView(belgeLineResultArrayList);

                } else
                    progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<BelgeLineResult>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toasty.error(getApplicationContext(), "Hata :" + t.toString(), Toast.LENGTH_LONG, true).show();

            }
        });

    }

    private void initActivity() {
        progressBar = findViewById(R.id.progressBar);
        rvBelgeList = findViewById(R.id.rvBelgeList);
        tvEmpytList = findViewById(R.id.tvEmptyList);
        progressBar.setVisibility(View.VISIBLE);
        toolbar = findViewById(R.id.toolbarBelgeList);
        ToolbarInit toolbarInit = new ToolbarInit(toolbar, null);
        toolbarInit.getToolbar("Belge Listesi", "", Color.WHITE, Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent i=getIntent();
        plaka=i.getStringExtra("plaka");
    }

    void setRecyclerView(ArrayList<BelgeLineResult> belgeLineResultArrayList1) {

        if (belgeLineResultArrayList1 != null && !belgeLineResultArrayList1.isEmpty()) {

            rvBelgeList.setLayoutManager(new LinearLayoutManager(BelgeListActivity.this));
            BelgeSorgulaAdapter belgeSorgulaAdapter = new BelgeSorgulaAdapter(BelgeListActivity.this, belgeLineResultArrayList1);
            rvBelgeList.setAdapter(belgeSorgulaAdapter);

        } else {
            rvBelgeList.setVisibility(View.GONE);
            tvEmpytList.setVisibility(View.VISIBLE);
            tvEmpytList.setText("Liste Bo??..");
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