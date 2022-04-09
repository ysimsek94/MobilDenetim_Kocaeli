package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class BelgeTasinanKurum implements Serializable, Parcelable
{

    @SerializedName("kurumId")
    @Expose
    private int kurumId;
    @SerializedName("belgeId")
    @Expose
    private int belgeId;
    @SerializedName("islemTarihi")
    @Expose
    private String islemTarihi;
    @SerializedName("kayitEden")
    @Expose
    private String kayitEden;
    public final static Creator<BelgeTasinanKurum> CREATOR = new Creator<BelgeTasinanKurum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BelgeTasinanKurum createFromParcel(android.os.Parcel in) {
            return new BelgeTasinanKurum(in);
        }

        public BelgeTasinanKurum[] newArray(int size) {
            return (new BelgeTasinanKurum[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3766904174677279171L;

    protected BelgeTasinanKurum(android.os.Parcel in) {
        this.kurumId = ((int) in.readValue((int.class.getClassLoader())));
        this.belgeId = ((int) in.readValue((int.class.getClassLoader())));
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public BelgeTasinanKurum() {
    }

    /**
     *
     * @param kurumId
     * @param islemTarihi
     * @param kayitEden
     * @param belgeId
     */
    public BelgeTasinanKurum(int kurumId, int belgeId, String islemTarihi, String kayitEden) {
        super();
        this.kurumId = kurumId;
        this.belgeId = belgeId;
        this.islemTarihi = islemTarihi;
        this.kayitEden = kayitEden;
    }

    public int getKurumId() {
        return kurumId;
    }

    public void setKurumId(int kurumId) {
        this.kurumId = kurumId;
    }

    public int getBelgeId() {
        return belgeId;
    }

    public void setBelgeId(int belgeId) {
        this.belgeId = belgeId;
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
        dest.writeValue(kurumId);
        dest.writeValue(belgeId);
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
    }

    public int describeContents() {
        return 0;
    }

}