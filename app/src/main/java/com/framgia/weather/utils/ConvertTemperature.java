package com.framgia.weather.utils;

/**
 * Created by TungAnh on 5/24/17.
 */
public class ConvertTemperature {
    public double convertFahrenheitToCelcius(double fahrenheit) {
        return ((fahrenheit - 32) * (9 / 5));
    }

    public double convertCelciusToFahrenheit(double celsius) {
        return (celsius / (9 / 5)) + 32;
    }
}
