package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class DenetimFormItem2 implements Serializable, Parcelable
{

    @SerializedName("denetimId")
    @Expose
    private int denetimId;
    @SerializedName("denetimFormItems")
    @Expose
    private List<DenetimFormItemLine> denetimFormItems = null;
    public final static Creator<DenetimFormItem2> CREATOR = new Creator<DenetimFormItem2>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DenetimFormItem2 createFromParcel(android.os.Parcel in) {
            return new DenetimFormItem2(in);
        }

        public DenetimFormItem2 [] newArray(int size) {
            return (new DenetimFormItem2[size]);
        }

    }
            ;
    private final static long serialVersionUID = -5735957564542726887L;

    protected DenetimFormItem2(android.os.Parcel in) {
        this.denetimId = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.denetimFormItems, (com.dembs.android.mobildenetim.models.DenetimFormItem.class.getClassLoader()));
    }

    public DenetimFormItem2() {
    }

    public int getDenetimId() {
        return denetimId;
    }

    public void setDenetimId(int denetimId) {
        this.denetimId = denetimId;
    }

    public List<DenetimFormItemLine> getDenetimFormItems() {
        return denetimFormItems;
    }

    public void setDenetimFormItems(List<DenetimFormItemLine> denetimFormItems) {
        this.denetimFormItems = denetimFormItems;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(denetimId);
        dest.writeList(denetimFormItems);
    }

    public int describeContents() {
        return 0;
    }

}


