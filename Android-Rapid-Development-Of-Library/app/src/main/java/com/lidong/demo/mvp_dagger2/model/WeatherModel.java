package com.lidong.demo.mvp_dagger2.model;

import rx.Subscription;

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
    Subscription getWeatherData(String format, String city);


}
