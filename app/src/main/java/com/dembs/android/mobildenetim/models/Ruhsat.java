package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Ruhsat implements Serializable, Parcelable
{

    @SerializedName("plaka")
    @Expose
    private String plaka;
    @SerializedName("ruhsatNo")
    @Expose
    private String ruhsatNo;
    @SerializedName("ruhsatTipId")
    @Expose
    private int ruhsatTipId;
    @SerializedName("ruhsatId")
    @Expose
    private int ruhsatId;
    @SerializedName("detayId")
    @Expose
    private int detayId;
    @SerializedName("adi")
    @Expose
    private String adi;
    @SerializedName("soyadi")
    @Expose
    private String soyadi;
    @SerializedName("islemTurId")
    @Expose
    private int islemTurId;
    @SerializedName("islemTurAdi")
    @Expose
    private String islemTurAdi;
    @SerializedName("kisiId")
    @Expose
    private int kisiId;
    @SerializedName("aracId")
    @Expose
    private int aracId;
    @SerializedName("kisiTurId")
    @Expose
    private int kisiTurId;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("aktif")
    @Expose
    private boolean aktif;
    @SerializedName("ybsNo")
    @Expose
    private String ybsNo;
    public final static Creator<Ruhsat> CREATOR = new Creator<Ruhsat>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Ruhsat createFromParcel(android.os.Parcel in) {
            return new Ruhsat(in);
        }

        public Ruhsat[] newArray(int size) {
            return (new Ruhsat[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1580379441584271705L;

    protected Ruhsat(android.os.Parcel in) {
        this.plaka = ((String) in.readValue((String.class.getClassLoader())));
        this.ruhsatNo = ((String) in.readValue((String.class.getClassLoader())));
        this.ruhsatTipId = ((int) in.readValue((int.class.getClassLoader())));
        this.ruhsatId = ((int) in.readValue((int.class.getClassLoader())));
        this.detayId = ((int) in.readValue((int.class.getClassLoader())));
        this.adi = ((String) in.readValue((String.class.getClassLoader())));
        this.soyadi = ((String) in.readValue((String.class.getClassLoader())));
        this.islemTurId = ((int) in.readValue((int.class.getClassLoader())));
        this.islemTurAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.kisiId = ((int) in.readValue((int.class.getClassLoader())));
        this.aracId = ((int) in.readValue((int.class.getClassLoader())));
        this.kisiTurId = ((int) in.readValue((int.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.aktif = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.ybsNo = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Ruhsat() {
    }

    /**
     *
     * @param ruhsatId
     * @param plaka
     * @param adi
     * @param ruhsatTipId
     * @param islemTurId
     * @param aktif
     * @param aciklama
     * @param soyadi
     * @param ruhsatNo
     * @param islemTurAdi
     * @param kisiId
     * @param ybsNo
     * @param kisiTurId
     * @param detayId
     */
    public Ruhsat(String plaka, String ruhsatNo, int ruhsatTipId, int ruhsatId, int detayId, String adi, String soyadi, int islemTurId, String islemTurAdi, int kisiId,int aracId, int kisiTurId, String aciklama, boolean aktif, String ybsNo) {
        super();
        this.plaka = plaka;
        this.ruhsatNo = ruhsatNo;
        this.ruhsatTipId = ruhsatTipId;
        this.ruhsatId = ruhsatId;
        this.detayId = detayId;
        this.adi = adi;
        this.soyadi = soyadi;
        this.islemTurId = islemTurId;
        this.islemTurAdi = islemTurAdi;
        this.kisiId = kisiId;
        this.aracId = aracId;
        this.kisiTurId = kisiTurId;
        this.aciklama = aciklama;
        this.aktif = aktif;
        this.ybsNo = ybsNo;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public String getRuhsatNo() {
        return ruhsatNo;
    }

    public void setRuhsatNo(String ruhsatNo) {
        this.ruhsatNo = ruhsatNo;
    }

    public int getRuhsatTipId() {
        return ruhsatTipId;
    }

    public void setRuhsatTipId(int ruhsatTipId) {
        this.ruhsatTipId = ruhsatTipId;
    }

    public int getRuhsatId() {
        return ruhsatId;
    }

    public void setRuhsatId(int ruhsatId) {
        this.ruhsatId = ruhsatId;
    }

    public int getDetayId() {
        return detayId;
    }

    public void setDetayId(int detayId) {
        this.detayId = detayId;
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

    public int getIslemTurId() {
        return islemTurId;
    }

    public void setIslemTurId(int islemTurId) {
        this.islemTurId = islemTurId;
    }

    public String getIslemTurAdi() {
        return islemTurAdi;
    }

    public void setIslemTurAdi(String islemTurAdi) {
        this.islemTurAdi = islemTurAdi;
    }

    public int getKisiId() {
        return kisiId;
    }

    public void setKisiId(int kisiId) {
        this.kisiId = kisiId;
    }

    public int getAracId() {
        return aracId;
    }

    public void setAracId(int kisiId) {
        this.aracId = kisiId;
    }

    public int getKisiTurId() {
        return kisiTurId;
    }

    public void setKisiTurId(int kisiTurId) {
        this.kisiTurId = kisiTurId;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    public String getYbsNo() {
        return ybsNo;
    }

    public void setYbsNo(String ybsNo) {
        this.ybsNo = ybsNo;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(plaka);
        dest.writeValue(ruhsatNo);
        dest.writeValue(ruhsatTipId);
        dest.writeValue(ruhsatId);
        dest.writeValue(detayId);
        dest.writeValue(adi);
        dest.writeValue(soyadi);
        dest.writeValue(islemTurId);
        dest.writeValue(islemTurAdi);
        dest.writeValue(kisiId);
        dest.writeValue(aracId);
        dest.writeValue(kisiTurId);
        dest.writeValue(aciklama);
        dest.writeValue(aktif);
        dest.writeValue(ybsNo);
    }

    public int describeContents() {
        return 0;
    }

}