package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Guzergah implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("belgeId")
    @Expose
    private int belgeId;
    @SerializedName("adi")
    @Expose
    private String adi;
    @SerializedName("islemTarihi")
    @Expose
    private String islemTarihi;
    @SerializedName("kayitEden")
    @Expose
    private String kayitEden;
    public final static Creator<Guzergah> CREATOR = new Creator<Guzergah>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Guzergah createFromParcel(android.os.Parcel in) {
            return new Guzergah(in);
        }

        public Guzergah[] newArray(int size) {
            return (new Guzergah[size]);
        }

    }
            ;
    private final static long serialVersionUID = -2732213918637272842L;

    protected Guzergah(android.os.Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.belgeId = ((int) in.readValue((int.class.getClassLoader())));
        this.adi = ((String) in.readValue((String.class.getClassLoader())));
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Guzergah() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBelgeId() {
        return belgeId;
    }

    public void setBelgeId(int belgeId) {
        this.belgeId = belgeId;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
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
        dest.writeValue(belgeId);
        dest.writeValue(adi);
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
    }

    public int describeContents() {
        return 0;
    }

}