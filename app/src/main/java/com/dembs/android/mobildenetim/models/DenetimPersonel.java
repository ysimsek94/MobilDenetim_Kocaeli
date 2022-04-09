package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class DenetimPersonel implements Serializable, Parcelable
{

    @SerializedName("kullaniciId")
    @Expose
    private int kullaniciId;
    @SerializedName("denetimId")
    @Expose
    private int denetimId;
    @SerializedName("sicilNo")
    @Expose
    private String sicilNo;
    @SerializedName("adi")
    @Expose
    private String adi;
    @SerializedName("soyadi")
    @Expose
    private String soyadi;
    public final static Creator<DenetimPersonel> CREATOR = new Creator<DenetimPersonel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DenetimPersonel createFromParcel(android.os.Parcel in) {
            return new DenetimPersonel(in);
        }

        public DenetimPersonel[] newArray(int size) {
            return (new DenetimPersonel[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6087863775013901228L;

    protected DenetimPersonel(android.os.Parcel in) {
        this.kullaniciId = ((int) in.readValue((int.class.getClassLoader())));
        this.denetimId = ((int) in.readValue((int.class.getClassLoader())));
        this.sicilNo = ((String) in.readValue((String.class.getClassLoader())));
        this.adi = ((String) in.readValue((String.class.getClassLoader())));
        this.soyadi = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DenetimPersonel() {
    }

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public int getDenetimId() {
        return denetimId;
    }

    public void setDenetimId(int denetimId) {
        this.denetimId = denetimId;
    }

    public String getSicilNo() {
        return sicilNo;
    }

    public void setSicilNo(String sicilNo) {
        this.sicilNo = sicilNo;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(kullaniciId);
        dest.writeValue(denetimId);
        dest.writeValue(sicilNo);
        dest.writeValue(adi);
        dest.writeValue(soyadi);
    }

    public int describeContents() {
        return 0;
    }

}