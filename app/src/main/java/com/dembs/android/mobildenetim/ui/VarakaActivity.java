package com.dembs.android.mobildenetim.ui;

import static com.dembs.android.mobildenetim.utils.DateTimeInit.getDateTimeNow;
import static com.dembs.android.mobildenetim.utils.DateTimeInit.getDateTimeString;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.dembs.android.mobildenetim.adapters.VarakaResimListAdapter;
import com.dembs.android.mobildenetim.models.DenetimForm;
import com.dembs.android.mobildenetim.models.Varaka;
import com.dembs.android.mobildenetim.models.VarakaResim;
import com.dembs.android.mobildenetim.models.VarakaResult;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.ui.fragments.BottomSheetDialogFragment;
import com.dembs.android.mobildenetim.utils.AlertDialogManager;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;
import com.dembs.android.mobildenetim.utils.Util;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
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
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VarakaActivity extends AppCompatActivity implements BottomSheetDialogFragment.ItemClickListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    //region değişkenler
    private static final int PICK_IMAGE = 2;
    private static final int IMAGE_CAPTURE = 1;
    private static final int IMAGE_CAPTURE_2 = 3;
    private static final String FILE_NAME = "Log.txt";
    ProgressDialogManager progressDialogManager = new ProgressDialogManager(VarakaActivity.this, "");
    String dateTimeNow;
    Toolbar toolbar;
    TextInputEditText etVarakaNo, etVarakaTarih, etVarakaTamamlanmaTarihi, etVarakaAciklama, etVarakaIhlalBendi, etVarakaIhlalMaddesi;
    File photoFile;
    String ihlalBendiAdi, ihlalMaddeAdi;
    int formItemId, ihlalBendId, ihlalMaddeId;
    String getDateNow;
    int year, day, month, hour, minute;
    int finalYear, finalMonth, finalDay, finalHour, finalMinute;
    int varakaId = 0;
    String varakaNo = "";
    List<VarakaResult> varakaResultList = new ArrayList<>();
    Varaka varaka;
    SharedPreferences sharedPref;
    String isUser;
    int denetimId;
    boolean durum;
    //String imageString;
    MaterialButton btnVarakaKaydet, btnVarakaSil, btnUploadImage;
    ImageButton btnViewBottomSheet;
    String path;
    ArrayList<VarakaResim> varakaResimList = new ArrayList<>();
    String varakaUser;
    String strTamamlanmaTarihi;
    float alphaValue = (float) 1.0;
    RecyclerView rvImageList;
    int columnIndex = 0;
    AlertDialogManager alertDialogManager;
    String currentPhotoPath;
    Uri imageUri;
    Cursor cursor;
    String strImageUri;
    private Api api;
    TextInputLayout tilTarih;
    private ImageButton ibVarakaTarih;
    private ImageView imShowPhoto;
    private CheckBox cbTamamlandi;
    String imageString, imagePath, imageName;
    byte[] imageInByte = null;
    ArrayList<String> strImageList = new ArrayList<>();
    ArrayList<Uri> imageUriList = new ArrayList<>();
    boolean isRuhsatsizDenetim;
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varaka);

        api = ClientConfigs.createRetrofit(this).create(Api.class);
        DenetimForm denetimForm;
        Intent i = getIntent();
        isRuhsatsizDenetim=i.getBooleanExtra("isRuhsatsizDenetim",false);
        ArrayList<DenetimForm> item;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission, 100);
            }


        }
        item = i.getParcelableArrayListExtra("denetimFormItem");
        if (item != null) {
            denetimForm = item.get(0);
            if (denetimForm.getFormItemId() > 0) {
                formItemId = item.get(0).getFormItemId();

            }
            ihlalBendiAdi = denetimForm.getIhlalBendAdi();
            ihlalMaddeAdi = denetimForm.getIhlalMaddeAdi();
            ihlalBendId = denetimForm.getIhlalBendId();
            ihlalMaddeId = denetimForm.getIhlalMaddeId();
        } else {
            formItemId = i.getIntExtra("itemId", 0);
        }
        denetimId = i.getIntExtra("denetimId", 0);


        getVaraka(denetimId, formItemId);

        createLayoutElements();

        sharedPref = getApplicationContext().getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        isUser = ClientConfigs.GetPrefStr("isUser", getApplicationContext());
        tilTarih.setEndIconOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(VarakaActivity.this, VarakaActivity.this, year, month, day);
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
        });
        btnViewBottomSheet.setOnClickListener(v -> {
            if (varakaId > 0) {
                BottomSheetDialogFragment addPhotoBottomDialogFragment = BottomSheetDialogFragment.newInstance();
                addPhotoBottomDialogFragment.show(getSupportFragmentManager(), BottomSheetDialogFragment.TAG);
            } else {
                AlertDialogManager alertDialogManager = new AlertDialogManager(VarakaActivity.this, "UYARI", "Resim yüklemeden önce varakayı kaydediniz..");
                alertDialogManager.createAlertDialog();
            }

        });
        btnVarakaKaydet.setOnClickListener(v -> {

            progressDialogManager.createProgressBar();
            if (varakaId > 0) {
                if (isUser.equals(varakaUser)) {

                    Call<Void> call = api.updateVaraka(getElementsValueForUpdate());
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {

                                Toasty.success(getApplicationContext(), "Varaka Güncellendi..", Toasty.LENGTH_LONG, true).show();

                            }
                            progressDialogManager.dismissDialog();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            progressDialogManager.dismissDialog();
                            Toasty.error(getApplicationContext(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
                        }
                    });
                } else {
                    progressDialogManager.dismissDialog();
                    alertDialogManager = new AlertDialogManager(VarakaActivity.this, "Dikkat", "Diğer ekiplere ait varakaya işlem yapamazsınız..");
                    alertDialogManager.createAlertDialog();
                }

            } else {

                Call<Void> call2 = api.addVaraka(getElementsValueForInsert());
                call2.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {

                            Toasty.success(getApplicationContext(), "Varaka Eklendi..", Toasty.LENGTH_LONG, true).show();
                            getVaraka(denetimId,formItemId);
                            progressDialogManager.dismissDialog();
                        } else {
                            Toasty.warning(getApplicationContext(), "Hata :" + response.errorBody().toString(), Toasty.LENGTH_LONG, true).show();
                            progressDialogManager.dismissDialog();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                        Toasty.error(getApplicationContext(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();
                        progressDialogManager.dismissDialog();
                    }
                });

            }

        });
        btnVarakaSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MaterialAlertDialogBuilder(VarakaActivity.this)
                        .setTitle("Dikkat")
                        .setMessage("Varakayı silmek istediğinize emin misiniz..?")
                        .setPositiveButton("Sil", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                progressDialogManager.createProgressBar();
                                if (varakaId > 0) {
                                    if (isUser.equals(varakaUser)) {
                                        if(varakaResultList!=null&&varakaResultList.size()>0&&varakaResultList.get(0).getVaraka().getIslemTarihi().equals(getDateTimeString()))
                                        {
                                            Call<Void> call = api.deleteVaraka(varakaId);
                                            call.enqueue(new Callback<Void>() {
                                                @Override
                                                public void onResponse(Call<Void> call, Response<Void> response) {
                                                    if (response.isSuccessful()) {
                                                        Toasty.success(getApplicationContext(), "Varaka Silindi..", Toasty.LENGTH_LONG, true).show();
                                                        onBackPressed();
                                                        finish();
                                                    }
                                                    progressDialogManager.dismissDialog();
                                                }

                                                @Override
                                                public void onFailure(Call<Void> call, Throwable t) {
                                                    progressDialogManager.dismissDialog();
                                                    Toasty.error(getApplicationContext(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();

                                                }
                                            });
                                        }else {
                                            progressDialogManager.dismissDialog();
                                            alertDialogManager = new AlertDialogManager(VarakaActivity.this, "Dikkat", "Geçmiş varakaya ait silme işlemi yapılamaz !");
                                            alertDialogManager.createAlertDialog();

                                        }


                                    } else {
                                        progressDialogManager.dismissDialog();
                                        alertDialogManager = new AlertDialogManager(VarakaActivity.this, "Dikkat", "Diğer ekiplere ait varakayı silemezsiniz..");
                                        alertDialogManager.createAlertDialog();

                                    }
                                } else {
                                    progressDialogManager.dismissDialog();
                                    alertDialogManager = new AlertDialogManager(VarakaActivity.this, "Dikkat", "Silinecek Kayıtlı Varaka Bulunmamaktadır....");
                                    alertDialogManager.createAlertDialog();

                                }
                            }
                        })
                        .setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }

        });
        cbTamamlandi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    durum = true;
                else
                    durum = false;
            }
        });

    }


    private VarakaResim getVarakaResimValues(String dosyaAdi, String dosyaUzanti) {
        VarakaResim varakaResim = new VarakaResim();
        varakaResim.setVarakaId(varakaId);
        varakaResim.setDocBinary(imageString);
        varakaResim.setDocName(dosyaAdi);
        varakaResim.setUzanti(dosyaUzanti);
        varakaResim.setIslemTarihi(dateTimeNow);
        varakaResim.setKayitEden(isUser);
        return varakaResim;
    }

    private Varaka getElementsValueForInsert() {

        Varaka varakaInsert = new Varaka();
        varakaInsert.setDenetimId(denetimId);
        varakaInsert.setFormItemId(formItemId);
        varakaInsert.setAciklama(etVarakaAciklama.getText().toString());
        varakaInsert.setTamamlandi(false);
        varakaInsert.setIslemTarihi(getDateTimeNow());
        varakaInsert.setVarakaNo("");
        varakaInsert.setTamamlanmaTarihi(strTamamlanmaTarihi);
        varakaInsert.setKayitEden(isUser);
        return varakaInsert;
    }

    private void setVarakaValues(List<VarakaResult> varakaResultList) {

        if (varakaResultList.size() > 0) {
            varaka=varakaResultList.get(0).getVaraka();
            varakaUser = varakaResultList.get(0).getVaraka().getKayitEden();
            //etVarakaNo.setText(String.valueOf(varakaResultList.get(0).getVaraka().getId()));
            etVarakaNo.setText(varakaResultList.get(0).getVaraka().getVarakaNo());
            etVarakaTarih.setText(varakaResultList.get(0).getVaraka().getIslemTarihi()!=null?getFormattedTime(varakaResultList.get(0).getVaraka().getIslemTarihi()):"-");
            if (ihlalBendiAdi != null) {
                etVarakaIhlalBendi.setText(ihlalBendiAdi);
            } else {
                etVarakaIhlalBendi.setText(varakaResultList.get(0).getIhlalBendAdi());
            }
            if (ihlalMaddeAdi != null) {
                etVarakaIhlalMaddesi.setText(ihlalMaddeAdi);
            } else {
                etVarakaIhlalMaddesi.setText(varakaResultList.get(0).getIhlalMaddeAdi());
            }
            etVarakaAciklama.setText(varakaResultList.get(0).getVaraka().getAciklama());
            Util.setDisableEditText(etVarakaTamamlanmaTarihi);
            etVarakaTamamlanmaTarihi.setText(varakaResultList.get(0).getVaraka().getTamamlanmaTarihi()!=null?getFormattedTime(varakaResultList.get(0).getVaraka().getTamamlanmaTarihi()):"-");


            varakaId = varakaResultList.get(0).getVaraka().getId();
            varakaNo = varakaResultList.get(0).getVaraka().getVarakaNo();


            durum = varakaResultList.get(0).getVaraka().isTamamlandi();
            if (durum) {
                cbTamamlandi.setChecked(true);
            } else {
                cbTamamlandi.setChecked(false);
            }

            getImage(varakaId);

        }

    }

    private void getVaraka(int denetimId, int formItemId) {

        Call<List<VarakaResult>> call = api.getVarakaList(denetimId, formItemId);
        call.enqueue(new Callback<List<VarakaResult>>() {
            @Override
            public void onResponse(Call<List<VarakaResult>> call, Response<List<VarakaResult>> response) {
                if (response.isSuccessful()) {
                    varakaResultList = response.body();

                    setVarakaValues(Objects.requireNonNull(varakaResultList));

                } else

                    Toasty.error(getApplicationContext(), "Hata : Daha sonra tekrar deneyiniz..", Toasty.LENGTH_LONG, true).show();

            }

            @Override
            public void onFailure(Call<List<VarakaResult>> call, Throwable t) {

                Toasty.error(getApplicationContext(), "Hata :" + t.toString(), Toasty.LENGTH_LONG, true).show();

            }
        });

    }

    @Override
    public void onItemClick(View view) {

        int id = view.getId();
        if (id == R.id.tvFromCamera) {
            openCamera();
        } else if (id == R.id.tvFromGallery) {
            openGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Toasty.success(getApplicationContext(), "Kamera ve Galeri için izin verildi..", Toasty.LENGTH_LONG, true).show();

            } else {
                Toasty.error(getApplicationContext(), "Kamera için lütfen izinleri kontrol ediniz !", Toasty.LENGTH_LONG, true).show();
            }
        }
    }
    private String getImageName(String imagePath) {
        int pathSize = imagePath.length();
        int indexSlash = imagePath.lastIndexOf('/');
        return imagePath.substring(indexSlash + 1, pathSize);
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

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bundle extras;
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

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
                            AlertDialog.Builder builder = new AlertDialog.Builder(VarakaActivity.this);
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
                                    if (varakaId > 0 && !imageString.equals("")) {

                                        int pathSize = imagePath.length();
                                        int indexSlash = imagePath.lastIndexOf('/');
                                        int indexDot = imagePath.lastIndexOf('.');
                                        String dosyaAdi = imagePath.substring(indexSlash + 1, pathSize);
                                        String dosyaUzanti = imagePath.substring(indexDot, pathSize);

                                        Call<Void> call = api.uploadVarakaImage(getVarakaResimValues(dosyaAdi,dosyaUzanti));
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {

                                                progressDialogManager.dismissDialog();
                                                if (response.isSuccessful()) {

                                                    Toasty.success(getApplicationContext(), "Resim Yüklendi..", Toasty.LENGTH_LONG, true).show();
                                                    //tvImagePath.setVisibility(View.GONE);
                                                    getImage(varakaId);

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
                            AlertDialog.Builder builder = new AlertDialog.Builder(VarakaActivity.this);
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
                                    if (varakaId > 0 && !imageString.equals("")) {

                                        int pathSize = imagePath.length();
                                        int indexSlash = imagePath.lastIndexOf('/');
                                        String dosyaAdi = imagePath.substring(indexSlash + 1, pathSize);
                                        int indexDot = imagePath.lastIndexOf('.');
                                        String dosyaUzanti = imagePath.substring(indexDot, pathSize);

                                        Call<Void> call = api.uploadVarakaImage(getVarakaResimValues(dosyaAdi,dosyaUzanti));
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {

                                                progressDialogManager.dismissDialog();
                                                if (response.isSuccessful()) {

                                                    Toasty.success(getApplicationContext(), "Resim Yüklendi..", Toasty.LENGTH_LONG, true).show();
                                                    //tvImagePath.setVisibility(View.GONE);
                                                    getImage(varakaId);

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
                            AlertDialog.Builder builder = new AlertDialog.Builder(VarakaActivity.this);
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
                                    if (varakaId > 0 && !imageString.equals("")) {

                                        int pathSize = imagePath.length();
                                        int indexSlash = imagePath.lastIndexOf('/');
                                        String dosyaAdi = imagePath.substring(indexSlash + 1, pathSize);
                                        int indexDot = imagePath.lastIndexOf('.');
                                        String dosyaUzanti = imagePath.substring(indexDot, pathSize);

                                        Call<Void> call = api.uploadVarakaImage(getVarakaResimValues(dosyaAdi,dosyaUzanti));
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {

                                                progressDialogManager.dismissDialog();
                                                if (response.isSuccessful()) {

                                                    Toasty.success(getApplicationContext(), "Resim Yüklendi..", Toasty.LENGTH_LONG, true).show();
                                                   // tvImagePath.setVisibility(View.GONE);
                                                    getImage(varakaId);

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
            Toasty.error(VarakaActivity.this,"Hata : "+e.getMessage(),Toasty.LENGTH_LONG,true).show();
        }

    }

    private void createLayoutElements() {

        toolbar = findViewById(R.id.toolbarVaraka);
        toolbar.setTitle("Varaka Detay");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnVarakaKaydet = findViewById(R.id.btnVarakaKaydet);
        rvImageList = findViewById(R.id.rvImageList);
        btnVarakaSil = findViewById(R.id.btnVarakaSil);
        etVarakaNo = findViewById(R.id.etVarakaNo);
        Util.setDisableEditText(etVarakaNo);
        etVarakaTarih = findViewById(R.id.etVarakaTarihi);
        Util.setDisableEditText(etVarakaTarih);
        etVarakaIhlalBendi = findViewById(R.id.etVarakaIhlalBendi);
        etVarakaIhlalMaddesi = findViewById(R.id.etVarakaIhlalMaddesi);
        etVarakaAciklama = findViewById(R.id.etVarakaAciklama);
        etVarakaTamamlanmaTarihi = findViewById(R.id.etVarakaTamamlanmaTarihi);
        btnViewBottomSheet = findViewById(R.id.btnVarakaViewBottom);
        tilTarih = findViewById(R.id.tilTarih);
        cbTamamlandi = findViewById(R.id.cbVarakaTamamlandi);
        etVarakaIhlalBendi.setText(ihlalBendiAdi);
        etVarakaIhlalMaddesi.setText(ihlalMaddeAdi);
        Util.setDisableEditText(etVarakaTamamlanmaTarihi);
        strTamamlanmaTarihi = getDateTimeNow();
        etVarakaTamamlanmaTarihi.setText(getFormattedTime(getDateTimeNow()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_varaka, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        } else if (item.getItemId() == R.id.iconVarakaHome) {
            Intent intent = new Intent(this, RuhsatAraActivity.class);
            startActivity(intent);
        } else {
            if (varakaResultList.size() == 1) {
                Intent i = new Intent(this, VarakaYazdirActivity.class);
                i.putExtra("denetimId", denetimId);
                i.putExtra("itemId", formItemId);
                i.putExtra("isRuhsatsizDenetim", isRuhsatsizDenetim);
                i.putExtra("varakaId", varakaId);
                i.putExtra("varakaNo", varakaNo);
                i.putParcelableArrayListExtra("varakaList", (ArrayList<? extends Parcelable>) varakaResultList);
                startActivity(i);
            } else {

                Toasty.error(getApplicationContext(), "Önce varakayı kaydediniz..", Toasty.LENGTH_LONG, true).show();
            }
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
        TimePickerDialog timePickerDialog = new TimePickerDialog(VarakaActivity.this, VarakaActivity.this, hour, minute, android.text.format.DateFormat.is24HourFormat(this));
        timePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Tamam", timePickerDialog);
        timePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Vazgeç", timePickerDialog);
        timePickerDialog.show();
        timePickerDialog.getButton(timePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        timePickerDialog.getButton(timePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        finalHour = hourOfDay;
        finalMinute = minute;
        @SuppressLint("DefaultLocale")
        String timeFormatted = (String.format("%02d:%02d", finalHour, finalMinute));
        String temp = (String.format("%02d-%02d-%02d", finalYear, finalMonth, finalDay));
        strTamamlanmaTarihi = temp + "T" + timeFormatted + ":00";
        etVarakaTamamlanmaTarihi.setText(getFormattedTime(strTamamlanmaTarihi));

    }

    public Varaka getElementsValueForUpdate() {

        varaka.setTamamlanmaTarihi(strTamamlanmaTarihi);
        varaka.setAciklama(Objects.requireNonNull(etVarakaAciklama.getText()).toString());
        varaka.setTamamlandi(durum);

        return varaka;
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
                            "com.dembs.android.mobildenetim.fileprovider",photoFile);
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

    public String getRealPathFromURI1(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    private void getImage(int varakaNo) {

        if (varakaNo > 0) {

            progressDialogManager.createProgressBar();

            Call<ArrayList<VarakaResim>> call = api.getVarakaImageList(varakaNo, false);//false olarak gönder ve sadece resim bilgileri gelsin
            call.enqueue(new Callback<ArrayList<VarakaResim>>() {
                @Override
                public void onResponse(Call<ArrayList<VarakaResim>> call, Response<ArrayList<VarakaResim>> response) {
                    if (response.isSuccessful()) {
                        varakaResimList = response.body();
                        if (varakaResimList != null) {

                            rvImageList.setLayoutManager(new LinearLayoutManager(VarakaActivity.this));
                            VarakaResimListAdapter varakaResimListAdapter = new VarakaResimListAdapter(VarakaActivity.this, varakaResimList);
                            rvImageList.setAdapter(varakaResimListAdapter);



//                            imageString = responseResimList.get(0).getDocBinary();
//                            imShowPhoto.setAlpha(alphaValue);
//                            byte[] decodeString = Base64.decode(imageString, Base64.NO_WRAP);
//                            Bitmap decoded = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
//                            try {
//                                imShowPhoto.setImageBitmap(decoded);
//                            } catch (Exception e) {
//                                System.out.println("Bir Hata oluştu: " + e);
//                            }

                        }

                        progressDialogManager.dismissDialog();

                    }
                }

                @Override
                public void onFailure(Call<ArrayList<VarakaResim>> call, Throwable t) {
                    Log.i("resim", "Hata...");
                    progressDialogManager.dismissDialog();
                }
            });

        }
    }

    private String getFormattedTime(String getDateNow) {

        String s = getDateNow.substring(8, 10) + "-" + getDateNow.substring(5, 7) + "-" + getDateNow.substring(0, 4) + " " + getDateNow.substring(11, 16);
        return s;
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

    @Override
    protected void onResume() {
        super.onResume();
        getImage(varakaId);
    }

}