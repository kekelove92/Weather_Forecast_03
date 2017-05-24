package com.framgia.weather.model.day;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TungAnh on 5/10/17.
 */
public class SOAnswersResponse {
    @SerializedName("main")
    @Expose
    private CurrentMain mCurrentMain;
    @SerializedName("wind")
    @Expose
    private CurrentWind mCurrentWind;
    @SerializedName("dt")
    @Expose
    private int  mDt;
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("cod")
    @Expose
    private int mCod;

    public CurrentMain getCurrentMain() {
        return mCurrentMain;
    }

    public void setCurrentMain(CurrentMain currentMain) {
        mCurrentMain = currentMain;
    }

    public CurrentWind getCurrentWind() {
        return mCurrentWind;
    }

    public void setCurrentWind(CurrentWind currentWind) {
        mCurrentWind = currentWind;
    }

    public int getDt() {
        return mDt;
    }

    public void setDt(int dt) {
        mDt = dt;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getCod() {
        return mCod;
    }

    public void setCod(int cod) {
        mCod = cod;
    }
}
