package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RuhsatDetay implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("ruhsatId")
    @Expose
    private int ruhsatId;
    @SerializedName("islemTurId")
    @Expose
    private int islemTurId;
    @SerializedName("kisiId")
    @Expose
    private int kisiId;
    @SerializedName("aracId")
    @Expose
    private int aracId;
    @SerializedName("teslimDurumId")
    @Expose
    private int teslimDurumId;
    @SerializedName("teslimTarihi")
    @Expose
    private String teslimTarihi;
    @SerializedName("sayisi")
    @Expose
    private int sayisi;
    @SerializedName("ilceId")
    @Expose
    private int ilceId;
    @SerializedName("odaId")
    @Expose
    private int odaId;
    @SerializedName("kooperatifId")
    @Expose
    private int kooperatifId;
    @SerializedName("hatDurakId")
    @Expose
    private int hatDurakId;
    @SerializedName("cekmeTarihi")
    @Expose
    private String cekmeTarihi;
    @SerializedName("cekmeSuresi")
    @Expose
    private int cekmeSuresi;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("devredenId")
    @Expose
    private int devredenId;
    @SerializedName("aktif")
    @Expose
    private boolean aktif;
    @SerializedName("durumId")
    @Expose
    private int durumId;
    @SerializedName("basvuruId")
    @Expose
    private int basvuruId;
    @SerializedName("basvuruTarihi")
    @Expose
    private String basvuruTarihi;
    @SerializedName("odaKayitTarihi")
    @Expose
    private String odaKayitTarihi;
    @SerializedName("odaKoopSicilNo")
    @Expose
    private String odaKoopSicilNo;
    @SerializedName("islemTarihi")
    @Expose
    private String islemTarihi;
    @SerializedName("kayitEden")
    @Expose
    private String kayitEden;
    public final static Creator<RuhsatDetay> CREATOR = new Creator<RuhsatDetay>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RuhsatDetay createFromParcel(android.os.Parcel in) {
            return new RuhsatDetay(in);
        }

        public RuhsatDetay[] newArray(int size) {
            return (new RuhsatDetay[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3535232490828605952L;

    protected RuhsatDetay(android.os.Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.ruhsatId = ((int) in.readValue((int.class.getClassLoader())));
        this.islemTurId = ((int) in.readValue((int.class.getClassLoader())));
        this.kisiId = ((int) in.readValue((int.class.getClassLoader())));
        this.aracId = ((int) in.readValue((int.class.getClassLoader())));
        this.teslimDurumId = ((int) in.readValue((int.class.getClassLoader())));
        this.teslimTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.sayisi = ((int) in.readValue((int.class.getClassLoader())));
        this.ilceId = ((int) in.readValue((int.class.getClassLoader())));
        this.odaId = ((int) in.readValue((int.class.getClassLoader())));
        this.kooperatifId = ((int) in.readValue((int.class.getClassLoader())));
        this.hatDurakId = ((int) in.readValue((int.class.getClassLoader())));
        this.cekmeTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.cekmeSuresi = ((int) in.readValue((int.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.devredenId = ((int) in.readValue((int.class.getClassLoader())));
        this.aktif = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.durumId = ((int) in.readValue((int.class.getClassLoader())));
        this.basvuruId = ((int) in.readValue((int.class.getClassLoader())));
        this.basvuruTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.odaKayitTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.odaKoopSicilNo = ((String) in.readValue((String.class.getClassLoader())));
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RuhsatDetay() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRuhsatId() {
        return ruhsatId;
    }

    public void setRuhsatId(int ruhsatId) {
        this.ruhsatId = ruhsatId;
    }

    public int getIslemTurId() {
        return islemTurId;
    }

    public void setIslemTurId(int islemTurId) {
        this.islemTurId = islemTurId;
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

    public int getTeslimDurumId() {
        return teslimDurumId;
    }

    public void setTeslimDurumId(int teslimDurumId) {
        this.teslimDurumId = teslimDurumId;
    }

    public String getTeslimTarihi() {
        return teslimTarihi;
    }

    public void setTeslimTarihi(String teslimTarihi) {
        this.teslimTarihi = teslimTarihi;
    }

    public int getSayisi() {
        return sayisi;
    }

    public void setSayisi(int sayisi) {
        this.sayisi = sayisi;
    }

    public int getIlceId() {
        return ilceId;
    }

    public void setIlceId(int ilceId) {
        this.ilceId = ilceId;
    }

    public int getOdaId() {
        return odaId;
    }

    public void setOdaId(int odaId) {
        this.odaId = odaId;
    }

    public int getKooperatifId() {
        return kooperatifId;
    }

    public void setKooperatifId(int kooperatifId) {
        this.kooperatifId = kooperatifId;
    }

    public int getHatDurakId() {
        return hatDurakId;
    }

    public void setHatDurakId(int hatDurakId) {
        this.hatDurakId = hatDurakId;
    }

    public String getCekmeTarihi() {
        return cekmeTarihi;
    }

    public void setCekmeTarihi(String cekmeTarihi) {
        this.cekmeTarihi = cekmeTarihi;
    }

    public int getCekmeSuresi() {
        return cekmeSuresi;
    }

    public void setCekmeSuresi(int cekmeSuresi) {
        this.cekmeSuresi = cekmeSuresi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getDevredenId() {
        return devredenId;
    }

    public void setDevredenId(int devredenId) {
        this.devredenId = devredenId;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    public int getDurumId() {
        return durumId;
    }

    public void setDurumId(int durumId) {
        this.durumId = durumId;
    }

    public int getBasvuruId() {
        return basvuruId;
    }

    public void setBasvuruId(int basvuruId) {
        this.basvuruId = basvuruId;
    }

    public String getBasvuruTarihi() {
        return basvuruTarihi;
    }

    public void setBasvuruTarihi(String basvuruTarihi) {
        this.basvuruTarihi = basvuruTarihi;
    }

    public String getOdaKayitTarihi() {
        return odaKayitTarihi;
    }

    public void setOdaKayitTarihi(String odaKayitTarihi) {
        this.odaKayitTarihi = odaKayitTarihi;
    }

    public String getOdaKoopSicilNo() {
        return odaKoopSicilNo;
    }

    public void setOdaKoopSicilNo(String odaKoopSicilNo) {
        this.odaKoopSicilNo = odaKoopSicilNo;
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
        dest.writeValue(ruhsatId);
        dest.writeValue(islemTurId);
        dest.writeValue(kisiId);
        dest.writeValue(aracId);
        dest.writeValue(teslimDurumId);
        dest.writeValue(teslimTarihi);
        dest.writeValue(sayisi);
        dest.writeValue(ilceId);
        dest.writeValue(odaId);
        dest.writeValue(kooperatifId);
        dest.writeValue(hatDurakId);
        dest.writeValue(cekmeTarihi);
        dest.writeValue(cekmeSuresi);
        dest.writeValue(aciklama);
        dest.writeValue(devredenId);
        dest.writeValue(aktif);
        dest.writeValue(durumId);
        dest.writeValue(basvuruId);
        dest.writeValue(basvuruTarihi);
        dest.writeValue(odaKayitTarihi);
        dest.writeValue(odaKoopSicilNo);
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
    }

    public int describeContents() {
        return 0;
    }

}