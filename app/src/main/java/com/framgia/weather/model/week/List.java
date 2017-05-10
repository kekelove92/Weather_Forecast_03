package com.framgia.weather.model.week;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TungAnh on 5/10/17.
 */
public class List {
    @SerializedName("dt")
    @Expose
    private int dt;
    @SerializedName("main")
    @Expose
    private WeekMain mWeekMain;
    @SerializedName("wind")
    @Expose
    private WeekWind mWeekWind;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public WeekMain getWeekMain() {
        return mWeekMain;
    }

    public void setWeekMain(WeekMain weekMain) {
        mWeekMain = weekMain;
    }

    public WeekWind getWeekWind() {
        return mWeekWind;
    }

    public void setWeekWind(WeekWind weekWind) {
        mWeekWind = weekWind;
    }

    public WeekWind getWind() {
        return mWeekWind;
    }

    public void setWind(WeekWind weekWind) {
        this.mWeekWind = weekWind;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }
}
