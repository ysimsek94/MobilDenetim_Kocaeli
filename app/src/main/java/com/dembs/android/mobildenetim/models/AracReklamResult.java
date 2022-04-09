package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AracReklamResult implements Serializable, Parcelable
{

    @SerializedName("aracReklam")
    @Expose
    private AracReklam aracReklam;
    @SerializedName("arac")
    @Expose
    private Arac arac;
    public final static Creator<AracReklamResult> CREATOR = new Creator<AracReklamResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AracReklamResult createFromParcel(android.os.Parcel in) {
            return new AracReklamResult(in);
        }

        public AracReklamResult[] newArray(int size) {
            return (new AracReklamResult[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1586283964917509463L;

    protected AracReklamResult(android.os.Parcel in) {
        this.aracReklam = ((AracReklam) in.readValue((AracReklam.class.getClassLoader())));
        this.arac = ((Arac) in.readValue((Arac.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public AracReklamResult() {
    }

    /**
     *
     * @param aracReklam
     * @param arac
     */
    public AracReklamResult(AracReklam aracReklam, Arac arac) {
        super();
        this.aracReklam = aracReklam;
        this.arac = arac;
    }

    public AracReklam getAracReklam() {
        return aracReklam;
    }

    public void setAracReklam(AracReklam aracReklam) {
        this.aracReklam = aracReklam;
    }

    public Arac getArac() {
        return arac;
    }

    public void setArac(Arac arac) {
        this.arac = arac;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(aracReklam);
        dest.writeValue(arac);
    }

    public int describeContents() {
        return 0;
    }

}