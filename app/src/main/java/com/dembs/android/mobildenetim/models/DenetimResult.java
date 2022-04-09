package com.dembs.android.mobildenetim.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DenetimResult implements Serializable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("denetimTurId")
    @Expose
    private int denetimTurId;
    @SerializedName("detayId")
    @Expose
    private int detayId;
    @SerializedName("ruhsatNo")
    @Expose
    private String ruhsatNo;
    @SerializedName("plaka")
    @Expose
    private String plaka;
    @SerializedName("hatKodu")
    @Expose
    private String hatKodu;
    @SerializedName("durakId")
    @Expose
    private int durakId;
    @SerializedName("durakKodu")
    @Expose
    private String durakKodu;
    @SerializedName("durakAdi")
    @Expose
    private String durakAdi;
    @SerializedName("denetimTarihi")
    @Expose
    private String denetimTarihi;
    @SerializedName("adres1")
    @Expose
    private String adres1;
    @SerializedName("ilceAdi")
    @Expose
    private String ilceAdi;
    @SerializedName("ilceId")
    @Expose
    private int ilceId;
    @SerializedName("mahalleAdi")
    @Expose
    private String mahalleAdi;
    @SerializedName("mahalleId")
    @Expose
    private int mahalleId;
    @SerializedName("sokakAdi")
    @Expose
    private String sokakAdi;
    @SerializedName("sokakId")
    @Expose
    private int sokakId;
    @SerializedName("surucuAdi")
    @Expose
    private String surucuAdi;
    @SerializedName("surucuTcNo")
    @Expose
    private String surucuTcNo;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("durumId")
    @Expose
    private int durumId;
    @SerializedName("iptal")
    @Expose
    private String iptal;
    @SerializedName("tip")
    @Expose
    private String tip;
    @SerializedName("kayitEden")
    @Expose
    private String kayitEden;
    @SerializedName("islemTarihi")
    @Expose
    private String islemTarihi;
    @SerializedName("x_koordinat")
    @Expose
    private float xKoordinat;
    @SerializedName("y_koordinat")
    @Expose
    private float yKoordinat;
    private final static long serialVersionUID = -1947619085157858479L;

    /**
     * No args constructor for use in serialization
     *
     */
    public DenetimResult() {
    }

    /**
     *
     * @param durakAdi
     * @param durumId
     * @param adres1
     * @param surucuTcNo
     * @param ilceId
     * @param kayitEden
     * @param aciklama
     * @param mahalleId
     * @param xKoordinat
     * @param mahalleAdi
     * @param tip
     * @param id
     * @param ruhsatNo
     * @param sokakId
     * @param detayId
     * @param hatKodu
     * @param iptal
     * @param islemTarihi
     * @param plaka
     * @param yKoordinat
     * @param durakId
     * @param denetimTarihi
     * @param surucuAdi
     * @param sokakAdi
     * @param denetimTurId
     * @param durakKodu
     * @param ilceAdi
     */
    public DenetimResult(int id, int denetimTurId, int detayId, String ruhsatNo, String plaka, String hatKodu, int durakId, String durakKodu, String durakAdi, String denetimTarihi, String adres1, String ilceAdi, int ilceId, String mahalleAdi, int mahalleId, String sokakAdi, int sokakId, String surucuAdi, String surucuTcNo, String aciklama, int durumId, String iptal, String tip, String kayitEden, String islemTarihi, float xKoordinat, float yKoordinat) {
        super();
        this.id = id;
        this.denetimTurId = denetimTurId;
        this.detayId = detayId;
        this.ruhsatNo = ruhsatNo;
        this.plaka = plaka;
        this.hatKodu = hatKodu;
        this.durakId = durakId;
        this.durakKodu = durakKodu;
        this.durakAdi = durakAdi;
        this.denetimTarihi = denetimTarihi;
        this.adres1 = adres1;
        this.ilceAdi = ilceAdi;
        this.ilceId = ilceId;
        this.mahalleAdi = mahalleAdi;
        this.mahalleId = mahalleId;
        this.sokakAdi = sokakAdi;
        this.sokakId = sokakId;
        this.surucuAdi = surucuAdi;
        this.surucuTcNo = surucuTcNo;
        this.aciklama = aciklama;
        this.durumId = durumId;
        this.iptal = iptal;
        this.tip = tip;
        this.kayitEden = kayitEden;
        this.islemTarihi = islemTarihi;
        this.xKoordinat = xKoordinat;
        this.yKoordinat = yKoordinat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDenetimTurId() {
        return denetimTurId;
    }

    public void setDenetimTurId(int denetimTurId) {
        this.denetimTurId = denetimTurId;
    }

    public int getDetayId() {
        return detayId;
    }

    public void setDetayId(int detayId) {
        this.detayId = detayId;
    }

    public String getRuhsatNo() {
        return ruhsatNo;
    }

    public void setRuhsatNo(String ruhsatNo) {
        this.ruhsatNo = ruhsatNo;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public String getHatKodu() {
        return hatKodu;
    }

    public void setHatKodu(String hatKodu) {
        this.hatKodu = hatKodu;
    }

    public int getDurakId() {
        return durakId;
    }

    public void setDurakId(int durakId) {
        this.durakId = durakId;
    }

    public String getDurakKodu() {
        return durakKodu;
    }

    public void setDurakKodu(String durakKodu) {
        this.durakKodu = durakKodu;
    }

    public String getDurakAdi() {
        return durakAdi;
    }

    public void setDurakAdi(String durakAdi) {
        this.durakAdi = durakAdi;
    }

    public String getDenetimTarihi() {
        return denetimTarihi;
    }

    public void setDenetimTarihi(String denetimTarihi) {
        this.denetimTarihi = denetimTarihi;
    }

    public String getAdres1() {
        return adres1;
    }

    public void setAdres1(String adres1) {
        this.adres1 = adres1;
    }

    public String getIlceAdi() {
        return ilceAdi;
    }

    public void setIlceAdi(String ilceAdi) {
        this.ilceAdi = ilceAdi;
    }

    public int getIlceId() {
        return ilceId;
    }

    public void setIlceId(int ilceId) {
        this.ilceId = ilceId;
    }

    public String getMahalleAdi() {
        return mahalleAdi;
    }

    public void setMahalleAdi(String mahalleAdi) {
        this.mahalleAdi = mahalleAdi;
    }

    public int getMahalleId() {
        return mahalleId;
    }

    public void setMahalleId(int mahalleId) {
        this.mahalleId = mahalleId;
    }

    public String getSokakAdi() {
        return sokakAdi;
    }

    public void setSokakAdi(String sokakAdi) {
        this.sokakAdi = sokakAdi;
    }

    public int getSokakId() {
        return sokakId;
    }

    public void setSokakId(int sokakId) {
        this.sokakId = sokakId;
    }

    public String getSurucuAdi() {
        return surucuAdi;
    }

    public void setSurucuAdi(String surucuAdi) {
        this.surucuAdi = surucuAdi;
    }

    public String getSurucuTcNo() {
        return surucuTcNo;
    }

    public void setSurucuTcNo(String surucuTcNo) {
        this.surucuTcNo = surucuTcNo;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getDurumId() {
        return durumId;
    }

    public void setDurumId(int durumId) {
        this.durumId = durumId;
    }

    public String getIptal() {
        return iptal;
    }

    public void setIptal(String iptal) {
        this.iptal = iptal;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getKayitEden() {
        return kayitEden;
    }

    public void setKayitEden(String kayitEden) {
        this.kayitEden = kayitEden;
    }

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    public float getXKoordinat() {
        return xKoordinat;
    }

    public void setXKoordinat(float xKoordinat) {
        this.xKoordinat = xKoordinat;
    }

    public float getYKoordinat() {
        return yKoordinat;
    }

    public void setYKoordinat(float yKoordinat) {
        this.yKoordinat = yKoordinat;
    }

}