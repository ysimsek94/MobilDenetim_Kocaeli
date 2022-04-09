package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class BelgeLine implements Serializable, Parcelable
{

    @SerializedName("belgeTuru")
    @Expose
    private String belgeTuru;
    @SerializedName("belge")
    @Expose
    private Belge belge;

    public final static Creator<BelgeLine> CREATOR = new Creator<BelgeLine>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BelgeLine createFromParcel(android.os.Parcel in) {
            return new BelgeLine(in);
        }

        public BelgeLine[] newArray(int size) {
            return (new BelgeLine[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4993610924591479130L;

    protected BelgeLine(android.os.Parcel in) {
        this.belgeTuru = ((String) in.readValue((String.class.getClassLoader())));
        this.belge = ((Belge) in.readValue((Belge.class.getClassLoader())));

    }

    /**
     * No args constructor for use in serialization
     *
     */
    public BelgeLine() {
    }

    /**
     *
     * @param belge
     * @param kisi
     * @param arac
     * @param belgeTuru
     */
    public BelgeLine(String belgeTuru, Belge belge, Arac arac, Kisi kisi) {
        super();
        this.belgeTuru = belgeTuru;
        this.belge = belge;

    }

    public String getBelgeTuru() {
        return belgeTuru;
    }

    public void setBelgeTuru(String belgeTuru) {
        this.belgeTuru = belgeTuru;
    }

    public Belge getBelge() {
        return belge;
    }

    public void setBelge(Belge belge) {
        this.belge = belge;
    }



    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(belgeTuru);
        dest.writeValue(belge);

    }

    public int describeContents() {
        return 0;
    }

}
