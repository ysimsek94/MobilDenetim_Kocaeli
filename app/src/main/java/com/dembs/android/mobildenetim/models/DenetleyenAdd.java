package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DenetleyenAdd implements Serializable, Parcelable
{

    @SerializedName("islemTarihi")
    @Expose
    private String islemTarihi;
    @SerializedName("kayitEden")
    @Expose
    private String kayitEden;
    @SerializedName("denetimId")
    @Expose
    private int denetimId;
    @SerializedName("kullaniciId")
    @Expose
    private int kullaniciId;
    public final static Creator<DenetleyenAdd> CREATOR = new Creator<DenetleyenAdd>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DenetleyenAdd createFromParcel(android.os.Parcel in) {
            return new DenetleyenAdd(in);
        }

        public DenetleyenAdd[] newArray(int size) {
            return (new DenetleyenAdd[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6320888457846586127L;

    protected DenetleyenAdd(android.os.Parcel in) {
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
        this.denetimId = ((int) in.readValue((int.class.getClassLoader())));
        this.kullaniciId = ((int) in.readValue((int.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public DenetleyenAdd() {
    }

    /**
     *
     * @param islemTarihi
     * @param denetimId
     * @param kayitEden
     * @param kullaniciId
     */
    public DenetleyenAdd(String islemTarihi, String kayitEden, int denetimId, int kullaniciId) {
        super();
        this.islemTarihi = islemTarihi;
        this.kayitEden = kayitEden;
        this.denetimId = denetimId;
        this.kullaniciId = kullaniciId;
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

    public int getDenetimId() {
        return denetimId;
    }

    public void setDenetimId(int denetimId) {
        this.denetimId = denetimId;
    }

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
        dest.writeValue(denetimId);
        dest.writeValue(kullaniciId);
    }

    public int describeContents() {
        return 0;
    }

}