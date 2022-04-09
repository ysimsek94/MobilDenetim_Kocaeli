package com.dembs.android.mobildenetim.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DenetimDenetleyenRapor implements Serializable
{

    @SerializedName("denetimId")
    @Expose
    private int denetimId;
    @SerializedName("denetimTuru")
    @Expose
    private String denetimTuru;
    @SerializedName("ilceAdi")
    @Expose
    private String ilceAdi;
    @SerializedName("ruhsatNo")
    @Expose
    private String ruhsatNo;
    @SerializedName("plaka")
    @Expose
    private String plaka;
    @SerializedName("varakaAdet")
    @Expose
    private int varakaAdet;
    @SerializedName("ekipAdi")
    @Expose
    private String ekipAdi;
    @SerializedName("denetimTarihi")
    @Expose
    private String denetimTarihi;
    private final static long serialVersionUID = 4843482541482872084L;

    /**
     * No args constructor for use in serialization
     *
     */
    public DenetimDenetleyenRapor() {
    }

    /**
     *
     * @param denetimTuru
     * @param varakaAdet
     * @param denetimId
     * @param plaka
     * @param ilceAdi
     * @param ruhsatNo
     * @param ekipAdi
     * @param denetimTarihi
     */
    public DenetimDenetleyenRapor(int denetimId, String denetimTuru, String ilceAdi, String ruhsatNo, String plaka, int varakaAdet, String ekipAdi, String denetimTarihi) {
        super();
        this.denetimId = denetimId;
        this.denetimTuru = denetimTuru;
        this.ilceAdi = ilceAdi;
        this.ruhsatNo = ruhsatNo;
        this.plaka = plaka;
        this.varakaAdet = varakaAdet;
        this.ekipAdi = ekipAdi;
        this.denetimTarihi = denetimTarihi;
    }

    public int getDenetimId() {
        return denetimId;
    }

    public void setDenetimId(int denetimId) {
        this.denetimId = denetimId;
    }

    public String getDenetimTuru() {
        return denetimTuru;
    }

    public void setDenetimTuru(String denetimTuru) {
        this.denetimTuru = denetimTuru;
    }

    public String getIlceAdi() {
        return ilceAdi;
    }

    public void setIlceAdi(String ilceAdi) {
        this.ilceAdi = ilceAdi;
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

    public int getVarakaAdet() {
        return varakaAdet;
    }

    public void setVarakaAdet(int varakaAdet) {
        this.varakaAdet = varakaAdet;
    }

    public String getEkipAdi() {
        return ekipAdi;
    }

    public void setEkipAdi(String ekipAdi) {
        this.ekipAdi = ekipAdi;
    }

    public String getDenetimTarihi() {
        return denetimTarihi;
    }

    public void setDenetimTarihi(String denetimTarihi) {
        this.denetimTarihi = denetimTarihi;
    }

}