package com.dembs.android.mobildenetim.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.GuzergahListAdapter;
import com.dembs.android.mobildenetim.models.Guzergah;
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

public class GuzergahListActivity extends AppCompatActivity {

    ArrayList<Guzergah> guzergahArrayList=new ArrayList<>();
    ToolbarInit toolbarInit;
    Api api;
    Toolbar toolbar;
    String ruhsatNo, plakaValue;
    RecyclerView recyclerView;
    TextView tvEmpytList;
    int belgeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guzergah_list);
        recyclerView = findViewById(R.id.rvGuzergahList);
        tvEmpytList=findViewById(R.id.tvEmptyList);
        initActivty();
    }

    void setRecyclerView(ArrayList<Guzergah> guzergahArrayList1) {

        if (guzergahArrayList1 != null && !guzergahArrayList1.isEmpty()) {

            recyclerView.setLayoutManager(new LinearLayoutManager(GuzergahListActivity.this));
            GuzergahListAdapter guzergahListAdapter = new GuzergahListAdapter(GuzergahListActivity.this, guzergahArrayList1);
            recyclerView.setAdapter(guzergahListAdapter);

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
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public  void initActivty()
    {

        Intent i = getIntent();
        belgeId =  i.getIntExtra("belgeId",0);


        List<String> listItem=null;
        toolbar = findViewById(R.id.toolbarGuzergahList);
        toolbarInit=new ToolbarInit(toolbar,listItem);
        toolbarInit.getToolbar("Güzergah Listesi","",Color.WHITE,Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        api = ClientConfigs.createRetrofit(this).create(Api.class);

        Call<ArrayList<Guzergah>> call = api.getGuzergahList(belgeId);
        call.enqueue(new Callback<ArrayList<Guzergah>>() {
            @Override
            public void onResponse(Call<ArrayList<Guzergah>> call, Response<ArrayList<Guzergah>> response) {

                if (response.isSuccessful()) {
                    guzergahArrayList= response.body();
                    setRecyclerView(guzergahArrayList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Guzergah>> call, Throwable t) {

                Toasty.error(getApplicationContext(), String.valueOf(t), Toasty.LENGTH_LONG,true).show();
            }
        });
    }
}

