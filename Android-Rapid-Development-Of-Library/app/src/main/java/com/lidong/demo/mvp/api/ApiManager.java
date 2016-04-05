package com.lidong.demo.mvp.api;


import com.lidong.demo.mvp.bean.WeatherData;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import rx.Observable;
/**
* @classnmae : ApiManager
* @describe :
* @author : lidong
* @createtime : 2016/4/5 16:55
* @company : chni
* @email : lidong@chni.com.cn
**/
@Module
public class ApiManager {

//    private static final String ENDPOINT = "http://v.juhe.cn";
//
//    private static final Retrofit sRetrofit = new Retrofit.Builder()
//            .baseUrl(ENDPOINT)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
//            .build();
//
//    private static final ApiManagerService apiManager = sRetrofit.create(ApiManagerService.class);

    @Inject ApiModule mApiModule;

    @Singleton
    public ApiManager(){}

    /**
     * 获取天气数据
     * @param city
     * @return
     */
    public    Observable<WeatherData> getWeatherData(String format, String city) {
        Observable<WeatherData> ss = mApiModule.provideApiManagerService().getWeatherData(format, city, "ad1d20bebafe0668502c8eea5ddd0333");
        return  ss;
    }




}
