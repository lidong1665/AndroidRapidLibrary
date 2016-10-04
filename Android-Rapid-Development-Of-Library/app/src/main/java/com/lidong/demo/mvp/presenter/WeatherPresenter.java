package com.lidong.demo.mvp.presenter;

/**
 * Created by lidong on 2016/3/2.
 */
public abstract class WeatherPresenter extends BasePresenter{
  /**
     * 获取天气信息
     * @param format
     * @param city
     */
  public abstract void getWeatherData(String format, String city);
}
