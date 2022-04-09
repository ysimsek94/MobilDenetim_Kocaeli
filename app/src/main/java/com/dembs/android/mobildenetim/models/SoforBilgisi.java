package com.dembs.android.mobildenetim.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SoforBilgisi implements Serializable, Parcelable
{

    @SerializedName("adi")
    @Expose
    private String adi;
    @SerializedName("soyadi")
    @Expose
    private String soyadi;
    @SerializedName("dogumTarihi")
    @Expose
    private String dogumTarihi;
    @SerializedName("tcVergiNo")
    @Expose
    private String tcVergiNo;
    @SerializedName("basvuruTuru")
    @Expose
    private String basvuruTuru;
    @SerializedName("gecerlilikTarihi")
    @Expose
    private String gecerlilikTarihi;
    @SerializedName("egitimMerkezi")
    @Expose
    private String egitimMerkezi;
    @SerializedName("sinif")
    @Expose
    private String sinif;
    @SerializedName("program")
    @Expose
    private String program;
    @SerializedName("donem")
    @Expose
    private String donem;
    @SerializedName("baslamaTarihi")
    @Expose
    private String baslamaTarihi;
    @SerializedName("bitisTarihi")
    @Expose
    private String bitisTarihi;
    private final static long serialVersionUID = 4578398519099001928L;

    /**
     * No args constructor for use in serialization
     *
     */
    public SoforBilgisi() {
    }

    /**
     *
     * @param sinif
     * @param egitimMerkezi
     * @param egitimProgrami
     * @param gecerlilikTarihi
     * @param dogumTarihi
     * @param bitisTarihi
     * @param adi
     * @param tcKimlikNo
     * @param soyadi
     * @param basvuruTuru
     * @param donemi
     * @param baslamaTarihi
     */
    public SoforBilgisi(String adi, String soyadi, String dogumTarihi, String tcKimlikNo, String basvuruTuru, String gecerlilikTarihi, String egitimMerkezi, String sinif, String egitimProgrami, String donemi, String baslamaTarihi, String bitisTarihi) {
        super();
        this.adi = adi;
        this.soyadi = soyadi;
        this.dogumTarihi = dogumTarihi;
        this.tcVergiNo = tcKimlikNo;
        this.basvuruTuru = basvuruTuru;
        this.gecerlilikTarihi = gecerlilikTarihi;
        this.egitimMerkezi = egitimMerkezi;
        this.sinif = sinif;
        this.program = egitimProgrami;
        this.donem = donemi;
        this.baslamaTarihi = baslamaTarihi;
        this.bitisTarihi = bitisTarihi;
    }

    protected SoforBilgisi(Parcel in) {
        adi = in.readString();
        soyadi = in.readString();
        dogumTarihi = in.readString();
        tcVergiNo = in.readString();
        basvuruTuru = in.readString();
        gecerlilikTarihi = in.readString();
        egitimMerkezi = in.readString();
        sinif = in.readString();
        program = in.readString();
        donem = in.readString();
        baslamaTarihi = in.readString();
        bitisTarihi = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(adi);
        dest.writeString(soyadi);
        dest.writeString(dogumTarihi);
        dest.writeString(tcVergiNo);
        dest.writeString(basvuruTuru);
        dest.writeString(gecerlilikTarihi);
        dest.writeString(egitimMerkezi);
        dest.writeString(sinif);
        dest.writeString(program);
        dest.writeString(donem);
        dest.writeString(baslamaTarihi);
        dest.writeString(bitisTarihi);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SoforBilgisi> CREATOR = new Creator<SoforBilgisi>() {
        @Override
        public SoforBilgisi createFromParcel(Parcel in) {
            return new SoforBilgisi(in);
        }

        @Override
        public SoforBilgisi[] newArray(int size) {
            return new SoforBilgisi[size];
        }
    };

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

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getTcKimlikNo() {
        return tcVergiNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcVergiNo = tcKimlikNo;
    }

    public String getBasvuruTuru() {
        return basvuruTuru;
    }

    public void setBasvuruTuru(String basvuruTuru) {
        this.basvuruTuru = basvuruTuru;
    }

    public String getGecerlilikTarihi() {
        return gecerlilikTarihi;
    }

    public void setGecerlilikTarihi(String gecerlilikTarihi) {
        this.gecerlilikTarihi = gecerlilikTarihi;
    }

    public String getEgitimMerkezi() {
        return egitimMerkezi;
    }

    public void setEgitimMerkezi(String egitimMerkezi) {
        this.egitimMerkezi = egitimMerkezi;
    }

    public String getSinif() {
        return sinif;
    }

    public void setSinif(String sinif) {
        this.sinif = sinif;
    }

    public String getEgitimProgrami() {
        return program;
    }

    public void setEgitimProgrami(String egitimProgrami) {
        this.program = egitimProgrami;
    }

    public String getDonemi() {
        return donem;
    }

    public void setDonemi(String donemi) {
        this.donem = donemi;
    }

    public String getBaslamaTarihi() {
        return baslamaTarihi;
    }

    public void setBaslamaTarihi(String baslamaTarihi) {
        this.baslamaTarihi = baslamaTarihi;
    }

    public String getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(String bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

}