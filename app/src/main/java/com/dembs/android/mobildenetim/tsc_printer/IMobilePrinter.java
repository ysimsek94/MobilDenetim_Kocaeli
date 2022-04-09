package com.dembs.android.mobildenetim.tsc_printer;

import android.content.Context;
import android.graphics.Bitmap;

public interface IMobilePrinter {

    boolean openPrinter(Context context,int portType, String logicalName, String deviceAdres, boolean isAsyncMode);


    boolean isConnectBluetoothDevice(String deviceAdres);

    void print(Context context, int width, int alignment, int brightness, int spinnerDither,String ruhsatNo,String tanzim, String deviceAdres, Bitmap bitmap, String varakaNo, String tcNo, String adSoyad, String plaka, String ikametAdres,
               String ihlalAdres, String ihlalTarihSaat, String ihlalBendAdi, String ihlalMaddeAdi, String ihlalBendAciklama,
               String varakaAciklama, String surucuTcNo,String surucuAd,String sicil1, String sicil2, String sicil3,String sicil4, String tebligTarihsaat, String mudur, String ukomeMetin,int counter);


}
