package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Varaka implements Serializable, Parcelable
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
    @SerializedName("denetimId")
    @Expose
    private int denetimId;
    @SerializedName("formItemId")
    @Expose
    private int formItemId;
    @SerializedName("varakaNo")
    @Expose
    private String varakaNo;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("tamamlandi")
    @Expose
    private boolean tamamlandi;
    @SerializedName("tamamlanmaTarihi")
    @Expose
    private String tamamlanmaTarihi;
    public final static Creator<Varaka> CREATOR = new Creator<Varaka>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Varaka createFromParcel(android.os.Parcel in) {
            return new Varaka(in);
        }

        public Varaka[] newArray(int size) {
            return (new Varaka[size]);
        }

    }
            ;
    private final static long serialVersionUID = 153864375337694866L;

    protected Varaka(android.os.Parcel in) {
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.denetimId = ((int) in.readValue((int.class.getClassLoader())));
        this.formItemId = ((int) in.readValue((int.class.getClassLoader())));
        this.varakaNo = ((String) in.readValue((String.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.tamamlandi = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.tamamlanmaTarihi = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Varaka() {
    }

    /**
     *
     * @param formItemId
     * @param aciklama
     * @param tamamlandi
     * @param islemTarihi
     * @param denetimId
     * @param kayitEden
     * @param varakaNo
     * @param id
     * @param tamamlanmaTarihi
     */
    public Varaka(String islemTarihi, String kayitEden, int id, int denetimId, int formItemId, String varakaNo, String aciklama, boolean tamamlandi, String tamamlanmaTarihi) {
        super();
        this.islemTarihi = islemTarihi;
        this.kayitEden = kayitEden;
        this.id = id;
        this.denetimId = denetimId;
        this.formItemId = formItemId;
        this.varakaNo = varakaNo;
        this.aciklama = aciklama;
        this.tamamlandi = tamamlandi;
        this.tamamlanmaTarihi = tamamlanmaTarihi;
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

    public int getDenetimId() {
        return denetimId;
    }

    public void setDenetimId(int denetimId) {
        this.denetimId = denetimId;
    }

    public int getFormItemId() {
        return formItemId;
    }

    public void setFormItemId(int formItemId) {
        this.formItemId = formItemId;
    }

    public String getVarakaNo() {
        return varakaNo;
    }

    public void setVarakaNo(String varakaNo) {
        this.varakaNo = varakaNo;
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

    public String getTamamlanmaTarihi() {
        return tamamlanmaTarihi;
    }

    public void setTamamlanmaTarihi(String tamamlanmaTarihi) {
        this.tamamlanmaTarihi = tamamlanmaTarihi;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
        dest.writeValue(id);
        dest.writeValue(denetimId);
        dest.writeValue(formItemId);
        dest.writeValue(varakaNo);
        dest.writeValue(aciklama);
        dest.writeValue(tamamlandi);
        dest.writeValue(tamamlanmaTarihi);
    }

    public int describeContents() {
        return 0;
    }

}