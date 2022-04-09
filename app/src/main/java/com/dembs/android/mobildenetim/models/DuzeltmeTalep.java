package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DuzeltmeTalep implements Serializable, Parcelable
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
    @SerializedName("talepTurId")
    @Expose
    private int talepTurId;
    @SerializedName("denetimId")
    @Expose
    private int denetimId;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    public final static Creator<DuzeltmeTalep> CREATOR = new Creator<DuzeltmeTalep>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DuzeltmeTalep createFromParcel(android.os.Parcel in) {
            return new DuzeltmeTalep(in);
        }

        public DuzeltmeTalep[] newArray(int size) {
            return (new DuzeltmeTalep[size]);
        }

    }
            ;
    private final static long serialVersionUID = -4669387021239369788L;

    protected DuzeltmeTalep(android.os.Parcel in) {
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.talepTurId = ((int) in.readValue((int.class.getClassLoader())));
        this.denetimId = ((int) in.readValue((int.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */


    /**
     *
     * @param aciklama
     * @param islemTarihi
     * @param denetimId
     * @param kayitEden
     * @param id
     * @param talepTurId
     */
    public DuzeltmeTalep(String islemTarihi, String kayitEden, int id, int talepTurId, int denetimId, String aciklama) {
        super();
        this.islemTarihi = islemTarihi;
        this.kayitEden = kayitEden;
        this.id = id;
        this.talepTurId = talepTurId;
        this.denetimId = denetimId;
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

    public int getTalepTurId() {
        return talepTurId;
    }

    public void setTalepTurId(int talepTurId) {
        this.talepTurId = talepTurId;
    }

    public int getDenetimId() {
        return denetimId;
    }

    public void setDenetimId(int denetimId) {
        this.denetimId = denetimId;
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
        dest.writeValue(talepTurId);
        dest.writeValue(denetimId);
        dest.writeValue(aciklama);
    }

    public int describeContents() {
        return 0;
    }

}