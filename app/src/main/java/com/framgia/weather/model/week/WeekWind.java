package com.framgia.weather.model.week;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TungAnh on 5/10/17.
 */
public class WeekWind {
    @SerializedName("speed")
    @Expose
    private double mSpeed;
    @SerializedName("deg")
    @Expose
    private double mDeg;

    public double getSpeed() {
        return mSpeed;
    }

    public void setSpeed(double speed) {
        mSpeed = speed;
    }

    public double getDeg() {
        return mDeg;
    }

    public void setDeg(double deg) {
        mDeg = deg;
    }
}

