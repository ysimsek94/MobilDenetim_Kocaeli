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
import com.dembs.android.mobildenetim.adapters.ReklamBilgisiAdapter;
import com.dembs.android.mobildenetim.models.AracReklamResult;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.utils.ToolbarInit;

import java.util.ArrayList;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReklamListActivity extends AppCompatActivity {
    public ProgressBar progressBar;
    Toolbar toolbar;
    Api api;
    String plaka;
    ArrayList<AracReklamResult> aracReklamResultArrayList = new ArrayList<>();
    RecyclerView rvReklamList;
    TextView tvEmpytList;
    ToolbarInit toolbarInit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reklam_list);
        api = ClientConfigs.createRetrofit(ReklamListActivity.this).create(Api.class);
        initActivity();

    }

    private void getReklamList() {
        Call<ArrayList<AracReklamResult>> call = api.getReklamListWithCall(plaka,0);
        call.enqueue(new Callback<ArrayList<AracReklamResult>>() {
            @Override
            public void onResponse(Call<ArrayList<AracReklamResult>> call, Response<ArrayList<AracReklamResult>> response) {
                if (response.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);
                    aracReklamResultArrayList = response.body();
                    setRecyclerView(aracReklamResultArrayList);

                } else
                    progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<AracReklamResult>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toasty.error(getApplicationContext(), "Hata :" + t.toString(), Toast.LENGTH_LONG, true).show();

            }
        });

    }

    private void initActivity() {
        progressBar = findViewById(R.id.progressBar);
        rvReklamList = findViewById(R.id.rvReklamBilgisiList);
        tvEmpytList = findViewById(R.id.tvEmptyList);
        progressBar.setVisibility(View.VISIBLE);
        toolbar = findViewById(R.id.toolbarReklamList);
        ToolbarInit toolbarInit = new ToolbarInit(toolbar, null);
        toolbarInit.getToolbar("Reklam Listesi", "", Color.WHITE, Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent i=getIntent();
        plaka=i.getStringExtra("plaka");

        getReklamList();
    }

    void setRecyclerView(ArrayList<AracReklamResult> reklamResultArrayList) {

        if (reklamResultArrayList != null && !reklamResultArrayList.isEmpty()) {

            rvReklamList.setLayoutManager(new LinearLayoutManager(ReklamListActivity.this));
            ReklamBilgisiAdapter reklamBilgisiAdapter = new ReklamBilgisiAdapter(ReklamListActivity.this, reklamResultArrayList,plaka);
            rvReklamList.setAdapter(reklamBilgisiAdapter);

        } else {
            rvReklamList.setVisibility(View.GONE);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        initActivity();
    }
}