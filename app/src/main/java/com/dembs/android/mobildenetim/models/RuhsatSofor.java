package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RuhsatSofor implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("tipId")
    @Expose
    private int tipId;
    @SerializedName("kisiId")
    @Expose
    private int kisiId;
    @SerializedName("detayId")
    @Expose
    private int detayId;
    @SerializedName("islemTarihi")
    @Expose
    private String islemTarihi;
    @SerializedName("kayitEden")
    @Expose
    private String kayitEden;
    public final static Creator<RuhsatSofor> CREATOR = new Creator<RuhsatSofor>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RuhsatSofor createFromParcel(android.os.Parcel in) {
            return new RuhsatSofor(in);
        }

        public RuhsatSofor[] newArray(int size) {
            return (new RuhsatSofor[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5294820247161029159L;

    protected RuhsatSofor(android.os.Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.tipId = ((int) in.readValue((int.class.getClassLoader())));
        this.kisiId = ((int) in.readValue((int.class.getClassLoader())));
        this.detayId = ((int) in.readValue((int.class.getClassLoader())));
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RuhsatSofor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipId() {
        return tipId;
    }

    public void setTipId(int tipId) {
        this.tipId = tipId;
    }

    public int getKisiId() {
        return kisiId;
    }

    public void setKisiId(int kisiId) {
        this.kisiId = kisiId;
    }

    public int getDetayId() {
        return detayId;
    }

    public void setDetayId(int detayId) {
        this.detayId = detayId;
    }

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    public String getKayitEden() {
        return kayitEden;
    }

    public void setKayitEden(String kayitEden) {
        this.kayitEden = kayitEden;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(tipId);
        dest.writeValue(kisiId);
        dest.writeValue(detayId);
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
    }

    public int describeContents() {
        return 0;
    }

}