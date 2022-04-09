package com.dembs.android.mobildenetim.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VarakaBekleyen implements Serializable, Parcelable
{

    @SerializedName("ruhsatNo")
    @Expose
    private String ruhsatNo;
    @SerializedName("adet")
    @Expose
    private int adet;
    private final static long serialVersionUID = -4391308961163652433L;

    public VarakaBekleyen() {
    }

    public VarakaBekleyen(String ruhsatNo, int adet) {
        super();
        this.ruhsatNo = ruhsatNo;
        this.adet = adet;
    }

    protected VarakaBekleyen(Parcel in) {
        ruhsatNo = in.readString();
        adet = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ruhsatNo);
        dest.writeInt(adet);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VarakaBekleyen> CREATOR = new Creator<VarakaBekleyen>() {
        @Override
        public VarakaBekleyen createFromParcel(Parcel in) {
            return new VarakaBekleyen(in);
        }

        @Override
        public VarakaBekleyen[] newArray(int size) {
            return new VarakaBekleyen[size];
        }
    };

    public String getRuhsatNo() {
        return ruhsatNo;
    }

    public void setRuhsatNo(String ruhsatNo) {
        this.ruhsatNo = ruhsatNo;
    }

    public VarakaBekleyen withRuhsatNo(String ruhsatNo) {
        this.ruhsatNo = ruhsatNo;
        return this;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public VarakaBekleyen withAdet(int adet) {
        this.adet = adet;
        return this;
    }

}