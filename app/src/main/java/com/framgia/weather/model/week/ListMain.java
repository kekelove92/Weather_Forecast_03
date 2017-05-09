package com.framgia.weather.model.week;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TungAnh on 5/10/17.
 */
public class ListMain {
    @SerializedName("dt")
    @Expose
    private int mDt;
    @SerializedName("temp")
    @Expose
    private WeekMain mWeekMain;
    @SerializedName("speed")
    @Expose
    private double Winds;

    public double getWinds() {
        return Winds;
    }

    public void setWinds(double winds) {
        Winds = winds;
    }

    public int getDt() {
        return mDt;
    }

    public void setDt(int dt) {
        mDt = dt;
    }

    public WeekMain getWeekMain() {
        return mWeekMain;
    }

    public void setWeekMain(WeekMain weekMain) {
        mWeekMain = weekMain;
    }

}
