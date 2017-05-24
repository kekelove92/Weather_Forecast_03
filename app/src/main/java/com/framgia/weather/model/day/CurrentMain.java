package com.framgia.weather.model.day;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TungAnh on 5/10/17.
 */
public class CurrentMain {
    public CurrentMain() {
    }

    @SerializedName("temp")
    @Expose
    private double mTemp;
    @SerializedName("pressure")
    @Expose
    private double mPressure;
    @SerializedName("humidity")
    @Expose
    private int mHumidity;
    @SerializedName("temp_min")
    @Expose
    private double mTempMin;
    @SerializedName("temp_max")
    @Expose
    private double mTempMax;

    public double getTemp() {
        return mTemp;
    }

    public void setTemp(double temp) {
        mTemp = temp;
    }

    public double getPressure() {
        return mPressure;
    }

    public void setPressure(double pressure) {
        mPressure = pressure;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public void setHumidity(int humidity) {
        mHumidity = humidity;
    }

    public double getTempMin() {
        return mTempMin;
    }

    public void setTempMin(double tempMin) {
        mTempMin = tempMin;
    }

    public double getTempMax() {
        return mTempMax;
    }

    public void setTempMax(double tempMax) {
        mTempMax = tempMax;
    }
}
