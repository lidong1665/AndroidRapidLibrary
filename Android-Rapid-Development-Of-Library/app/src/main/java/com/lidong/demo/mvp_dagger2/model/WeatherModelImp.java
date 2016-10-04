package com.lidong.demo.mvp_dagger2.model;

import com.lidong.demo.mvp.bean.WeatherData;
import com.lidong.demo.mvp_dagger2.api.ApiManager;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class WeatherModelImp  implements WeatherModel {

    private WeatherOnListener mWeatherOnListener;

    public WeatherModelImp(WeatherOnListener mWeatherOnListener) {
        this.mWeatherOnListener = mWeatherOnListener;
    }

    @Override
    public Subscription getWeatherData(String format,String city) {
         Observable<WeatherData> request = ApiManager.getWeatherData(format, city).cache();

         Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeatherData>() {
                    @Override
                    public void call(WeatherData weatherData) {
                        mWeatherOnListener.onSuccess(weatherData);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mWeatherOnListener.onFailure(throwable);
                    }
                });
        return  sub;

    }

    public interface WeatherOnListener{
        void onSuccess(WeatherData s);
        void onFailure(Throwable e);
    }
}
