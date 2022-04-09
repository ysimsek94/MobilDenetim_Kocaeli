package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Konum implements Serializable, Parcelable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("coordinates")
    @Expose
    private List<Double> coordinates = new ArrayList<>();
    public final static Creator<Konum> CREATOR = new Creator<Konum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Konum createFromParcel(android.os.Parcel in) {
            return new Konum(in);
        }

        public Konum[] newArray(int size) {
            return (new Konum[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7759347585728350620L;

    protected Konum(android.os.Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.coordinates, (Double.class.getClassLoader()));
    }

    public Konum() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeList(coordinates);
    }


    public int describeContents() {
        return 0;
    }

}