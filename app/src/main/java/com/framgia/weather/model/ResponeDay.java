package com.framgia.weather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TungAnh on 5/9/17.
 */
public class ResponeDay {
    @SerializedName("cod")
    private int mCod;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("cnt")
    private int mCnt;
    @SerializedName("list")
    private List mList;

}
