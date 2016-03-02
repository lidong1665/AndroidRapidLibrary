package com.lidong.demo.android_rapid_development_of_library.mvp.presenter;

import android.util.Log;

import com.lidong.demo.android_rapid_development_of_library.mvp.bean.WeatherData;
import com.lidong.demo.android_rapid_development_of_library.mvp.model.WeatherModel;
import com.lidong.demo.android_rapid_development_of_library.mvp.model.WeatherModelImp;
import com.lidong.demo.android_rapid_development_of_library.mvp.view.WeatherView;

/**
 * Created by lidong on 2016/3/2.
 */
public class WeatherPresenterImp  implements WeatherPresenter ,WeatherModelImp.WeatherOnListener{

   private WeatherModel mWeatherModel;
   private WeatherView mWeatherView;

    public WeatherPresenterImp( WeatherView mWeatherView) {
        this.mWeatherModel = new WeatherModelImp(this);
        this.mWeatherView = mWeatherView;
    }

    @Override
    public void getWeatherData(String format, String city) {
        mWeatherView.showProgress();
        mWeatherModel.getWeatherData(format,city);
    }

    @Override
    public void onSuccess(WeatherData s) {
        mWeatherView.loadWeather(s);
        mWeatherView.hideProgress();
        Log.d("-------", "onSuccess() called with: " + "s = [" + s.toString() + "]");
    }

    @Override
    public void onFailure(Throwable e) {
        mWeatherView.hideProgress();
        Log.d("-------","onFailure"+e.getMessage());
    }
}
