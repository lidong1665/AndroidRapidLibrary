package com.lidong.demo.mvp_dagger2.api;


import com.lidong.demo.mvp.bean.WeatherData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @className：ApiManagerService
 * Created by lidong on 2016/3/2.
 */
public interface ApiManagerService {
    /**
     * 获取数据
     * @param cityname
     * @param key
     * @return
     */
    @GET("/weather/index")
    Observable<WeatherData> getWeatherData(@Query("format") String format, @Query("cityname") String cityname, @Query("key") String key);

}
