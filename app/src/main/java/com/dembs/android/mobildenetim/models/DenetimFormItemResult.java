package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class DenetimFormItemResult implements Serializable, Parcelable
{


    @SerializedName("denetimId")
    @Expose
    private int denetimId;
    @SerializedName("denetimFormItems")
    @Expose
    private List<DenetimFormItemLine> denetimFormItemLineList = null;
    public final static Creator<DenetimFormItemResult> CREATOR = new Creator<DenetimFormItemResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DenetimFormItemResult createFromParcel(android.os.Parcel in) {
            return new DenetimFormItemResult(in);
        }

        public DenetimFormItemResult[] newArray(int size) {
            return (new DenetimFormItemResult[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4570044159751666466L;

    protected DenetimFormItemResult(android.os.Parcel in) {
        this.denetimId = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.denetimFormItemLineList, (com.dembs.android.mobildenetim.models.DenetimFormItemLine.class.getClassLoader()));
    }

    public DenetimFormItemResult() {
    }

    public DenetimFormItemResult(int denetimId, List<DenetimFormItemLine> denetimFormItemLineList) {
        this.denetimId = denetimId;
        this.denetimFormItemLineList = denetimFormItemLineList;
    }

    public int getDenetimId() {
        return denetimId;
    }

    public void setDenetimId(int denetimId) {
        this.denetimId = denetimId;
    }

    public List<DenetimFormItemLine> getDenetimFormItemLineList() {
        return denetimFormItemLineList;
    }

    public void setDenetimFormItemLineList(List<DenetimFormItemLine> denetimFormItemLineList) {
        this.denetimFormItemLineList = denetimFormItemLineList;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(denetimId);
        dest.writeList(denetimFormItemLineList);
    }

    public int describeContents() {
        return 0;
    }

}