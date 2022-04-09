package com.dembs.android.mobildenetim.ui;

import static com.dembs.android.mobildenetim.utils.Constants.userName;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.DenetimFormListAdapter;
import com.dembs.android.mobildenetim.models.DenetimForm;
import com.dembs.android.mobildenetim.models.DenetimFormItem;
import com.dembs.android.mobildenetim.models.DenetimFormItemLine;
import com.dembs.android.mobildenetim.models.DenetimFormItemResult;
import com.dembs.android.mobildenetim.models.DuzeltmeTalebi;
import com.dembs.android.mobildenetim.models.DuzeltmeTalebiAdd;
import com.dembs.android.mobildenetim.models.DuzeltmeTalebiLineAdd;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.utils.OnItemClick;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;
import com.dembs.android.mobildenetim.utils.ToolbarInit;
import com.google.android.material.button.MaterialButton;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DenetimFormActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, OnItemClick {
    ToolbarInit toolbarInit;
    ProgressDialogManager progressDialogManager = new ProgressDialogManager(DenetimFormActivity.this, "");
    androidx.appcompat.widget.Toolbar toolbar;
    ArrayList<DenetimForm> denetimFormList = new ArrayList<>();
    ArrayList<DenetimFormItem> denetimFormItemsList = new ArrayList<>();
    int counter = 0;
    Context context = this;
    List<DuzeltmeTalebi> duzeltmeTalebiList = new ArrayList<>();
    ArrayList<DenetimFormItemLine> denetimFormItemLineArrayList = new ArrayList<>();
    int cnt = 0;
    ArrayList<DenetimForm> checkedDenetimFormArrayList;
    int flag = 0;
    ArrayList<String> checkedCountries = new ArrayList<>();
    DividerItemDecoration dividerItemDecoration;
    int denetimId = 0, denetimTipId, denetimTurId;
    Api api, api2;
    String plakaNo, ruhsatNo, tcKimlikNo;
    MaterialButton btnDenetimFormKaydet;
    boolean[] checkedItems = new boolean[4];
    String[] listItems;
    List<DuzeltmeTalebiLineAdd> duzeltmeTalebiLineAddList = new ArrayList<>();
    private RecyclerView rvDenetimFormList;
    private List<String> itemList;
    private DenetimFormItemResult denetimFormItemResult;
    private DenetimFormListAdapter _denetimFormListAdapter;
    private boolean isAracUygunluk;
    private boolean isRuhsatsizDenetim;
    private int aracId;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denetim_form);

        if (savedInstanceState != null) {
            checkedDenetimFormArrayList = savedInstanceState.getParcelableArrayList("checkedCountries");
            flag = savedInstanceState.getInt("savedflag");
        }

        context = getApplicationContext();

        itemList = new ArrayList<>();
        itemList.add(0, "Düzeltme Talebi");
        itemList.add(1, "Araç/Şöför Bilgileri");

        toolbar = findViewById(R.id.toolbarDenetimForm);
        toolbarInit = new ToolbarInit(toolbar, itemList);
        toolbarInit.getToolbar("Mobil Ceza & Denetim", "Denetim Formu", Color.WHITE, Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getIntenteValues();

        rvDenetimFormList = findViewById(R.id.rvDenetimFormList);
        btnDenetimFormKaydet = findViewById(R.id.btnDenetimFormKaydet);

        api = ClientConfigs.createRetrofitRxJava(this).create(Api.class);

        api2 = ClientConfigs.createRetrofit(this).create(Api.class);

        loadData(api);
        getDuzeltmeTalebi(api2);


        btnDenetimFormKaydet.setOnClickListener(v -> sendListService(api2));

    }

    void getIntenteValues() {
        Intent intent = getIntent();
        denetimId = intent.getIntExtra("denetimId", 0);
        aracId = intent.getIntExtra("aracId", 0);
        plakaNo = intent.getStringExtra("plakaNo");
        tcKimlikNo = intent.getStringExtra("tcKimlikNo");
        denetimTipId = intent.getIntExtra("denetimTipId", 0);
        denetimTurId = intent.getIntExtra("denetimTurId", 0);
        isAracUygunluk = intent.getBooleanExtra("isAracUygunluk", false);
        isRuhsatsizDenetim = intent.getBooleanExtra("isRuhsatsizDenetim", false);

    }

    public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
        String checkedText = cb.getText() + "";
        String checkedId = cb.getId() + "";
        if (isChecked) {
            checkedCountries.add(checkedText + "/");
            Toast.makeText(this, cb.getText() + " is checked!!!", Toast.LENGTH_SHORT).show();
        } else {
            checkedCountries.remove(checkedText);
            Toast.makeText(this, cb.getText() + " is not checked!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        flag = 1;
        savedState.putStringArrayList("checkedCountries", checkedCountries);
        savedState.putInt("savedflag", flag);
    }

    private void createCheckBoxList(ArrayList<DenetimForm> denetimFormArrayList, ArrayList<DenetimFormItem> denetimFormItemList) {

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        rvDenetimFormList.setLayoutManager(recyclerLayoutManager);
        //RecyclerView item decorator
        dividerItemDecoration = new DividerItemDecoration(rvDenetimFormList.getContext(),
                recyclerLayoutManager.getOrientation());
        _denetimFormListAdapter = new DenetimFormListAdapter(denetimFormArrayList, getApplicationContext(), denetimFormItemList, denetimId, this, denetimTipId, isRuhsatsizDenetim);

        rvDenetimFormList.setAdapter(_denetimFormListAdapter);

        rvDenetimFormList.addItemDecoration(dividerItemDecoration);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbarInit.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                _denetimFormListAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Date currentTime = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String getDateNow = sdf.format(currentTime);//getting date and time

        if (item.getItemId() == android.R.id.home) {

            onBackPressed();
            //finish();
            return super.onOptionsItemSelected(item);

        } else if (item.getItemId() == 0) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(DenetimFormActivity.this);
            mBuilder.setTitle("Düzeltme Talebi");
            mBuilder.setMultiChoiceItems(listItems, checkedItems, (dialogInterface, position, isChecked) -> {

                if (isChecked) {

                    checkedItems[position] = true;
                    duzeltmeTalebiList.get(position).setValid(true);

                } else {
                    checkedItems[position] = false;
                    duzeltmeTalebiList.get(position).setValid(false);
                }
            });
            mBuilder.setCancelable(false);
            mBuilder.setPositiveButton("Kaydet", (dialogInterface, which) -> {

                for (int i = 0; i < checkedItems.length; i++) {
                    if (checkedItems[i]) {

                        duzeltmeTalebiLineAddList.add(new DuzeltmeTalebiLineAdd(getDateNow, userName, 0, duzeltmeTalebiList.get(i).getTalepTurId(), denetimId, " ", false));
                    }

                }
                sendDuzeltmeTalebiList(api, duzeltmeTalebiLineAddList);

            });
            mBuilder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    duzeltmeTalebiLineAddList.clear();
                    dialogInterface.dismiss();

                }
            });


            AlertDialog mDialog = mBuilder.create();
            mDialog.show();

        } else {
            Intent i = new Intent(new Intent(getApplicationContext(), AracSoforBilgisiActivity.class));
            i.putExtra("plakaNo", plakaNo);
            i.putExtra("denetimId", denetimId);
            i.putExtra("aracId", aracId);
            i.putExtra("tcKimlikNo", tcKimlikNo);
            i.putExtra("denetimTurId", denetimTurId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendDuzeltmeTalebiList(Api api, List<DuzeltmeTalebiLineAdd> duzeltmeTalebiLineAddList1) {
        DuzeltmeTalebiAdd duzeltmeTalebiAdd = new DuzeltmeTalebiAdd();
        duzeltmeTalebiAdd.setDenetimId(denetimId);
        duzeltmeTalebiAdd.setDuzeltmeTalebiList(duzeltmeTalebiLineAddList1);
        // duzeltmeTalebiAdd.setDuzeltmeTalebiList(checkDuzeltmeTalebiList(duzeltmeTalebiList));
        Call<Void> call = api.addDuzeltmeTalebi(duzeltmeTalebiAdd);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressDialogManager.dismissDialog();

                if (response.isSuccessful()) {
                    Toasty.success(getApplicationContext(), "Düzeltme Talebi Kaydedildi..", Toasty.LENGTH_SHORT, true).show();
                    duzeltmeTalebiLineAddList.clear();
                    getDuzeltmeTalebi(api2);
                    return;
                }
                duzeltmeTalebiLineAddList.clear();
                Toasty.error(getApplicationContext(), "Hata :" + response.errorBody().toString(), Toasty.LENGTH_SHORT, true).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialogManager.dismissDialog();
                duzeltmeTalebiLineAddList.clear();
                Toasty.error(getApplicationContext(), "Hata :" + t.toString(), Toasty.LENGTH_SHORT, true).show();
            }
        });
    }

    private List<DuzeltmeTalebiLineAdd> checkDuzeltmeTalebiList(List<DuzeltmeTalebi> duzeltmeTalebiList) {
        List<DuzeltmeTalebi> duzeltmeTalebiList1;
        List<DuzeltmeTalebiLineAdd> duzeltmeTalebiLineAddList = new ArrayList<DuzeltmeTalebiLineAdd>();
        duzeltmeTalebiList1 = duzeltmeTalebiList;

        //Todo burasoı debug edilecek burada valid durumu sıkıntı oluyor abimle konusup bakılması lazım talebilineadd modelince isvalid yok

        for (Iterator<DuzeltmeTalebi> iterator = duzeltmeTalebiList1.iterator(); iterator.hasNext(); ) {
            boolean checkValid = iterator.next().isValid();
            if (!checkValid) {
                iterator.remove();
            }

        }
        for (int i = 0; i < duzeltmeTalebiList1.size(); i++) {

            duzeltmeTalebiLineAddList.add(i, new DuzeltmeTalebiLineAdd(duzeltmeTalebiList1.get(i).getDuzeltmeTalep().getIslemTarihi(),
                    duzeltmeTalebiList1.get(i).getDuzeltmeTalep().getKayitEden(), duzeltmeTalebiList1.get(i).getDuzeltmeTalep().getId(), duzeltmeTalebiList1.get(i).getDuzeltmeTalep().getTalepTurId(),
                    duzeltmeTalebiList1.get(i).getDuzeltmeTalep().getDenetimId(), duzeltmeTalebiList1.get(i).getDuzeltmeTalep().getAciklama(),
                    false));
        }

        return duzeltmeTalebiLineAddList;
    }

    private void sendListService(Api api) {

        denetimFormItemResult = new DenetimFormItemResult(denetimId, denetimFormItemLineArrayList);

        if (cnt > 0) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DenetimFormActivity.this);
            // alert dialog başlığını tanımlıyoruz.
            alertDialogBuilder.setTitle("Dikkat");
            // alert dialog özelliklerini oluşturuyoruz.
            alertDialogBuilder
                    .setMessage("Değişiklik yapıldı Kaydedilsinmi?")
                    .setCancelable(false)
                    .setIcon(R.drawable.error_icon)
                    // Evet butonuna tıklanınca yapılacak işlemleri buraya yazıyoruz.
                    .setPositiveButton("Evet", (dialog, which) -> {
                        cnt = 0;
                        _denetimFormListAdapter.SetCount(cnt);

                        Call<Void> call = api.denetimFormItemAdd(denetimFormItemResult);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {

                                    Toasty.success(context, "Form Kaydedildi..", Toasty.LENGTH_LONG).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                                Toasty.error(context, "Hata Oluştu : " + t.toString(), Toasty.LENGTH_LONG).show();
                            }
                        });


                    }).setNegativeButton("Hayır", (dialog, which) -> {

                dialog.dismiss();
            });

            alertDialogBuilder.show();
        }

    }

    @SuppressLint("CheckResult")
    public void loadData(Api api) {

        Observable<ArrayList<DenetimForm>> btcObservable = api.getDenetimForms(denetimTipId);
        Observable<ArrayList<DenetimFormItem>> ethObservable = api.getDenetimFormItems(denetimId);
        Observable.merge(btcObservable, ethObservable)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);

    }

    private void handleResults(ArrayList<? extends Serializable> serializables) {

        counter++;
        Log.d("DenetimFormActivity", "handleResults: " + "methoda girildi");
        if (counter == 1) {
            this.denetimFormList = (ArrayList<DenetimForm>) serializables;
            Log.i("doin", "handleResults  - denetimFormList- " + denetimFormList.size());
        } else if (counter == 2) {
            this.denetimFormItemsList = (ArrayList<DenetimFormItem>) serializables;
            createCheckBoxList(denetimFormList, denetimFormItemsList);
        }

    }

    private void handleError(Throwable t) {

        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void getDuzeltmeTalebi(Api api) {

        Call<List<DuzeltmeTalebi>> callDuzeltmeTalebi = api.getDuzeltmeTalebi(denetimId);
        callDuzeltmeTalebi.enqueue(new Callback<List<DuzeltmeTalebi>>() {
            @Override
            public void onResponse(Call<List<DuzeltmeTalebi>> call, Response<List<DuzeltmeTalebi>> response) {
                if (response.isSuccessful()) {
                    duzeltmeTalebiList = response.body();
                    if (duzeltmeTalebiList != null && duzeltmeTalebiList.size() > 0) {
                        listItems = new String[duzeltmeTalebiList.size()];
                        checkedItems = new boolean[duzeltmeTalebiList.size()];
                        for (int i = 0; i < duzeltmeTalebiList.size(); i++) {
                            listItems[i] = duzeltmeTalebiList.get(i).getTalepTurAdi();
                            checkedItems[i] = duzeltmeTalebiList.get(i).isValid();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<DuzeltmeTalebi>> call, Throwable t) {
                Log.i("duzeltmeTalebi", "hata    " + t.toString());
            }
        });
    }


    @Override
    public void onClick(ArrayList<DenetimFormItemLine> list) {

        denetimFormItemLineArrayList = list;

    }

    @Override
    public void onClickCounter(int counter) {

        cnt = counter;

    }
}





