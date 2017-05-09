package com.framgia.weather.model;

/**
 * Created by TungAnh on 5/4/17.
 */
public class Weather {

    private String id;
    private String name;
    private String day;
    private String date;
    private String tempCurent;
    private String humidity;
    private String windSpeed;
    private String tempMin;
    private String tempMax;

    public Weather(String day, String tempMin, String tempMax) {
        this.day = day;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public Weather(String id, String day, String tempMin, String tempMax) {
        this.id = id;
        this.day = day;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public Weather(String id, String name, String day, String date, String tempCurent,
                   String humidity, String windSpeed, String tempMin, String tempMax) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.date = date;
        this.tempCurent = tempCurent;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTempCurent() {
        return tempCurent;
    }

    public void setTempCurent(String tempCurent) {
        this.tempCurent = tempCurent;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }
}
