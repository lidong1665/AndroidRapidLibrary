package com.lidong.demo.mvp_dagger2.model;

import com.lidong.demo.mvp.bean.WeatherData;
import com.lidong.demo.mvp_dagger2.api.ApiManager;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class WeatherModelImp  implements WeatherModel {

    private WeatherOnListener mWeatherOnListener;

    public WeatherModelImp(WeatherOnListener mWeatherOnListener) {
        this.mWeatherOnListener = mWeatherOnListener;
    }

    @Override
    public void getWeatherData(String format,String city) {
       ApiManager.getWeatherData(format, city).subscribeOn(Schedulers.io())
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

    }

    public interface WeatherOnListener{
        void onSuccess(WeatherData s);
        void onFailure(Throwable e);
    }
}
