package com.dembs.android.mobildenetim.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeInit extends AppCompatActivity  {



public static  String getDateTimeNow()
{
    Date currentTime = Calendar.getInstance().getTime();
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    String dateTimeNow = sdf.format(currentTime);//get current date
    return dateTimeNow;
}
public static  String getCurrentUser(Context context)
{
   SharedPreferences sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
   return sharedPref.getString("isUser", null);
}
    public static String getFormattedTime() {
    Date currentTime = Calendar.getInstance().getTime();
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    String dateTimeNow = sdf.format(currentTime);//get current date

        String s = dateTimeNow.substring(8, 10) + "-" + dateTimeNow.substring(5, 7) + "-" + dateTimeNow.substring(0, 4) + " " + dateTimeNow.substring(11, 16);
        return s;
    }

    public static String getFormattedTime(String tarih) {

        if(tarih!=null&&!tarih.equals(" - ")){
            String s = tarih.substring(8, 10) + "-" + tarih.substring(5, 7) + "-" + tarih.substring(0, 4) + " " + tarih.substring(11, 16);
            return s;
        }
        return  tarih;
    }

    public static String getFormattedTimeWithoutSaat(String tarih) {

        if(tarih==null||tarih.equals(" - ")){
            return tarih;
        }
        return tarih.substring(8, 10) + "-" + tarih.substring(5, 7) + "-" + tarih.substring(0, 4) ;
    }

    public static String getFormattedTimeYilAyGun() {
        Date currentTime = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String dateTimeNow = sdf.format(currentTime);//get current date

        if(dateTimeNow.equals("")){
            return dateTimeNow;
        }
        return dateTimeNow.substring(5, 7) + "-" + dateTimeNow.substring(8, 10) + "-" + dateTimeNow.substring(0, 4) ;
    }
    public static String getDateTimeString()
    {
        String currentDateTime = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        return  currentDateTime;

    }

    public static String getDateTimeStringWithParameter(String time)
    {
        if(time.equals("")){
            return time;
        }
        String temp=  time.substring(8, 10)+ "/" + time.substring(5, 7) +"/" + time.substring(0, 4) ;
        return  temp;
    }


}
