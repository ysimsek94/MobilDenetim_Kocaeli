package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DenetimDenetleyen implements Serializable, Parcelable
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
    public final static Creator<DenetimDenetleyen> CREATOR = new Creator<DenetimDenetleyen>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DenetimDenetleyen createFromParcel(android.os.Parcel in) {
            return new DenetimDenetleyen(in);
        }

        public DenetimDenetleyen[] newArray(int size) {
            return (new DenetimDenetleyen[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4455698802058747398L;

    protected DenetimDenetleyen(android.os.Parcel in) {
        this.kullaniciId = ((int) in.readValue((int.class.getClassLoader())));
        this.denetimId = ((int) in.readValue((int.class.getClassLoader())));
        this.sicilNo = ((String) in.readValue((String.class.getClassLoader())));
        this.adi = ((String) in.readValue((String.class.getClassLoader())));
        this.soyadi = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public DenetimDenetleyen() {
    }

    /**
     *
     * @param denetimId
     * @param adi
     * @param soyadi
     * @param sicilNo
     * @param kullaniciId
     */
    public DenetimDenetleyen(int kullaniciId, int denetimId, String sicilNo, String adi, String soyadi) {
        super();
        this.kullaniciId = kullaniciId;
        this.denetimId = denetimId;
        this.sicilNo = sicilNo;
        this.adi = adi;
        this.soyadi = soyadi;
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