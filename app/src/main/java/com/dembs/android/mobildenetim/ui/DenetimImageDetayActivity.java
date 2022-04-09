package com.dembs.android.mobildenetim.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.DenetimImageModel;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DenetimImageDetayActivity extends AppCompatActivity {
    ImageView ivDenetimImage;
    MaterialButton btnResimSil;
    Toolbar toolbar;
    DenetimImageModel imageModel = new DenetimImageModel();
    float alphaValue = (float) 1.0;
    int imageId;
    String imageString;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private Api api;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denetim_image_detay);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        imageId = getIntent().getIntExtra("imageId", 0);
        initActivity();
        api = ClientConfigs.createRetrofit(this).create(Api.class);
        btnResimSil.setOnClickListener(v -> {

            if (imageId > 0) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Uyarı");
                builder.setMessage("Silmek İstediğinize Emin Misiniz?" );
                builder.setNegativeButton("Vazgeç", (dialog, which) -> {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Resim silmeyi iptal ettiniz !", Toast.LENGTH_LONG).show();
                });
                builder.setPositiveButton("Sil", (dialogInterface, i) -> {
                    Call<Void> call = api.deleteDenetimImage(imageId);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                            if (response.isSuccessful()) {

                                onBackPressed();

                            }else
                            {

                                Toast.makeText(getApplicationContext(), "Resim Silmede Hata Oluştu !", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(@NonNull Call<Void> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Hata : "+t.toString(), Toast.LENGTH_LONG).show();
                        }
                    });

                });
                builder.show();
            }

        });
    }

    private void getImage() {


        Call<DenetimImageModel> call = api.getDenetimImage(imageId);
        call.enqueue(new Callback<DenetimImageModel>() {
            @Override
            public void onResponse(Call<DenetimImageModel> call, Response<DenetimImageModel> response) {
                if (response.isSuccessful())
                {
                    imageModel=response.body();
                    imageString= imageModel != null ? imageModel.getDocBinary() : null;
                    setImage();
                }
                else
                {
                    imageString="null;";
                }
            }

            @Override
            public void onFailure(Call<DenetimImageModel> call, Throwable t) {

            }
        });


    }

    private void setImage() {
        //Base64String To ByteArray Convert
        byte[] decodeString = Base64.decode(imageString, Base64.NO_WRAP);
        Bitmap decoded = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        ivDenetimImage.setAlpha(alphaValue);
        ivDenetimImage.setImageBitmap(decoded);
    }

    private void initActivity() {
        toolbar = findViewById(R.id.toolbarDenetimImageDetay);
        toolbar.setTitle("Resim Detay");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnResimSil = findViewById(R.id.btnResimSil);
        ivDenetimImage = findViewById(R.id.ivResim);
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

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getImage();
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        setResult(5, intent);
        finish();

    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            ivDenetimImage.setScaleX(mScaleFactor);
            ivDenetimImage.setScaleY(mScaleFactor);
            return true;
        }
    }
}