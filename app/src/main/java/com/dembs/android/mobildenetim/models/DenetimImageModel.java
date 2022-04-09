package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DenetimImageModel implements Serializable, Parcelable
{
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getdenetimId() {
        return denetimId;
    }

    public void setdenetimId(int denetimId) {
        this.denetimId = denetimId;
    }

    public String getKayitEden() {
        return kayitEden;
    }

    public void setKayitEden(String kayitEden) {
        this.kayitEden = kayitEden;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocBinary() {
        return docBinary;
    }

    public void setDocBinary(String docBinary) {
        this.docBinary = docBinary;
    }

    public String getUzanti() {
        return uzanti;
    }

    public void setUzanti(String uzanti) {
        this.uzanti = uzanti;
    }

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("denetimId")
    @Expose
    public int denetimId;
    @SerializedName("kayitEden")
    @Expose
    public String kayitEden;
    @SerializedName("aciklama")
    @Expose
    public String aciklama;
    @SerializedName("docName")
    @Expose
    public String docName;
    @SerializedName("docBinary")
    @Expose
    public String docBinary;
    @SerializedName("uzanti")
    @Expose
    public String uzanti;

    @SerializedName("islemTarihi")
    @Expose
    public String islemTarihi;

    public final static Creator<DenetimImageModel> CREATOR = new Creator<DenetimImageModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DenetimImageModel createFromParcel(android.os.Parcel in) {
            return new DenetimImageModel(in);
        }

        public DenetimImageModel[] newArray(int size) {
            return (new DenetimImageModel[size]);
        }

    }
            ;
    private final static long serialVersionUID = -4493180687017795581L;

    protected DenetimImageModel(android.os.Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.denetimId = ((int) in.readValue((int.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.docName = ((String) in.readValue((String.class.getClassLoader())));
        this.docBinary = ((String) in.readValue((String.class.getClassLoader())));
        this.uzanti = ((String) in.readValue((String.class.getClassLoader())));
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));

    }

    public DenetimImageModel() {
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(denetimId);
        dest.writeValue(kayitEden);
        dest.writeValue(aciklama);
        dest.writeValue(docName);
        dest.writeValue(docBinary);
        dest.writeValue(uzanti);
        dest.writeValue(islemTarihi);
    }

    public int describeContents() {
        return 0;
    }

}