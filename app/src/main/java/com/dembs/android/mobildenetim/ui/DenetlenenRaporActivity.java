package com.dembs.android.mobildenetim.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.DenetleyenRaporListAdapter;
import com.dembs.android.mobildenetim.models.DenetimDenetleyenRapor;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dembs.android.mobildenetim.utils.Constants.ekipId;
import static com.dembs.android.mobildenetim.utils.DateTimeInit.getFormattedTimeYilAyGun;

public class DenetlenenRaporActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tvEmptyList, tvEmptyListRapor;
    RecyclerView rvDenetleyen;
    Api api;
    SharedPreferences sharedPref;
    String isUser;
    DenetleyenRaporListAdapter denetleyenRaporListAdapter;
    List<DenetimDenetleyenRapor> denetleyenRaporList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denetlenen_rapor);
        api = ClientConfigs.createRetrofit(this).create(Api.class);
        sharedPref = getApplicationContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        isUser = sharedPref.getString("isUser", null);
        createLayoutElements();
        getDenetimDenetleyenListRapor();
    }

    private void createLayoutElements() {
        toolbar = findViewById(R.id.toolbarDenetlenenRapor);
        toolbar.setTitle("Denetleyen Rapor Listesi");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rvDenetleyen = findViewById(R.id.rvDenetleyen);
        tvEmptyListRapor = findViewById(R.id.tvEmptyListRapor);
    }
    private void getDenetimDenetleyenListRapor() {
        Call<List<DenetimDenetleyenRapor>> call = api.getDenetimDenetleyenRapor(getFormattedTimeYilAyGun(),ekipId);
        call.enqueue(new Callback<List<DenetimDenetleyenRapor>>() {
            @Override
            public void onResponse(Call<List<DenetimDenetleyenRapor>> call, Response<List<DenetimDenetleyenRapor>> response) {

                if (response.isSuccessful()) {
                    denetleyenRaporList = response.body();
                    setRecylerViewDenetleyenRapor();
                } else
                    Toasty.error(DenetlenenRaporActivity.this, "Bir Hata Oluştu :" + response.errorBody().toString(), Toasty.LENGTH_LONG, true).show();
            }

            @Override
            public void onFailure(Call<List<DenetimDenetleyenRapor>> call, Throwable t) {
                Toasty.error(DenetlenenRaporActivity.this, "Bir Hata Oluştu :" + t.toString(), Toasty.LENGTH_LONG, true).show();
            }
        });
    }
    private void setRecylerViewDenetleyenRapor() {
        if (denetleyenRaporList != null && !denetleyenRaporList.isEmpty())
        {   rvDenetleyen.setHasFixedSize(true);
            rvDenetleyen.setLayoutManager(new LinearLayoutManager(DenetlenenRaporActivity.this));
            denetleyenRaporListAdapter = new DenetleyenRaporListAdapter(getApplicationContext(), denetleyenRaporList);
            rvDenetleyen.setAdapter(denetleyenRaporListAdapter);
        } else {
            rvDenetleyen.setVisibility(View.GONE);
            tvEmptyListRapor.setVisibility(View.VISIBLE);
            tvEmptyListRapor.setText("Liste Boş..");
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}