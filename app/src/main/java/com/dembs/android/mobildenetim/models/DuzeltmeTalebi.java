package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DuzeltmeTalebi implements Serializable, Parcelable
{

    @SerializedName("duzeltmeTalep")
    @Expose
    private DuzeltmeTalep duzeltmeTalep;
    @SerializedName("talepTurId")
    @Expose
    private int talepTurId;
    @SerializedName("talepTurAdi")
    @Expose
    private String talepTurAdi;
    @SerializedName("valid")
    @Expose
    private boolean valid;
    public final static Creator<DuzeltmeTalebi> CREATOR = new Creator<DuzeltmeTalebi>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DuzeltmeTalebi createFromParcel(android.os.Parcel in) {
            return new DuzeltmeTalebi(in);
        }

        public DuzeltmeTalebi[] newArray(int size) {
            return (new DuzeltmeTalebi[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1199356221261984771L;

    protected DuzeltmeTalebi(android.os.Parcel in) {
        this.duzeltmeTalep = ((DuzeltmeTalep) in.readValue((DuzeltmeTalep.class.getClassLoader())));
        this.talepTurId=((int) in.readValue((int.class.getClassLoader())));
        this.talepTurAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.valid = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public DuzeltmeTalebi() {
    }

    /**
     *
     * @param valid
     * @param duzeltmeTalep
     * @param talepTurAdi
     */
    public DuzeltmeTalebi(DuzeltmeTalep duzeltmeTalep,int talepTurId,String talepTurAdi, boolean valid) {
        super();
        this.duzeltmeTalep = duzeltmeTalep;
        this.talepTurId = talepTurId;
        this.talepTurAdi = talepTurAdi;
        this.valid = valid;
    }

    public DuzeltmeTalep getDuzeltmeTalep() {
        return duzeltmeTalep;
    }

    public void setDuzeltmeTalep(DuzeltmeTalep duzeltmeTalep) {
        this.duzeltmeTalep = duzeltmeTalep;
    }

    public int getTalepTurId() {
        return talepTurId;
    }

    public void setTalepTurId(int talepTurId) {
        this.talepTurId = talepTurId;
    }

    public String getTalepTurAdi() {
        return talepTurAdi;
    }

    public void setTalepTurAdi(String talepTurAdi) {
        this.talepTurAdi = talepTurAdi;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(duzeltmeTalep);
        dest.writeValue(talepTurId);
        dest.writeValue(talepTurAdi);
        dest.writeValue(valid);
    }

    public int describeContents() {
        return 0;
    }

}


