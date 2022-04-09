package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Ceza implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("denetimTurId")
    @Expose
    private int denetimTurId;
    @SerializedName("detayId")
    @Expose
    private int detayId;
    @SerializedName("plaka")
    @Expose
    private String plaka;
    @SerializedName("ruhsatNo")
    @Expose
    private String ruhsatNo;
    @SerializedName("varakaNo")
    @Expose
    private String varakaNo;
    @SerializedName("denetimFormItemId")
    @Expose
    private int denetimFormItemId;
    @SerializedName("kayitTipId")
    @Expose
    private int kayitTipId;
    @SerializedName("ihlalTarihi")
    @Expose
    private String ihlalTarihi;
    @SerializedName("ihlalMaddeId")
    @Expose
    private int ihlalMaddeId;
    @SerializedName("ihlalBendId")
    @Expose
    private int ihlalBendId;
    @SerializedName("ihlalAdres")
    @Expose
    private String ihlalAdres;
    @SerializedName("ilceId")
    @Expose
    private int ilceId;
    @SerializedName("mahalleId")
    @Expose
    private int mahalleId;
    @SerializedName("sokakId")
    @Expose
    private int sokakId;
    @SerializedName("savunmaPostaTarihi")
    @Expose
    private String savunmaPostaTarihi;
    @SerializedName("savunmaTeslimTarihi")
    @Expose
    private String savunmaTeslimTarihi;
    @SerializedName("savunmadurumId")
    @Expose
    private int savunmadurumId;
    @SerializedName("sikayetEden")
    @Expose
    private String sikayetEden;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("denetimKararId")
    @Expose
    private int denetimKararId;
    @SerializedName("denetimKararTarihi")
    @Expose
    private String denetimKararTarihi;
    @SerializedName("cezaDurumId")
    @Expose
    private int cezaDurumId;
    @SerializedName("ybsNo")
    @Expose
    private String ybsNo;
    @SerializedName("mahkeme")
    @Expose
    private boolean mahkeme;
    @SerializedName("encumenSevkTarihi")
    @Expose
    private String encumenSevkTarihi;
    @SerializedName("encumenKararTarihi")
    @Expose
    private String encumenKararTarihi;
    @SerializedName("encumenKararNo")
    @Expose
    private String encumenKararNo;
    @SerializedName("encumenKararId")
    @Expose
    private int encumenKararId;
    @SerializedName("encumenCezaTutar")
    @Expose
    private double encumenCezaTutar;
    @SerializedName("cezaTebligDurumId")
    @Expose
    private int cezaTebligDurumId;
    @SerializedName("sonTebligTarihi")
    @Expose
    private String sonTebligTarihi;
    @SerializedName("yaptirimTalep")
    @Expose
    private String yaptirimTalep;
    @SerializedName("kooperatifId")
    @Expose
    private int kooperatifId;
    @SerializedName("odaId")
    @Expose
    private int odaId;
    @SerializedName("sil")
    @Expose
    private boolean sil;
    @SerializedName("iptal")
    @Expose
    private boolean iptal;
    @SerializedName("sikayetCevapTarihi")
    @Expose
    private String sikayetCevapTarihi;
    @SerializedName("ihlalBitisSaati")
    @Expose
    private IhlalBitisSaati ihlalBitisSaati;
    @SerializedName("varakaId")
    @Expose
    private int varakaId;
    @SerializedName("islemTarihi")
    @Expose
    private String islemTarihi;
    @SerializedName("kayitEden")
    @Expose
    private String kayitEden;
    public final static Creator<Ceza> CREATOR = new Creator<Ceza>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Ceza createFromParcel(android.os.Parcel in) {
            return new Ceza(in);
        }

        public Ceza[] newArray(int size) {
            return (new Ceza[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5266016385662319239L;

    protected Ceza(android.os.Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.denetimTurId = ((int) in.readValue((int.class.getClassLoader())));
        this.detayId = ((int) in.readValue((int.class.getClassLoader())));
        this.plaka = ((String) in.readValue((String.class.getClassLoader())));
        this.ruhsatNo = ((String) in.readValue((String.class.getClassLoader())));
        this.varakaNo = ((String) in.readValue((String.class.getClassLoader())));
        this.denetimFormItemId = ((int) in.readValue((int.class.getClassLoader())));
        this.kayitTipId = ((int) in.readValue((int.class.getClassLoader())));
        this.ihlalTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalMaddeId = ((int) in.readValue((int.class.getClassLoader())));
        this.ihlalBendId = ((int) in.readValue((int.class.getClassLoader())));
        this.ihlalAdres = ((String) in.readValue((String.class.getClassLoader())));
        this.ilceId = ((int) in.readValue((int.class.getClassLoader())));
        this.mahalleId = ((int) in.readValue((int.class.getClassLoader())));
        this.sokakId = ((int) in.readValue((int.class.getClassLoader())));
        this.savunmaPostaTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.savunmaTeslimTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.savunmadurumId = ((int) in.readValue((int.class.getClassLoader())));
        this.sikayetEden = ((String) in.readValue((String.class.getClassLoader())));
        this.aciklama = ((String) in.readValue((String.class.getClassLoader())));
        this.denetimKararId = ((int) in.readValue((int.class.getClassLoader())));
        this.denetimKararTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.cezaDurumId = ((int) in.readValue((int.class.getClassLoader())));
        this.ybsNo = ((String) in.readValue((String.class.getClassLoader())));
        this.mahkeme = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.encumenSevkTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.encumenKararTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.encumenKararNo = ((String) in.readValue((String.class.getClassLoader())));
        this.encumenKararId = ((int) in.readValue((int.class.getClassLoader())));
        this.encumenCezaTutar = ((double) in.readValue((double.class.getClassLoader())));
        this.cezaTebligDurumId = ((int) in.readValue((int.class.getClassLoader())));
        this.sonTebligTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.yaptirimTalep = ((String) in.readValue((String.class.getClassLoader())));
        this.kooperatifId = ((int) in.readValue((int.class.getClassLoader())));
        this.odaId = ((int) in.readValue((int.class.getClassLoader())));
        this.sil = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.iptal = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.sikayetCevapTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalBitisSaati = ((IhlalBitisSaati) in.readValue((IhlalBitisSaati.class.getClassLoader())));
        this.varakaId = ((int) in.readValue((int.class.getClassLoader())));
        this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
        this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Ceza() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDenetimTurId() {
        return denetimTurId;
    }

    public void setDenetimTurId(int denetimTurId) {
        this.denetimTurId = denetimTurId;
    }

    public int getDetayId() {
        return detayId;
    }

    public void setDetayId(int detayId) {
        this.detayId = detayId;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public String getRuhsatNo() {
        return ruhsatNo;
    }

    public void setRuhsatNo(String ruhsatNo) {
        this.ruhsatNo = ruhsatNo;
    }

    public String getVarakaNo() {
        return varakaNo;
    }

    public void setVarakaNo(String varakaNo) {
        this.varakaNo = varakaNo;
    }

    public int getDenetimFormItemId() {
        return denetimFormItemId;
    }

    public void setDenetimFormItemId(int denetimFormItemId) {
        this.denetimFormItemId = denetimFormItemId;
    }

    public int getKayitTipId() {
        return kayitTipId;
    }

    public void setKayitTipId(int kayitTipId) {
        this.kayitTipId = kayitTipId;
    }

    public String getIhlalTarihi() {
        return ihlalTarihi;
    }

    public void setIhlalTarihi(String ihlalTarihi) {
        this.ihlalTarihi = ihlalTarihi;
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

    public String getIhlalAdres() {
        return ihlalAdres;
    }

    public void setIhlalAdres(String ihlalAdres) {
        this.ihlalAdres = ihlalAdres;
    }

    public int getIlceId() {
        return ilceId;
    }

    public void setIlceId(int ilceId) {
        this.ilceId = ilceId;
    }

    public int getMahalleId() {
        return mahalleId;
    }

    public void setMahalleId(int mahalleId) {
        this.mahalleId = mahalleId;
    }

    public int getSokakId() {
        return sokakId;
    }

    public void setSokakId(int sokakId) {
        this.sokakId = sokakId;
    }

    public String getSavunmaPostaTarihi() {
        return savunmaPostaTarihi;
    }

    public void setSavunmaPostaTarihi(String savunmaPostaTarihi) {
        this.savunmaPostaTarihi = savunmaPostaTarihi;
    }

    public String getSavunmaTeslimTarihi() {
        return savunmaTeslimTarihi;
    }

    public void setSavunmaTeslimTarihi(String savunmaTeslimTarihi) {
        this.savunmaTeslimTarihi = savunmaTeslimTarihi;
    }

    public int getSavunmadurumId() {
        return savunmadurumId;
    }

    public void setSavunmadurumId(int savunmadurumId) {
        this.savunmadurumId = savunmadurumId;
    }

    public String getSikayetEden() {
        return sikayetEden;
    }

    public void setSikayetEden(String sikayetEden) {
        this.sikayetEden = sikayetEden;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getDenetimKararId() {
        return denetimKararId;
    }

    public void setDenetimKararId(int denetimKararId) {
        this.denetimKararId = denetimKararId;
    }

    public String getDenetimKararTarihi() {
        return denetimKararTarihi;
    }

    public void setDenetimKararTarihi(String denetimKararTarihi) {
        this.denetimKararTarihi = denetimKararTarihi;
    }

    public int getCezaDurumId() {
        return cezaDurumId;
    }

    public void setCezaDurumId(int cezaDurumId) {
        this.cezaDurumId = cezaDurumId;
    }

    public String getYbsNo() {
        return ybsNo;
    }

    public void setYbsNo(String ybsNo) {
        this.ybsNo = ybsNo;
    }

    public boolean isMahkeme() {
        return mahkeme;
    }

    public void setMahkeme(boolean mahkeme) {
        this.mahkeme = mahkeme;
    }

    public String getEncumenSevkTarihi() {
        return encumenSevkTarihi;
    }

    public void setEncumenSevkTarihi(String encumenSevkTarihi) {
        this.encumenSevkTarihi = encumenSevkTarihi;
    }

    public String getEncumenKararTarihi() {
        return encumenKararTarihi;
    }

    public void setEncumenKararTarihi(String encumenKararTarihi) {
        this.encumenKararTarihi = encumenKararTarihi;
    }

    public String getEncumenKararNo() {
        return encumenKararNo;
    }

    public void setEncumenKararNo(String encumenKararNo) {
        this.encumenKararNo = encumenKararNo;
    }

    public int getEncumenKararId() {
        return encumenKararId;
    }

    public void setEncumenKararId(int encumenKararId) {
        this.encumenKararId = encumenKararId;
    }

    public double getEncumenCezaTutar() {
        return encumenCezaTutar;
    }

    public void setEncumenCezaTutar(double encumenCezaTutar) {
        this.encumenCezaTutar = encumenCezaTutar;
    }

    public int getCezaTebligDurumId() {
        return cezaTebligDurumId;
    }

    public void setCezaTebligDurumId(int cezaTebligDurumId) {
        this.cezaTebligDurumId = cezaTebligDurumId;
    }

    public String getSonTebligTarihi() {
        return sonTebligTarihi;
    }

    public void setSonTebligTarihi(String sonTebligTarihi) {
        this.sonTebligTarihi = sonTebligTarihi;
    }

    public String getYaptirimTalep() {
        return yaptirimTalep;
    }

    public void setYaptirimTalep(String yaptirimTalep) {
        this.yaptirimTalep = yaptirimTalep;
    }

    public int getKooperatifId() {
        return kooperatifId;
    }

    public void setKooperatifId(int kooperatifId) {
        this.kooperatifId = kooperatifId;
    }

    public int getOdaId() {
        return odaId;
    }

    public void setOdaId(int odaId) {
        this.odaId = odaId;
    }

    public boolean isSil() {
        return sil;
    }

    public void setSil(boolean sil) {
        this.sil = sil;
    }

    public boolean isIptal() {
        return iptal;
    }

    public void setIptal(boolean iptal) {
        this.iptal = iptal;
    }

    public String getSikayetCevapTarihi() {
        return sikayetCevapTarihi;
    }

    public void setSikayetCevapTarihi(String sikayetCevapTarihi) {
        this.sikayetCevapTarihi = sikayetCevapTarihi;
    }

    public IhlalBitisSaati getIhlalBitisSaati() {
        return ihlalBitisSaati;
    }

    public void setIhlalBitisSaati(IhlalBitisSaati ihlalBitisSaati) {
        this.ihlalBitisSaati = ihlalBitisSaati;
    }

    public int getVarakaId() {
        return varakaId;
    }

    public void setVarakaId(int varakaId) {
        this.varakaId = varakaId;
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

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(denetimTurId);
        dest.writeValue(detayId);
        dest.writeValue(plaka);
        dest.writeValue(ruhsatNo);
        dest.writeValue(varakaNo);
        dest.writeValue(denetimFormItemId);
        dest.writeValue(kayitTipId);
        dest.writeValue(ihlalTarihi);
        dest.writeValue(ihlalMaddeId);
        dest.writeValue(ihlalBendId);
        dest.writeValue(ihlalAdres);
        dest.writeValue(ilceId);
        dest.writeValue(mahalleId);
        dest.writeValue(sokakId);
        dest.writeValue(savunmaPostaTarihi);
        dest.writeValue(savunmaTeslimTarihi);
        dest.writeValue(savunmadurumId);
        dest.writeValue(sikayetEden);
        dest.writeValue(aciklama);
        dest.writeValue(denetimKararId);
        dest.writeValue(denetimKararTarihi);
        dest.writeValue(cezaDurumId);
        dest.writeValue(ybsNo);
        dest.writeValue(mahkeme);
        dest.writeValue(encumenSevkTarihi);
        dest.writeValue(encumenKararTarihi);
        dest.writeValue(encumenKararNo);
        dest.writeValue(encumenKararId);
        dest.writeValue(encumenCezaTutar);
        dest.writeValue(cezaTebligDurumId);
        dest.writeValue(sonTebligTarihi);
        dest.writeValue(yaptirimTalep);
        dest.writeValue(kooperatifId);
        dest.writeValue(odaId);
        dest.writeValue(sil);
        dest.writeValue(iptal);
        dest.writeValue(sikayetCevapTarihi);
        dest.writeValue(ihlalBitisSaati);
        dest.writeValue(varakaId);
        dest.writeValue(islemTarihi);
        dest.writeValue(kayitEden);
    }

    public int describeContents() {
        return 0;
    }

}