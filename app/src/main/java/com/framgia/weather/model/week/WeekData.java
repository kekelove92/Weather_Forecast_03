package com.framgia.weather.model.week;

import java.util.Date;

/**
 * Created by TungAnh on 5/12/17.
 */
public class WeekData {
    private double mTempMin;
    private double mTempMax;
    private Date mDay;


    public WeekData(double tempMin, double tempMax, Date day) {
        mTempMin = tempMin;
        mTempMax = tempMax;
        mDay = day;
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

    public Date getDay() {
        return mDay;
    }

    public void setDay(Date day) {
        mDay = day;
    }
}
