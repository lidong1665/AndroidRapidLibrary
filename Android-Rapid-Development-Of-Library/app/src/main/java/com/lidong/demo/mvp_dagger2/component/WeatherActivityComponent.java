package com.lidong.demo.mvp_dagger2.component;

import com.lidong.demo.AppComponent;
import com.lidong.demo.ActivityScope;
import com.lidong.demo.mvp_dagger2.WeatherActivity;
import com.lidong.demo.mvp_dagger2.module.WeatherActivityModule;

import dagger.Component;

@ActivityScope
@Component(modules = WeatherActivityModule.class,dependencies = AppComponent.class)
public interface WeatherActivityComponent {

    WeatherActivity inject(WeatherActivity mainActivity);

}
