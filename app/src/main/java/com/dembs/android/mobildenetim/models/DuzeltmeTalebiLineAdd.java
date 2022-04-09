package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class DuzeltmeTalebiLineAdd implements Serializable, Parcelable
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
    @SerializedName("tamamlandi")
    @Expose
    private boolean tamamlandi;
    public final static Creator<DuzeltmeTalebiLineAdd> CREATOR = new Creator<DuzeltmeTalebiLineAdd>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DuzeltmeTalebiLineAdd createFromParcel(android.os.Parcel in) {
            return new DuzeltmeTalebiLineAdd(in);
        }

        public DuzeltmeTalebiLineAdd[] newArray(int size) {
            return (new DuzeltmeTalebiLineAdd[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7169543196956891339L;

    protected DuzeltmeTalebiLineAdd(android.os.Parcel in) {
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.talepTurId = ((int) in.readValue((int.class.getClassLoader())));
        this.denetimId = ((int) in.readValue((int.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.tamamlandi = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */


//    public DuzeltmeTalebiLineAdd() {
//    }

    public DuzeltmeTalebiLineAdd(String islemTarihi, String kayitEden, int id, int talepTurId, int denetimId, String aciklama, boolean tamamlandi) {
        this.islemTarihi = islemTarihi;
        this.kayitEden = kayitEden;
        this.id = id;
        this.talepTurId = talepTurId;
        this.denetimId = denetimId;
        this.aciklama = aciklama;
        this.tamamlandi = tamamlandi;
    }

    /**
     *
     * @param aciklama
     * @param tamamlandi
     * @param islemTarihi
     * @param denetimId
     * @param kayitEden
     * @param id
     * @param talepTurId
     */


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

    public boolean isTamamlandi() {
        return tamamlandi;
    }

    public void setTamamlandi(boolean tamamlandi) {
        this.tamamlandi = tamamlandi;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
        dest.writeValue(id);
        dest.writeValue(talepTurId);
        dest.writeValue(denetimId);
        dest.writeValue(aciklama);
        dest.writeValue(tamamlandi);
    }

    public int describeContents() {
        return 0;
    }

}