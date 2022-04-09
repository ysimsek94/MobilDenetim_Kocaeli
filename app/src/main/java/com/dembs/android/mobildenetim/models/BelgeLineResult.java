package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class BelgeLineResult implements Serializable, Parcelable
{

    @SerializedName("ruhsat")
    @Expose
    private Ruhsat ruhsat;
    @SerializedName("ruhsatDetay")
    @Expose
    private RuhsatDetay ruhsatDetay;
    @SerializedName("belgeLine")
    @Expose
    private BelgeLine belgeLine;
    @SerializedName("arac")
    @Expose
    private Arac arac;
    @SerializedName("kisi")
    @Expose
    private Kisi kisi;
    public final static Creator<BelgeLineResult> CREATOR = new Creator<BelgeLineResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BelgeLineResult createFromParcel(android.os.Parcel in) {
            return new BelgeLineResult(in);
        }

        public BelgeLineResult[] newArray(int size) {
            return (new BelgeLineResult[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6822941829530095307L;

    protected BelgeLineResult(android.os.Parcel in) {
        this.ruhsat = ((Ruhsat) in.readValue((Ruhsat.class.getClassLoader())));
        this.ruhsatDetay = ((RuhsatDetay) in.readValue((RuhsatDetay.class.getClassLoader())));
        this.belgeLine = ((BelgeLine) in.readValue((BelgeLine.class.getClassLoader())));
        this.arac = ((Arac) in.readValue((Arac.class.getClassLoader())));
        this.kisi = ((Kisi) in.readValue((Kisi.class.getClassLoader())));
    }

    public BelgeLineResult() {
    }

    public Ruhsat getRuhsat() {
        return ruhsat;
    }

    public void setRuhsat(Ruhsat ruhsat) {
        this.ruhsat = ruhsat;
    }

    public RuhsatDetay getRuhsatDetay() {
        return ruhsatDetay;
    }

    public void setRuhsatDetay(RuhsatDetay ruhsatDetay) {
        this.ruhsatDetay = ruhsatDetay;
    }

    public BelgeLine getBelgeLine() {
        return belgeLine;
    }

    public void setBelgeLine(BelgeLine belgeLine) {
        this.belgeLine = belgeLine;
    }

    public Arac getArac() {
        return arac;
    }

    public void setArac(Arac arac) {
        this.arac = arac;
    }

    public Kisi getKisi() {
        return kisi;
    }

    public void setKisi(Kisi kisi) {
        this.kisi = kisi;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(ruhsat);
        dest.writeValue(ruhsatDetay);
        dest.writeValue(belgeLine);
        dest.writeValue(arac);
        dest.writeValue(kisi);
    }

    public int describeContents() {
        return 0;
    }

}