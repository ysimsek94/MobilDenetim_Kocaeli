package com.dembs.android.mobildenetim.utils;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AppManager {

   public Toolbar  GetToolBar(View view, int as, @Nullable String subTitle)
    {
        int id=as;
        Toolbar toolbar;
        toolbar =view.findViewById(id);
        toolbar.setTitle("Mobil Ceza & Denetim");
        toolbar.setSubtitle(subTitle);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        return toolbar;
    }

    public static boolean isGPSEnabled(Context context) {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }
    static String getAddressFromLatLong(Context context,double latitude, double longitude) {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);

                result.append(address.getSubLocality()).append(" Mah. ");//mahalle
                result.append(address.getThoroughfare()).append("\n");//mahalle
                result.append(address.getFeatureName()).append("\n");//no
                result.append(address.getSubAdminArea()).append("/");//ilçe
                result.append(address.getAdminArea()).append("/");//il
                result.append(address.getCountryName());// ülke

                //progressBar.setVisibility(View.GONE);
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();
    }
}
