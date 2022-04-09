package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class DenetimLine implements Serializable, Parcelable
{

    @SerializedName("denetim")
    @Expose
    private Denetim denetim;
    @SerializedName("denetimTuru")
    @Expose
    private String denetimTuru;
    @SerializedName("taksiDurakAdi")
    @Expose
    private String taksiDurakAdi;
    @SerializedName("varakaAdet")
    @Expose
    private int varakaAdet;
    public final static Creator<DenetimLine> CREATOR = new Creator<DenetimLine>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DenetimLine createFromParcel(android.os.Parcel in) {
            return new DenetimLine(in);
        }

        public DenetimLine[] newArray(int size) {
            return (new DenetimLine[size]);
        }

    }
            ;
    private final static long serialVersionUID = -8673520346347962434L;

    protected DenetimLine(android.os.Parcel in) {
        this.denetim = ((Denetim) in.readValue((Denetim.class.getClassLoader())));
        this.denetimTuru = ((String) in.readValue((String.class.getClassLoader())));
        this.taksiDurakAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.varakaAdet = ((int) in.readValue((int.class.getClassLoader())));
    }

    public DenetimLine() {
    }

    public DenetimLine(Denetim denetim, String denetimTuru, String taksiDurakAdi, int varakaAdet) {
        this.denetim = denetim;
        this.denetimTuru = denetimTuru;
        this.taksiDurakAdi = taksiDurakAdi;
        this.varakaAdet = varakaAdet;
    }

    public Denetim getDenetim() {
        return denetim;
    }

    public void setDenetim(Denetim denetim) {
        this.denetim = denetim;
    }

    public String getDenetimTuru() {
        return denetimTuru;
    }

    public void setDenetimTuru(String denetimTuru) {
        this.denetimTuru = denetimTuru;
    }

    public String getTaksiDurakAdi() {
        return taksiDurakAdi;
    }

    public void setTaksiDurakAdi(String taksiDurakAdi) {
        this.taksiDurakAdi = taksiDurakAdi;
    }

    public int getVarakaAdet() {
        return varakaAdet;
    }

    public void setVarakaAdet(int varakaAdet) {
        this.varakaAdet = varakaAdet;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(denetim);
        dest.writeValue(denetimTuru);
        dest.writeValue(taksiDurakAdi);
        dest.writeValue(varakaAdet);
    }

    public int describeContents() {
        return 0;
    }

}