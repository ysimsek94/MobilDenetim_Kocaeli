package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Arac implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("plaka")
    @Expose
    private String plaka;
    @SerializedName("aracCinsId")
    @Expose
    private int aracCinsId;
    @SerializedName("model")
    @Expose
    private int model;
    @SerializedName("markaId")
    @Expose
    private int markaId;
    @SerializedName("yolcuAdet")
    @Expose
    private int yolcuAdet;
    @SerializedName("yolcuAdetArti")
    @Expose
    private int yolcuAdetArti;
    @SerializedName("motorNo")
    @Expose
    private String motorNo;
    @SerializedName("saseNo")
    @Expose
    private String saseNo;
    @SerializedName("yakitTipId")
    @Expose
    private int yakitTipId;
    @SerializedName("kullanimTurId")
    @Expose
    private int kullanimTurId;
    @SerializedName("tescilTarihi")
    @Expose
    private String tescilTarihi;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("sil")
    @Expose
    private boolean sil;
    @SerializedName("islemTarihi")
    @Expose
    private String islemTarihi;
    @SerializedName("kayitEden")
    @Expose
    private String kayitEden;
    public final static Creator<Arac> CREATOR = new Creator<Arac>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Arac createFromParcel(android.os.Parcel in) {
            return new Arac(in);
        }

        public Arac[] newArray(int size) {
            return (new Arac[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1916810099165365512L;

    protected Arac(android.os.Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.plaka = ((String) in.readValue((String.class.getClassLoader())));
        this.aracCinsId = ((int) in.readValue((int.class.getClassLoader())));
        this.model = ((int) in.readValue((int.class.getClassLoader())));
        this.markaId = ((int) in.readValue((int.class.getClassLoader())));
        this.yolcuAdet = ((int) in.readValue((int.class.getClassLoader())));
        this.yolcuAdetArti = ((int) in.readValue((int.class.getClassLoader())));
        this.motorNo = ((String) in.readValue((String.class.getClassLoader())));
        this.saseNo = ((String) in.readValue((String.class.getClassLoader())));
        this.yakitTipId = ((int) in.readValue((int.class.getClassLoader())));
        this.kullanimTurId = ((int) in.readValue((int.class.getClassLoader())));
        this.tescilTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.sil = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Arac() {
    }

    /**
     *
     * @param aracCinsId
     * @param yolcuAdet
     * @param tescilTarihi
     * @param islemTarihi
     * @param plaka
     * @param markaId
     * @param saseNo
     * @param kullanimTurId
     * @param motorNo
     * @param aciklama
     * @param yolcuAdetArti
     * @param sil
     * @param kayitEden
     * @param model
     * @param id
     * @param yakitTipId
     */
    public Arac(int id, String plaka, int aracCinsId, int model, int markaId, int yolcuAdet, int yolcuAdetArti, String motorNo, String saseNo, int yakitTipId, int kullanimTurId, String tescilTarihi, String aciklama, boolean sil, String islemTarihi, String kayitEden) {
        super();
        this.id = id;
        this.plaka = plaka;
        this.aracCinsId = aracCinsId;
        this.model = model;
        this.markaId = markaId;
        this.yolcuAdet = yolcuAdet;
        this.yolcuAdetArti = yolcuAdetArti;
        this.motorNo = motorNo;
        this.saseNo = saseNo;
        this.yakitTipId = yakitTipId;
        this.kullanimTurId = kullanimTurId;
        this.tescilTarihi = tescilTarihi;
        this.aciklama = aciklama;
        this.sil = sil;
        this.islemTarihi = islemTarihi;
        this.kayitEden = kayitEden;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public int getAracCinsId() {
        return aracCinsId;
    }

    public void setAracCinsId(int aracCinsId) {
        this.aracCinsId = aracCinsId;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getMarkaId() {
        return markaId;
    }

    public void setMarkaId(int markaId) {
        this.markaId = markaId;
    }

    public int getYolcuAdet() {
        return yolcuAdet;
    }

    public void setYolcuAdet(int yolcuAdet) {
        this.yolcuAdet = yolcuAdet;
    }

    public int getYolcuAdetArti() {
        return yolcuAdetArti;
    }

    public void setYolcuAdetArti(int yolcuAdetArti) {
        this.yolcuAdetArti = yolcuAdetArti;
    }

    public String getMotorNo() {
        return motorNo;
    }

    public void setMotorNo(String motorNo) {
        this.motorNo = motorNo;
    }

    public String getSaseNo() {
        return saseNo;
    }

    public void setSaseNo(String saseNo) {
        this.saseNo = saseNo;
    }

    public int getYakitTipId() {
        return yakitTipId;
    }

    public void setYakitTipId(int yakitTipId) {
        this.yakitTipId = yakitTipId;
    }

    public int getKullanimTurId() {
        return kullanimTurId;
    }

    public void setKullanimTurId(int kullanimTurId) {
        this.kullanimTurId = kullanimTurId;
    }

    public String getTescilTarihi() {
        return tescilTarihi;
    }

    public void setTescilTarihi(String tescilTarihi) {
        this.tescilTarihi = tescilTarihi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public boolean isSil() {
        return sil;
    }

    public void setSil(boolean sil) {
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

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(plaka);
        dest.writeValue(aracCinsId);
        dest.writeValue(model);
        dest.writeValue(markaId);
        dest.writeValue(yolcuAdet);
        dest.writeValue(yolcuAdetArti);
        dest.writeValue(motorNo);
        dest.writeValue(saseNo);
        dest.writeValue(yakitTipId);
        dest.writeValue(kullanimTurId);
        dest.writeValue(tescilTarihi);
        dest.writeValue(aciklama);
        dest.writeValue(sil);
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
    }

    public int describeContents() {
        return 0;
    }

}