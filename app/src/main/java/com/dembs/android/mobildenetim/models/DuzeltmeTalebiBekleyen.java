package com.dembs.android.mobildenetim.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DuzeltmeTalebiBekleyen  implements Serializable ,Parcelable
{

    @SerializedName("denetimId")
    @Expose
    private int denetimId;
    @SerializedName("talepTurId")
    @Expose
    private int talepTurId;
    @SerializedName("denetimTurAdi")
    @Expose
    private String denetimTurAdi;
    @SerializedName("valid")
    @Expose
    private String valid;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("durumu")
    @Expose
    private String durumu;
    @SerializedName("tarih")
    @Expose
    private String tarih;
    private final static long serialVersionUID = 533613717611680251L;

    /**
     * No args constructor for use in serialization
     *
     */
    public DuzeltmeTalebiBekleyen() {
    }

    /**
     *
     * @param valid
     * @param denetimTurAdi
     * @param aciklama
     * @param tarih
     * @param denetimId
     * @param durumu
     * @param talepTurId
     */
    public DuzeltmeTalebiBekleyen(int denetimId, int talepTurId, String denetimTurAdi, String valid, String aciklama, String durumu, String tarih) {
        super();
        this.denetimId = denetimId;
        this.talepTurId = talepTurId;
        this.denetimTurAdi = denetimTurAdi;
        this.valid = valid;
        this.aciklama = aciklama;
        this.durumu = durumu;
        this.tarih = tarih;
    }

    protected DuzeltmeTalebiBekleyen(Parcel in) {
        denetimId = in.readInt();
        talepTurId = in.readInt();
        denetimTurAdi = in.readString();
        valid = in.readString();
        aciklama = in.readString();
        durumu = in.readString();
        tarih = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(denetimId);
        dest.writeInt(talepTurId);
        dest.writeString(denetimTurAdi);
        dest.writeString(valid);
        dest.writeString(aciklama);
        dest.writeString(durumu);
        dest.writeString(tarih);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DuzeltmeTalebiBekleyen> CREATOR = new Creator<DuzeltmeTalebiBekleyen>() {
        @Override
        public DuzeltmeTalebiBekleyen createFromParcel(Parcel in) {
            return new DuzeltmeTalebiBekleyen(in);
        }

        @Override
        public DuzeltmeTalebiBekleyen[] newArray(int size) {
            return new DuzeltmeTalebiBekleyen[size];
        }
    };

    public int getDenetimId() {
        return denetimId;
    }

    public void setDenetimId(int denetimId) {
        this.denetimId = denetimId;
    }

    public int getTalepTurId() {
        return talepTurId;
    }

    public void setTalepTurId(int talepTurId) {
        this.talepTurId = talepTurId;
    }

    public String getDenetimTurAdi() {
        return denetimTurAdi;
    }

    public void setDenetimTurAdi(String denetimTurAdi) {
        this.denetimTurAdi = denetimTurAdi;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getDurumu() {
        return durumu;
    }

    public void setDurumu(String durumu) {
        this.durumu = durumu;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

}