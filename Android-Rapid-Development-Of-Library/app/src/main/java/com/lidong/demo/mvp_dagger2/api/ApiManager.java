package com.lidong.demo.mvp_dagger2.api;


import com.lidong.demo.TApplication;
import com.lidong.demo.mvp.bean.WeatherData;

import rx.Observable;

/**
* @classnmae : ApiManager
* @describe :
* @author : lidong
* @createtime : 2016/4/5 16:55
* @company : chni
* @email : lidong@chni.com.cn
**/
public class ApiManager {
    private String TAG =ApiManager.class.getSimpleName();
    /**
     * 获取天气数据
     * @param city
     * @return
     */
    public static  Observable<WeatherData> getWeatherData(String format, String city) {
        Observable<WeatherData> ss = TApplication.getAppComponent().getService().getWeatherData(format, city, "ad1d20bebafe0668502c8eea5ddd0333");
        return  ss;
    }



}
