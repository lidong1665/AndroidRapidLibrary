package com.lidong.demo.android_rapid_development_of_library.mvp.model;

/**
 *
 * Created by lidong on 2016/3/2.
 */
public interface WeatherModel {
    /**
     * 获取天气信息
     * @param format
     * @param city
     */
    void getWeatherData(String format,String city);


}
