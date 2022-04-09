package com.dembs.android.mobildenetim.models;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Token implements Serializable, Parcelable
{

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("ekipId")
    @Expose
    private int ekipId;
    @SerializedName("kullaniciId")
    @Expose
    private int kullaniciId;
    @SerializedName("tcKimlikNo")
    @Expose
    private String tcKimlikNo;
    @SerializedName("token")
    @Expose
    private String token;
    public final static Creator<Token> CREATOR = new Creator<Token>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Token createFromParcel(android.os.Parcel in) {
            return new Token(in);
        }

        public Token[] newArray(int size) {
            return (new Token[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1388413141731987850L;

    protected Token(android.os.Parcel in) {
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.ekipId = ((int) in.readValue((int.class.getClassLoader())));
        this.kullaniciId = ((int) in.readValue((int.class.getClassLoader())));
        this.tcKimlikNo = ((String) in.readValue((String.class.getClassLoader())));
        this.token = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Token() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEkipId() {
        return ekipId;
    }

    public void setEkipId(int ekipId) {
        this.ekipId = ekipId;
    }

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(username);
        dest.writeValue(ekipId);
        dest.writeValue(kullaniciId);
        dest.writeValue(tcKimlikNo);
        dest.writeValue(token);
    }

    public int describeContents() {
        return 0;
    }

}