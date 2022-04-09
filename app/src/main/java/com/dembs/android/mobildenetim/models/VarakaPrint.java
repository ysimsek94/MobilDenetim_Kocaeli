package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VarakaPrint implements Serializable, Parcelable {

    @SerializedName("denetimId")
    @Expose
    private int denetimId;
    @SerializedName("varakaNo")
    @Expose
    private String varakaNo;
    @SerializedName("tcKimlikNo")
    @Expose
    private String tcKimlikNo;
    @SerializedName("varakaId")
    @Expose
    private int varakaId;
    @SerializedName("kisiId")
    @Expose
    private int kisiId;
    @SerializedName("adSoyad")
    @Expose
    private String adSoyad;
    @SerializedName("surucuTc")
    @Expose
    private String surucuTc;
    @SerializedName("surucuAdi")
    @Expose
    private String surucuAdi;
    @SerializedName("ruhsatNo")
    @Expose
    private String ruhsatNo;
    @SerializedName("plaka")
    @Expose
    private String plaka;
    @SerializedName("ikametAdres")
    @Expose
    private String ikametAdres;
    @SerializedName("ihlalAdres")
    @Expose
    private String ihlalAdres;
    @SerializedName("ihlalTarihi")
    @Expose
    private String ihlalTarihi;
    @SerializedName("ihlalSaat")
    @Expose
    private String ihlalSaat;
    @SerializedName("ihlalMaddeAdi")
    @Expose
    private String ihlalMaddeAdi;
    @SerializedName("ihlalBendAdi")
    @Expose
    private String ihlalBendAdi;
    @SerializedName("ihlalBendAciklama")
    @Expose
    private String ihlalBendAciklama;
    @SerializedName("maddeId")
    @Expose
    private int maddeId;
    @SerializedName("bendId")
    @Expose
    private int bendId;
    @SerializedName("varakaAciklama")
    @Expose
    private String varakaAciklama;
    @SerializedName("mudur")
    @Expose
    private String mudur;
    @SerializedName("mudurUnvan")
    @Expose
    private String mudurUnvan;
    @SerializedName("kullaniciSicilList")
    @Expose
    private List<KullaniciSicil> kullaniciSicilList = null;
    @SerializedName("telNo")
    @Expose
    private String telNo;
    public final static Creator<VarakaPrint> CREATOR = new Creator<VarakaPrint>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VarakaPrint createFromParcel(android.os.Parcel in) {
            return new VarakaPrint(in);
        }

        public VarakaPrint[] newArray(int size) {
            return (new VarakaPrint[size]);
        }

    };
    private final static long serialVersionUID = 4218760702081254487L;

    protected VarakaPrint(android.os.Parcel in) {
        this.denetimId = ((int) in.readValue((int.class.getClassLoader())));
        this.varakaNo = ((String) in.readValue((String.class.getClassLoader())));
        this.tcKimlikNo = ((String) in.readValue((String.class.getClassLoader())));
        this.varakaId = ((int) in.readValue((int.class.getClassLoader())));
        this.kisiId = ((int) in.readValue((int.class.getClassLoader())));
        this.adSoyad = ((String) in.readValue((String.class.getClassLoader())));
        this.surucuTc = ((String) in.readValue((String.class.getClassLoader())));
        this.surucuAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.ruhsatNo = ((String) in.readValue((String.class.getClassLoader())));
        this.plaka = ((String) in.readValue((String.class.getClassLoader())));
        this.ikametAdres = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalAdres = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalSaat = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalMaddeAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalBendAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalBendAciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.maddeId = ((int) in.readValue((int.class.getClassLoader())));
        this.bendId = ((int) in.readValue((int.class.getClassLoader())));
        this.varakaAciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.mudur = ((String) in.readValue((String.class.getClassLoader())));
        this.mudurUnvan = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.kullaniciSicilList, (com.dembs.android.mobildenetim.models.KullaniciSicil.class.getClassLoader()));
        this.telNo = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public VarakaPrint() {
    }

    /**
     * @param varakaAciklama
     * @param ihlalTarihi
     * @param ihlalBendAciklama
     * @param tcKimlikNo
     * @param plaka
     * @param kullaniciSicilList
     * @param varakaNo
     * @param bendId
     * @param ihlalBendAdi
     * @param surucuTc
     * @param ihlalMaddeAdi
     * @param mudur
     * @param surucuAdi
     * @param maddeId
     * @param ihlalSaat
     * @param ihlalAdres
     * @param mudurUnvan
     * @param denetimId
     * @param ikametAdres
     * @param adSoyad
     * @param ruhsatNo
     */
    public VarakaPrint(int denetimId, String varakaNo, String tcKimlikNo,int varakaId, String adSoyad, String surucuTc, int kisiId, String surucuAdi, String ruhsatNo, String plaka, String ikametAdres, String ihlalAdres, String ihlalTarihi, String ihlalSaat, String ihlalMaddeAdi, String ihlalBendAdi, String ihlalBendAciklama, int maddeId, int bendId, String varakaAciklama, String mudur, String mudurUnvan, List<KullaniciSicil> kullaniciSicilList,String telNo) {
        super();
        this.denetimId = denetimId;
        this.varakaNo = varakaNo;
        this.tcKimlikNo = tcKimlikNo;
        this.varakaId=varakaId;
        this.kisiId = kisiId;
        this.adSoyad = adSoyad;
        this.surucuTc = surucuTc;
        this.surucuAdi = surucuAdi;
        this.ruhsatNo = ruhsatNo;
        this.plaka = plaka;
        this.ikametAdres = ikametAdres;
        this.ihlalAdres = ihlalAdres;
        this.ihlalTarihi = ihlalTarihi;
        this.ihlalSaat = ihlalSaat;
        this.ihlalMaddeAdi = ihlalMaddeAdi;
        this.ihlalBendAdi = ihlalBendAdi;
        this.ihlalBendAciklama = ihlalBendAciklama;
        this.maddeId = maddeId;
        this.bendId = bendId;
        this.varakaAciklama = varakaAciklama;
        this.mudur = mudur;
        this.mudurUnvan = mudurUnvan;
        this.kullaniciSicilList = kullaniciSicilList;
        this.telNo=telNo;
    }

    public int getDenetimId() {
        return denetimId;
    }

    public void setDenetimId(int denetimId) {
        this.denetimId = denetimId;
    }

    public String getVarakaNo() {
        return varakaNo;
    }

    public void setVarakaNo(String varakaNo) {
        this.varakaNo = varakaNo;
    }

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public int getVarakaId() {
        return varakaId;
    }

    public void setVarakaId(int varakaId) {
        this.varakaId = varakaId;
    }

    public int getKisiId() {
        return kisiId;
    }

    public void setKisiId(int kisiId) {
        this.kisiId = kisiId;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getSurucuTc() {
        return surucuTc;
    }

    public void setSurucuTc(String surucuTc) {
        this.surucuTc = surucuTc;
    }

    public String getSurucuAdi() {
        return surucuAdi;
    }

    public void setSurucuAdi(String surucuAdi) {
        this.surucuAdi = surucuAdi;
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

    public String getIkametAdres() {
        return ikametAdres;
    }

    public void setIkametAdres(String ikametAdres) {
        this.ikametAdres = ikametAdres;
    }

    public String getIhlalAdres() {
        return ihlalAdres;
    }

    public void setIhlalAdres(String ihlalAdres) {
        this.ihlalAdres = ihlalAdres;
    }

    public String getIhlalTarihi() {
        return ihlalTarihi;
    }

    public void setIhlalTarihi(String ihlalTarihi) {
        this.ihlalTarihi = ihlalTarihi;
    }

    public String getIhlalSaat() {
        return ihlalSaat;
    }

    public void setIhlalSaat(String ihlalSaat) {
        this.ihlalSaat = ihlalSaat;
    }

    public String getIhlalMaddeAdi() {
        return ihlalMaddeAdi;
    }

    public void setIhlalMaddeAdi(String ihlalMaddeAdi) {
        this.ihlalMaddeAdi = ihlalMaddeAdi;
    }

    public String getIhlalBendAdi() {
        return ihlalBendAdi;
    }

    public void setIhlalBendAdi(String ihlalBendAdi) {
        this.ihlalBendAdi = ihlalBendAdi;
    }

    public String getIhlalBendAciklama() {
        return ihlalBendAciklama;
    }

    public void setIhlalBendAciklama(String ihlalBendAciklama) {
        this.ihlalBendAciklama = ihlalBendAciklama;
    }

    public int getMaddeId() {
        return maddeId;
    }

    public void setMaddeId(int maddeId) {
        this.maddeId = maddeId;
    }

    public int getBendId() {
        return bendId;
    }

    public void setBendId(int bendId) {
        this.bendId = bendId;
    }

    public String getVarakaAciklama() {
        return varakaAciklama;
    }

    public void setVarakaAciklama(String varakaAciklama) {
        this.varakaAciklama = varakaAciklama;
    }

    public String getMudur() {
        return mudur;
    }

    public void setMudur(String mudur) {
        this.mudur = mudur;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getMudurUnvan() {
        return mudurUnvan;
    }

    public void setMudurUnvan(String mudurUnvan) {
        this.mudurUnvan = mudurUnvan;
    }

    public List<KullaniciSicil> getKullaniciSicilList() {
        return kullaniciSicilList;
    }

    public void setKullaniciSicilList(List<KullaniciSicil> kullaniciSicilList) {
        this.kullaniciSicilList = kullaniciSicilList;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(denetimId);
        dest.writeValue(varakaNo);
        dest.writeValue(tcKimlikNo);
        dest.writeValue(varakaId);
        dest.writeValue(adSoyad);
        dest.writeValue(surucuTc);
        dest.writeValue(kisiId);
        dest.writeValue(surucuAdi);
        dest.writeValue(ruhsatNo);
        dest.writeValue(plaka);
        dest.writeValue(ikametAdres);
        dest.writeValue(ihlalAdres);
        dest.writeValue(ihlalTarihi);
        dest.writeValue(ihlalSaat);
        dest.writeValue(ihlalMaddeAdi);
        dest.writeValue(ihlalBendAdi);
        dest.writeValue(ihlalBendAciklama);
        dest.writeValue(maddeId);
        dest.writeValue(bendId);
        dest.writeValue(varakaAciklama);
        dest.writeValue(mudur);
        dest.writeValue(mudurUnvan);
        dest.writeList(kullaniciSicilList);
        dest.writeValue(telNo);
    }

    public int describeContents() {
        return 0;
    }

}