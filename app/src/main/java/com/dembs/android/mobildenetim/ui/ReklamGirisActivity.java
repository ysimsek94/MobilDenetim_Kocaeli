package com.dembs.android.mobildenetim.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.adapters.ReklamResimListAdapter;
import com.dembs.android.mobildenetim.models.AracReklam;
import com.dembs.android.mobildenetim.models.AracReklamResult;
import com.dembs.android.mobildenetim.models.ReklamResim;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.ui.fragments.BottomSheetDialogFragment;
import com.dembs.android.mobildenetim.utils.AlertDialogManager;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReklamGirisActivity extends AppCompatActivity implements BottomSheetDialogFragment.ItemClickListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    //region
    int cntTarih = 0;
    ProgressDialogManager progressDialogManager = new ProgressDialogManager(ReklamGirisActivity.this, "");
    ReklamResim reklamResim = new ReklamResim();
    ArrayList<String> strImageList = new ArrayList<>();
    ArrayList<Uri> imageUriList = new ArrayList<>();
    String dateTimeNow;
    MaterialButton btnKaydet, btnSil;
    ImageButton btnViewBottomSheet;
    Toolbar toolbar;
    TextView tvImagePath;
    EditText etPlaka, etBaslangicTarihi, etReklamAciklama, etBitisTarihi, etYbsNo;
    byte[] imageInByte = null;
    CheckBox cbAktif, cbSag, cbSol, cbOn, cbArka;
    Uri imageUri;
    Bitmap bitmap;
    RecyclerView rvImageList;
    String plaka;
    int reklamId, aracId;
    AracReklam reklam ;
    String imageString, imagePath;
    ArrayList<ReklamResim> reklamResimArrayList = new ArrayList<>();
    int year, day, month, hour, minute;
    int finalYear, finalMonth, finalDay, finalHour, finalMinute;
    String strBaslangicTarihi, strBitisTarihi;
    int columnIndex = 0;
    File photoFile;
    String currentPhotoPath;
    private Api api;
    TextInputLayout tipBasTarih,tipBitTarih;
    AracReklamResult reklamArac=new AracReklamResult();

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reklam_giris);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission, 100);
            }
        }

        createRetrofit();
        Intent i = getIntent();
        plaka = i.getStringExtra("plaka");
        aracId = i.getIntExtra("aracId", 0);
        reklamArac = i.getParcelableExtra("reklamList");
        if (reklamArac!=null)
        {
            reklam=reklamArac.getAracReklam();
        }

        createLayoutElements();

        if (plaka != null) {
            etPlaka.setText(plaka);
        }
        if (reklam != null) {
            reklamId = reklam.getId();
            setReklamValues(reklam);
        }

        btnKaydet.setOnClickListener(v -> {
            AracReklam r = new AracReklam();

            if (reklamId > 0) {
                progressDialogManager.createProgressBar();
                reklam = getReklamValuesForUpdate();
                Call<Void> call = api.updateReklam(reklam);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            progressDialogManager.dismissDialog();
                            Toasty.success(getApplicationContext(), "Veriler Güncellendi..", Toasty.LENGTH_LONG, true).show();

                        } else {
                            progressDialogManager.dismissDialog();
                            Toasty.error(getApplicationContext(), "Veri Güncellenemedi.!!!", Toasty.LENGTH_LONG, true).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        progressDialogManager.dismissDialog();
                        Toasty.error(getApplicationContext(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();

                    }
                });
            } else {
                progressDialogManager.createProgressBar();

                r = getReklamValues();
                Call<AracReklam> call = api.addReklam(r);
                call.enqueue(new Callback<AracReklam>() {
                    @Override
                    public void onResponse(Call<AracReklam> call, Response<AracReklam> response) {

                        if (response.isSuccessful()) {
                            reklam = response.body();
                            Toasty.success(getApplicationContext(), "Veriler Kaydedildi..", Toasty.LENGTH_LONG, true).show();
                            reklamId = reklam.getId();

                        }
                        progressDialogManager.dismissDialog();
                    }

                    @Override
                    public void onFailure(Call<AracReklam> call, Throwable t) {
                        progressDialogManager.dismissDialog();
                        Toasty.error(getApplicationContext(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();

                    }
                });
            }
        });
        tipBasTarih.setEndIconOnClickListener(v -> {

            cntTarih = 1;
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(ReklamGirisActivity.this, ReklamGirisActivity.this, year, month, day);
            datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Tamam", datePickerDialog);
            datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Vazgeç", datePickerDialog);
            datePickerDialog.show();
            datePickerDialog.getButton(datePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            datePickerDialog.getButton(datePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            datePickerDialog.getButton(datePickerDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datePickerDialog.dismiss();
                }
            });

            etBaslangicTarihi.setInputType(InputType.TYPE_NULL);
        });
        tipBitTarih.setEndIconOnClickListener(v -> {
            cntTarih = 2;
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog2 = new DatePickerDialog(ReklamGirisActivity.this, ReklamGirisActivity.this, year, month, day);
            datePickerDialog2.setButton(DialogInterface.BUTTON_POSITIVE, "Tamam", datePickerDialog2);
            datePickerDialog2.setButton(DialogInterface.BUTTON_NEGATIVE, "Vazgeç", datePickerDialog2);
            datePickerDialog2.show();
            datePickerDialog2.getButton(datePickerDialog2.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            datePickerDialog2.getButton(datePickerDialog2.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            datePickerDialog2.getButton(datePickerDialog2.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datePickerDialog2.dismiss();
                }
            });
            etBitisTarihi.setInputType(InputType.TYPE_NULL);
        });
        btnViewBottomSheet.setOnClickListener(v -> {
            BottomSheetDialogFragment addPhotoBottomDialogFragment = BottomSheetDialogFragment.newInstance();
            addPhotoBottomDialogFragment.show(getSupportFragmentManager(), BottomSheetDialogFragment.TAG);
        });
        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (reklamId > 0) {
                    progressDialogManager.createProgressBar();

                    Call<Void> call = api.deleteReklam(reklamId);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {

                                Toasty.success(getApplicationContext(), "Reklam Silindi..", Toasty.LENGTH_LONG, true).show();
                                onBackPressed();
                                finish();
                            } else {
                                Toasty.error(getApplicationContext(), "Reklam silinirken hata oluştu.." + response.errorBody(), Toasty.LENGTH_LONG, true).show();

                            }
                            progressDialogManager.dismissDialog();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            progressDialogManager.dismissDialog();
                            Toasty.success(getApplicationContext(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();

                        }
                    });
                } else {
                    Toasty.warning(getApplicationContext(), "Önce Reklamı Kaydediniz..", Toasty.LENGTH_LONG, true).show();

                }
            }
        });

    }

    private AracReklam getReklamValues() {
        AracReklam aracReklam = new AracReklam();
        aracReklam.setBaslangicTarihi(strBaslangicTarihi);
        aracReklam.setBitisTarihi(strBitisTarihi);
        aracReklam.setYbsNo(etYbsNo.getText().toString());
        aracReklam.setAracId(aracId);
        aracReklam.setAciklama(etReklamAciklama.getText().toString());
        if (cbSol.isChecked()) {
            aracReklam.setSolBolum(true);
        } else aracReklam.setSolBolum(false);
        if (cbSag.isChecked()) {
            aracReklam.setSagBolum(true);
        } else aracReklam.setSagBolum(false);
        if (cbArka.isChecked()) {
            aracReklam.setArkaBolum(true);
        } else aracReklam.setArkaBolum(false);
        if (cbOn.isChecked()) {
            aracReklam.setOnBolum(true);
        } else aracReklam.setOnBolum(false);
        if (cbAktif.isChecked()) {
            aracReklam.setAktif(true);
        } else aracReklam.setAktif(false);

        return aracReklam;
    }

    private void setReklamValues(AracReklam aracReklam) {
        strBaslangicTarihi = aracReklam.getBaslangicTarihi();
        strBitisTarihi = aracReklam.getBitisTarihi();
        etPlaka.setText(plaka);
        etBaslangicTarihi.setText(getFormattedTime(strBaslangicTarihi));
        etBitisTarihi.setText(getFormattedTime(strBitisTarihi));
        etYbsNo.setText(aracReklam.getYbsNo());
        etReklamAciklama.setText(aracReklam.getAciklama());

        //checkNullControl();

        if (aracReklam.isOnBolum()) {
            cbOn.setChecked(true);
        }
        if (aracReklam.isArkaBolum()) {
            cbArka.setChecked(true);
        }
        if (aracReklam.isSagBolum()) {
            cbSag.setChecked(true);
        }
        if (aracReklam.isSolBolum()) {
            cbSol.setChecked(true);
        }
        if (aracReklam.isAktif()) {
            cbAktif.setChecked(true);
        }
        //getReklamImage(reklamArrayList.get(0).getId());
    }


    private AracReklam getReklamValuesForUpdate() {

        reklam.setBaslangicTarihi(strBaslangicTarihi);
        reklam.setBitisTarihi(strBitisTarihi);
        reklam.setYbsNo(etYbsNo.getText().toString());
        reklam.setAciklama(etReklamAciklama.getText().toString());
        if (cbSol.isChecked()) {
            reklam.setSolBolum(true);
        } else reklam.setSolBolum(false);
        if (cbSag.isChecked()) {
            reklam.setSagBolum(true);
        } else reklam.setSagBolum(false);
        if (cbArka.isChecked()) {
            reklam.setArkaBolum(true);
        } else reklam.setArkaBolum(false);
        if (cbOn.isChecked()) {
            reklam.setOnBolum(true);
        } else reklam.setOnBolum(false);
        if (cbAktif.isChecked()) {
            reklam.setAktif(true);
        } else reklam.setAktif(false);

        return reklam;
    }

    private void getReklamImage(int reklamId) {

        if (reklamId > 0) {
            progressDialogManager.createProgressBar();


            Call<ArrayList<ReklamResim>> call = api.getReklamResimList(reklamId,false);//load Image servisle beraber resimlerde gelsinmi demek false ise sadece resim bilgileri gelir true iken resim de beraber gleir.
            call.enqueue(new Callback<ArrayList<ReklamResim>>() {
                @Override
                public void onResponse(Call<ArrayList<ReklamResim>> call, Response<ArrayList<ReklamResim>> response) {
                    if (response.isSuccessful()) {
                        reklamResimArrayList = response.body();

                        if (reklamResimArrayList != null) {

                            rvImageList.setLayoutManager(new LinearLayoutManager(ReklamGirisActivity.this));
                            ReklamResimListAdapter reklamResimListAdapter = new ReklamResimListAdapter(ReklamGirisActivity.this, reklamResimArrayList);
                            rvImageList.setAdapter(reklamResimListAdapter);
                        }
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<ReklamResim>> call, Throwable t) {
                    Log.i("resim", "Hata...");
                    progressDialogManager.dismissDialog();
                }
            });
            progressDialogManager.dismissDialog();
        }

    }

    @Override
    public void onItemClick(View view) {

        int id = view.getId();
        if (id == R.id.tvFromCamera && reklamId > 0) {
            openCamera();
        } else if (id == R.id.tvFromGallery && reklamId > 0) {
            openGallery();
        } else {
            AlertDialogManager alertDialogManager = new AlertDialogManager(ReklamGirisActivity.this, "UYARI", "Resim yüklemeden önce reklamı kaydediniz..");
            alertDialogManager.createAlertDialog();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bundle extras;
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            // Get the cursor
            Cursor cursor = null;
            if (resultCode == RESULT_OK ) {
                switch (requestCode) {

                    case 1001:
                        imagePath = getRealPathFromURI1(imageUri);
                        imageInByte = byteArrayOutputStream.toByteArray();
                        strImageList.add(getImageName(imagePath));
                        imageUriList.add(imageUri);
                        compressImage(imagePath);
                        if (imageUriList != null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ReklamGirisActivity.this);
                            builder.setTitle("Resim Yüklemeyi Onaylıyor musunuz ?");
                            builder.setMessage("Yüklenecek resim : " + imagePath);
                            builder.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Toasty.warning(getApplicationContext(), "Resim yüklemeyi iptal ettiniz !", Toasty.LENGTH_LONG, true).show();
                                }
                            });
                            builder.setPositiveButton("Yükle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    progressDialogManager.createProgressBar();
                                    if (reklamId > 0 && !imageString.equals("")) {

                                        int pathSize = imagePath.length();
                                        int indexSlash = imagePath.lastIndexOf('/');
                                        String dosyaAdi = imagePath.substring(indexSlash + 1, pathSize);

                                        Call<Void> call = api.uploadReklamResim(getReklamResimValue(dosyaAdi));
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {

                                                progressDialogManager.dismissDialog();
                                                if (response.isSuccessful()) {

                                                    Toasty.success(getApplicationContext(), "Resim Yüklendi..", Toasty.LENGTH_LONG, true).show();
                                                    tvImagePath.setVisibility(View.GONE);
                                                    getReklamImage(reklamId);

                                                } else {
                                                    if (response.errorBody() != null) {
                                                        Toasty.error(getApplicationContext(), "Hata :" + response.errorBody().toString(), Toasty.LENGTH_LONG, true).show();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                progressDialogManager.dismissDialog();
                                                Toasty.success(getApplicationContext(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
                                            }
                                        });
                                    }
                                }
                            });
                            builder.show();
                            InputStream iStream = null;
                            try {
                                if (imageUri != null) {
                                    iStream = getContentResolver().openInputStream(imageUri);
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            try {
                                byte[] inputData = getBytes(iStream);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        break;

                    case 2:
                        imageUri = data.getData();
                        imagePath = getRealPathFromURI1(imageUri);
                        imageInByte = byteArrayOutputStream.toByteArray();
                        strImageList.add(getImageName(imagePath));
                        imageUriList.add(imageUri);
                        compressImage(imagePath);
                        if (imageUriList != null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ReklamGirisActivity.this);
                            builder.setTitle("Resim Yüklemeyi Onaylıyor musunuz ?");
                            builder.setMessage("Yüklenecek resim : " + imagePath);
                            builder.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Toasty.warning(getApplicationContext(), "Resim yüklemeyi iptal ettiniz !", Toasty.LENGTH_LONG, true).show();
                                }
                            });
                            builder.setPositiveButton("Yükle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    progressDialogManager.createProgressBar();
                                    if (reklamId > 0 && !imageString.equals("")) {

                                        int pathSize = imagePath.length();
                                        int indexSlash = imagePath.lastIndexOf('/');
                                        String dosyaAdi = imagePath.substring(indexSlash + 1, pathSize);

                                        Call<Void> call = api.uploadReklamResim(getReklamResimValue(dosyaAdi));
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {

                                                progressDialogManager.dismissDialog();
                                                if (response.isSuccessful()) {

                                                    Toasty.success(getApplicationContext(), "Resim Yüklendi..", Toasty.LENGTH_LONG, true).show();
                                                    tvImagePath.setVisibility(View.GONE);
                                                    getReklamImage(reklamId);

                                                } else {
                                                    if (response.errorBody() != null) {
                                                        Toasty.error(getApplicationContext(), "Hata :" + response.errorBody().toString(), Toasty.LENGTH_LONG, true).show();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                progressDialogManager.dismissDialog();
                                                Toasty.success(getApplicationContext(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
                                            }
                                        });
                                    }
                                }
                            });
                            builder.show();
                            InputStream iStream = null;
                            try {
                                if (imageUri != null) {
                                    iStream = getContentResolver().openInputStream(imageUri);
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            try {
                                byte[] inputData = getBytes(iStream);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        break;

                    case 1:
                        imageUri = data.getData();
                        imagePath = getRealPathFromURI1(imageUri);
                        cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                        if (cursor != null) {
                            cursor.moveToFirst();
                            columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imagePath = cursor.getString(columnIndex);
                            cursor.close();
                        }
                        compressImage(imagePath);
                        strImageList.add(getImageName(imagePath));
                        imageUriList.add(imageUri);
                        if (imageUriList != null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ReklamGirisActivity.this);
                            builder.setTitle("Resim Yüklemeyi Onaylıyor musunuz ?");
                            builder.setMessage("Yüklenecek resim : " + imagePath);
                            builder.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Toasty.warning(getApplicationContext(), "Resim yüklemeyi iptal ettiniz !", Toasty.LENGTH_LONG, true).show();
                                }
                            });
                            builder.setPositiveButton("Yükle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    progressDialogManager.createProgressBar();
                                    if (reklamId > 0 && !imageString.equals("")) {

                                        int pathSize = imagePath.length();
                                        int indexSlash = imagePath.lastIndexOf('/');
                                        String dosyaAdi = imagePath.substring(indexSlash + 1, pathSize);

                                        Call<Void> call = api.uploadReklamResim(getReklamResimValue(dosyaAdi));
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {

                                                progressDialogManager.dismissDialog();
                                                if (response.isSuccessful()) {

                                                    Toasty.success(getApplicationContext(), "Resim Yüklendi..", Toasty.LENGTH_LONG, true).show();
                                                    tvImagePath.setVisibility(View.GONE);
                                                    getReklamImage(reklamId);

                                                } else {
                                                    if (response.errorBody() != null) {
                                                        Toasty.error(getApplicationContext(), "Hata :" + response.errorBody().toString(), Toasty.LENGTH_LONG, true).show();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                progressDialogManager.dismissDialog();
                                                Toasty.success(getApplicationContext(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
                                            }
                                        });
                                    }
                                }
                            });
                            builder.show();
                        }
                        InputStream iStream2 = null;
                        try {
                            iStream2 = getContentResolver().openInputStream(imageUri);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            byte[] inputData = getBytes(iStream2);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    default:
                        break;

                }
            }
        }
        catch (Exception e)
        {
            Toasty.error(ReklamGirisActivity.this,"Hata : "+e.getMessage(),Toasty.LENGTH_LONG,true).show();
        }

    }


    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    private void createLayoutElements() {

        Date currentTime = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateTimeNow = sdf.format(currentTime);//get current date
        strBaslangicTarihi = dateTimeNow;
        strBitisTarihi = dateTimeNow;
        toolbar = findViewById(R.id.toolbarReklamGiris);
        toolbar.setTitle("Reklam Detay");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnKaydet = findViewById(R.id.btnReklamKaydet);
        btnSil = findViewById(R.id.btnReklamSil);
        // btnResimYukle = findViewById(R.id.btnUploadImage);
        etPlaka = findViewById(R.id.etReklamPlaka);
        etPlaka.setText(plaka);
        etBaslangicTarihi = findViewById(R.id.etBaslangiTarihi);
        etBaslangicTarihi.setText(getFormattedTime(strBaslangicTarihi));
        etBitisTarihi = findViewById(R.id.etBitisTarihi);
        etBitisTarihi.setText(getFormattedTime(strBitisTarihi));
        etYbsNo = findViewById(R.id.etYbsNo);
        etReklamAciklama = findViewById(R.id.etReklamAciklama);
        btnViewBottomSheet = findViewById(R.id.btnReklamViewBottomSheet);
        rvImageList = findViewById(R.id.rvImageList);
        tipBasTarih=findViewById(R.id.tipBasTarih);
        tipBitTarih=findViewById(R.id.tipBitTarih);
        cbAktif = findViewById(R.id.cbIsAktif);
        cbSag = findViewById(R.id.cbSag);
        cbSol = findViewById(R.id.cbSol);
        cbOn = findViewById(R.id.cbOn);
        cbArka = findViewById(R.id.cbArka);
        tvImagePath = findViewById(R.id.tvImagePath);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void createRetrofit() {
        api = ClientConfigs.createRetrofit(this).create(Api.class);
    }

    private String getImageName(String imagePath) {
        int pathSize = imagePath.length();
        int indexSlash = imagePath.lastIndexOf('/');
        return imagePath.substring(indexSlash + 1, pathSize);
    }

    private ReklamResim getReklamResimValue(String dosyaAdi) {

        reklamResim.setreklamId(reklamId);
        reklamResim.setDocName(dosyaAdi);
        reklamResim.setDocBinary(imageString);
        String isUser = ClientConfigs.GetPrefStr("isUser", getApplicationContext());
        reklamResim.setKayitEden(isUser);
        reklamResim.setIslemTarihi(dateTimeNow);
        return reklamResim;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        finalYear = year;
        finalMonth = month + 1;
        finalDay = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(ReklamGirisActivity.this, ReklamGirisActivity.this, hour, minute, android.text.format.DateFormat.is24HourFormat(this));
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

        if (cntTarih == 1) {
            strBaslangicTarihi = temp + "T" + timeFormatted + ":00";
            etBaslangicTarihi.setText(getFormattedTime(strBaslangicTarihi));
        } else if (cntTarih == 2) {
            strBitisTarihi = temp + "T" + timeFormatted + ":00";
            etBitisTarihi.setText(getFormattedTime(strBitisTarihi));
        }

    }

    private String getFormattedTime(String getDateNow) {

        String s = getDateNow.substring(8, 10) + "-" + getDateNow.substring(5, 7) + "-" + getDateNow.substring(0, 4) + " " + getDateNow.substring(11, 16);
        return s;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getReklamImage(reklamId);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toasty.success(getApplicationContext(), "Kamera ve Galeri için izin verildi..", Toasty.LENGTH_LONG,true).show();

                } else {
                    Toasty.error(getApplicationContext(), "Kamera için lütfen izinleri kontrol ediniz !", Toasty.LENGTH_LONG,true).show();
                }
            }
        }
    }

    public String getRealPathFromURI1(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public void compressImage(String strImageUri) {

        String filePath = getRealPathFromURI(strImageUri);
        Bitmap scaledBitmap = null;
        byte[] bitmapdata = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;
//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;//612.0f;

        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }
//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);

            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);

            } else if (orientation == 8) {
                matrix.postRotate(270);

            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bitmapdata = bos.toByteArray();
            imageString = Base64.encodeToString(bitmapdata, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    private void openCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.Images.Media.TITLE, "New");
                contentValues.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    Toasty.error(getApplicationContext(), ex.getMessage(), Toasty.LENGTH_LONG, true).show();
                }
                if (photoFile != null) {
                    imageUri = FileProvider.getUriForFile(this,
                            "com.dembs.android.mobildenetim.fileprovider",
                            photoFile);
                    imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(cameraIntent, 1001);

                }
            } else {
                Toast.makeText(getApplicationContext(), "Ayarlardan Kamera erişimini açınız..", Toast.LENGTH_LONG).show();
            }
        } else {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 2);
            } else {
                Toast.makeText(getApplicationContext(), "Ayarlardan Kamera erişimini açınız..", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void openGallery() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK);
            pickPhoto.setType("image/*");
            startActivityForResult(pickPhoto, 1);//one can be replaced with any action code
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        // Toasty.success(getApplicationContext(), "current path : " + currentPhotoPath, Toasty.LENGTH_LONG, true).show();

        return image;
    }
}