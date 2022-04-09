package com.dembs.android.mobildenetim.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VarakaEksikler  implements Serializable
{

    @SerializedName("adet")
    @Expose
    private int adet;
    @SerializedName("varakaAdet")
    @Expose
    private int varakaAdet;
    @SerializedName("fark")
    @Expose
    private int fark;
    private final static long serialVersionUID = -4504694486660233051L;

    /**
     * No args constructor for use in serialization
     *
     */
    public VarakaEksikler() {
    }

    /**
     *
     * @param varakaAdet
     * @param fark
     * @param adet
     */
    public VarakaEksikler(int adet, int varakaAdet, int fark) {
        super();
        this.adet = adet;
        this.varakaAdet = varakaAdet;
        this.fark = fark;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public int getVarakaAdet() {
        return varakaAdet;
    }

    public void setVarakaAdet(int varakaAdet) {
        this.varakaAdet = varakaAdet;
    }

    public int getFark() {
        return fark;
    }

    public void setFark(int fark) {
        this.fark = fark;
    }

}