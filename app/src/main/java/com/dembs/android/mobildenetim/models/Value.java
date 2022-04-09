package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Value implements Serializable, Parcelable
{

    @SerializedName("ticks")
    @Expose
    private long ticks;
    @SerializedName("days")
    @Expose
    private int days;
    @SerializedName("hours")
    @Expose
    private int hours;
    @SerializedName("milliseconds")
    @Expose
    private int milliseconds;
    @SerializedName("minutes")
    @Expose
    private int minutes;
    @SerializedName("seconds")
    @Expose
    private int seconds;
    @SerializedName("totalDays")
    @Expose
    private double totalDays;
    @SerializedName("totalHours")
    @Expose
    private double totalHours;
    @SerializedName("totalMilliseconds")
    @Expose
    private int totalMilliseconds;
    @SerializedName("totalMinutes")
    @Expose
    private int totalMinutes;
    @SerializedName("totalSeconds")
    @Expose
    private int totalSeconds;
    public final static Creator<Value> CREATOR = new Creator<Value>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Value createFromParcel(android.os.Parcel in) {
            return new Value(in);
        }

        public Value[] newArray(int size) {
            return (new Value[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6314891934929806784L;

    protected Value(android.os.Parcel in) {
        this.ticks = ((long) in.readValue((long.class.getClassLoader())));
        this.days = ((int) in.readValue((int.class.getClassLoader())));
        this.hours = ((int) in.readValue((int.class.getClassLoader())));
        this.milliseconds = ((int) in.readValue((int.class.getClassLoader())));
        this.minutes = ((int) in.readValue((int.class.getClassLoader())));
        this.seconds = ((int) in.readValue((int.class.getClassLoader())));
        this.totalDays = ((double) in.readValue((double.class.getClassLoader())));
        this.totalHours = ((double) in.readValue((double.class.getClassLoader())));
        this.totalMilliseconds = ((int) in.readValue((int.class.getClassLoader())));
        this.totalMinutes = ((int) in.readValue((int.class.getClassLoader())));
        this.totalSeconds = ((int) in.readValue((int.class.getClassLoader())));
    }

    public Value() {
    }

    public long getTicks() {
        return ticks;
    }

    public void setTicks(long ticks) {
        this.ticks = ticks;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public double getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(double totalDays) {
        this.totalDays = totalDays;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public int getTotalMilliseconds() {
        return totalMilliseconds;
    }

    public void setTotalMilliseconds(int totalMilliseconds) {
        this.totalMilliseconds = totalMilliseconds;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public int getTotalSeconds() {
        return totalSeconds;
    }

    public void setTotalSeconds(int totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(ticks);
        dest.writeValue(days);
        dest.writeValue(hours);
        dest.writeValue(milliseconds);
        dest.writeValue(minutes);
        dest.writeValue(seconds);
        dest.writeValue(totalDays);
        dest.writeValue(totalHours);
        dest.writeValue(totalMilliseconds);
        dest.writeValue(totalMinutes);
        dest.writeValue(totalSeconds);
    }

    public int describeContents() {
        return 0;
    }

}