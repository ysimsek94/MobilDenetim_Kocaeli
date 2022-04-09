package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class DenetimFormItem implements Serializable, Parcelable
{
    public DenetimFormItem(int denetimId, int formItemId, boolean secim, boolean varaka) {
        this.denetimId = denetimId;
        this.formItemId = formItemId;
        this.secim = secim;
        this.varaka = varaka;
    }

    @SerializedName("denetimId")
    @Expose
    private int denetimId;
    @SerializedName("formItemId")
    @Expose
    private int formItemId;
    @SerializedName("secim")
    @Expose
    private boolean secim;
    @SerializedName("varaka")
    @Expose
    private boolean varaka;
    public final static Creator<DenetimFormItem> CREATOR = new Creator<DenetimFormItem>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DenetimFormItem createFromParcel(android.os.Parcel in) {
            return new DenetimFormItem(in);
        }

        public DenetimFormItem[] newArray(int size) {
            return (new DenetimFormItem[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1867724089544133938L;

    protected DenetimFormItem(android.os.Parcel in) {
        this.denetimId = ((int) in.readValue((int.class.getClassLoader())));
        this.formItemId = ((int) in.readValue((int.class.getClassLoader())));
        this.secim = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.varaka = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    public DenetimFormItem() {
    }

    public int getDenetimId() {
        return denetimId;
    }

    public void setDenetimId(int denetimId) {
        this.denetimId = denetimId;
    }

    public int getFormItemId() {
        return formItemId;
    }

    public void setFormItemId(int formItemId) {
        this.formItemId = formItemId;
    }

    public boolean isSecim() {
        return secim;
    }

    public void setSecim(boolean secim) {
        this.secim = secim;
    }

    public boolean isVaraka() {
        return varaka;
    }

    public void setVaraka(boolean varaka) {
        this.varaka = varaka;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(denetimId);
        dest.writeValue(formItemId);
        dest.writeValue(secim);
        dest.writeValue(varaka);
    }

    public int describeContents() {
        return 0;
    }

}