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
    private List<ListMain> mListMain = null;

    public List<ListMain> getListMain() {
        return mListMain;
    }

    public void setListMain(List<ListMain> listMain) {
        this.mListMain = listMain;
    }
}
