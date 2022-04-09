package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class BandrolLine implements Serializable, Parcelable
{

    @SerializedName("bandrolTuru")
    @Expose
    private String bandrolTuru;
    @SerializedName("ruhsatBandrol")
    @Expose
    private RuhsatBandrol ruhsatBandrol;
    public final static Creator<BandrolLine> CREATOR = new Creator<BandrolLine>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BandrolLine createFromParcel(android.os.Parcel in) {
            return new BandrolLine(in);
        }

        public BandrolLine[] newArray(int size) {
            return (new BandrolLine[size]);
        }

    }
            ;
    private final static long serialVersionUID = 3017061053711677926L;

    protected BandrolLine(android.os.Parcel in) {
        this.bandrolTuru = ((String) in.readValue((String.class.getClassLoader())));
        this.ruhsatBandrol = ((RuhsatBandrol) in.readValue((RuhsatBandrol.class.getClassLoader())));
    }

    public BandrolLine() {
    }

    public String getBandrolTuru() {
        return bandrolTuru;
    }

    public void setBandrolTuru(String bandrolTuru) {
        this.bandrolTuru = bandrolTuru;
    }

    public RuhsatBandrol getRuhsatBandrol() {
        return ruhsatBandrol;
    }

    public void setRuhsatBandrol(RuhsatBandrol ruhsatBandrol) {
        this.ruhsatBandrol = ruhsatBandrol;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(bandrolTuru);
        dest.writeValue(ruhsatBandrol);
    }

    public int describeContents() {
        return 0;
    }

}