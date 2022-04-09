package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class DenetimFormItemLine implements Serializable, Parcelable
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
    @SerializedName("secim")
    @Expose
    private boolean secim;
    public final static Creator<DenetimFormItemLine> CREATOR = new Creator<DenetimFormItemLine>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DenetimFormItemLine createFromParcel(android.os.Parcel in) {
            return new DenetimFormItemLine(in);
        }

        public DenetimFormItemLine[] newArray(int size) {
            return (new DenetimFormItemLine[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1317503888758049151L;

    protected DenetimFormItemLine(android.os.Parcel in) {
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.denetimId = ((int) in.readValue((int.class.getClassLoader())));
        this.formItemId = ((int) in.readValue((int.class.getClassLoader())));
        this.secim = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    public DenetimFormItemLine() {
    }

    public DenetimFormItemLine(String islemTarihi, String kayitEden, int id, int denetimId, int formItemId, boolean secim) {
        this.islemTarihi = islemTarihi;
        this.kayitEden = kayitEden;
        this.id = id;
        this.denetimId = denetimId;
        this.formItemId = formItemId;
        this.secim = secim;
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

    public boolean isSecim() {
        return secim;
    }

    public void setSecim(boolean secim) {
        this.secim = secim;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
        dest.writeValue(id);
        dest.writeValue(denetimId);
        dest.writeValue(formItemId);
        dest.writeValue(secim);
    }

    public int describeContents() {
        return 0;
    }

}