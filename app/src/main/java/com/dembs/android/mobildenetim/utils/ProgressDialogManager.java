package com.dembs.android.mobildenetim.utils;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.Nullable;

public class ProgressDialogManager {
    private Context mContext;
    private String mesaj = "Lütfen Bekleyiniz...";
    private ProgressDialog mDialog;

    public ProgressDialogManager(Context context, @Nullable String mesaj) {
        this.mContext = context;

    }

    public void createProgressBar() {


        mDialog = null;
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage(mesaj);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();


    }
    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
            mDialog = null;
        }
    }
}