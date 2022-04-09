package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kisi implements Serializable, Parcelable
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
    @SerializedName("tcVergiNo")
    @Expose
    private String tcVergiNo;
    @SerializedName("turId")
    @Expose
    private int turId;
    @SerializedName("adi")
    @Expose
    private String adi;
    @SerializedName("soyadi")
    @Expose
    private String soyadi;
    @SerializedName("babaAdi")
    @Expose
    private String babaAdi;
    @SerializedName("dogumTarihi")
    @Expose
    private String dogumTarihi;
    @SerializedName("cinsiyet")
    @Expose
    private String cinsiyet;
    @SerializedName("dogumYeri")
    @Expose
    private String dogumYeri;
    @SerializedName("ilceId")
    @Expose
    private int ilceId;
    @SerializedName("adres")
    @Expose
    private String adres;
    @SerializedName("ybsSicilNo")
    @Expose
    private String ybsSicilNo;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("telefon")
    @Expose
    private String telefon;
    @SerializedName("telefon2")
    @Expose
    private String telefon2;
    @SerializedName("vergiDairesi")
    @Expose
    private String vergiDairesi;
    @SerializedName("ehliyetTurId")
    @Expose
    private int ehliyetTurId;
    @SerializedName("ehliyetNo")
    @Expose
    private String ehliyetNo;
    @SerializedName("kanGrupId")
    @Expose
    private int kanGrupId;
    @SerializedName("egitimDurumId")
    @Expose
    private int egitimDurumId;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("sil")
    @Expose
    private boolean sil;
    public final static Creator<Kisi> CREATOR = new Creator<Kisi>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Kisi createFromParcel(android.os.Parcel in) {
            return new Kisi(in);
        }

        public Kisi[] newArray(int size) {
            return (new Kisi[size]);
        }

    }
            ;
    private final static long serialVersionUID = 3983776564403678511L;

    protected Kisi(android.os.Parcel in) {
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.tcVergiNo = ((String) in.readValue((String.class.getClassLoader())));
        this.turId = ((int) in.readValue((int.class.getClassLoader())));
        this.adi = ((String) in.readValue((String.class.getClassLoader())));
        this.soyadi = ((String) in.readValue((String.class.getClassLoader())));
        this.babaAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.dogumTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.cinsiyet = ((String) in.readValue((String.class.getClassLoader())));
        this.dogumYeri = ((String) in.readValue((String.class.getClassLoader())));
        this.ilceId = ((int) in.readValue((int.class.getClassLoader())));
        this.adres = ((String) in.readValue((String.class.getClassLoader())));
        this.ybsSicilNo = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.telefon = ((String) in.readValue((String.class.getClassLoader())));
        this.telefon2 = ((String) in.readValue((String.class.getClassLoader())));
        this.vergiDairesi = ((String) in.readValue((String.class.getClassLoader())));
        this.ehliyetTurId = ((int) in.readValue((int.class.getClassLoader())));
        this.ehliyetNo = ((String) in.readValue((String.class.getClassLoader())));
        this.kanGrupId = ((int) in.readValue((int.class.getClassLoader())));
        this.egitimDurumId = ((int) in.readValue((int.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.sil = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Kisi() {
    }

    /**
     *
     * @param ybsSicilNo
     * @param islemTarihi
     * @param kanGrupId
     * @param adi
     * @param dogumYeri
     * @param tcVergiNo
     * @param ehliyetNo
     * @param ilceId
     * @param vergiDairesi
     * @param babaAdi
     * @param dogumTarihi
     * @param aciklama
     * @param turId
     * @param telefon
     * @param cinsiyet
     * @param sil
     * @param kayitEden
     * @param soyadi
     * @param id
     * @param adres
     * @param ehliyetTurId
     * @param email
     * @param egitimDurumId
     * @param telefon2
     */
    public Kisi(String islemTarihi, String kayitEden, int id, String tcVergiNo, int turId, String adi, String soyadi, String babaAdi, String dogumTarihi, String cinsiyet, String dogumYeri, int ilceId, String adres, String ybsSicilNo, String email, String telefon, String telefon2, String vergiDairesi, int ehliyetTurId, String ehliyetNo, int kanGrupId, int egitimDurumId, String aciklama, boolean sil) {
        super();
        this.islemTarihi = islemTarihi;
        this.kayitEden = kayitEden;
        this.id = id;
        this.tcVergiNo = tcVergiNo;
        this.turId = turId;
        this.adi = adi;
        this.soyadi = soyadi;
        this.babaAdi = babaAdi;
        this.dogumTarihi = dogumTarihi;
        this.cinsiyet = cinsiyet;
        this.dogumYeri = dogumYeri;
        this.ilceId = ilceId;
        this.adres = adres;
        this.ybsSicilNo = ybsSicilNo;
        this.email = email;
        this.telefon = telefon;
        this.telefon2 = telefon2;
        this.vergiDairesi = vergiDairesi;
        this.ehliyetTurId = ehliyetTurId;
        this.ehliyetNo = ehliyetNo;
        this.kanGrupId = kanGrupId;
        this.egitimDurumId = egitimDurumId;
        this.aciklama = aciklama;
        this.sil = sil;
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

    public String getTcVergiNo() {
        return tcVergiNo;
    }

    public void setTcVergiNo(String tcVergiNo) {
        this.tcVergiNo = tcVergiNo;
    }

    public int getTurId() {
        return turId;
    }

    public void setTurId(int turId) {
        this.turId = turId;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getBabaAdi() {
        return babaAdi;
    }

    public void setBabaAdi(String babaAdi) {
        this.babaAdi = babaAdi;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getDogumYeri() {
        return dogumYeri;
    }

    public void setDogumYeri(String dogumYeri) {
        this.dogumYeri = dogumYeri;
    }

    public int getIlceId() {
        return ilceId;
    }

    public void setIlceId(int ilceId) {
        this.ilceId = ilceId;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getYbsSicilNo() {
        return ybsSicilNo;
    }

    public void setYbsSicilNo(String ybsSicilNo) {
        this.ybsSicilNo = ybsSicilNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTelefon2() {
        return telefon2;
    }

    public void setTelefon2(String telefon2) {
        this.telefon2 = telefon2;
    }

    public String getVergiDairesi() {
        return vergiDairesi;
    }

    public void setVergiDairesi(String vergiDairesi) {
        this.vergiDairesi = vergiDairesi;
    }

    public int getEhliyetTurId() {
        return ehliyetTurId;
    }

    public void setEhliyetTurId(int ehliyetTurId) {
        this.ehliyetTurId = ehliyetTurId;
    }

    public String getEhliyetNo() {
        return ehliyetNo;
    }

    public void setEhliyetNo(String ehliyetNo) {
        this.ehliyetNo = ehliyetNo;
    }

    public int getKanGrupId() {
        return kanGrupId;
    }

    public void setKanGrupId(int kanGrupId) {
        this.kanGrupId = kanGrupId;
    }

    public int getEgitimDurumId() {
        return egitimDurumId;
    }

    public void setEgitimDurumId(int egitimDurumId) {
        this.egitimDurumId = egitimDurumId;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public boolean isSil() {
        return sil;
    }

    public void setSil(boolean sil) {
        this.sil = sil;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
        dest.writeValue(id);
        dest.writeValue(tcVergiNo);
        dest.writeValue(turId);
        dest.writeValue(adi);
        dest.writeValue(soyadi);
        dest.writeValue(babaAdi);
        dest.writeValue(dogumTarihi);
        dest.writeValue(cinsiyet);
        dest.writeValue(dogumYeri);
        dest.writeValue(ilceId);
        dest.writeValue(adres);
        dest.writeValue(ybsSicilNo);
        dest.writeValue(email);
        dest.writeValue(telefon);
        dest.writeValue(telefon2);
        dest.writeValue(vergiDairesi);
        dest.writeValue(ehliyetTurId);
        dest.writeValue(ehliyetNo);
        dest.writeValue(kanGrupId);
        dest.writeValue(egitimDurumId);
        dest.writeValue(aciklama);
        dest.writeValue(sil);
    }

    public int describeContents() {
        return 0;
    }

}