package com.lidong.demo.mvp.view;


import com.lidong.demo.mvp.bean.WeatherData;

/**
 * Created by lidong on 2016/3/2.
 */
public interface WeatherView {

    void showProgress();
    void hideProgress();
    void loadWeather(WeatherData weatherData);
}
