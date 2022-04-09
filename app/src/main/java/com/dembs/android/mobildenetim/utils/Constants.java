package com.dembs.android.mobildenetim.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Constants {
    public static String userName;
    public static  int ekipId;
    public static int kullaniciId;
    public static String tcKimlikNo;

    public static String getDateTimeNow2() {

        String getDateNow;
        Date currentTime = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        getDateNow = sdf.format(currentTime);//getting date and time

        return getDateNow;
    }

    public static String getDateTimeWithOutTime() {

        String getDateNow;
        Date currentTime = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        getDateNow = sdf.format(currentTime);//getting date and time

        return getDateNow;
    }


    public static String getDateTimeNow() {

        String getDateNow;
        Date currentTime = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        getDateNow = sdf.format(currentTime);//getting date and time
//        String strDateTimeNow = getDateNow.substring(8, 10) + "-" + getDateNow.substring(5, 7) + "-" +
//                getDateNow.substring(0, 4) + " " + getDateNow.substring(11, 16);

        String strDateTimeNow = getDateNow.substring(0, 4) + "-" + getDateNow.substring(5, 7)+"-" +getDateNow.substring(8, 10)+"-"+getDateNow.substring(11, 16);

        return strDateTimeNow;
    }


    public static String getFormattedTimeWithOutT(String dateTime) {

        String strDateTimeNow = dateTime.substring(8, 10) + "-" + dateTime.substring(5, 7) + "-" + dateTime.substring(0, 4) + " " + dateTime.substring(11, 16);

        return strDateTimeNow;
    }

    public static String getFormattedTime(String dateTime) {

        String strDateTimeNow = dateTime.substring(8, 10) + "-" + dateTime.substring(5, 7) + "-" + dateTime.substring(0, 4) + "T" + dateTime.substring(11, 16);

        return strDateTimeNow;
    }
}
