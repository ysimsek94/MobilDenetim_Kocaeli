package com.dembs.android.mobildenetim.utils;

import android.content.Context;

import com.dembs.android.mobildenetim.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AlertDialogManager extends AppCompatActivity {
    private Context mContext;
    private String title, message;

    public AlertDialogManager(Context mContext, String title, String message) {
        this.mContext = mContext;
        this.title = title;
        this.message = message;
    }

    public void createAlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        // alert dialog başlığını tanımlıyoruz.
        alertDialogBuilder.setTitle(title);
        // alert dialog özelliklerini oluşturuyoruz.
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setIcon(R.drawable.error_icon)
                // Evet butonuna tıklanınca yapılacak işlemleri buraya yazıyoruz.
                .setPositiveButton("Tamam", (dialog, which) ->
                        dialog.dismiss());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
