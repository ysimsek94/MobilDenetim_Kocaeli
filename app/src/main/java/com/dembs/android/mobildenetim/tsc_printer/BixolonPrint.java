package com.dembs.android.mobildenetim.tsc_printer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.dembs.android.mobildenetim.R;
import com.dembs.android.mobildenetim.bixolon.BixolonPrinter;
import com.dembs.android.mobildenetim.ui.VarakaYazdirActivity;

import es.dmoral.toasty.Toasty;

public class BixolonPrint implements  IMobilePrinter{
    private static BixolonPrinter bxlPrinter = null;

    @Override
    public boolean openPrinter(Context context,int portType, String logicalName, String deviceAdres, boolean isAsyncMode) {
        bxlPrinter = new BixolonPrinter(context);
               boolean rtn = bxlPrinter.printerOpen(0, logicalName, deviceAdres, false);

        return rtn;
    }

    @Override
    public boolean isConnectBluetoothDevice(String deviceAdres) {
        return false;
    }

    @Override
    public void print(Context context, int width,int alignment,int brightness,int spinnerDither,String ruhsatNo,String tanzim, String deviceAdres, Bitmap bitmap, String varakaNo, String tcNo, String adSoyad, String plaka, String ikametAdres, String ihlalAdres, String ihlalTarihSaat, String ihlalBendAdi, String ihlalMaddeAdi, String ihlalBendAciklama, String varakaAciklama,String surucuTcNo,String surucuAd,String sicil1, String sicil2, String sicil3,String sicil4,String tebligTarihsaat, String mudur, String ukomeMetin,int counter) {
        while(counter<2)
        {
            width = 750;
            brightness = 25;



//Resim Yazdırma
            bxlPrinter.printImage(bitmap, width, alignment, brightness, spinnerDither);
//Boşluk
            String text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n\n");


//VarakaNo
            text = "Varaka No :" + varakaNo;
            PrintText1(text, 20, 50, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");


//TCVergiNo
            text = "TC / Vergi No        : ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "");
            text = tcNo;
            PrintText1(text, 35, 28, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
//Boşluk
            text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
//İlgilinin Adı Soyadı
            text = "İlgilinin Adı Soyadı : ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "");
            text = adSoyad;
            PrintText1(text, 35, 28, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
//Boşluk
            text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
//Ruhsat No
            text = "Ruhsat No            : ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "");
            text = ruhsatNo;
            PrintText1(text, 35, 28, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
//Boşluk
            text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");

            //Plaka No
            text = "Plaka No             : ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "");
            text = plaka;
            PrintText1(text, 35, 28, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
            //Boşluk
            text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
            //İkamet Adresi
            text = "İkamet Adresi        : ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "");
            text = ikametAdres;
            PrintText1(text, 35, 28, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
//Boşluk
            text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
//İhlal Adresi
            text = "İhlal Adresi         : ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "");
            text = ihlalAdres;
            PrintText1(text, 35, 28, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
////Boşluk
            text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
////İhlal TarihSaat
            text = "İhlal Tarih/Saat     : ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "");
            text = ihlalTarihSaat;
            PrintText1(text, 35, 28, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
////Boşluk
            text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
////Metin
            text = tanzim;
            PrintText1(text, 57, 5, BixolonPrinter.ATTRIBUTE_FONT_A, 1, "\n");
////Boşluk
            text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
//İhlal bendi
            text = "İhlal Bendi          : ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
            text = ihlalBendAciklama;
            PrintText1(text, 57, 5, BixolonPrinter.ATTRIBUTE_FONT_A, 1, "\n");
////Boşluk
            text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
//
////İhlal Açıklama
            text = "İhlal Açıklaması     : ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "");
            text = varakaAciklama;
            PrintText1(text, 35, 28, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
////Boşluk
            text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");

            text = "MUHATABIN ADI SOYADI";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "");

            text = "BELEDİYE DENETİM PERSONELİ";
            PrintText1(text, 35, 8, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");

            text = "İMZASI";
            PrintText1(text, 57, 9, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");

            text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");

            if (!surucuTcNo.equals("")) {
                text = surucuTcNo;
                PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "");
                text = "Sicil No :" + sicil1;
                PrintText1(text, 35, 22, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
            } else {
                text = "Sicil No :" + sicil1;
                PrintText1(text, 57, 38, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
            }

            if (!surucuAd.equals("")) {
                text = surucuAd;
                PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "");
                text = "Sicil No :" + sicil2;
                PrintText1(text, 35, 21, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
            } else {
                text = "Sicil No :" + sicil2;
                PrintText1(text, 57, 38, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
            }

            text = "Sicil No :" + sicil3;
            PrintText1(text, 57, 38, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");

            text = "Tebliğ Tarihi/Saati";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "");

            if (!sicil4.equals(".")){
                text = "Sicil No :" + sicil4;
                PrintText1(text, 35, 14, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
            }else{
                text = " ";
                PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
            }

            text = tebligTarihsaat;
            PrintText1(text, 25, 7, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");

////Boşluk
            text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");

            text = mudur;
            PrintText1(text, 25, 10, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");

            text = "Toplu Taşıma Şube Müdürü";
            PrintText1(text, 35, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
////Boşluk
            text = " ";
            PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");

            text = ".................................................................";
            PrintText1(text, 75, 10, BixolonPrinter.ATTRIBUTE_FONT_B, 1, "\n");

            text = "20.01.2014 tarih ve 2014/20 sayılı UKOME kararı gereğince araçlarda 30 gün kayıt saklama süresi olan kamera sistemi bulunması gerekmektedir. Bu bağlamda konu ile ilgili savunmanızı elden teslim alımlarda 2 (gün) , diğer durumlarda kanuni süresi 15 (onbeş) gün içerisinde aracınızda bulunan kamera sisteminin yukarda belirtilen tarih ve saatte kayıt altına aldığı görüntülerle birlikte Başkanlığımıza savunmanızı yazılı olarak vermeniz gerekmektedir. Aksi halde savunmanızdan vazgeçmiş sayılacaksınız.";
            PrintText1(text, 75, 5, BixolonPrinter.ATTRIBUTE_FONT_B, 1, "\n");

            int spaceCount = 0;

            while (spaceCount < 8) {
                text = " ";
                PrintText1(text, 25, 5, BixolonPrinter.ATTRIBUTE_BOLD, 1, "\n");
                spaceCount++;
            }

            bxlPrinter.formFeed();
            counter++;
        }
        bxlPrinter.printerClose();
    }

    private void PrintText1(String text, int limit, int leftSpaceCnt, int isBold, int textSize, String newLine) {
        //text size 1 A - 2 B
        //BixolonPrinter.ATTRIBUTE_BOLD
        //
        String leftSpace = "";

        for (int i = 1; i <= leftSpaceCnt; i++) {
            leftSpace = leftSpace + " ";
        }
        String leftSpace1 = leftSpace;

        String dummy = "";

        double cnt = Math.ceil((double) text.length() / limit);

        for (int i = 0; i < cnt; i++) {
            if (leftSpaceCnt == 28) {
                if (i == 0) {
                    leftSpace = " ";
                } else {
                    leftSpace = leftSpace1;
                }
            }

            // dummy = text.substring(0, limit).trim();

            if (text.length() > limit) {
                dummy = text.substring(0, limit).trim();
                text = text.substring(limit).trim();
               bxlPrinter.printText(leftSpace + dummy + newLine, BixolonPrinter.ALIGNMENT_LEFT, isBold, textSize);
                continue;
            }

            if (text.length() < limit) {
                dummy = leftSpace + text.trim();
                bxlPrinter.printText(dummy + newLine, BixolonPrinter.ALIGNMENT_LEFT, isBold, textSize);
                break;
            }
        }
    }


}
