package com.dembs.android.mobildenetim.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ClientConfigs {
    private static Retrofit retrofit=null;

    public static final String BASE_URL =  "http://ulasimapi.kocaeli.bel.tr/api/";//Kocaeli-public
    //public static final String BASE_URL =  "http://10.100.8.60:8034/api/";//Kocaeli-private



    public static Retrofit createRetrofit(Context ctxt) {
        SharedPreferences sharedPref = ctxt.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);

        String token = sharedPref.getString("token", null);

        Gson gson = new GsonBuilder().setLenient()
                .create();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + token)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .callTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES);

        retrofit = new Retrofit.Builder()
                .baseUrl(ClientConfigs.BASE_URL)
                .client(httpClient.build()) // add http client
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))//add gson converter
                .build();
        return retrofit;
    }

    public static Retrofit createRetrofitRxJava(Context ctxt) {
        SharedPreferences sharedPref = ctxt.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);

        String token = sharedPref.getString("token", null);

        Gson gson = new GsonBuilder().serializeNulls().create();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + token)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .callTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES);

        retrofit = new Retrofit.Builder()
                .baseUrl(ClientConfigs.BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// add http client
                .addConverterFactory(GsonConverterFactory.create(gson))//add gson converter
                .build();
        return retrofit;
    }

    public static String GetPrefStr(String KEY, Context cntx)
    {
        SharedPreferences sharedPref;
        sharedPref = cntx.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        return sharedPref.getString(KEY, null);
    }

}

