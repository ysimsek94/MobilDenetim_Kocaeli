package com.dembs.android.mobildenetim.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VarakaSms implements Serializable , Parcelable
{

    @SerializedName("telNo")
    @Expose
    private String telNo;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("denetimId")
    @Expose
    private int denetimId;
    @SerializedName("kisiId")
    @Expose
    private int kisiId;
    @SerializedName("itemId")
    @Expose
    private int itemId;
    private final static long serialVersionUID = -693101124432294667L;

    /**
     * No args constructor for use in serialization
     *
     */
    public VarakaSms() {
    }

    /**
     *
     * @param itemId
     * @param denetimId
     * @param kisiId
     * @param message
     * @param telNo
     */
    public VarakaSms(String telNo, String message, int denetimId, int kisiId, int itemId) {
        super();
        this.telNo = telNo;
        this.message = message;
        this.denetimId = denetimId;
        this.kisiId = kisiId;
        this.itemId = itemId;
    }

    protected VarakaSms(Parcel in) {
        telNo = in.readString();
        message = in.readString();
        denetimId = in.readInt();
        kisiId = in.readInt();
        itemId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(telNo);
        dest.writeString(message);
        dest.writeInt(denetimId);
        dest.writeInt(kisiId);
        dest.writeInt(itemId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VarakaSms> CREATOR = new Creator<VarakaSms>() {
        @Override
        public VarakaSms createFromParcel(Parcel in) {
            return new VarakaSms(in);
        }

        @Override
        public VarakaSms[] newArray(int size) {
            return new VarakaSms[size];
        }
    };

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDenetimId() {
        return denetimId;
    }

    public void setDenetimId(int denetimId) {
        this.denetimId = denetimId;
    }

    public int getKisiId() {
        return kisiId;
    }

    public void setKisiId(int kisiId) {
        this.kisiId = kisiId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}