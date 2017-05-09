package com.framgia.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TungAnh on 5/9/17.
 */
public class Clouds {

    @SerializedName("all")
    @Expose
    private int all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

}
