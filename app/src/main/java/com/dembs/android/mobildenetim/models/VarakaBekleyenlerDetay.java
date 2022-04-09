package com.dembs.android.mobildenetim.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VarakaBekleyenlerDetay implements Serializable, Parcelable
{

    @SerializedName("itemId")
    @Expose
    private int itemId;
    @SerializedName("denetimId")
    @Expose
    private int denetimId;
    @SerializedName("maddeNo")
    @Expose
    private String maddeNo;
    @SerializedName("ihlalMaddeAdi")
    @Expose
    private String ihlalMaddeAdi;
    @SerializedName("ihlalBendAdi")
    @Expose
    private String ihlalBendAdi;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("tip")
    @Expose
    private String tip;
    @SerializedName("ihlalMaddeId")
    @Expose
    private float ihlalMaddeId;
    @SerializedName("ihlalBendId")
    @Expose
    private float ihlalBendId;
    @SerializedName("resim")
    @Expose
    private String resim;
    @SerializedName("durum")
    @Expose
    private String durum;
    @SerializedName("tamamlanmaTarihi")
    @Expose
    private String tamamlanmaTarihi;
    @SerializedName("tamamlanmaTarihiString")
    @Expose
    private String tamamlanmaTarihiString;
    @SerializedName("varakaId")
    @Expose
    private int varakaId;
    @SerializedName("varakaNo")
    @Expose
    private String varakaNo;
    @SerializedName("isUser")
    @Expose
    private String isUser;
    private final static long serialVersionUID = -2560149599141270092L;

    /**
     * No args constructor for use in serialization
     *
     */
    public VarakaBekleyenlerDetay() {
    }

    /**
     *
     * @param durum
     * @param varakaNo
     * @param ihlalBendAdi
     * @param tamamlanmaTarihi
     * @param maddeNo
     * @param ihlalMaddeAdi
     * @param ihlalMaddeId
     * @param tamamlanmaTarihiString
     * @param itemId
     * @param resim
     * @param aciklama
     * @param varakaId
     * @param denetimId
     * @param tip
     * @param ihlalBendId
     * @param isUser
     */
    public VarakaBekleyenlerDetay(int itemId, int denetimId, String maddeNo, String ihlalMaddeAdi, String ihlalBendAdi, String aciklama, String tip, float ihlalMaddeId, float ihlalBendId, String resim, String durum, String tamamlanmaTarihi, String tamamlanmaTarihiString, int varakaId, String varakaNo, String isUser) {
        super();
        this.itemId = itemId;
        this.denetimId = denetimId;
        this.maddeNo = maddeNo;
        this.ihlalMaddeAdi = ihlalMaddeAdi;
        this.ihlalBendAdi = ihlalBendAdi;
        this.aciklama = aciklama;
        this.tip = tip;
        this.ihlalMaddeId = ihlalMaddeId;
        this.ihlalBendId = ihlalBendId;
        this.resim = resim;
        this.durum = durum;
        this.tamamlanmaTarihi = tamamlanmaTarihi;
        this.tamamlanmaTarihiString = tamamlanmaTarihiString;
        this.varakaId = varakaId;
        this.varakaNo = varakaNo;
        this.isUser = isUser;
    }

    protected VarakaBekleyenlerDetay(Parcel in) {
        itemId = in.readInt();
        denetimId = in.readInt();
        maddeNo = in.readString();
        ihlalMaddeAdi = in.readString();
        ihlalBendAdi = in.readString();
        aciklama = in.readString();
        tip = in.readString();
        ihlalMaddeId = in.readFloat();
        ihlalBendId = in.readFloat();
        resim = in.readString();
        durum = in.readString();
        tamamlanmaTarihi = in.readString();
        tamamlanmaTarihiString = in.readString();
        varakaId = in.readInt();
        varakaNo = in.readString();
        isUser = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(itemId);
        dest.writeInt(denetimId);
        dest.writeString(maddeNo);
        dest.writeString(ihlalMaddeAdi);
        dest.writeString(ihlalBendAdi);
        dest.writeString(aciklama);
        dest.writeString(tip);
        dest.writeFloat(ihlalMaddeId);
        dest.writeFloat(ihlalBendId);
        dest.writeString(resim);
        dest.writeString(durum);
        dest.writeString(tamamlanmaTarihi);
        dest.writeString(tamamlanmaTarihiString);
        dest.writeInt(varakaId);
        dest.writeString(varakaNo);
        dest.writeString(isUser);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VarakaBekleyenlerDetay> CREATOR = new Creator<VarakaBekleyenlerDetay>() {
        @Override
        public VarakaBekleyenlerDetay createFromParcel(Parcel in) {
            return new VarakaBekleyenlerDetay(in);
        }

        @Override
        public VarakaBekleyenlerDetay[] newArray(int size) {
            return new VarakaBekleyenlerDetay[size];
        }
    };

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getDenetimId() {
        return denetimId;
    }

    public void setDenetimId(int denetimId) {
        this.denetimId = denetimId;
    }

    public String getMaddeNo() {
        return maddeNo;
    }

    public void setMaddeNo(String maddeNo) {
        this.maddeNo = maddeNo;
    }

    public String getIhlalMaddeAdi() {
        return ihlalMaddeAdi;
    }

    public void setIhlalMaddeAdi(String ihlalMaddeAdi) {
        this.ihlalMaddeAdi = ihlalMaddeAdi;
    }

    public String getIhlalBendAdi() {
        return ihlalBendAdi;
    }

    public void setIhlalBendAdi(String ihlalBendAdi) {
        this.ihlalBendAdi = ihlalBendAdi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public float getIhlalMaddeId() {
        return ihlalMaddeId;
    }

    public void setIhlalMaddeId(float ihlalMaddeId) {
        this.ihlalMaddeId = ihlalMaddeId;
    }

    public float getIhlalBendId() {
        return ihlalBendId;
    }

    public void setIhlalBendId(float ihlalBendId) {
        this.ihlalBendId = ihlalBendId;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getTamamlanmaTarihi() {
        return tamamlanmaTarihi;
    }

    public void setTamamlanmaTarihi(String tamamlanmaTarihi) {
        this.tamamlanmaTarihi = tamamlanmaTarihi;
    }

    public String getTamamlanmaTarihiString() {
        return tamamlanmaTarihiString;
    }

    public void setTamamlanmaTarihiString(String tamamlanmaTarihiString) {
        this.tamamlanmaTarihiString = tamamlanmaTarihiString;
    }

    public int getVarakaId() {
        return varakaId;
    }

    public void setVarakaId(int varakaId) {
        this.varakaId = varakaId;
    }

    public String getVarakaNo() {
        return varakaNo;
    }

    public void setVarakaNo(String varakaNo) {
        this.varakaNo = varakaNo;
    }

    public String getIsUser() {
        return isUser;
    }

    public void setIsUser(String isUser) {
        this.isUser = isUser;
    }

}