package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class DenetimDeleteKontrol implements Serializable, Parcelable
{

    @SerializedName("varakaAdet")
    @Expose
    private int varakaAdet;
    @SerializedName("duzeltmeTalepAdet")
    @Expose
    private int duzeltmeTalepAdet;
    @SerializedName("denetimFormuAdet")
    @Expose
    private int denetimFormuAdet;
    @SerializedName("varakaResimAdet")
    @Expose
    private int varakaResimAdet;
    @SerializedName("denetleyenAdet")
    @Expose
    private int denetleyenAdet;
    public final static Creator<DenetimDeleteKontrol> CREATOR = new Creator<DenetimDeleteKontrol>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DenetimDeleteKontrol createFromParcel(android.os.Parcel in) {
            return new DenetimDeleteKontrol(in);
        }

        public DenetimDeleteKontrol[] newArray(int size) {
            return (new DenetimDeleteKontrol[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1979430704739331455L;

    protected DenetimDeleteKontrol(android.os.Parcel in) {
        this.varakaAdet = ((int) in.readValue((int.class.getClassLoader())));
        this.duzeltmeTalepAdet = ((int) in.readValue((int.class.getClassLoader())));
        this.denetimFormuAdet = ((int) in.readValue((int.class.getClassLoader())));
        this.varakaResimAdet = ((int) in.readValue((int.class.getClassLoader())));
        this.denetleyenAdet = ((int) in.readValue((int.class.getClassLoader())));
    }

    public DenetimDeleteKontrol() {
    }

    public int getVarakaAdet() {
        return varakaAdet;
    }

    public void setVarakaAdet(int varakaAdet) {
        this.varakaAdet = varakaAdet;
    }

    public int getDuzeltmeTalepAdet() {
        return duzeltmeTalepAdet;
    }

    public void setDuzeltmeTalepAdet(int duzeltmeTalepAdet) {
        this.duzeltmeTalepAdet = duzeltmeTalepAdet;
    }

    public int getDenetimFormuAdet() {
        return denetimFormuAdet;
    }

    public void setDenetimFormuAdet(int denetimFormuAdet) {
        this.denetimFormuAdet = denetimFormuAdet;
    }

    public int getVarakaResimAdet() {
        return varakaResimAdet;
    }

    public void setVarakaResimAdet(int varakaResimAdet) {
        this.varakaResimAdet = varakaResimAdet;
    }

    public int getDenetleyenAdet() {
        return denetleyenAdet;
    }

    public void setDenetleyenAdet(int denetleyenAdet) {
        this.denetleyenAdet = denetleyenAdet;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(varakaAdet);
        dest.writeValue(duzeltmeTalepAdet);
        dest.writeValue(denetimFormuAdet);
        dest.writeValue(varakaResimAdet);
        dest.writeValue(denetleyenAdet);
    }

    public int describeContents() {
        return 0;
    }

}