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
    private Integer dt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private Integer cod;

    public CurrentMain getCurrentMain() {
        return mCurrentMain;
    }

    public void setCurrentMain(CurrentMain currentMain) {
        this.mCurrentMain = currentMain;
    }

    public CurrentWind getWind() {
        return mCurrentWind;
    }

    public void setWind(CurrentWind currentWind) {
        this.mCurrentWind = currentWind;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }
}
