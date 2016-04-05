package com.lidong.demo;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

/**
 * Created by lidong on 2016/3/4.
 */
public class TApplication extends Application {

    private static final String TAG = "AndFix";
    public static String VERSION_NAME = "";
    public static PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            PackageInfo mPackageInfo = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), 0);
            VERSION_NAME = mPackageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        initAndFix();
    }

    private void initAndFix() {
        // initialize

        mPatchManager = new PatchManager(this);
        mPatchManager.init(VERSION_NAME);
        Log.d(TAG, "inited.");
        // load patch
        mPatchManager.loadPatch();
//        Log.d(TAG, "apatch loaded.");
    }
}
