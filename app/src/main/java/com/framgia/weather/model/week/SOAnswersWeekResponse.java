package com.framgia.weather.model.week;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TungAnh on 5/10/17.
 */
public class SOAnswersWeekResponse {
    @SerializedName("list")
    @Expose
    private List<List> list = null;

    public List<List> getList() {
        return list;
    }

    public void setList(List<List> list) {
        this.list = list;
    }
}
