package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ReklamResim implements Serializable, Parcelable
{

    @SerializedName("islemTarihi")
    @Expose
    private String islemTarihi;
    @SerializedName("kayitEden")
    @Expose
    private String kayitEden;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("reklamId")
    @Expose
    private int reklamId;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("docName")
    @Expose
    private String docName;
    @SerializedName("docBinary")
    @Expose
    private String docBinary;
    @SerializedName("uzanti")
    @Expose
    private String uzanti;
    public final static Creator<ReklamResim> CREATOR = new Creator<ReklamResim>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReklamResim createFromParcel(android.os.Parcel in) {
            return new ReklamResim(in);
        }

        public ReklamResim[] newArray(int size) {
            return (new ReklamResim[size]);
        }

    }
            ;
    private final static long serialVersionUID = -8668892528006637140L;

    protected ReklamResim(android.os.Parcel in) {
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.reklamId = ((int) in.readValue((int.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.docName = ((String) in.readValue((String.class.getClassLoader())));
        this.docBinary = ((String) in.readValue((String.class.getClassLoader())));
        this.uzanti = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public ReklamResim() {
    }

    /**
     *
     * @param docName
     * @param reklamId
     * @param aciklama
     * @param islemTarihi
     * @param kayitEden
     * @param docBinary
     * @param id
     * @param uzanti
     */
    public ReklamResim(String islemTarihi, String kayitEden, int id, int reklamId, String aciklama, String docName, String docBinary, String uzanti) {
        super();
        this.islemTarihi = islemTarihi;
        this.kayitEden = kayitEden;
        this.id = id;
        this.reklamId = reklamId;
        this.aciklama = aciklama;
        this.docName = docName;
        this.docBinary = docBinary;
        this.uzanti = uzanti;
    }

    public String getIslemTarihi() {
        return islemTarihi;
    }

    public void setIslemTarihi(String islemTarihi) {
        this.islemTarihi = islemTarihi;
    }

    public String getKayitEden() {
        return kayitEden;
    }

    public void setKayitEden(String kayitEden) {
        this.kayitEden = kayitEden;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getreklamId() {
        return reklamId;
    }

    public void setreklamId(int reklamId) {
        this.reklamId = reklamId;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocBinary() {
        return docBinary;
    }

    public void setDocBinary(String docBinary) {
        this.docBinary = docBinary;
    }

    public String getUzanti() {
        return uzanti;
    }

    public void setUzanti(String uzanti) {
        this.uzanti = uzanti;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
        dest.writeValue(id);
        dest.writeValue(reklamId);
        dest.writeValue(aciklama);
        dest.writeValue(docName);
        dest.writeValue(docBinary);
        dest.writeValue(uzanti);
    }

    public int describeContents() {
        return 0;
    }

}