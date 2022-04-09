package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AracReklam implements Serializable, Parcelable
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
    @SerializedName("aracId")
    @Expose
    private int aracId;
    @SerializedName("baslangicTarihi")
    @Expose
    private String baslangicTarihi;
    @SerializedName("bitisTarihi")
    @Expose
    private String bitisTarihi;
    @SerializedName("ybsNo")
    @Expose
    private String ybsNo;
    @SerializedName("onBolum")
    @Expose
    private boolean onBolum;
    @SerializedName("arkaBolum")
    @Expose
    private boolean arkaBolum;
    @SerializedName("sagBolum")
    @Expose
    private boolean sagBolum;
    @SerializedName("solBolum")
    @Expose
    private boolean solBolum;
    @SerializedName("ustBolum")
    @Expose
    private boolean ustBolum;
    @SerializedName("aktif")
    @Expose
    private boolean aktif;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    public final static Creator<AracReklam> CREATOR = new Creator<AracReklam>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AracReklam createFromParcel(android.os.Parcel in) {
            return new AracReklam(in);
        }

        public AracReklam[] newArray(int size) {
            return (new AracReklam[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7563532136713065859L;

    protected AracReklam(android.os.Parcel in) {
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.aracId = ((int) in.readValue((int.class.getClassLoader())));
        this.baslangicTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.bitisTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.ybsNo = ((String) in.readValue((String.class.getClassLoader())));
        this.onBolum = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.arkaBolum = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.sagBolum = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.solBolum = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.ustBolum = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.aktif = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public AracReklam() {
    }

    /**
     *
     * @param sagBolum
     * @param islemTarihi
     * @param bitisTarihi
     * @param onBolum
     * @param aktif
     * @param aciklama
     * @param aracId
     * @param kayitEden
     * @param id
     * @param arkaBolum
     * @param solBolum
     * @param ybsNo
     * @param baslangicTarihi
     * @param ustBolum
     */
    public AracReklam(String islemTarihi, String kayitEden, int id, int aracId, String baslangicTarihi, String bitisTarihi, String ybsNo, boolean onBolum, boolean arkaBolum, boolean sagBolum, boolean solBolum, boolean ustBolum, boolean aktif, String aciklama) {
        super();
        this.islemTarihi = islemTarihi;
        this.kayitEden = kayitEden;
        this.id = id;
        this.aracId = aracId;
        this.baslangicTarihi = baslangicTarihi;
        this.bitisTarihi = bitisTarihi;
        this.ybsNo = ybsNo;
        this.onBolum = onBolum;
        this.arkaBolum = arkaBolum;
        this.sagBolum = sagBolum;
        this.solBolum = solBolum;
        this.ustBolum = ustBolum;
        this.aktif = aktif;
        this.aciklama = aciklama;
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

    public int getAracId() {
        return aracId;
    }

    public void setAracId(int aracId) {
        this.aracId = aracId;
    }

    public String getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(String baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public String getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(String bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public String getYbsNo() {
        return ybsNo;
    }

    public void setYbsNo(String ybsNo) {
        this.ybsNo = ybsNo;
    }

    public boolean isOnBolum() {
        return onBolum;
    }

    public void setOnBolum(boolean onBolum) {
        this.onBolum = onBolum;
    }

    public boolean isArkaBolum() {
        return arkaBolum;
    }

    public void setArkaBolum(boolean arkaBolum) {
        this.arkaBolum = arkaBolum;
    }

    public boolean isSagBolum() {
        return sagBolum;
    }

    public void setSagBolum(boolean sagBolum) {
        this.sagBolum = sagBolum;
    }

    public boolean isSolBolum() {
        return solBolum;
    }

    public void setSolBolum(boolean solBolum) {
        this.solBolum = solBolum;
    }

    public boolean isUstBolum() {
        return ustBolum;
    }

    public void setUstBolum(boolean ustBolum) {
        this.ustBolum = ustBolum;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
        dest.writeValue(id);
        dest.writeValue(aracId);
        dest.writeValue(baslangicTarihi);
        dest.writeValue(bitisTarihi);
        dest.writeValue(ybsNo);
        dest.writeValue(onBolum);
        dest.writeValue(arkaBolum);
        dest.writeValue(sagBolum);
        dest.writeValue(solBolum);
        dest.writeValue(ustBolum);
        dest.writeValue(aktif);
        dest.writeValue(aciklama);
    }

    public int describeContents() {
        return 0;
    }

}