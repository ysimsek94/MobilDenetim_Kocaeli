package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RuhsatBandrol implements Serializable, Parcelable
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
    @SerializedName("detayId")
    @Expose
    private int detayId;
    @SerializedName("bandrolTurId")
    @Expose
    private int bandrolTurId;
    @SerializedName("makbuzNo")
    @Expose
    private String makbuzNo;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("tutar")
    @Expose
    private float tutar;
    public final static Creator<RuhsatBandrol> CREATOR = new Creator<RuhsatBandrol>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RuhsatBandrol createFromParcel(android.os.Parcel in) {
            return new RuhsatBandrol(in);
        }

        public RuhsatBandrol[] newArray(int size) {
            return (new RuhsatBandrol[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5288834115642127188L;

    protected RuhsatBandrol(android.os.Parcel in) {
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.detayId = ((int) in.readValue((int.class.getClassLoader())));
        this.bandrolTurId = ((int) in.readValue((int.class.getClassLoader())));
        this.makbuzNo = ((String) in.readValue((String.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.tutar = ((float) in.readValue((float.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public RuhsatBandrol() {
    }

    /**
     *
     * @param aciklama
     * @param islemTarihi
     * @param kayitEden
     * @param id
     * @param bandrolTurId
     * @param tutar
     * @param makbuzNo
     * @param detayId
     */
    public RuhsatBandrol(String islemTarihi, String kayitEden, int id, int detayId, int bandrolTurId, String makbuzNo, String aciklama, float tutar) {
        super();
        this.islemTarihi = islemTarihi;
        this.kayitEden = kayitEden;
        this.id = id;
        this.detayId = detayId;
        this.bandrolTurId = bandrolTurId;
        this.makbuzNo = makbuzNo;
        this.aciklama = aciklama;
        this.tutar = tutar;
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

    public int getDetayId() {
        return detayId;
    }

    public void setDetayId(int detayId) {
        this.detayId = detayId;
    }

    public int getBandrolTurId() {
        return bandrolTurId;
    }

    public void setBandrolTurId(int bandrolTurId) {
        this.bandrolTurId = bandrolTurId;
    }

    public String getMakbuzNo() {
        return makbuzNo;
    }

    public void setMakbuzNo(String makbuzNo) {
        this.makbuzNo = makbuzNo;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public float getTutar() {
        return tutar;
    }

    public void setTutar(float tutar) {
        this.tutar = tutar;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
        dest.writeValue(id);
        dest.writeValue(detayId);
        dest.writeValue(bandrolTurId);
        dest.writeValue(makbuzNo);
        dest.writeValue(aciklama);
        dest.writeValue(tutar);
    }

    public int describeContents() {
        return 0;
    }

}