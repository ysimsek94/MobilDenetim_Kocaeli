
package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Denetim implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("denetimTurId")
    @Expose
    private int denetimTurId;
    @SerializedName("denetimTipId")
    @Expose
    private int denetimTipId;
    @SerializedName("denetimTarihi")
    @Expose
    private String denetimTarihi;
    @SerializedName("detayId")
    @Expose
    private int detayId;
    @SerializedName("plaka")
    @Expose
    private String plaka;
    @SerializedName("ruhsatNo")
    @Expose
    private String ruhsatNo;
    @SerializedName("ilceId")
    @Expose
    private int ilceId;
    @SerializedName("mahalleId")
    @Expose
    private int mahalleId;
    @SerializedName("sokakId")
    @Expose
    private int sokakId;
    @SerializedName("adres")
    @Expose
    private String adres;
    @SerializedName("surucuTcNo")
    @Expose
    private String surucuTcNo;
    @SerializedName("surucuAdi")
    @Expose
    private String surucuAdi;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("durumId")
    @Expose
    private int durumId;
    @SerializedName("sil")
    @Expose
    private boolean sil;
    @SerializedName("konum")
    @Expose
    private Konum konum;
    @SerializedName("ekipId")
    @Expose
    private int ekipId;
    @SerializedName("islemTarihi")
    @Expose
    private String islemTarihi;
    @SerializedName("kayitEden")
    @Expose
    private String kayitEden;
    public final static Creator<Denetim> CREATOR = new Creator<Denetim>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Denetim createFromParcel(android.os.Parcel in) {
            return new Denetim(in);
        }

        public Denetim[] newArray(int size) {
            return (new Denetim[size]);
        }

    }
            ;
    private final static long serialVersionUID = -8049410387172546673L;

    protected Denetim(android.os.Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.denetimTurId = ((int) in.readValue((int.class.getClassLoader())));
        this.denetimTipId = ((int) in.readValue((int.class.getClassLoader())));
        this.denetimTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.detayId = ((int) in.readValue((int.class.getClassLoader())));
        this.plaka = ((String) in.readValue((String.class.getClassLoader())));
        this.ruhsatNo = ((String) in.readValue((String.class.getClassLoader())));
        this.ilceId = ((int) in.readValue((int.class.getClassLoader())));
        this.mahalleId = ((int) in.readValue((int.class.getClassLoader())));
        this.sokakId = ((int) in.readValue((int.class.getClassLoader())));
        this.adres = ((String) in.readValue((String.class.getClassLoader())));
        this.surucuTcNo = ((String) in.readValue((String.class.getClassLoader())));
        this.surucuAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.durumId = ((int) in.readValue((int.class.getClassLoader())));
        this.sil = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.konum = ((Konum) in.readValue((Konum.class.getClassLoader())));
        this.ekipId = ((int) in.readValue((int.class.getClassLoader())));
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Denetim() {
    }

    /**
     *
     * @param islemTarihi
     * @param ekipId
     * @param plaka
     * @param durumId
     * @param surucuTcNo
     * @param ilceId
     * @param denetimTarihi
     * @param surucuAdi
     * @param aciklama
     * @param mahalleId
     * @param konum
     * @param denetimTurId
     * @param sil
     * @param kayitEden
     * @param id
     * @param ruhsatNo
     * @param adres
     * @param sokakId
     * @param detayId
     */
    public Denetim(int id, int denetimTurId,int denetimTipId, String denetimTarihi, int detayId, String plaka, String ruhsatNo, int ilceId, int mahalleId, int sokakId, String adres, String surucuTcNo, String surucuAdi, String aciklama, int durumId, boolean sil, Konum konum, int ekipId, String islemTarihi, String kayitEden) {
        super();
        this.id = id;
        this.denetimTurId = denetimTurId;
        this.denetimTipId = denetimTipId;
        this.denetimTarihi = denetimTarihi;
        this.detayId = detayId;
        this.plaka = plaka;
        this.ruhsatNo = ruhsatNo;
        this.ilceId = ilceId;
        this.mahalleId = mahalleId;
        this.sokakId = sokakId;
        this.adres = adres;
        this.surucuTcNo = surucuTcNo;
        this.surucuAdi = surucuAdi;
        this.aciklama = aciklama;
        this.durumId = durumId;
        this.sil = sil;
        this.konum = konum;
        this.ekipId = ekipId;
        this.islemTarihi = islemTarihi;
        this.kayitEden = kayitEden;
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

    public int getDenetimTipId() {
        return denetimTipId;
    }

    public void setDenetimTipId(int denetimTipId) {
        this.denetimTipId = denetimTipId;
    }


    public String getDenetimTarihi() {
        return denetimTarihi;
    }

    public void setDenetimTarihi(String denetimTarihi) {
        this.denetimTarihi = denetimTarihi;
    }

    public int getDetayId() {
        return detayId;
    }

    public void setDetayId(int detayId) {
        this.detayId = detayId;
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

    public int getIlceId() {
        return ilceId;
    }

    public void setIlceId(int ilceId) {
        this.ilceId = ilceId;
    }

    public int getMahalleId() {
        return mahalleId;
    }

    public void setMahalleId(int mahalleId) {
        this.mahalleId = mahalleId;
    }

    public int getSokakId() {
        return sokakId;
    }

    public void setSokakId(int sokakId) {
        this.sokakId = sokakId;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getSurucuTcNo() {
        return surucuTcNo;
    }

    public void setSurucuTcNo(String surucuTcNo) {
        this.surucuTcNo = surucuTcNo;
    }

    public String getSurucuAdi() {
        return surucuAdi;
    }

    public void setSurucuAdi(String surucuAdi) {
        this.surucuAdi = surucuAdi;
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

    public boolean isSil() {
        return sil;
    }

    public void setSil(boolean sil) {
        this.sil = sil;
    }

    public Konum getKonum() {
        return konum;
    }

    public void setKonum(Konum konum) {
        this.konum = konum;
    }

    public int getEkipId() {
        return ekipId;
    }

    public void setEkipId(int ekipId) {
        this.ekipId = ekipId;
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
        dest.writeValue(denetimTurId);
        dest.writeValue(denetimTipId);
        dest.writeValue(denetimTarihi);
        dest.writeValue(detayId);
        dest.writeValue(plaka);
        dest.writeValue(ruhsatNo);
        dest.writeValue(ilceId);
        dest.writeValue(mahalleId);
        dest.writeValue(sokakId);
        dest.writeValue(adres);
        dest.writeValue(surucuTcNo);
        dest.writeValue(surucuAdi);
        dest.writeValue(aciklama);
        dest.writeValue(durumId);
        dest.writeValue(sil);
        dest.writeValue(konum);
        dest.writeValue(ekipId);
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
    }

    public int describeContents() {
        return 0;
    }

}