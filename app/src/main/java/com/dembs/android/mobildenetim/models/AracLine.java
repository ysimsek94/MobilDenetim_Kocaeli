package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AracLine implements Serializable, Parcelable
{

    @SerializedName("arac")
    @Expose
    private Arac arac;
    @SerializedName("markasi")
    @Expose
    private String markasi;
    @SerializedName("aracCinsi")
    @Expose
    private String aracCinsi;
    @SerializedName("yakitTipi")
    @Expose
    private Object yakitTipi;
    @SerializedName("kullanimTuru")
    @Expose
    private String kullanimTuru;
    public final static Creator<AracLine> CREATOR = new Creator<AracLine>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AracLine createFromParcel(android.os.Parcel in) {
            return new AracLine(in);
        }

        public AracLine[] newArray(int size) {
            return (new AracLine[size]);
        }

    }
            ;
    private final static long serialVersionUID = -9109193926345530899L;

    protected AracLine(android.os.Parcel in) {
        this.arac = ((Arac) in.readValue((Arac.class.getClassLoader())));
        this.markasi = ((String) in.readValue((String.class.getClassLoader())));
        this.aracCinsi = ((String) in.readValue((String.class.getClassLoader())));
        this.yakitTipi = ((Object) in.readValue((Object.class.getClassLoader())));
        this.kullanimTuru = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public AracLine() {
    }

    /**
     *
     * @param yakitTipi
     * @param aracCinsi
     * @param kullanimTuru
     * @param arac
     * @param markasi
     */
    public AracLine(Arac arac, String markasi, String aracCinsi, Object yakitTipi, String kullanimTuru) {
        super();
        this.arac = arac;
        this.markasi = markasi;
        this.aracCinsi = aracCinsi;
        this.yakitTipi = yakitTipi;
        this.kullanimTuru = kullanimTuru;
    }

    public Arac getArac() {
        return arac;
    }

    public void setArac(Arac arac) {
        this.arac = arac;
    }

    public String getMarkasi() {
        return markasi;
    }

    public void setMarkasi(String markasi) {
        this.markasi = markasi;
    }

    public String getAracCinsi() {
        return aracCinsi;
    }

    public void setAracCinsi(String aracCinsi) {
        this.aracCinsi = aracCinsi;
    }

    public Object getYakitTipi() {
        return yakitTipi;
    }

    public void setYakitTipi(Object yakitTipi) {
        this.yakitTipi = yakitTipi;
    }

    public String getKullanimTuru() {
        return kullanimTuru;
    }

    public void setKullanimTuru(String kullanimTuru) {
        this.kullanimTuru = kullanimTuru;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(arac);
        dest.writeValue(markasi);
        dest.writeValue(aracCinsi);
        dest.writeValue(yakitTipi);
        dest.writeValue(kullanimTuru);
    }

    public int describeContents() {
        return 0;
    }

}