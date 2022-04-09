package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class IhlalBitisSaati implements Serializable, Parcelable
{

    @SerializedName("hasValue")
    @Expose
    private boolean hasValue;
    @SerializedName("value")
    @Expose
    private Value value;
    public final static Creator<IhlalBitisSaati> CREATOR = new Creator<IhlalBitisSaati>() {


        @SuppressWarnings({
                "unchecked"
        })
        public IhlalBitisSaati createFromParcel(android.os.Parcel in) {
            return new IhlalBitisSaati(in);
        }

        public IhlalBitisSaati[] newArray(int size) {
            return (new IhlalBitisSaati[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5677499207836797085L;

    protected IhlalBitisSaati(android.os.Parcel in) {
        this.hasValue = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.value = ((Value) in.readValue((Value.class.getClassLoader())));
    }

    public IhlalBitisSaati() {
    }

    public boolean isHasValue() {
        return hasValue;
    }

    public void setHasValue(boolean hasValue) {
        this.hasValue = hasValue;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(hasValue);
        dest.writeValue(value);
    }

    public int describeContents() {
        return 0;
    }

}