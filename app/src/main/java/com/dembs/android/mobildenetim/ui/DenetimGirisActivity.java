package com.dembs.android.mobildenetim.ui;

import static com.dembs.android.mobildenetim.utils.AppManager.isGPSEnabled;
import static com.dembs.android.mobildenetim.utils.Constants.ekipId;
import static com.dembs.android.mobildenetim.utils.DateTimeInit.getDateTimeString;
import static com.dembs.android.mobildenetim.utils.DateTimeInit.getDateTimeStringWithParameter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.Denetim;
import com.dembs.android.mobildenetim.models.DenetimDeleteKontrol;
import com.dembs.android.mobildenetim.models.DenetimLine;
import com.dembs.android.mobildenetim.models.Kisi;
import com.dembs.android.mobildenetim.models.Konum;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.utils.AlertDialogManager;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;
import com.dembs.android.mobildenetim.utils.ToolbarInit;
import com.dembs.android.mobildenetim.utils.Util;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DenetimGirisActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    //region Değişkenler
    ProgressDialogManager progressDialogManager = new ProgressDialogManager(DenetimGirisActivity.this, "");
    Denetim denetimInsert;
    Denetim denetimUpdate;
    Toolbar toolbar;
    MaterialButton btnDenetimKaydet, btnDenetimForm, btnDenetimSil, btnDenetimResim;
    int denetimId, detayId, denetimTurId;
    SharedPreferences sharedPref;
    String currentUser;
    String ruhsatNo, plaka, denetimTur, denetimTarihi, adres1, tcNo, surucuAd, kaydeden, islemTarihi, isUser, getDateNow, tip, denetimTurAdi, denetimTipAdi;
    int year, day, month, hour, minute;
    int finalYear, finalMonth, finalDay, finalHour, finalMinute;
    String sendDateTimeValue, durakAdi;
    AlertDialogManager alertDialogManager;
    String[] ilceAdiArray, denetimTipIdArray, ilceIdArray, denetimTurAdiArray, denetimTurIdArray, denetimTipAdiArray;
    String ilceAdi = "";

    String denetimSilKontrolMesaj;
    int ilceId = 0;
    DenetimDeleteKontrol denetimDeleteKontrol = new DenetimDeleteKontrol();
    List<String> ilceAdiList = new ArrayList<>();
    List<String> ilceIdList = new ArrayList<>();
    List<String> denetimTurAdiList = new ArrayList<>();
    List<String> denetimTurIdList = new ArrayList<>();
    List<String> denetimTipAdiList = new ArrayList<>();
    List<String> denetimTipIdList = new ArrayList<>();
    Denetim denetimResult1;
    Denetim denetim = new Denetim();
    Kisi kisiBilgisi;
    boolean isAracUygunluk, isRuhsatsizDenetim;
    int denetimTipId;
    AutoCompleteTextView tvAutocompleteDenetimTur;
    AutoCompleteTextView tvAutocompleteDenetimTip;
    TextInputLayout textInputLayoutDenetimTipi, textInputLayoutDenetimTuru;
    ArrayAdapter<String> denetimTipArrayAdapter;
    ArrayAdapter<String> denetimTurArrayAdapter;
    TextInputLayout tipTarih;
    private Spinner spinnerIlceList;
    private DenetimLine denetimLine = new DenetimLine();
    private EditText etplaka, etDenetimTarihi, etIhlalAdres, etTcNo, etSurucuAd, etKaydeden, etIslemTarihi;
    private Api api;
    TextInputLayout tilTcNo;
    List<Integer>denetimResimTipIdList;
    private LocationRequest locationRequest;
    double latitude ,longitude;
    Konum konum=new Konum();
    List<Double>cordinatesList=new ArrayList<>();//Kordinat dizisinde ilk parametre longitude 2. parametre latitude
    String dogumTarihi;
    String formattedDogumTarihi ="";
    int aracId;
//endregion

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denetim_giris);


        initLocationServices();

        denetimTurAdiArray = getResources().getStringArray(R.array.denetimTurAd);
        denetimTurIdArray = getResources().getStringArray(R.array.denetimTurId);
        denetimTurAdiList = new LinkedList<>(Arrays.asList(denetimTurAdiArray));
        denetimTurIdList = new LinkedList<>(Arrays.asList(denetimTurIdArray));
        denetimResimTipIdList=new ArrayList<>();
        denetimResimTipIdList.add(4);
        denetimResimTipIdList.add(5);
        denetimResimTipIdList.add(6);
        denetimResimTipIdList.add(7);
        denetimResimTipIdList.add(8);

        denetimTipAdiArray = getResources().getStringArray(R.array.denetimTipAd);
        denetimTipIdArray = getResources().getStringArray(R.array.denetimTipId);
        denetimTipAdiList = new LinkedList<>(Arrays.asList(denetimTipAdiArray));
        denetimTipIdList = new LinkedList<>(Arrays.asList(denetimTipIdArray));

        ilceAdiArray = getResources().getStringArray(R.array.ilceler);
        ilceIdArray = getResources().getStringArray(R.array.ilceId);
        ilceIdList = new LinkedList<>(Arrays.asList(ilceIdArray));
        ilceAdiList = new LinkedList<>(Arrays.asList(ilceAdiArray));

        Date currentTime = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        getDateNow = sdf.format(currentTime);//getting date and time
        sendDateTimeValue = getDateNow;

        getIntentValues();
        api = ClientConfigs.createRetrofit(this).create(Api.class);
        denetimTurAdi = getFindTurAdi(denetimTurId);
        denetimTipAdi = getFindTipAdi(denetimTipId);
        initActivity();


        if (denetimId > 0) {

            getDenetimDeleteKontrol(denetimId);
            String denetimIlceAdi = getFindIlceAdi(denetimLine.getDenetim().getIlceId());

            if (denetimIlceAdi != null) {
                int indexIlce = ilceAdiList.indexOf(denetimIlceAdi);
                ilceAdiList.remove(indexIlce);
                ilceAdiList.add(0, denetimIlceAdi);
                String ilceID = ilceIdList.get(indexIlce);
                ilceIdList.remove(indexIlce);
                ilceIdList.add(0, ilceID);
            }


            denetim.setId(denetimLine.getDenetim().getId());
            durakAdi = denetimLine.getTaksiDurakAdi();
            ruhsatNo = (denetimLine.getDenetim().getRuhsatNo());
            denetim.setRuhsatNo(ruhsatNo);
            plaka = (denetimLine.getDenetim().getPlaka());
            Util.setDisableEditText(etplaka);
            denetim.setPlaka(plaka);
            denetimTur = (denetimLine.getDenetimTuru());
            denetimTarihi = denetimLine.getDenetim().getDenetimTarihi();
            denetim.setDenetimTarihi(denetimTarihi);
            sendDateTimeValue = denetimTarihi;
            adres1 = String.valueOf((denetimLine.getDenetim().getAdres() == null ? "" : denetimLine.getDenetim().getAdres()));
            denetim.setAdres(adres1);
            tcNo = String.valueOf((denetimLine.getDenetim().getSurucuTcNo() == null ? "" : denetimLine.getDenetim().getSurucuTcNo()));
            denetim.setSurucuTcNo(tcNo);
            surucuAd = String.valueOf((denetimLine.getDenetim().getSurucuAdi() == null ? "" : denetimLine.getDenetim().getSurucuAdi()));
            kaydeden = denetimLine.getDenetim().getKayitEden();
            currentUser = denetimLine.getDenetim().getKayitEden();
            denetim.setKayitEden(kaydeden);
            islemTarihi = (denetimLine.getDenetim().getIslemTarihi());
            denetim.setIslemTarihi(islemTarihi);
            etplaka.setText(plaka);
            //etDenetimTur.setText(denetimTurAdi);
            // etDenetimTip.setText(denetimTipAdi);
            etDenetimTarihi.setText(getFormattedTime(denetimTarihi));
            etIhlalAdres.setText(adres1);
            etTcNo.setText(tcNo);
            etSurucuAd.setText(surucuAd);
            etKaydeden.setText(kaydeden);
            etIslemTarihi.setText(getFormattedTime(islemTarihi));
        } else {
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            String savedString = sharedPref.getString("tip", "Kayıt Yok");
            // etRuhsatNo.setText(ruhsatNo);
            etplaka.setText(plaka);
            //etDenetimTur.setText(denetimTurAdi);
            //etDenetimTip.setText(denetimTipAdi);
            etKaydeden.setText(isUser);
            etIslemTarihi.setText(getFormattedTime(getDateNow));
            Util.setEnabledEditText(etplaka);
        }

        spinnerIlceList.setOnItemSelectedListener(this);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ilceAdiList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerIlceList.setAdapter(dataAdapter);

        denetimTurArrayAdapter = new ArrayAdapter<>(this, R.layout.denetim_tur_list_item, denetimTurAdiList);

        tvAutocompleteDenetimTur.setAdapter(denetimTurArrayAdapter);

        denetimTipArrayAdapter = new ArrayAdapter<>(this, R.layout.denetim_tip_list_item, denetimTipAdiList);

        tvAutocompleteDenetimTip.setAdapter(denetimTipArrayAdapter);

        tvAutocompleteDenetimTur.setOnItemClickListener((parent, view, position, id) -> {

            denetimTurAdi = parent.getItemAtPosition(position).toString();
            denetimTurId = Integer.parseInt(denetimTurIdList.get(position));


            if (isRuhsatsizDenetim) {
                if (denetimTurId > 0) {

                    tvAutocompleteDenetimTip.setClickable(true);
                    tvAutocompleteDenetimTip.setEnabled(true);
                    tvAutocompleteDenetimTip.setActivated(true);
                    textInputLayoutDenetimTipi.setEnabled(true);
                    textInputLayoutDenetimTipi.setClickable(true);
                    textInputLayoutDenetimTipi.setEndIconActivated(true);
                    tvAutocompleteDenetimTip.setText("Denetim Tipi Seçiniz..", false);
                    filterDenetimTipList();
                } else {
                    tvAutocompleteDenetimTip.setText("Denetim Tipi Seçiniz..", false);
                    denetimTipId = 0;
                    tvAutocompleteDenetimTip.setClickable(false);
                    tvAutocompleteDenetimTip.setEnabled(false);
                    tvAutocompleteDenetimTip.setActivated(false);
                    textInputLayoutDenetimTipi.setEnabled(false);
                    textInputLayoutDenetimTipi.setClickable(false);
                    textInputLayoutDenetimTipi.setEndIconActivated(false);
                    //filterDenetimTipList();
                }
            } else {
                if (denetimTurId > 0) {

                    tvAutocompleteDenetimTip.setClickable(true);
                    tvAutocompleteDenetimTip.setEnabled(true);
                    tvAutocompleteDenetimTip.setActivated(true);
                    textInputLayoutDenetimTipi.setEnabled(true);
                    textInputLayoutDenetimTipi.setClickable(true);
                    textInputLayoutDenetimTipi.setEndIconActivated(true);
                    tvAutocompleteDenetimTip.setText("Denetim Tipi Seçiniz..", false);
                    filterAracUygunlukDenetimTipList();
                } else {
                    tvAutocompleteDenetimTip.setText("Denetim Tipi Seçiniz..", false);
                    denetimTipId = 0;
                    tvAutocompleteDenetimTip.setClickable(false);
                    tvAutocompleteDenetimTip.setEnabled(false);
                    tvAutocompleteDenetimTip.setActivated(false);
                    textInputLayoutDenetimTipi.setEnabled(false);
                    textInputLayoutDenetimTipi.setClickable(false);
                    textInputLayoutDenetimTipi.setEndIconActivated(false);
                    // filterDenetimTipList();
                }
            }

        });

        tvAutocompleteDenetimTip.setOnItemClickListener((parent, view, position, id) -> {
            denetimTipAdi = parent.getItemAtPosition(position).toString();
            denetimTipId = Integer.parseInt(denetimTipIdList.get(position));
//            if (isRuhsatsizDenetim)
//            {//TODO burası sonradan açılacak
//                if (denetimResimTipIdList.contains(denetimTipId)) {//genel denetim değilse resim eklenebilsin 1,2,3 servis taksi toplu taşımanın tipleri
//                    btnDenetimResim.setVisibility(View.VISIBLE);
//                }else{
//                    btnDenetimResim.setVisibility(View.INVISIBLE);
//                }
//            }

            //Log.d("ITEMCLICK", "PONITEMCLİCK:   " + denetimTipId);
        });

        tipTarih.setEndIconOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(DenetimGirisActivity.this, DenetimGirisActivity.this, year, month, day);
            datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Tamam", datePickerDialog);
            datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Vazgeç", datePickerDialog);
            datePickerDialog.show();
            datePickerDialog.getButton(datePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            datePickerDialog.getButton(datePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            datePickerDialog.getButton(datePickerDialog.BUTTON_NEGATIVE).setOnClickListener(v1 -> datePickerDialog.dismiss());
            etDenetimTarihi.setInputType(InputType.TYPE_NULL);
        });

        btnDenetimForm.setOnClickListener(v -> {
            progressDialogManager.createProgressBar();

            if (denetimId > 0) {

                Intent i1 = new Intent(getApplicationContext(), DenetimFormActivity.class);
                i1.putExtra("denetimId", denetimId);
                i1.putExtra("denetimTurId", denetimTurId);
                i1.putExtra("denetimTipId", denetimTipId);
                i1.putExtra("aracId", aracId);
                i1.putExtra("isAracUygunluk", isAracUygunluk);
                i1.putExtra("isRuhsatsizDenetim", isRuhsatsizDenetim);
                i1.putExtra("plakaNo", plaka);
                i1.putExtra("tcKimlikNo", tcNo);
                startActivity(i1);



                progressDialogManager.dismissDialog();
            } else {
                progressDialogManager.dismissDialog();
                Toasty.warning(getApplicationContext(), "Önce Denetim Kaydediniz..", Toasty.LENGTH_LONG, true).show();
            }
        });

        btnDenetimKaydet.setOnClickListener(v -> {

//Update işlemi
            progressDialogManager.createProgressBar();
            if (denetimId > 0) {

                if (isUser.equals(currentUser)) {
                    if (CheckEditTextsValue()) {
                        int ilceCount = Integer.parseInt(ilceIdList.get(spinnerIlceList.getSelectedItemPosition()));
                        if (ilceCount > 0) {
                            denetimUpdate = getDenetimValueForUpdate();
                            if (denetimUpdate.getSurucuTcNo().length() == 0 || denetimUpdate.getSurucuTcNo().length() == 11) {
                                if (denetimUpdate.getSurucuTcNo().length() == 0) {
                                    Call<Void> call = api.updateDenetim(denetimUpdate);//detay id ve denetim objesi gönderiliyor
                                    call.enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

                                            if (response.isSuccessful()) {
                                                progressDialogManager.dismissDialog();
                                                Toasty.success(getApplicationContext(), "Denetim Güncellendi..", Toasty.LENGTH_LONG, true).show();

                                            } else {
                                                progressDialogManager.dismissDialog();
                                                Toasty.error(getApplicationContext(), "Hata Oluştu.." + Objects.requireNonNull(response.errorBody()).toString(), Toasty.LENGTH_LONG, true).show();
                                                //finish this activity with result OK to refresh the data from server
                                            }
                                        }

                                        @Override
                                        public void onFailure(@NonNull Call<Void> call, Throwable t) {
                                            progressDialogManager.dismissDialog();
                                            Toasty.error(getApplicationContext(), String.valueOf(t), Toasty.LENGTH_LONG, true).show();
                                        }
                                    });

                                } else {
                                    if (!denetimUpdate.getSurucuAdi().equals("")) {
                                        Call<Void> call = api.updateDenetim(denetimUpdate);//detay id ve denetim objesi gönderiliyor
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

                                                if (response.isSuccessful()) {
                                                    progressDialogManager.dismissDialog();
                                                    Toasty.success(getApplicationContext(), "Denetim Güncellendi..", Toasty.LENGTH_LONG, true).show();

                                                } else {
                                                    progressDialogManager.dismissDialog();
                                                    Toasty.error(getApplicationContext(), "Hata Oluştu.." + Objects.requireNonNull(response.errorBody()).toString(), Toasty.LENGTH_LONG, true).show();
                                                    //finish this activity with result OK to refresh the data from server
                                                }
                                            }

                                            @Override
                                            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                                                progressDialogManager.dismissDialog();
                                                Toasty.error(getApplicationContext(), String.valueOf(t), Toasty.LENGTH_LONG, true).show();
                                            }
                                        });


                                    } else {
                                        progressDialogManager.dismissDialog();
                                        alertDialogManager = new AlertDialogManager(DenetimGirisActivity.this, "Dikkat", "Sürücü AdSoyad giriniz..");
                                        alertDialogManager.createAlertDialog();
                                    }

                                }

                            } else {
                                progressDialogManager.dismissDialog();
                                alertDialogManager = new AlertDialogManager(DenetimGirisActivity.this, "Dikkat", "TcNo 11 karakter olmalıdır..");
                                alertDialogManager.createAlertDialog();
                            }

                        } else {

                            alertDialogManager = new AlertDialogManager(DenetimGirisActivity.this, "Dikkat", "Lütfen İlçe bilgisi giriniz..");
                            alertDialogManager.createAlertDialog();
                            progressDialogManager.dismissDialog();
                        }
                    }
                } else {
                    progressDialogManager.dismissDialog();
                    alertDialogManager = new AlertDialogManager(DenetimGirisActivity.this, "Dikkat", "Diğer ekiplere ait denetimi güncelleyemezsiniz..");
                    alertDialogManager.createAlertDialog();
                }
            }
//insert işlemi
            else if (denetimId == 0) {
                if (CheckEditTextsValue()) {
                    denetimInsert = getDenetimValueForInsert();
                    int ilceCount = Integer.parseInt(ilceIdArray[spinnerIlceList.getSelectedItemPosition()]);
                    if (ilceCount > 0 && plaka != null && !plaka.equals("") && denetimTurId > 0 && denetimTipId > 0) {
                        if (denetimInsert.getSurucuTcNo().length() == 0 || denetimInsert.getSurucuTcNo().length() == 11) {
                            if (denetimInsert.getSurucuTcNo().length() == 0) {

                                Call<Denetim> call = api.addDenetim(denetimInsert);
                                call.enqueue(new Callback<Denetim>() {
                                    @Override
                                    public void onResponse(@NonNull Call<Denetim> call, @NonNull Response<Denetim> response) {

                                        if (response.isSuccessful()) {

                                            if (response.body() != null) {
                                                denetimResult1 = response.body();
                                                Denetim denetim2 = new Denetim();
                                                ruhsatNo = denetimResult1.getRuhsatNo();
                                                denetimId = denetimResult1.getId();
                                                //denetimTurId = denetimResult1.getDenetimTurId();
                                                currentUser = denetimResult1.getKayitEden();
                                                isUser = denetimResult1.getKayitEden();
                                                denetim2.setRuhsatNo(denetimResult1.getRuhsatNo());
                                                denetim2.setKayitEden(denetimResult1.getKayitEden());
                                                denetim2.setId(denetimResult1.getId());
                                                denetim2.setDenetimTurId(denetimResult1.getDenetimTurId());
                                                if (denetimLine == null) {
                                                    denetimLine = new DenetimLine();
                                                    denetimLine.setDenetim(denetim2);
                                                }


                                                Toasty.success(getApplicationContext(), "Denetim Eklendi..", Toasty.LENGTH_LONG, true).show();

                                            }
                                        } else {
                                            Toasty.error(getApplicationContext(), "Hata Oluştu..", Toasty.LENGTH_LONG, true).show();
                                        }
                                        progressDialogManager.dismissDialog();

                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<Denetim> call, @NonNull Throwable t) {
                                        progressDialogManager.dismissDialog();
                                        Toasty.error(getApplicationContext(), String.valueOf(t), Toasty.LENGTH_LONG, true).show();
                                    }
                                });

                            } else {
                                if (!denetimInsert.getSurucuAdi().equals("")) {
                                    Call<Denetim> call = api.addDenetim(denetimInsert);
                                    call.enqueue(new Callback<Denetim>() {
                                        @Override
                                        public void onResponse(@NonNull Call<Denetim> call, @NonNull Response<Denetim> response) {

                                            if (response.isSuccessful()) {

                                                if (response.body() != null) {
                                                    denetimResult1 = response.body();
                                                    Denetim denetim2 = new Denetim();
                                                    ruhsatNo = denetimResult1.getRuhsatNo();
                                                    denetimId = denetimResult1.getId();
                                                    //denetimTurId = denetimResult1.getDenetimTurId();
                                                    currentUser = denetimResult1.getKayitEden();
                                                    isUser = denetimResult1.getKayitEden();

                                                    denetim2.setRuhsatNo(denetimResult1.getRuhsatNo());
                                                    denetim2.setKayitEden(denetimResult1.getKayitEden());
                                                    denetim2.setId(denetimResult1.getId());
                                                    denetim2.setDenetimTurId(denetimResult1.getDenetimTurId());
                                                    denetimLine = new DenetimLine(denetimResult1, tip, "", 0);

                                                    Toasty.success(getApplicationContext(), "Denetim Eklendi..", Toasty.LENGTH_LONG, true).show();

                                                }
                                            } else {
                                                Toasty.error(getApplicationContext(), "Hata Oluştu..", Toasty.LENGTH_LONG, true).show();
                                            }
                                            progressDialogManager.dismissDialog();

                                        }

                                        @Override
                                        public void onFailure(@NonNull Call<Denetim> call, @NonNull Throwable t) {
                                            progressDialogManager.dismissDialog();
                                            Toasty.error(getApplicationContext(), String.valueOf(t), Toasty.LENGTH_LONG, true).show();
                                        }
                                    });

                                } else {
                                    progressDialogManager.dismissDialog();
                                    alertDialogManager = new AlertDialogManager(DenetimGirisActivity.this, "Dikkat", "Sürücü AdSoyad giriniz..");
                                    alertDialogManager.createAlertDialog();

                                }


                            }

                        } else {
                            progressDialogManager.dismissDialog();
                            alertDialogManager = new AlertDialogManager(DenetimGirisActivity.this, "Dikkat", "TcNo 11 karakter olmalıdır..");
                            alertDialogManager.createAlertDialog();
                        }
                    } else {
                        progressDialogManager.dismissDialog();
                        alertDialogManager = new AlertDialogManager(DenetimGirisActivity.this, "Dikkat", "Lütfen Boş Alanları Doldurunuz..");
                        alertDialogManager.createAlertDialog();
                    }
                }
            }
        });

        btnDenetimSil.setOnClickListener(v -> {
            progressDialogManager.dismissDialog();
            if (denetimId > 0) {

                if (denetimLine.getDenetim().getKayitEden().equals(isUser)) {
                    if (getDateTimeStringWithParameter(denetim.getIslemTarihi()).equals(getDateTimeString())) {
                        deleteDenetim();
                    } else {
                        progressDialogManager.dismissDialog();
                        alertDialogManager = new AlertDialogManager(DenetimGirisActivity.this, "Dikkat", "Geçmiş denetime ait silme işlemi yapılamaz !");
                        alertDialogManager.createAlertDialog();

                    }
                } else {
                    alertDialogManager = new AlertDialogManager(DenetimGirisActivity.this, "Dikkat", "Diğer ekiplere ait denetimi silemezsiniz..");
                    alertDialogManager.createAlertDialog();
                }

            } else {
                progressDialogManager.dismissDialog();
                Toasty.warning(getApplicationContext(), "Önce denetim giriniz..", Toasty.LENGTH_LONG, true).show();
            }

        });

        btnDenetimResim.setOnClickListener(v -> {
            if (denetimId > 0) {
                Intent i = new Intent(this, DenetimImageListActivity.class);
                i.putExtra("denetimId", denetimId);
                startActivity(i);
            } else {
                AlertDialogManager alertDialogManager = new AlertDialogManager(this, "UYARI", "Önce Denetimi Kaydediniz..");
                alertDialogManager.createAlertDialog();
            }


        });

        tilTcNo.setEndIconOnClickListener(v -> {
            soforBulDialog();
        });

    }

    void soforBulDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setIcon(R.drawable.person_add);
        dialogBuilder.setTitle("Şöför Bul");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_sofor_bul, null);
        TextInputEditText etTcNoAlertDialog = dialogView.findViewById(R.id.etTcNo);
        if (etTcNo.getText()!=null&&etTcNo.getText().length()==11)
        {
            etTcNoAlertDialog.setText(etTcNo.getText());
        }
        TextInputEditText etTarihNoAlertDialog = dialogView.findViewById(R.id.etDogumTarihi  );
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s-%s-%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    etTarihNoAlertDialog.setText(current);
                    etTarihNoAlertDialog.setSelection(sel < current.length() ? sel : current.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            private String current = "";
            //private String ddmmyyyy = "DDMMYYYY";
            private String ddmmyyyy = "GGAAYYYY";
            private Calendar cal = Calendar.getInstance();};
        etTarihNoAlertDialog.addTextChangedListener(tw);
        dialogBuilder.setView(dialogView);
        dialogBuilder
                .setCancelable(false)
                .setPositiveButton("Bul", (dialog, id) -> {
                    dogumTarihi=etTarihNoAlertDialog.getText().toString();
                    if (Objects.requireNonNull(etTcNoAlertDialog.getText()).toString()!=null&&etTcNoAlertDialog.getText().length() == 11&& !dogumTarihi.equals("GG/AA/YYYY")) {
                        progressDialogManager.createProgressBar();
                        tcNo=etTcNoAlertDialog.getText().toString();
                        dogumTarihi=etTarihNoAlertDialog.getText().toString();
                        formattedDogumTarihi = dogumTarihi.substring(6,10);
                        formattedDogumTarihi = formattedDogumTarihi +"-"+dogumTarihi.substring(3,5);
                        formattedDogumTarihi = formattedDogumTarihi +"-"+dogumTarihi.substring(0,2);
                        etTarihNoAlertDialog.setText(formattedDogumTarihi);
                        etTcNo.setText(tcNo);
                        getKisiBilgisi(tcNo, formattedDogumTarihi);

                    } else {
                        alertDialogManager = new AlertDialogManager(DenetimGirisActivity.this, "Dikkat", "TcKimlikNo veya Doğum Tarihini kontrol ediniz..");
                        alertDialogManager.createAlertDialog();
                    }

                })
                .setNegativeButton("Kapat",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled(DenetimGirisActivity.this)) {

                    getCurrentLocation();

                } else {

                    turnOnGPS();
                }
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {

                getCurrentLocation();
            }
        }
    }

    private void initLocationServices() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);
        getCurrentLocation();
    }

    private void getCurrentLocation() {


        if (ActivityCompat.checkSelfPermission(DenetimGirisActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (isGPSEnabled(DenetimGirisActivity.this)) {

                LocationServices.getFusedLocationProviderClient(DenetimGirisActivity.this)
                        .requestLocationUpdates(locationRequest, new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {
                                super.onLocationResult(locationResult);

                                LocationServices.getFusedLocationProviderClient(DenetimGirisActivity.this)
                                        .removeLocationUpdates(this);

                                if (locationResult != null && locationResult.getLocations().size() > 0) {

                                    int index = locationResult.getLocations().size() - 1;
                                     latitude = locationResult.getLocations().get(index).getLatitude();
                                     longitude = locationResult.getLocations().get(index).getLongitude();
//                                    Log.d(String.valueOf(longitude),"deger");
//                                    Log.d(String.valueOf(latitude),"deger");
                                    //Toast.makeText(getApplicationContext(),String.valueOf(longitude),Toast.LENGTH_SHORT).show();
                                    //AddressText.setText("Latitude: " + latitude + "\n" + "Longitude: " + longitude);
//                                    String a="adad";
//                                    a=getAddressFromLatLong(getApplicationContext(),latitude,longitude);
//                                    AddressText.setText(a);
                                }
                            }
                        }, Looper.getMainLooper());

            } else {
                turnOnGPS();
            }

        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    private void turnOnGPS() {


        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(DenetimGirisActivity.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(DenetimGirisActivity.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });

    }

    private String getFindIlceAdi(int ilceId) {
        int index = ilceIdList.indexOf(String.valueOf(ilceId));
        return ilceAdiList.get(index);
    }

    private String getFindTurAdi(int denetimTurId) {
        int index = denetimTurIdList.indexOf(String.valueOf(denetimTurId));
        denetimTurAdi = denetimTurAdiList.get(index);
        return denetimTurAdiList.get(index);
    }

    private String getFindTipAdi(int denetimTipId) {
        int index = denetimTipIdList.indexOf(String.valueOf(denetimTipId));
        denetimTipAdi = denetimTipAdiList.get(index);
        return denetimTipAdiList.get(index);
    }

    private void deleteDenetim() {
        if (denetimDeleteKontrol != null) {

            denetimSilKontrolMesaj = "Denetime ait kayıtlar var \n\n";
            denetimSilKontrolMesaj = denetimSilKontrolMesaj + "Denetim Formu Adet :" + denetimDeleteKontrol.getDenetimFormuAdet() + "\n\n";
            denetimSilKontrolMesaj = denetimSilKontrolMesaj + "Denetleyen Adet :" + denetimDeleteKontrol.getDenetleyenAdet() + "\n\n";
            denetimSilKontrolMesaj = denetimSilKontrolMesaj + "Düzeltme Talep Adet :" + denetimDeleteKontrol.getDuzeltmeTalepAdet() + "\n\n";
            denetimSilKontrolMesaj = denetimSilKontrolMesaj + "Varaka Adet :" + denetimDeleteKontrol.getVarakaAdet() + "\n\n";
            denetimSilKontrolMesaj = denetimSilKontrolMesaj + "Varaka Resim Adet :" + denetimDeleteKontrol.getVarakaResimAdet() + "\n\n";

        }

        Dialog dialog;
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this, R.style.custom);
        // Set the dialog title
        builder.setTitle(" Denetimi Silmeyi Onaylıyor musunuz ?")
                .setMessage(denetimSilKontrolMesaj
                )
                .setCancelable(false)
                .setPositiveButton("Evet", (dialog12, id) -> {

                    Call<Void> call = api.DenetimDelete(denetimId);//denetim id almalı
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

                            if (response.isSuccessful()) {
                                progressDialogManager.dismissDialog();
                                Toasty.success(getApplicationContext(), "Denetim Silindi..", Toasty.LENGTH_LONG, true).show();
                                onBackPressed();
                                finish();
                            } else {
                                Toasty.error(getApplicationContext(), "Hata Oluştu..", Toasty.LENGTH_LONG, true).show();
                                progressDialogManager.dismissDialog();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Void> call, Throwable t) {
                            progressDialogManager.dismissDialog();
                            Toasty.error(getApplicationContext(), String.valueOf(t), Toasty.LENGTH_LONG, true).show();
                        }
                    });

                })
                .setNegativeButton("Hayır", (dialog1, id) -> {
                    progressDialogManager.dismissDialog();
                    dialog1.dismiss();
                });

        dialog = builder.create();
        dialog.show();
    }

    private void getIntentValues() {

        sharedPref = this.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        Intent i = getIntent();

        detayId = i.getIntExtra("detayId", 0);
        denetimId = i.getIntExtra("denetimId", 0);
        denetimTurId = i.getIntExtra("denetimTurId", 0);
        denetimTipId = i.getIntExtra("denetimTipId", 0);
        aracId = i.getIntExtra("aracId", 0);
        ruhsatNo = i.getStringExtra("ruhsatNo");
//        aracUygunlukTurId = i.getIntExtra("aracUygunlukTurId", 0);
        denetimTipAdi = i.getStringExtra("denetimTipAdi");
        plaka = i.getStringExtra("Plaka");
        denetimLine = i.getParcelableExtra("denetimLine");
        isUser = sharedPref.getString("isUser", null);
        isRuhsatsizDenetim = i.getBooleanExtra("isRuhsatsızDenetim", false);
        isAracUygunluk = i.getBooleanExtra("isAracUygunluk", false);
        // denetimTurId=sharedPref.getInt("denetimTurId", 0);
        // Log.d("denetimTurId", "TurId :" + denetimTurId);
    }

    private void getDenetimDeleteKontrol(int denetimId) {
        Call<DenetimDeleteKontrol> call = api.getDenetimDeleteKontrol(denetimId);
        call.enqueue(new Callback<DenetimDeleteKontrol>() {
            @Override
            public void onResponse(@NonNull Call<DenetimDeleteKontrol> call, @NonNull Response<DenetimDeleteKontrol> response) {
                if (response.isSuccessful()) {

                    denetimDeleteKontrol = response.body();

                }
            }

            @Override
            public void onFailure(@NonNull Call<DenetimDeleteKontrol> call, @NonNull Throwable t) {

            }
        });
    }

    private void initActivity() {

        toolbar = findViewById(R.id.toolbarDenetimDetay);
        ToolbarInit toolbarInit = new ToolbarInit(toolbar, null);
        toolbarInit.getToolbar("Denetim Detay", "", Color.WHITE, Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnDenetimSil = findViewById(R.id.btnDenetimSil);
        btnDenetimKaydet = findViewById(R.id.btnDenetimKaydet);
        btnDenetimForm = findViewById(R.id.btnDenetimForm);
        btnDenetimResim = findViewById(R.id.btnDenetimResim);
        //TODO burası sonradan açılacak
//        if (isAracUygunluk||isRuhsatsizDenetim||denetimResimTipIdList.contains(denetimTipId)) {
//            btnDenetimResim.setVisibility(View.VISIBLE);
//        }
        spinnerIlceList = findViewById(R.id.spinnerIlceList);
        //etRuhsatNo = findViewById(R.id.etRuhsatNo);
        // Util.setDisableEditText(etRuhsatNo);
        etplaka = findViewById(R.id.etPlaka);
        etplaka.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        if (!isRuhsatsizDenetim) {
            Util.setDisableEditText(etplaka);
        }
        tipTarih = findViewById(R.id.tipTarih);
        tipTarih.setEndIconActivated(true);
        etDenetimTarihi = findViewById(R.id.etDenetimTarihi);
        Util.setDisableEditText(etDenetimTarihi);
        sendDateTimeValue = getDateNow;
        etDenetimTarihi.setText(getFormattedTime(getDateNow));
        etIhlalAdres = findViewById(R.id.etIhlalAdresi);
        etTcNo = findViewById(R.id.etTcNo);
        tilTcNo=findViewById(R.id.tilTcNo);
        tilTcNo.setEndIconActivated(true);
        etSurucuAd = findViewById(R.id.etSurucu);
        etKaydeden = findViewById(R.id.etKaydeden);
        Util.setDisableEditText(etKaydeden);
        etIslemTarihi = findViewById(R.id.etIslemTarihi);
        Util.setDisableEditText(etIslemTarihi);

        tvAutocompleteDenetimTip = findViewById(R.id.autoCompleteDenetimTip);

        textInputLayoutDenetimTipi = findViewById(R.id.tilDenetimTipi);

        tvAutocompleteDenetimTur = findViewById(R.id.autoCompleteDenetimTur);

        textInputLayoutDenetimTuru = findViewById(R.id.textInputLayoutDenetimTuru);
        if (isAracUygunluk) {
            tvAutocompleteDenetimTip.setText("Denetim Tipi Seçiniz..", false);

        } else {
            tvAutocompleteDenetimTip.setText(denetimTipAdi, false);
        }
        tvAutocompleteDenetimTur.setText(denetimTurAdi);


        if (denetimTurId < 1) {
            tvAutocompleteDenetimTip.setClickable(false);
            tvAutocompleteDenetimTip.setEnabled(false);
            tvAutocompleteDenetimTip.setActivated(false);
            textInputLayoutDenetimTipi.setEnabled(false);
            textInputLayoutDenetimTipi.setClickable(false);
            textInputLayoutDenetimTipi.setEndIconActivated(false);
        }

        if (!isRuhsatsizDenetim)//Ruhsatsız denetim değilse
        {
            tvAutocompleteDenetimTur.setClickable(false);
            tvAutocompleteDenetimTur.setEnabled(false);
            tvAutocompleteDenetimTur.setActivated(false);
            textInputLayoutDenetimTuru.setEnabled(false);
            textInputLayoutDenetimTuru.setClickable(false);
            textInputLayoutDenetimTuru.setEndIconActivated(false);

            if (isAracUygunluk||denetimResimTipIdList.contains(denetimTipId))//Araç uygunluk veya Muayene denetimi yapılıyor ise
            {
                filterAracUygunlukDenetimTipList();

            } else {
                tvAutocompleteDenetimTip.setClickable(false);
                tvAutocompleteDenetimTip.setEnabled(false);
                tvAutocompleteDenetimTip.setActivated(false);
                textInputLayoutDenetimTipi.setEnabled(false);
                textInputLayoutDenetimTipi.setClickable(false);
                textInputLayoutDenetimTipi.setEndIconActivated(false);
            }

        }
    }

    private void getKisiBilgisi(String tcKimlikNo,String dogumTarihi) {
        Call<Kisi> call = api.getKisiBilgiByTcNo(tcKimlikNo,dogumTarihi);
        call.enqueue(new Callback<Kisi>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Kisi> call, @NonNull Response<Kisi> response) {
                // btnDenetimKaydet.setEnabled(true);
                if (response.isSuccessful()) {
                    progressDialogManager.dismissDialog();
                    kisiBilgisi = response.body();
                    etSurucuAd.setText(Objects.requireNonNull(kisiBilgisi).getAdi() + " " + kisiBilgisi.getSoyadi());
                } else {
                    progressDialogManager.dismissDialog();
                    Toasty.warning(DenetimGirisActivity.this, "TcNo dan sürücü bilgisi çekilemedi. Sürücüyü manuel giriniz.!", Toasty.LENGTH_LONG, true).show();

                }
            }

            @Override
            public void onFailure(@NonNull Call<Kisi> call, @NonNull Throwable t) {
                //btnDenetimKaydet.setEnabled(true);
                progressDialogManager.dismissDialog();
                Toasty.error(DenetimGirisActivity.this, "TcNo çekilirken hata oluştu manuel giriş yapınız.." + t.toString(), Toasty.LENGTH_LONG, true).show();
            }
        });
    }

    private String getFormattedTime(String getDateNow) {

        String s = getDateNow.substring(8, 10) + "-" + getDateNow.substring(5, 7) + "-" + getDateNow.substring(0, 4) + " " + getDateNow.substring(11, 16);
        return s;
    }

    public boolean CheckEditTextsValue() {
        denetimTarihi = etDenetimTarihi.getText().toString();
        adres1 = etIhlalAdres.getText().toString();
        tcNo = etTcNo.getText().toString();
        surucuAd = etSurucuAd.getText().toString();

        return !denetimTarihi.matches("") || !adres1.matches("") || !tcNo.matches("") || !surucuAd.matches("");
    }

    private Denetim getDenetimValueForInsert() {
        Denetim denetimInsert = new Denetim();
        denetimInsert.setEkipId(ekipId);
        if (plaka.equals("")) {
            plaka = etplaka.getText().toString();
        }
        denetimInsert.setPlaka(plaka);
        denetimInsert.setDetayId(detayId);

        if (ruhsatNo.equals("")) {
            ruhsatNo = "R";//ruhsatsızlarda yeni denetimde ruhsat numarasına R basılır.
        }

        denetimInsert.setRuhsatNo(ruhsatNo);
        denetimInsert.setDenetimTurId(denetimTurId);
        denetimInsert.setDenetimTipId(denetimTipId);
        denetimInsert.setKayitEden(isUser);
        denetimTarihi = sendDateTimeValue;
        denetimInsert.setDenetimTarihi(denetimTarihi);
        adres1 = etIhlalAdres.getText().toString();
        denetimInsert.setAdres(adres1);
        tcNo = etTcNo.getText().toString();
        denetimInsert.setSurucuTcNo(tcNo);
        surucuAd = etSurucuAd.getText().toString();
        denetimInsert.setSurucuAdi(surucuAd);
        denetimInsert.setIslemTarihi(getDateNow);
        denetimInsert.setIlceId(Integer.parseInt(ilceIdArray[spinnerIlceList.getSelectedItemPosition()]));

        if (denetimInsert.getAdres() == null||denetimInsert.getAdres().equals("")){
            denetimInsert.setAdres("-");
        }

//        if (denetimInsert.getDurakId() == 0)
//            denetimInsert.setDurakId(0);
//        if (denetimInsert.getDurakAdi() == null)
//            denetimInsert.setDurakAdi("-");
//        if (denetimInsert.getDurakKodu() == null)
//            denetimInsert.setDurakKodu("0");
//        if (denetimInsert.getHatKodu() == null)
//            denetimInsert.setHatKodu("0");
        if (denetimInsert.getDurumId() == 0)
            denetimInsert.setDurumId(0);
        if (denetimInsert.getDenetimTurId() == 0)
            denetimInsert.setDenetimTurId(denetimTurId);
        if (denetimInsert.getMahalleId() == 0)
            denetimInsert.setMahalleId(0);
        if (denetimInsert.getSokakId() == 0)
            denetimInsert.setSokakId(0);
        setCordinateValues();
        denetimInsert.setKonum(konum);
        return denetimInsert;
    }

    private void filterAracUygunlukDenetimTipList() {
        if (denetimTurId == 1) {
            denetimTipAdiList.clear();
            denetimTipIdList.clear();
            denetimTipAdiList.add(denetimTipAdiArray[0]);
            //denetimTipAdiList.add(denetimTipAdiArray[1]);
            denetimTipAdiList.add(denetimTipAdiArray[4]);
            denetimTipAdiList.add(denetimTipAdiArray[7]);
            denetimTipIdList.add(denetimTipIdArray[0]);
            // denetimTipIdList.add(denetimTipIdArray[1]);
            denetimTipIdList.add(denetimTipIdArray[4]);
            denetimTipIdList.add(denetimTipIdArray[7]);
        } else if (denetimTurId == 2) {
            denetimTipAdiList.clear();
            denetimTipIdList.clear();
            denetimTipAdiList.add(denetimTipAdiArray[0]);
            //denetimTipAdiList.add(denetimTipAdiArray[2]);
            denetimTipAdiList.add(denetimTipAdiArray[5]);
            denetimTipAdiList.add(denetimTipAdiArray[8]);
            denetimTipIdList.add(denetimTipIdArray[0]);
            // denetimTipIdList.add(denetimTipIdArray[2]);
            denetimTipIdList.add(denetimTipIdArray[5]);
            denetimTipIdList.add(denetimTipIdArray[8]);
        } else if (denetimTurId == 3) {
            denetimTipAdiList.clear();
            denetimTipIdList.clear();
            denetimTipAdiList.add(denetimTipAdiArray[0]);
            // denetimTipAdiList.add(denetimTipAdiArray[3]);
            denetimTipAdiList.add(denetimTipAdiArray[6]);
            denetimTipAdiList.add(denetimTipAdiArray[9]);
            denetimTipIdList.add(denetimTipIdArray[0]);
            // denetimTipIdList.add(denetimTipIdArray[3]);
            denetimTipIdList.add(denetimTipIdArray[6]);
            denetimTipIdList.add(denetimTipIdArray[9]);
        }

    }

    private void filterDenetimTipList() {
        if (denetimTurId == 1) {
            denetimTipAdiList.clear();
            denetimTipIdList.clear();
            denetimTipAdiList.add(denetimTipAdiArray[0]);
            denetimTipAdiList.add(denetimTipAdiArray[1]);
            denetimTipAdiList.add(denetimTipAdiArray[4]);
            denetimTipAdiList.add(denetimTipAdiArray[7]);
            denetimTipIdList.add(denetimTipIdArray[0]);
            denetimTipIdList.add(denetimTipIdArray[1]);
            denetimTipIdList.add(denetimTipIdArray[4]);
            denetimTipIdList.add(denetimTipIdArray[7]);
        } else if (denetimTurId == 2) {
            denetimTipAdiList.clear();
            denetimTipIdList.clear();
            denetimTipAdiList.add(denetimTipAdiArray[0]);
            denetimTipAdiList.add(denetimTipAdiArray[2]);
            denetimTipAdiList.add(denetimTipAdiArray[5]);
            denetimTipAdiList.add(denetimTipAdiArray[8]);

            denetimTipIdList.add(denetimTipIdArray[0]);
            denetimTipIdList.add(denetimTipIdArray[2]);
            denetimTipIdList.add(denetimTipIdArray[5]);
            denetimTipIdList.add(denetimTipIdArray[8]);
        } else if (denetimTurId == 3) {
            denetimTipAdiList.clear();
            denetimTipIdList.clear();
            denetimTipAdiList.add(denetimTipAdiArray[0]);
            denetimTipAdiList.add(denetimTipAdiArray[3]);
            denetimTipAdiList.add(denetimTipAdiArray[6]);
            denetimTipAdiList.add(denetimTipAdiArray[9]);
            denetimTipIdList.add(denetimTipIdArray[0]);
            denetimTipIdList.add(denetimTipIdArray[3]);
            denetimTipIdList.add(denetimTipIdArray[6]);
            denetimTipIdList.add(denetimTipIdArray[9]);
        }

    }

    private Denetim getDenetimValueForUpdate() {

        isUser = ClientConfigs.GetPrefStr("isUser", getApplicationContext());//sharedPref.getString("isUser", null);
        denetim.setRuhsatNo(ruhsatNo);
        denetim.setEkipId(ekipId);
        denetim.setDetayId(detayId);
        denetim.setPlaka(plaka);
        denetim.setId(denetimId);
        adres1 = etIhlalAdres.getText().toString();
        denetim.setAdres(adres1);
        tcNo = etTcNo.getText().toString();
        denetim.setSurucuTcNo(tcNo);
        surucuAd = etSurucuAd.getText().toString();
        denetim.setSurucuAdi(surucuAd);
        denetim.setKayitEden(isUser);
        denetimTarihi = sendDateTimeValue;
        denetim.setDenetimTarihi(denetimTarihi);
        denetim.setIlceId(Integer.parseInt(ilceIdList.get(spinnerIlceList.getSelectedItemPosition())));
        denetim.setIslemTarihi(sendDateTimeValue);
        if (denetim.getAdres() == null || denetim.getAdres().equals(""))
            denetim.setAdres("-");
        if (denetim.getDurumId() == 0)
            denetim.setDurumId(0);
        if (denetim.getDenetimTurId() == 0)
            denetim.setDenetimTurId(denetimTurId);
        if (denetim.getMahalleId() == 0)
            denetim.setMahalleId(0);
        if (denetim.getSokakId() == 0)
            denetim.setSokakId(0);
        denetim.setDenetimTipId(denetimTipId);
        setCordinateValues();
        denetim.setKonum(konum);
        return denetim;
    }

    private void setCordinateValues() {

        konum.setType("Point");
        cordinatesList.add(0,latitude);
        cordinatesList.add(1,longitude);

//        cordinatesList.add(0,39.58023693896136);
//        cordinatesList.add(1,27.482876220409455);
        konum.setCoordinates(cordinatesList);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        ilceAdi = parent.getItemAtPosition(position).toString();
        ilceId = Integer.parseInt(ilceIdList.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_denetleyen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.iconDenetleyen) {
            Intent i = new Intent(getApplicationContext(), DenetimDenetleyenActivity.class);
            i.putExtra("denetimId", denetimId);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        finalYear = year;
        finalMonth = month + 1;
        finalDay = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(DenetimGirisActivity.this, DenetimGirisActivity.this, hour, minute, android.text.format.DateFormat.is24HourFormat(this));
        timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Tamam", timePickerDialog);
        timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Vazgeç", timePickerDialog);
        timePickerDialog.show();
        timePickerDialog.getButton(timePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        timePickerDialog.getButton(timePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        finalHour = hourOfDay;
        finalMinute = minute;
        @SuppressLint("DefaultLocale")
        String timeFormatted = (String.format("%02d:%02d", finalHour, finalMinute));
        @SuppressLint("DefaultLocale")
        String temp = (String.format("%02d-%02d-%02d", finalYear, finalMonth, finalDay));
        sendDateTimeValue = temp + "T" + timeFormatted + ":00";
        etDenetimTarihi.setText(getFormattedTime(sendDateTimeValue));

    }

    @Override
    protected void onResume() {
        super.onResume();
        getDenetimDeleteKontrol(denetimId);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        intent.putExtra("ruhsatNo", ruhsatNo);
        startActivity(intent);
    }

}
