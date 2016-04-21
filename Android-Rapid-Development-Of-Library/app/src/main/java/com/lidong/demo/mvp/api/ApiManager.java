package com.lidong.demo.mvp.api;

import com.lidong.demo.mvp.bean.DreamData;
import com.lidong.demo.mvp.bean.WeatherData;
import com.lidong.demo.mvp.bean.WinXinData;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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
    /**
     * 基础地址
     */
    private static final String ENDPOINT = "http://v.juhe.cn";

    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();

    private static final ApiManagerService apiManager = sRetrofit.create(ApiManagerService.class);

    /**
     * 获取天气数据
     * @param city
     * @return
     */
    public static  Observable<WeatherData> getWeatherData(String format, String city) {
        Observable<WeatherData> ss = apiManager.getWeatherData(format, city, "ad1d20bebafe0668502c8eea5ddd0333");
        return  ss;
    }
    /**
     * 获取周公解梦类型
     * @param key
     * @return
     */
    public static  Observable<DreamData> getDreamData(String key) {
        Observable<DreamData> ss = apiManager.getDreamData(key);
        return  ss;
    }


    /**
     * 获取微信咨询
     * @param pno
     * @param ps
     * @param key
     * @return
     */
    public static  Observable<WinXinData> getWeiXinData(int pno, String ps, String key) throws Exception {
        Observable<WinXinData> ss = apiManager.getWinXinNewData(pno,ps,key);
        return  ss;
    }

}
