package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RehberPersonelLine implements Serializable, Parcelable
{

    @SerializedName("ruhsatSofor")
    @Expose
    private RuhsatSofor ruhsatSofor;
    @SerializedName("adi")
    @Expose
    private String adi;
    @SerializedName("soyadi")
    @Expose
    private String soyadi;
    public final static Creator<RehberPersonelLine> CREATOR = new Creator<RehberPersonelLine>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RehberPersonelLine createFromParcel(android.os.Parcel in) {
            return new RehberPersonelLine(in);
        }

        public RehberPersonelLine[] newArray(int size) {
            return (new RehberPersonelLine[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6301490108027109086L;

    protected RehberPersonelLine(android.os.Parcel in) {
        this.ruhsatSofor = ((RuhsatSofor) in.readValue((RuhsatSofor.class.getClassLoader())));
        this.adi = ((String) in.readValue((String.class.getClassLoader())));
        this.soyadi = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RehberPersonelLine() {
    }

    public RuhsatSofor getRuhsatSofor() {
        return ruhsatSofor;
    }

    public void setRuhsatSofor(RuhsatSofor ruhsatSofor) {
        this.ruhsatSofor = ruhsatSofor;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(ruhsatSofor);
        dest.writeValue(adi);
        dest.writeValue(soyadi);
    }

    public int describeContents() {
        return 0;
    }

}