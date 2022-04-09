package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CezaLine implements Serializable, Parcelable
{

    @SerializedName("ceza")
    @Expose
    private Ceza ceza;
    @SerializedName("denetimTuru")
    @Expose
    private String denetimTuru;
    @SerializedName("kayitTipi")
    @Expose
    private String kayitTipi;
    @SerializedName("ihlalMaddeAdi")
    @Expose
    private String ihlalMaddeAdi;
    @SerializedName("ihlalBendAdi")
    @Expose
    private String ihlalBendAdi;
    @SerializedName("ihlalBendAciklama")
    @Expose
    private String ihlalBendAciklama;
    public final static Creator<CezaLine> CREATOR = new Creator<CezaLine>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CezaLine createFromParcel(android.os.Parcel in) {
            return new CezaLine(in);
        }

        public CezaLine[] newArray(int size) {
            return (new CezaLine[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6414469314993626617L;

    protected CezaLine(android.os.Parcel in) {
        this.ceza = ((Ceza) in.readValue((Ceza.class.getClassLoader())));
        this.denetimTuru = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitTipi = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalMaddeAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalBendAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalBendAciklama = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CezaLine() {
    }

    public Ceza getCeza() {
        return ceza;
    }

    public void setCeza(Ceza ceza) {
        this.ceza = ceza;
    }

    public String getDenetimTuru() {
        return denetimTuru;
    }

    public void setDenetimTuru(String denetimTuru) {
        this.denetimTuru = denetimTuru;
    }

    public String getKayitTipi() {
        return kayitTipi;
    }

    public void setKayitTipi(String kayitTipi) {
        this.kayitTipi = kayitTipi;
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

    public String getIhlalBendAciklama() {
        return ihlalBendAciklama;
    }

    public void setIhlalBendAciklama(String ihlalBendAciklama) {
        this.ihlalBendAciklama = ihlalBendAciklama;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(ceza);
        dest.writeValue(denetimTuru);
        dest.writeValue(kayitTipi);
        dest.writeValue(ihlalMaddeAdi);
        dest.writeValue(ihlalBendAdi);
        dest.writeValue(ihlalBendAciklama);
    }

    public int describeContents() {
        return 0;
    }

}