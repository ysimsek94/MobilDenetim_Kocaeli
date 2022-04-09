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
import com.dembs.android.mobildenetim.adapters.RehberPersonelListAdapter;
import com.dembs.android.mobildenetim.models.RehberPersonelLine;
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

public class RehberPersonelListActivity extends AppCompatActivity {

    ArrayList<RehberPersonelLine> rehberPersonelLineArrayList =new ArrayList<>();
    ToolbarInit toolbarInit;
    Api api;
    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView tvEmpytList;
    int belgeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehber_personel_list);
        recyclerView = findViewById(R.id.rvRehberPersonel);
        tvEmpytList=findViewById(R.id.tvEmptyList);
        initActivty();
    }

    void setRecyclerView(ArrayList<RehberPersonelLine> tasinanKurumLineArrayList1) {

        if (tasinanKurumLineArrayList1 != null && !tasinanKurumLineArrayList1.isEmpty()) {

            recyclerView.setLayoutManager(new LinearLayoutManager(RehberPersonelListActivity.this));
            RehberPersonelListAdapter rehberPersonelListAdapter = new RehberPersonelListAdapter(RehberPersonelListActivity.this, rehberPersonelLineArrayList);
            recyclerView.setAdapter(rehberPersonelListAdapter);

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
        toolbar = findViewById(R.id.toolbarRehberPersonelList);
        toolbarInit=new ToolbarInit(toolbar,listItem);
        toolbarInit.getToolbar("Rehber Personel Listesi","",Color.WHITE,Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        api = ClientConfigs.createRetrofit(this).create(Api.class);

        Call<ArrayList<RehberPersonelLine>> call = api.getRehberPersonelList(belgeId);
        call.enqueue(new Callback<ArrayList<RehberPersonelLine>>() {
            @Override
            public void onResponse(Call<ArrayList<RehberPersonelLine>> call, Response<ArrayList<RehberPersonelLine>> response) {

                if (response.isSuccessful()) {
                    rehberPersonelLineArrayList= response.body();
                    setRecyclerView(rehberPersonelLineArrayList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RehberPersonelLine>> call, Throwable t) {

                Toasty.error(getApplicationContext(), String.valueOf(t), Toasty.LENGTH_LONG,true).show();
            }
        });
    }
}

