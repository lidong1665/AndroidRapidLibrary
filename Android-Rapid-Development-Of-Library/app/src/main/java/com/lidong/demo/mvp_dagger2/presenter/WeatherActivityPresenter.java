package com.lidong.demo.mvp_dagger2.presenter;

import com.lidong.demo.mvp.bean.WeatherData;
import com.lidong.demo.mvp_dagger2.WeatherActivity;
import com.lidong.demo.mvp_dagger2.model.WeatherModel;
import com.lidong.demo.mvp_dagger2.model.WeatherModelImp;


public class WeatherActivityPresenter implements WeatherModelImp.WeatherOnListener{
     WeatherActivity weatherActivity;
     WeatherModel mWeatherModel;

    public WeatherActivityPresenter(WeatherActivity weatherActivity) {
        this.weatherActivity = weatherActivity;
        mWeatherModel = new WeatherModelImp(this);
    }

    /**
     * 获取天气信息
     * @param format
     * @param city
     */
    public void getWeatherData(String format, String city){
        mWeatherModel.getWeatherData(format,city);
        weatherActivity.showProgress();
    }


    @Override
    public void onSuccess(WeatherData s) {
        weatherActivity.hideProgress();
        weatherActivity.loadWeather(s);
    }

    @Override
    public void onFailure(Throwable e) {
        weatherActivity.hideProgress();
    }
}
