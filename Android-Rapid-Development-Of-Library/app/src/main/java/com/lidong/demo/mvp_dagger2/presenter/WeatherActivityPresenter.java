package com.lidong.demo.mvp_dagger2.presenter;

import com.lidong.demo.mvp.bean.WeatherData;
import com.lidong.demo.mvp_dagger2.WeatherActivity;
import com.lidong.demo.mvp_dagger2.model.WeatherModel;
import com.lidong.demo.mvp_dagger2.model.WeatherModelImp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

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
        addSubscription(mWeatherModel.getWeatherData(format,city));
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

    CompositeSubscription mCompositeSubscription;

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    //RXjava注册
    public void addSubscription(Subscription subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscriber);
    }
}
