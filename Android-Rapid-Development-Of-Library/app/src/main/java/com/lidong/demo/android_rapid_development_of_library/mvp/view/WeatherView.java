package com.lidong.demo.android_rapid_development_of_library.mvp.view;

import com.lidong.demo.android_rapid_development_of_library.mvp.bean.WeatherData;

/**
 * Created by lidong on 2016/3/2.
 */
public interface WeatherView {

    void showProgress();
    void hideProgress();
    void loadWeather(WeatherData weatherData);
}
