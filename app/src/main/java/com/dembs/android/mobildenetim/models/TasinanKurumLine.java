package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TasinanKurumLine implements Serializable, Parcelable
{

    @SerializedName("belgeTasinanKurum")
    @Expose
    private BelgeTasinanKurum belgeTasinanKurum;
    @SerializedName("kurumAdi")
    @Expose
    private String kurumAdi;
    public final static Creator<TasinanKurumLine> CREATOR = new Creator<TasinanKurumLine>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TasinanKurumLine createFromParcel(android.os.Parcel in) {
            return new TasinanKurumLine(in);
        }

        public TasinanKurumLine[] newArray(int size) {
            return (new TasinanKurumLine[size]);
        }

    }
            ;
    private final static long serialVersionUID = 863473377659840626L;

    protected TasinanKurumLine(android.os.Parcel in) {
        this.belgeTasinanKurum = ((BelgeTasinanKurum) in.readValue((BelgeTasinanKurum.class.getClassLoader())));
        this.kurumAdi = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public TasinanKurumLine() {
    }

    /**
     *
     * @param belgeTasinanKurum
     * @param kurumAdi
     */
    public TasinanKurumLine(BelgeTasinanKurum belgeTasinanKurum, String kurumAdi) {
        super();
        this.belgeTasinanKurum = belgeTasinanKurum;
        this.kurumAdi = kurumAdi;
    }

    public BelgeTasinanKurum getBelgeTasinanKurum() {
        return belgeTasinanKurum;
    }

    public void setBelgeTasinanKurum(BelgeTasinanKurum belgeTasinanKurum) {
        this.belgeTasinanKurum = belgeTasinanKurum;
    }

    public String getKurumAdi() {
        return kurumAdi;
    }

    public void setKurumAdi(String kurumAdi) {
        this.kurumAdi = kurumAdi;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(belgeTasinanKurum);
        dest.writeValue(kurumAdi);
    }

    public int describeContents() {
        return 0;
    }

}