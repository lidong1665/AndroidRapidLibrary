package com.lidong.demo.mvp_dagger2.module;

import com.lidong.demo.ActivityScope;
import com.lidong.demo.mvp_dagger2.WeatherActivity;
import com.lidong.demo.mvp_dagger2.model.WeatherModel;
import com.lidong.demo.mvp_dagger2.model.WeatherModelImp;
import com.lidong.demo.mvp_dagger2.presenter.WeatherActivityPresenter;
import com.lidong.demo.mvp_dagger2.model.WeatherModelImp.WeatherOnListener;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/4/6.
 */
@Module
public class WeatherActivityModule {

    private WeatherActivity weatherActivity;


    public  WeatherActivityModule( WeatherActivity weatherActivity) {
        this.weatherActivity = weatherActivity;
    }

    @Provides
    @ActivityScope
    public WeatherActivity provideWeatherActivity() {
        return weatherActivity;
    }


    @Provides
    @ActivityScope
    public WeatherActivityPresenter provideWeatherActivityPresenter(WeatherActivity weatherActivity) {
        return new WeatherActivityPresenter(weatherActivity);
    }

    @Provides
    @Singleton
    public WeatherModel provideWeatherModel(WeatherOnListener mWeatherOnListener){
        return  new WeatherModelImp(mWeatherOnListener);
    }


}
