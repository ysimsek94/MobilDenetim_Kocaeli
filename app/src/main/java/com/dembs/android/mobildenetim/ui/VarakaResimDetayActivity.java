package com.dembs.android.mobildenetim.ui;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.models.ReklamResim;
import com.dembs.android.mobildenetim.models.VarakaResim;
import com.dembs.android.mobildenetim.network.Api;
import com.dembs.android.mobildenetim.network.ClientConfigs;
import com.dembs.android.mobildenetim.utils.ProgressDialogManager;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VarakaResimDetayActivity extends AppCompatActivity {
    ProgressDialogManager progressDialogManager = new ProgressDialogManager(VarakaResimDetayActivity.this, "");
    ImageView ivResim;
    MaterialButton btnResimSil;
    Toolbar toolbar;
    ReklamResim reklamResim = new ReklamResim();
    float alphaValue = (float) 1.0;
    Retrofit retrofit;
    int resimId;
    String imageString;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private Api api;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varaka_resim_detay);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        resimId = getIntent().getIntExtra("resimId", 0);
        createLayoutElements();
        api = ClientConfigs.createRetrofit(this).create(Api.class);
        btnResimSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (resimId > 0) {
                    progressDialogManager.createProgressBar();
                    Call<Void> call = api.deleteVarakaImage(resimId);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                progressDialogManager.dismissDialog();
                                onBackPressed();
                                finish();
                            }else
                            {
                                progressDialogManager.dismissDialog();
                                Toasty.error(getApplicationContext(), "Resim Silmede Hata Oluştu !", Toasty.LENGTH_LONG, true).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            progressDialogManager.dismissDialog();
                            Toasty.error(getApplicationContext(), "Hata : "+t.toString(), Toasty.LENGTH_LONG, true).show();
                        }
                    });


                }
            }
        });
    }

    private void getImage() {
        progressDialogManager.createProgressBar();
        Call<VarakaResim> call = api.getVarakaImage(resimId);
        call.enqueue(new Callback<VarakaResim>() {
            @Override
            public void onResponse(Call<VarakaResim> call, Response<VarakaResim> response) {
                if (response.isSuccessful()) {
                    imageString = response.body().getDocBinary();
                    setImage();
                    progressDialogManager.dismissDialog();
                } else {
                    progressDialogManager.dismissDialog();
                }
            }

            @Override
            public void onFailure(Call<VarakaResim> call, Throwable t) {
                Toasty.error(VarakaResimDetayActivity.this,"Hata :"+t.getMessage().toString(),Toasty.LENGTH_SHORT,true).show();
                progressDialogManager.createProgressBar();
            }
        });


    }

    private void setImage() {
        //Base64String To ByteArray Convert
        byte[] decodeString = Base64.decode(imageString, Base64.NO_WRAP);
        Bitmap decoded = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        ivResim.setAlpha(alphaValue);
        ivResim.setImageBitmap(decoded);
    }

    private void createLayoutElements() {
        toolbar = findViewById(R.id.toolbarVarakaResimDetay);
        toolbar.setTitle("Resim Detay");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnResimSil = findViewById(R.id.btnResimSil);
        ivResim = findViewById(R.id.ivResim);
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

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            ivResim.setScaleX(mScaleFactor);
            ivResim.setScaleY(mScaleFactor);
            return true;
        }
    }
}
