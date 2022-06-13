package com.dembs.android.mobildenetim.models;

import java.io.Serializable;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


    public class BelgeOzetLine implements Serializable, Parcelable
    {

        @SerializedName("belgeId")
        @Expose
        private int belgeId;
        @SerializedName("belgeTuru")
        @Expose
        private String belgeTuru;
        @SerializedName("plaka")
        @Expose
        private String plaka;
        @SerializedName("adi")
        @Expose
        private String adi;
        @SerializedName("soyadi")
        @Expose
        private String soyadi;
        @SerializedName("sayisi")
        @Expose
        private int sayisi;
        @SerializedName("kisiId")
        @Expose
        private int kisiId;
        @SerializedName("aracId")
        @Expose
        private int aracId;
        @SerializedName("evrakTarihi")
        @Expose
        private String evrakTarihi;
        @SerializedName("gecerlilikTarihi")
        @Expose
        private String gecerlilikTarihi;
        @SerializedName("teslimTarihi")
        @Expose
        private String teslimTarihi;
        @SerializedName("sivilPlaka")
        @Expose
        private String sivilPlaka;
        @SerializedName("islemTarihi")
        @Expose
        private String islemTarihi;
        @SerializedName("kayitEden")
        @Expose
        private String kayitEden;
        public final static Creator<BelgeOzetLine> CREATOR = new Creator<BelgeOzetLine>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public BelgeOzetLine createFromParcel(android.os.Parcel in) {
                return new BelgeOzetLine(in);
            }

            public BelgeOzetLine[] newArray(int size) {
                return (new BelgeOzetLine[size]);
            }

        }
                ;
        private final static long serialVersionUID = 6402752088717876897L;

        protected BelgeOzetLine(android.os.Parcel in) {
            this.belgeId = ((int) in.readValue((int.class.getClassLoader())));
            this.belgeTuru = ((String) in.readValue((String.class.getClassLoader())));
            this.plaka = ((String) in.readValue((String.class.getClassLoader())));
            this.adi = ((String) in.readValue((String.class.getClassLoader())));
            this.soyadi = ((String) in.readValue((String.class.getClassLoader())));
            this.sayisi = ((int) in.readValue((int.class.getClassLoader())));
            this.kisiId = ((int) in.readValue((int.class.getClassLoader())));
            this.aracId = ((int) in.readValue((int.class.getClassLoader())));
            this.evrakTarihi = ((String) in.readValue((String.class.getClassLoader())));
            this.gecerlilikTarihi = ((String) in.readValue((String.class.getClassLoader())));
            this.teslimTarihi = ((String) in.readValue((String.class.getClassLoader())));
            this.sivilPlaka = ((String) in.readValue((String.class.getClassLoader())));
            this.islemTarihi = ((String) in.readValue((String.class.getClassLoader())));
            this.kayitEden = ((String) in.readValue((String.class.getClassLoader())));
        }

        public BelgeOzetLine() {
        }

        public int getBelgeId() {
            return belgeId;
        }

        public void setBelgeId(int belgeId) {
            this.belgeId = belgeId;
        }

        public String getBelgeTuru() {
            return belgeTuru;
        }

        public void setBelgeTuru(String belgeTuru) {
            this.belgeTuru = belgeTuru;
        }

        public String getPlaka() {
            return plaka;
        }

        public void setPlaka(String plaka) {
            this.plaka = plaka;
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

        public int getSayisi() {
            return sayisi;
        }

        public void setSayisi(int sayisi) {
            this.sayisi = sayisi;
        }

        public int getKisiId() {
            return kisiId;
        }

        public void setKisiId(int kisiId) {
            this.kisiId = kisiId;
        }

        public int getAracId() {
            return aracId;
        }

        public void setAracId(int aracId) {
            this.aracId = aracId;
        }

        public String getEvrakTarihi() {
            return evrakTarihi;
        }

        public void setEvrakTarihi(String evrakTarihi) {
            this.evrakTarihi = evrakTarihi;
        }

        public String getGecerlilikTarihi() {
            return gecerlilikTarihi;
        }

        public void setGecerlilikTarihi(String gecerlilikTarihi) {
            this.gecerlilikTarihi = gecerlilikTarihi;
        }

        public String getTeslimTarihi() {
            return teslimTarihi;
        }

        public void setTeslimTarihi(String teslimTarihi) {
            this.teslimTarihi = teslimTarihi;
        }

        public String getSivilPlaka() {
            return sivilPlaka;
        }

        public void setSivilPlaka(String sivilPlaka) {
            this.sivilPlaka = sivilPlaka;
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
            dest.writeValue(belgeId);
            dest.writeValue(belgeTuru);
            dest.writeValue(plaka);
            dest.writeValue(adi);
            dest.writeValue(soyadi);
            dest.writeValue(sayisi);
            dest.writeValue(kisiId);
            dest.writeValue(aracId);
            dest.writeValue(evrakTarihi);
            dest.writeValue(gecerlilikTarihi);
            dest.writeValue(teslimTarihi);
            dest.writeValue(sivilPlaka);
            dest.writeValue(islemTarihi);
            dest.writeValue(kayitEden);
        }

        public int describeContents() {
            return 0;
        }


    }
