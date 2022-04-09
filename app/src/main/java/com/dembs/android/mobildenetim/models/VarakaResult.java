package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class VarakaResult implements Serializable, Parcelable
{

    @SerializedName("varaka")
    @Expose
    private Varaka varaka;
    @SerializedName("ihlalMaddeAdi")
    @Expose
    private String ihlalMaddeAdi;
    @SerializedName("ihlalBendAdi")
    @Expose
    private String ihlalBendAdi;
    @SerializedName("ihlalMaddeId")
    @Expose
    private int ihlalMaddeId;
    @SerializedName("ihlalBendId")
    @Expose
    private int ihlalBendId;
    @SerializedName("varakaHasImage")
    @Expose
    private boolean varakaHasImage;
    public final static Creator<VarakaResult> CREATOR = new Creator<VarakaResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VarakaResult createFromParcel(android.os.Parcel in) {
            return new VarakaResult(in);
        }

        public VarakaResult[] newArray(int size) {
            return (new VarakaResult[size]);
        }

    }
            ;
    private final static long serialVersionUID = -892924097097740572L;

    protected VarakaResult(android.os.Parcel in) {
        this.varaka = ((Varaka) in.readValue((Varaka.class.getClassLoader())));
        this.ihlalMaddeAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalBendAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalMaddeId = ((int) in.readValue((int.class.getClassLoader())));
        this.ihlalBendId = ((int) in.readValue((int.class.getClassLoader())));
        this.varakaHasImage = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public VarakaResult() {
    }

    /**
     *
     * @param ihlalMaddeId
     * @param varaka
     * @param ihlalBendAdi
     * @param ihlalBendId
     * @param varakaHasImage
     * @param ihlalMaddeAdi
     */
    public VarakaResult(Varaka varaka, String ihlalMaddeAdi, String ihlalBendAdi, int ihlalMaddeId, int ihlalBendId, boolean varakaHasImage) {
        super();
        this.varaka = varaka;
        this.ihlalMaddeAdi = ihlalMaddeAdi;
        this.ihlalBendAdi = ihlalBendAdi;
        this.ihlalMaddeId = ihlalMaddeId;
        this.ihlalBendId = ihlalBendId;
        this.varakaHasImage = varakaHasImage;
    }

    public Varaka getVaraka() {
        return varaka;
    }

    public void setVaraka(Varaka varaka) {
        this.varaka = varaka;
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

    public int getIhlalMaddeId() {
        return ihlalMaddeId;
    }

    public void setIhlalMaddeId(int ihlalMaddeId) {
        this.ihlalMaddeId = ihlalMaddeId;
    }

    public int getIhlalBendId() {
        return ihlalBendId;
    }

    public void setIhlalBendId(int ihlalBendId) {
        this.ihlalBendId = ihlalBendId;
    }

    public boolean isVarakaHasImage() {
        return varakaHasImage;
    }

    public void setVarakaHasImage(boolean varakaHasImage) {
        this.varakaHasImage = varakaHasImage;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(varaka);
        dest.writeValue(ihlalMaddeAdi);
        dest.writeValue(ihlalBendAdi);
        dest.writeValue(ihlalMaddeId);
        dest.writeValue(ihlalBendId);
        dest.writeValue(varakaHasImage);
    }

    public int describeContents() {
        return 0;
    }

}