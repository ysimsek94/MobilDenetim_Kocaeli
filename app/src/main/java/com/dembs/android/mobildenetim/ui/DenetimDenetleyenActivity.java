package com.dembs.android.mobildenetim.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.DenetleyenListAdapter;
import com.dembs.android.mobildenetim.adapters.DenetleyenRaporListAdapter;
import com.dembs.android.mobildenetim.models.DenetimDenetleyen;
import com.dembs.android.mobildenetim.models.DenetleyenAdd;
import com.dembs.android.mobildenetim.models.DenetimPersonel;
import com.dembs.android.mobildenetim.models.DenetimDenetleyenRapor;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DenetimDenetleyenActivity extends AppCompatActivity {
   //region
    Toolbar toolbar;
    TextView tvEmptyList;
    SharedPreferences sharedPref;
    List<DenetimDenetleyenRapor> denetleyenRaporList = new ArrayList<>();
    List<DenetimPersonel> denetleyenListAll = new ArrayList<>();
    List<DenetimDenetleyen> denetimDenetleyenList = new ArrayList<>();
    DenetleyenAdd denetleyenAdd =new DenetleyenAdd();
    ProgressDialogManager progressDialogManager = new ProgressDialogManager(DenetimDenetleyenActivity.this, "");
    Api api;
    String[] denetleyenAdSoyadArray;
    int []  denetleyenIdArray;
    AlertDialog denetleyenDialog;
    int pos = 0;
    RecyclerView  rvDenetimDenetleyen;
    List<DenetimPersonel> selectedDenetleyen = new ArrayList<>();
    String isUser;
    int denetimId;
    DenetleyenRaporListAdapter denetleyenRaporListAdapter;
    private DenetleyenListAdapter denetleyenListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
//endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denetim_denetleyen);

        Intent i = getIntent();
        denetimId = i.getIntExtra("denetimId", 0);
        createLayoutElements();
        sharedPref = getApplicationContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        isUser = sharedPref.getString("isUser", null);
        api = ClientConfigs.createRetrofit(this).create(Api.class);
       //1
      //  getDenetimDenetleyenListRapor();
        if (denetimId > 0) {
            getDenetimDenetleyen();
        }
    }

    private void createLayoutElements() {
        toolbar = findViewById(R.id.toolbarDenetimDenetleyen);
        toolbar.setTitle("Denetim Denetleyen");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       // rvDenetleyen = findViewById(R.id.rvDenetleyen);
        rvDenetimDenetleyen = findViewById(R.id.rvDenetimDenetleyen);
        tvEmptyList = findViewById(R.id.tvEmptyList);
        //tvEmptyListRapor = findViewById(R.id.tvEmptyListRapor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_denetleyen_add, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        } else if (item.getItemId() == R.id.iconAdd) {
            if (denetimId > 0) {
                getKisiList();
            } else {
                Toasty.warning(DenetimDenetleyenActivity.this, "Denetleyen ekleyeceğiniz denetimi kaydedin..!", Toasty.LENGTH_LONG, true).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDenetimDenetleyen() {

        Call<List<DenetimDenetleyen>> call = api.getDenetimDenetleyen(denetimId);
        call.enqueue(new Callback<List<DenetimDenetleyen>>() {
            @Override
            public void onResponse(Call<List<DenetimDenetleyen>> call, Response<List<DenetimDenetleyen>> response) {

                if (response.isSuccessful()) {
                    denetimDenetleyenList = response.body();
                    setRecylerViewDenetimDenetleyen();
                } else {

                    Toasty.error(DenetimDenetleyenActivity.this, "Bir Hata Oluştu :" + response.errorBody().toString(), Toasty.LENGTH_LONG, true).show();
                }

            }

            @Override
            public void onFailure(Call<List<DenetimDenetleyen>> call, Throwable t) {
                progressDialogManager.dismissDialog();
                Toasty.error(DenetimDenetleyenActivity.this, "Bir Hata Oluştu :" + t.toString(), Toasty.LENGTH_LONG, true).show();
            }
        });
    }

    private void getKisiList() {
        Call<List<DenetimPersonel>> call = api.getDenetimDenetleyenAll();
        call.enqueue(new Callback<List<DenetimPersonel>>() {
            @Override
            public void onResponse(Call<List<DenetimPersonel>> call, Response<List<DenetimPersonel>> response) {
                if (response.isSuccessful()) {
                    denetleyenListAll = response.body();
                    if (denetleyenListAll.size() > 0)
                        denetleyenAdSoyadArray = new String[denetleyenListAll.size()];
                    convertStringArray();
                    createDialog();
                }
            }

            @Override
            public void onFailure(Call<List<DenetimPersonel>> call, Throwable t) {

                Toasty.error(getApplicationContext(), "HATA : " + t.toString(), Toasty.LENGTH_LONG, true).show();
            }
        });
    }

    private void setRecylerViewDenetimDenetleyen() {
        if (denetimDenetleyenList != null && !denetimDenetleyenList.isEmpty()) {
            rvDenetimDenetleyen.setVisibility(View.VISIBLE);
            tvEmptyList.setVisibility(View.GONE);
            rvDenetimDenetleyen.setHasFixedSize(true);
            rvDenetimDenetleyen.setLayoutManager(new LinearLayoutManager(DenetimDenetleyenActivity.this));
            denetleyenListAdapter = new DenetleyenListAdapter(getApplicationContext(), denetimDenetleyenList,denetimId);
            rvDenetimDenetleyen.setAdapter(denetleyenListAdapter);
        } else {
            rvDenetimDenetleyen.setVisibility(View.GONE);
            tvEmptyList.setVisibility(View.VISIBLE);
            tvEmptyList.setText("Liste Boş..");
        }

    }

    private void convertStringArray() {
        int index = 0;
        for (DenetimPersonel value : denetleyenListAll) {
            denetleyenAdSoyadArray[index] = value.getAdi() + " " + value.getSoyadi();
            int kId=value.getKullaniciId();
           // denetleyenIdArray[index]=kId;
            index++;
        }
    }

    private void createDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Kişi Listesi")
                .setItems(denetleyenAdSoyadArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (selectedDenetleyen.size()>0)
                        {
                            selectedDenetleyen.clear();
                        }
                        progressDialogManager.createProgressBar();
                        pos = which;
                        selectedDenetleyen.add(denetleyenListAll.get(pos));
                        denetleyenAdd.setDenetimId(denetimId);
                        denetleyenAdd.setKullaniciId(selectedDenetleyen.get(0).getKullaniciId());
                        denetleyenAdd.setKayitEden(isUser);
                        Date currentTime = Calendar.getInstance().getTime();
                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                       String dateTimeNow = sdf.format(currentTime);//get current date
                        denetleyenAdd.setIslemTarihi(dateTimeNow);
                        Call<Void> call = api.denetimDenetleyenAdd(denetleyenAdd);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful())
                                {
                                    getDenetimDenetleyen();
                                    Toasty.success(DenetimDenetleyenActivity.this,"Eklendi..",Toasty.LENGTH_LONG,true).show();

                                }else {
                                    Toasty.warning(DenetimDenetleyenActivity.this, "Eklenen kişi tekrar eklenemez.".toString(), Toasty.LENGTH_LONG, true).show();
                                }
                                progressDialogManager.dismissDialog();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toasty.error(DenetimDenetleyenActivity.this,"Hata : "+t.toString(),Toasty.LENGTH_LONG,true).show();
                                progressDialogManager.dismissDialog();
                            }
                        });

                    }
                });
        denetleyenDialog = builder.create();
        denetleyenDialog.show();

    }

}
