package com.lidong.demo;

import android.app.Application;

import com.lidong.demo.mvp_dagger2.api.ApiManagerService;
import com.lidong.demo.mvp_dagger2.api.ApiManagerServiceModule;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {ApiManagerServiceModule.class,AppModule.class})
public interface AppComponent {
    /**
     * 获取上下文
     * @return
     */
    Application getApplication();

    /**
     * 获取ApiManagerService
     * @return
     */
    ApiManagerService getService();
}
