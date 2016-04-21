package com.lidong.demo.mvp.api;


import com.lidong.demo.mvp.bean.DreamData;
import com.lidong.demo.mvp.bean.WeatherData;
import com.lidong.demo.mvp.bean.WinXinData;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @className：ApiManagerService
 * Created by lidong on 2016/3/2.
 */
public interface ApiManagerService {
    /**
     * 获取天气数据
     * @param cityname
     * @param key
     * @return
     */
    @GET("/weather/index")
    Observable<WeatherData> getWeatherData(@Query("format") String format,@Query("cityname") String cityname,@Query("key") String key);

    /**
     * 获取周公解梦类型
     * @param key
     * @return
     */
    @GET("/dream/category")
    Observable<DreamData> getDreamData(@Query("key") String key);


    /**
     *
     * @param pno  当前页数，默认1
     * @param ps 每页返回条数，最大100，默认20
     * @param key 是	应用APPKEY(应用详细页查询)
     * @return
     */
    @POST("/weixin/query")
    Observable<WinXinData> getWinXinNewData(@Query("pno") int pno, @Query("ps") String ps, @Query("key") String key) throws  Exception;


}
