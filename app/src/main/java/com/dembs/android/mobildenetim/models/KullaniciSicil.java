package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KullaniciSicil implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("adi")
    @Expose
    private String adi;
    @SerializedName("soyadi")
    @Expose
    private String soyadi;
    @SerializedName("tcKimlikNo")
    @Expose
    private String tcKimlikNo;
    @SerializedName("sicilNo")
    @Expose
    private String sicilNo;
    public final static Creator<KullaniciSicil> CREATOR = new Creator<KullaniciSicil>() {


        @SuppressWarnings({
                "unchecked"
        })
        public KullaniciSicil createFromParcel(android.os.Parcel in) {
            return new KullaniciSicil(in);
        }

        public KullaniciSicil[] newArray(int size) {
            return (new KullaniciSicil[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6561149750927963055L;

    protected KullaniciSicil(android.os.Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.adi = ((String) in.readValue((String.class.getClassLoader())));
        this.soyadi = ((String) in.readValue((String.class.getClassLoader())));
        this.tcKimlikNo = ((String) in.readValue((String.class.getClassLoader())));
        this.sicilNo = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public KullaniciSicil() {
    }

    /**
     *
     * @param adi
     * @param tcKimlikNo
     * @param soyadi
     * @param id
     * @param sicilNo
     */
    public KullaniciSicil(int id, String adi, String soyadi, String tcKimlikNo, String sicilNo) {
        super();
        this.id = id;
        this.adi = adi;
        this.soyadi = soyadi;
        this.tcKimlikNo = tcKimlikNo;
        this.sicilNo = sicilNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public String getSicilNo() {
        return sicilNo;
    }

    public void setSicilNo(String sicilNo) {
        this.sicilNo = sicilNo;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(adi);
        dest.writeValue(soyadi);
        dest.writeValue(tcKimlikNo);
        dest.writeValue(sicilNo);
    }

    public int describeContents() {
        return 0;
    }

}