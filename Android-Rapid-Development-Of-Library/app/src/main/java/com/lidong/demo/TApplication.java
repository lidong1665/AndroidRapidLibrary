package com.lidong.demo;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.baidu.mapapi.SDKInitializer;
import com.lidong.demo.mvp_dagger2.api.ApiManagerServiceModule;
import com.lidong.demo.util.CrashHandler;
import com.lidong.demo.util.ExceptionUtils;

/**
 * Created by lidong on 2016/3/4.
 */
public class TApplication extends Application {

    private static final String TAG = "AndFix";
    public static String VERSION_NAME = "";
    public static PatchManager mPatchManager;

    private static AppComponent appComponent;
    private static TApplication sTApplication;
    /**
     * 发布时候修改
     */
    public static boolean release =false;


    public static TApplication get(Context context){
        return (TApplication)context.getApplicationContext();
    }

    public static TApplication getContext(){
        return sTApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        try {
            PackageInfo mPackageInfo = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), 0);
            VERSION_NAME = mPackageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            ExceptionUtils.handleException(getApplicationContext(),e);
        }
        initAndFix();
        appComponent=DaggerAppComponent.builder().
                appModule(new AppModule(this)).
                apiManagerServiceModule(new ApiManagerServiceModule())
                .build();
        //百度地图的初始化
        SDKInitializer.initialize(this);

    }

    /**
     * 初始化热修复
     */
    private void initAndFix() {
        // initialize
        mPatchManager = new PatchManager(this);
        mPatchManager.init(VERSION_NAME);
        Log.d(TAG, "inited.");
        // load patch
        mPatchManager.loadPatch();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void attachBaseContext(Context base) {
        MultiDex.install(base);
        super.attachBaseContext(base);
    }

}
