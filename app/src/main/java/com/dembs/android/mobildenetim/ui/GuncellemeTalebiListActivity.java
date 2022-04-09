package com.dembs.android.mobildenetim.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.GuncellemeTalebiBekleyenListAdapter;
import com.dembs.android.mobildenetim.models.DuzeltmeTalebi;
import com.dembs.android.mobildenetim.utils.ToolbarInit;

import java.util.List;
import java.util.Objects;

public class GuncellemeTalebiListActivity extends AppCompatActivity {
    List<DuzeltmeTalebi> duzeltmeTalebiBekleyenList;
    ToolbarInit toolbarInit;
    Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guncelleme_talebi_list);

        initActivty();

    }
    public  void initActivty()
    {
        Intent i = getIntent();
        duzeltmeTalebiBekleyenList =i.getParcelableArrayListExtra("duzeltmeTalebiBekleyenList");

        toolbar = findViewById(R.id.toolbarGuncellemeTalebiList);
        toolbarInit=new ToolbarInit(toolbar,null);
        toolbarInit.getToolbar("Güncelleme Talebi Listesi","",Color.WHITE,Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setRecyclerView(duzeltmeTalebiBekleyenList);

    }
    void setRecyclerView(List<DuzeltmeTalebi> duzeltmeTalebiBekleyenList1) {

        if (duzeltmeTalebiBekleyenList1 != null && !duzeltmeTalebiBekleyenList1.isEmpty()) {
            recyclerView = findViewById(R.id.rvGuncellemeTalebi);
            recyclerView.setLayoutManager(new LinearLayoutManager(GuncellemeTalebiListActivity.this));
            GuncellemeTalebiBekleyenListAdapter denetimListAdapter = new GuncellemeTalebiBekleyenListAdapter(GuncellemeTalebiListActivity.this, duzeltmeTalebiBekleyenList1);
            recyclerView.setAdapter(denetimListAdapter);

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
