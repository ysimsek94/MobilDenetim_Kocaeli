package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Belge implements Serializable, Parcelable
{

    @SerializedName("islemTarihi")
    @Expose
    private String islemTarihi;
    @SerializedName("kayitEden")
    @Expose
    private String kayitEden;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("belgeTurId")
    @Expose
    private int belgeTurId;
    @SerializedName("detayId")
    @Expose
    private int detayId;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("sayisi")
    @Expose
    private String sayisi;
    @SerializedName("kisiId")
    @Expose
    private int kisiId;
    @SerializedName("aracId")
    @Expose
    private int aracId;
    @SerializedName("surucuKisiId")
    @Expose
    private int surucuKisiId;
    @SerializedName("evrakTarihi")
    @Expose
    private String evrakTarihi;
    @SerializedName("gecerlilikTarihi")
    @Expose
    private String gecerlilikTarihi;
    @SerializedName("gecerliGun")
    @Expose
    private int gecerliGun;
    @SerializedName("teslimTarihi")
    @Expose
    private String teslimTarihi;
    @SerializedName("basvuruId")
    @Expose
    private int basvuruId;
    @SerializedName("sil")
    @Expose
    private boolean sil;
    public final static Creator<Belge> CREATOR = new Creator<Belge>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Belge createFromParcel(android.os.Parcel in) {
            return new Belge(in);
        }

        public Belge[] newArray(int size) {
            return (new Belge[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7354013319142951195L;

    protected Belge(android.os.Parcel in) {
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.belgeTurId = ((int) in.readValue((int.class.getClassLoader())));
        this.detayId = ((int) in.readValue((int.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.sayisi = ((String) in.readValue((String.class.getClassLoader())));
        this.kisiId = ((int) in.readValue((int.class.getClassLoader())));
        this.aracId = ((int) in.readValue((int.class.getClassLoader())));
        this.surucuKisiId = ((int) in.readValue((int.class.getClassLoader())));
        this.evrakTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.gecerlilikTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.gecerliGun = ((int) in.readValue((int.class.getClassLoader())));
        this.teslimTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.basvuruId = ((int) in.readValue((int.class.getClassLoader())));
        this.sil = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Belge() {
    }

    /**
     *
     * @param gecerlilikTarihi
     * @param basvuruId
     * @param islemTarihi
     * @param belgeTurId
     * @param evrakTarihi
     * @param teslimTarihi
     * @param gecerliGun
     * @param surucuKisiId
     * @param aciklama
     * @param aracId
     * @param sil
     * @param kayitEden
     * @param sayisi
     * @param id
     * @param kisiId
     * @param detayId
     */
    public Belge(String islemTarihi, String kayitEden, int id, int belgeTurId, int detayId, String aciklama, String sayisi, int kisiId, int aracId, int surucuKisiId, String evrakTarihi, String gecerlilikTarihi, int gecerliGun, String teslimTarihi, int basvuruId, boolean sil) {
        super();
        this.islemTarihi = islemTarihi;
        this.kayitEden = kayitEden;
        this.id = id;
        this.belgeTurId = belgeTurId;
        this.detayId = detayId;
        this.aciklama = aciklama;
        this.sayisi = sayisi;
        this.kisiId = kisiId;
        this.aracId = aracId;
        this.surucuKisiId = surucuKisiId;
        this.evrakTarihi = evrakTarihi;
        this.gecerlilikTarihi = gecerlilikTarihi;
        this.gecerliGun = gecerliGun;
        this.teslimTarihi = teslimTarihi;
        this.basvuruId = basvuruId;
        this.sil = sil;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBelgeTurId() {
        return belgeTurId;
    }

    public void setBelgeTurId(int belgeTurId) {
        this.belgeTurId = belgeTurId;
    }

    public int getDetayId() {
        return detayId;
    }

    public void setDetayId(int detayId) {
        this.detayId = detayId;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getSayisi() {
        return sayisi;
    }

    public void setSayisi(String sayisi) {
        this.sayisi = sayisi;
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

    public void setAracId(int aracId) {
        this.aracId = aracId;
    }

    public int getSurucuKisiId() {
        return surucuKisiId;
    }

    public void setSurucuKisiId(int surucuKisiId) {
        this.surucuKisiId = surucuKisiId;
    }

    public String getEvrakTarihi() {
        return evrakTarihi;
    }

    public void setEvrakTarihi(String evrakTarihi) {
        this.evrakTarihi = evrakTarihi;
    }

    public String getGecerlilikTarihi() {
        return gecerlilikTarihi;
    }

    public void setGecerlilikTarihi(String gecerlilikTarihi) {
        this.gecerlilikTarihi = gecerlilikTarihi;
    }

    public int getGecerliGun() {
        return gecerliGun;
    }

    public void setGecerliGun(int gecerliGun) {
        this.gecerliGun = gecerliGun;
    }

    public String getTeslimTarihi() {
        return teslimTarihi;
    }

    public void setTeslimTarihi(String teslimTarihi) {
        this.teslimTarihi = teslimTarihi;
    }

    public int getBasvuruId() {
        return basvuruId;
    }

    public void setBasvuruId(int basvuruId) {
        this.basvuruId = basvuruId;
    }

    public boolean isSil() {
        return sil;
    }

    public void setSil(boolean sil) {
        this.sil = sil;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
        dest.writeValue(id);
        dest.writeValue(belgeTurId);
        dest.writeValue(detayId);
        dest.writeValue(aciklama);
        dest.writeValue(sayisi);
        dest.writeValue(kisiId);
        dest.writeValue(aracId);
        dest.writeValue(surucuKisiId);
        dest.writeValue(evrakTarihi);
        dest.writeValue(gecerlilikTarihi);
        dest.writeValue(gecerliGun);
        dest.writeValue(teslimTarihi);
        dest.writeValue(basvuruId);
        dest.writeValue(sil);
    }

    public int describeContents() {
        return 0;
    }

}