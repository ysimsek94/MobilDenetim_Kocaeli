package com.dembs.android.mobildenetim.ui;

import static com.dembs.android.mobildenetim.utils.AppManager.isGPSEnabled;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputFilter;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.Token;
import com.dembs.android.mobildenetim.models.User;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.utils.AlertDialogManager;
import com.dembs.android.mobildenetim.utils.Constants;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public String tcKimlikNo, password;
    Api api;
    ProgressDialogManager progressDialogManager = new ProgressDialogManager(LoginActivity.this, "");
    Token token;
    int color;
    SharedPreferences sharedPref, sharedPreferences;
    SharedPreferences.Editor editor;
    User user;
    CheckBox cbRememberMe;
    private Toolbar toolbar;
    private AppCompatButton btnLogin;
    private TextInputEditText etTcKimlikNo, etSifre;
    private boolean connected;
    private LocationRequest locationRequest;
    @SuppressLint({"CommitPrefEdits", "MissingPermission"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
//        toolbar = findViewById(R.id.toolbarLogin);
//        toolbar.setTitle("Mobil Ceza & Denetim");
//        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setSubtitleTextColor(Color.WHITE);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE
            }, 100);
        }
        btnLogin = findViewById(R.id.btnLogin);
        etTcKimlikNo = findViewById(R.id.etTcKimlikNo);
        etSifre = findViewById(R.id.etSifre);
        cbRememberMe = findViewById(R.id.cbRemember);

        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(11);
        etTcKimlikNo.setFilters(filterArray);

        Resources res = getResources();
        color = res.getColor(R.color.colorPrimaryDark);
        user = new User();

        sharedPref = getApplicationContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        /////////////////To get Stored Data/////////////////////////////////
        tcKimlikNo = sharedPreferences.getString("tcKimlikNo", "");
        password = sharedPreferences.getString("password", "");
        if (!tcKimlikNo.equals("") && !password.equals("")) {
            etTcKimlikNo.setText(tcKimlikNo);
            etSifre.setText(password);
            cbRememberMe.setChecked(true);

        }


        btnLogin.setOnClickListener(v -> {

            connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

            tcKimlikNo = Objects.requireNonNull(etTcKimlikNo.getText()).toString();
            password = Objects.requireNonNull(etSifre.getText()).toString();
            boolean isGpsOpen=isGPSEnabled(LoginActivity.this);
            if (isGpsOpen){

                if (!connected) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Dikkat");
                    builder.setMessage("Lütfen İnternet Bağlantınızı Kontrol Ediniz..");
                    builder.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("Ayarlar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS));
//                        Intent intent = new Intent(Intent.ACTION_MAIN);
//                        intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
                        }
                    });
                    builder.show();

                } else {

                    progressDialogManager.createProgressBar();
                    if (!tcKimlikNo.equals("") && !password.equals("")) {
                        user.setUserName(tcKimlikNo);
                        user.setPassword(password);
                        user.setServiceUserName("mobile");
                        user.setServicePassword("UkomeMob@Ws987");


                        api = ClientConfigs.createRetrofit(this).create(Api.class);

                        Call<Token> call = api.Authentication(user);
                        call.enqueue(new Callback<Token>() {
                            @Override
                            public void onResponse(Call<Token> call, Response<Token> response) {

                                if (response.isSuccessful()) {
                                    if (cbRememberMe.isChecked()) {
                                        editor.putString("tcKimlikNo", etTcKimlikNo.getText().toString());
                                        editor.putString("password", etSifre.getText().toString());
                                        editor.apply();
                                    } else {
                                        editor.putString("tcKimlikNo", "");
                                        editor.putString("password", "");
                                        editor.commit();
                                    }

                                    token = response.body();
                                    if (token != null) {
                                        Constants.ekipId = token.getEkipId() > 0 ? token.getEkipId() : -1;
                                        Constants.kullaniciId=token.getKullaniciId() > 0 ? token.getKullaniciId() : -1;
                                        Constants.userName =token.getUsername()!=null && !token.getUsername().equals("") ? token.getUsername() :"***";
                                        Constants.tcKimlikNo=token.getTcKimlikNo()!=null && !token.getTcKimlikNo().equals("") ? token.getTcKimlikNo() :"***";
                                    }

                                    setSharedPref();
                                    ruhsatAraActivity();


                                } else {

                                    AlertDialogManager alertDialogManager = new AlertDialogManager(LoginActivity.this, "Dikkat", "KullanıcıAdı veya Şifre hatalı..");
                                    alertDialogManager.createAlertDialog();
                                    Toasty.error(getApplicationContext(), "Giriş Başarasız..", Toasty.LENGTH_LONG, true).show();
                                }
                                progressDialogManager.dismissDialog();
                            }


                            @Override
                            public void onFailure(Call<Token> call, Throwable t) { progressDialogManager.dismissDialog();
                                AlertDialogManager alertDialogManager = new AlertDialogManager(LoginActivity.this, "Dikkat",  "Uygulama zaman aşıma uğradı..");
                                alertDialogManager.createAlertDialog();
                                // Toast.makeText(getApplicationContext(), String.valueOf(t), Toast.LENGTH_LONG).show();

                            }
                        });

                    } else {
                        progressDialogManager.dismissDialog();
                        Toasty.error(getApplicationContext(), "Kullanıcı Adı Veya Şifre Boş Geçilemez", Toasty.LENGTH_SHORT, true).show();
                    }

                }
            }else{

                turnOnGPS();
            }


        });


    }
    private void getCurrentLocation() {


        if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (isGPSEnabled(LoginActivity.this)) {
                turnOnGPS();
            }

        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled(LoginActivity.this)) {

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
    private void turnOnGPS() {


        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(task -> {

            try {
                LocationSettingsResponse response = task.getResult(ApiException.class);
                Toast.makeText(LoginActivity.this, "GPS Açıldı. Giriş Yapmayı Tekrar Deneyiniz..", Toast.LENGTH_SHORT).show();

            } catch (ApiException e) {

                switch (e.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                        try {
                            ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                            resolvableApiException.startResolutionForResult(LoginActivity.this, 2);
                        } catch (IntentSender.SendIntentException ex) {
                            ex.printStackTrace();
                        }
                        break;

                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        //Device does not have location
                        break;
                }
            }
        });

    }

    public void ruhsatAraActivity() {
        Intent intent = new Intent(getApplicationContext(), RuhsatAraActivity.class);
        startActivity(intent);
    }

    public void setSharedPref() {
        editor = sharedPref.edit();
        editor.putString("isUser", tcKimlikNo);
        editor.putString("token", token.getToken());
        editor.apply();
    }

    @Override
    public void onPause() {
        super.onPause();
        progressDialogManager.dismissDialog();
    }
}
