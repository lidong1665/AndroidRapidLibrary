package com.lidong.demo.mvp.presenter;

/**
 * Created by lidong on 2016/3/2.
 */
public interface WeatherPresenter {
    /**
     * 获取天气信息
     * @param format
     * @param city
     */
    void getWeatherData(String format, String city);
}
