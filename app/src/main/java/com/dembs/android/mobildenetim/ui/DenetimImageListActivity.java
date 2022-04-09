package com.dembs.android.mobildenetim.ui;

import static com.dembs.android.mobildenetim.utils.Constants.getDateTimeNow2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.dembs.android.mobildenetim.adapters.DenetimImageListAdapter;
import com.dembs.android.mobildenetim.models.DenetimImageModel;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.ui.fragments.BottomSheetDialogFragment;
import com.dembs.android.mobildenetim.utils.AlertDialogManager;
import com.dembs.android.mobildenetim.utils.Constants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DenetimImageListActivity extends AppCompatActivity implements BottomSheetDialogFragment.ItemClickListener {
    Toolbar toolbar;
    RecyclerView rvDenetimImageList;
    ProgressBar progressBar;
    TextView tvEmpytList;
    ArrayList<DenetimImageModel> denetimImageModelArrayList;
    int id;
    Api api;
    int denetimId;
    File photoFile;
    String currentPhotoPath;
    Uri imageUri;
    String imageString, imagePath, imageName;
    byte[] imageInByte = null;
    int columnIndex = 0;
    ArrayList<String> strImageList = new ArrayList<>();
    ArrayList<Uri> imageUriList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denetim_image_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission, 100);
            }


        }
        api = ClientConfigs.createRetrofit(this).create(Api.class);
        getIntentValue();
        initActivity();
        getDenetimImageList();


    }

    private void getIntentValue() {
        Intent i = getIntent();
        denetimId = i.getIntExtra("denetimId", 0);

    }

    private void initActivity() {
        toolbar = findViewById(R.id.toolbarDenetimResimler);
        toolbar.setTitle("Resim Listesi");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rvDenetimImageList = findViewById(R.id.rvDenetimImageList);
        tvEmpytList = findViewById(R.id.tvEmptyList);
        progressBar = findViewById(R.id.progressBar);

    }

    private void getDenetimImageList() {


        Call<ArrayList<DenetimImageModel>> call = api.getDenetimImageList(denetimId,false);
        call.enqueue(new Callback<ArrayList<DenetimImageModel>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ArrayList<DenetimImageModel>> call, @NonNull Response<ArrayList<DenetimImageModel>> response) {

                if (response.isSuccessful()) {

                    denetimImageModelArrayList = response.body();

                    if (denetimImageModelArrayList == null || denetimImageModelArrayList.size() < 1) {
                        progressBar.setVisibility(View.GONE);
                        rvDenetimImageList.setVisibility(View.GONE);
                        tvEmpytList.setVisibility(View.VISIBLE);
                        tvEmpytList.setText("Denetime Ait Resim Bulunamadı..");

                    } else {
                        if (!rvDenetimImageList.isShown())
                        {
                            rvDenetimImageList.setVisibility(View.VISIBLE);
                            tvEmpytList.setVisibility(View.GONE);
                        }
                        progressBar.setVisibility(View.GONE);
                        setRecyclerView(denetimImageModelArrayList);
                    }

                } else {
                    rvDenetimImageList.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    tvEmpytList.setVisibility(View.VISIBLE);
                    tvEmpytList.setText("Hata Oluştu..");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DenetimImageModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), String.valueOf(t), Toast.LENGTH_LONG).show();
            }
        });
    }

    void setRecyclerView(ArrayList<DenetimImageModel> DenetimImageModelArrayList1) {

        if (DenetimImageModelArrayList1 != null && !DenetimImageModelArrayList1.isEmpty()) {
            rvDenetimImageList.setLayoutManager(new LinearLayoutManager(this));
            DenetimImageListAdapter denetimImageListAdapter = new DenetimImageListAdapter(this, DenetimImageModelArrayList1);
            rvDenetimImageList.setAdapter(denetimImageListAdapter);

        } else {
            rvDenetimImageList.setVisibility(View.GONE);
            tvEmpytList.setVisibility(View.VISIBLE);
            tvEmpytList.setText("Liste Boş..");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_denetim_menu, menu);

        // MenuItem menuItem = menu.findItem(R.id.add_denetim_item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.add_denetim_item) {
            if (denetimId > 0) {
              BottomSheetDialogFragment bottomSheetDialogFragment = BottomSheetDialogFragment.newInstance();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), BottomSheetDialogFragment.TAG);
            } else {
                AlertDialogManager alertDialogManager = new AlertDialogManager(this, "UYARI", "Resim yüklemeden önce denetimi kaydediniz..");
                alertDialogManager.createAlertDialog();
            }
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Toasty.success(getApplicationContext(), "Kamera ve Galeri için izin verildi..", Toasty.LENGTH_LONG, true).show();

            } else {
                Toast.makeText(getApplicationContext(), "Kamera için lütfen izinleri kontrol ediniz !", Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bundle extras;
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = null;
            if (resultCode == RESULT_OK) {
                switch (requestCode) {

                    case 1001:
                        imagePath = getRealPathFromURI1(imageUri);
                        imageInByte = byteArrayOutputStream.toByteArray();
                        strImageList.add(getImageName(imagePath));
                        imageUriList.add(imageUri);
                        compressImage(imagePath);
                        if (imageUriList != null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle("Resim Yüklemeyi Onaylıyor musunuz ?");
                            builder.setMessage("Yüklenecek resim : " + imagePath);
                            builder.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Resim yüklemeyi iptal ettiniz !", Toast.LENGTH_LONG).show();
                                }
                            });
                            builder.setPositiveButton("Yükle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    if (denetimId > 0 && !imageString.equals("")) {

                                        int pathSize = imagePath.length();
                                        int indexSlash = imagePath.lastIndexOf('/');
                                        int indexDot = imagePath.lastIndexOf('.');
                                        String dosyaAdi = imagePath.substring(indexSlash + 1, pathSize);
                                        String dosyaUzanti = imagePath.substring(indexDot, pathSize);

                                        Call<Void> call = api.uploadDenetimImage(getDenetimImageValue(dosyaAdi, dosyaUzanti));
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {

                                                progressBar.setVisibility(View.GONE);
                                                if (response.isSuccessful()) {

                                                    Toast.makeText(getApplicationContext(), "Resim Yüklendi..", Toast.LENGTH_LONG).show();
                                                    //tvImagePath.setVisibility(View.GONE);
                                                    getDenetimImageList();

                                                } else {
                                                    if (response.errorBody() != null) {
                                                        Toast.makeText(getApplicationContext(), "Hata :" + response.errorBody().toString(), Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(getApplicationContext(), "Hata :" + t.toString(), Toast.LENGTH_LONG).show();
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
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle("Resim Yüklemeyi Onaylıyor musunuz ?");
                            builder.setMessage("Yüklenecek resim : " + imagePath);
                            builder.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Resim yüklemeyi iptal ettiniz !", Toast.LENGTH_LONG).show();
                                }
                            });
                            builder.setPositiveButton("Yükle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    if (denetimId > 0 && !imageString.equals("")) {

                                        int pathSize = imagePath.length();
                                        int indexSlash = imagePath.lastIndexOf('/');
                                        String dosyaAdi = imagePath.substring(indexSlash + 1, pathSize);
                                        int indexDot = imagePath.lastIndexOf('.');
                                        String dosyaUzanti = imagePath.substring(indexDot, pathSize);

                                        Call<Void> call = api.uploadDenetimImage(getDenetimImageValue(dosyaAdi, dosyaUzanti));
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {

                                                progressBar.setVisibility(View.GONE);
                                                if (response.isSuccessful()) {

                                                    Toast.makeText(getApplicationContext(), "Resim Yüklendi..", Toast.LENGTH_LONG).show();
                                                    //tvImagePath.setVisibility(View.GONE);
                                                    getDenetimImageList();

                                                } else {
                                                    if (response.errorBody() != null) {
                                                        Toast.makeText(getApplicationContext(), "Hata :" + response.errorBody().toString(), Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(getApplicationContext(), "Hata :" + t.toString(), Toast.LENGTH_LONG).show();
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
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle("Resim Yüklemeyi Onaylıyor musunuz ?");
                            builder.setMessage("Yüklenecek resim : " + imagePath);
                            builder.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Resim yüklemeyi iptal ettiniz !", Toast.LENGTH_LONG).show();
                                }
                            });
                            builder.setPositiveButton("Yükle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    if (denetimId > 0 && !imageString.equals("")) {

                                        int pathSize = imagePath.length();
                                        int indexSlash = imagePath.lastIndexOf('/');
                                        String dosyaAdi = imagePath.substring(indexSlash + 1, pathSize);
                                        int indexDot = imagePath.lastIndexOf('.');
                                        String dosyaUzanti = imagePath.substring(indexDot, pathSize);

                                        Call<Void> call = api.uploadDenetimImage(getDenetimImageValue(dosyaAdi, dosyaUzanti));
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {

                                                progressBar.setVisibility(View.GONE);
                                                if (response.isSuccessful()) {

                                                    Toast.makeText(getApplicationContext(), "Resim Yüklendi..", Toast.LENGTH_LONG).show();
                                                    // tvImagePath.setVisibility(View.GONE);
                                                    getDenetimImageList();

                                                } else {
                                                    if (response.errorBody() != null) {
                                                        Toast.makeText(getApplicationContext(), "Hata :" + response.errorBody().toString(), Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {

                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(getApplicationContext(), "Hata :" + t.toString(), Toast.LENGTH_LONG).show();
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

            else if(resultCode==5){
                api = ClientConfigs.createRetrofit(this).create(Api.class);
                getIntentValue();
                initActivity();
                getDenetimImageList();//result code 5 detay da fotoğraf silindiğinde listeyi refresh etmeyi temsil eder.
            }
        } catch (Exception e) {
            Toast.makeText(this, "Hata : " + e.getMessage(), Toast.LENGTH_LONG).show();
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

    @Override
    public void onItemClick(View view) {

        int id = view.getId();
        if (id == R.id.tvFromCamera) {
            openCamera();
        } else if (id == R.id.tvFromGallery) {
            openGallery();
        }
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
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
                if (photoFile != null) {
                    imageUri = FileProvider.getUriForFile(this,
                            "com.dembs.android.mobildenetim.fileprovider", photoFile);
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

    private DenetimImageModel getDenetimImageValue(String dosyaAdi, String dosyaUzanti) {
        DenetimImageModel DenetimImageModel = new DenetimImageModel();
        DenetimImageModel.setdenetimId(denetimId);
        DenetimImageModel.setDocBinary(imageString);
        DenetimImageModel.setDocName(dosyaAdi);
        DenetimImageModel.setUzanti(dosyaUzanti);
        DenetimImageModel.setIslemTarihi(getDateTimeNow2().substring(0,10));
        DenetimImageModel.setKayitEden(Constants.userName);
        return DenetimImageModel;
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
        // Toast.success(getApplicationContext(), "current path : " + currentPhotoPath, Toast.LENGTH_LONG, true).show();

        return image;
    }


}