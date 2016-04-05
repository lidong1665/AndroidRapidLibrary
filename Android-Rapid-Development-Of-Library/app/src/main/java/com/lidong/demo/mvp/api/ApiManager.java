package com.lidong.demo.mvp.api;


import com.lidong.demo.mvp.bean.WeatherData;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;


/**
 * Created by lidong on 2016/3/2.
 */
public class ApiManager {

//    private static final String ENDPOINT = "http://v.juhe.cn";
//
//    private static final Retrofit sRetrofit = new Retrofit .Builder()
//            .baseUrl(ENDPOINT)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
//            .build();
//
//    private static final ApiManagerService apiManager = sRetrofit.create(ApiManagerService.class);

    @Inject ApiModule apiModule;

    /**
     * 获取天气数据
     * @param city
     * @return
     */
    public static Observable<WeatherData> getWeatherData(String format, String city) {
        return apiModule.provideApiManagerService().getWeatherData().getWeatherData(format,city,"ad1d20bebafe0668502c8eea5ddd0333");
    }




}
