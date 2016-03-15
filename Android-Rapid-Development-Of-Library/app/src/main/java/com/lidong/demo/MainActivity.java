package com.lidong.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.igexin.sdk.PushManager;
import com.lidong.android_ibrary.switchlayout.BaseEffects;
import com.lidong.android_ibrary.switchlayout.SwichLayoutInterFace;
import com.lidong.android_ibrary.switchlayout.SwitchLayout;
import com.lidong.demo.mvp.WeatherActivity;
import com.lidong.demo.recyclerViewDemo.SwipeListViewActivity;
import com.lidong.demo.rule.RulerActivity;
import com.lidong.demo.shuffling_pages.ShufflingPagerActivity;
import com.lidong.demo.switchLayout.SwitchLayoutDemoActivity;
import com.lidong.demo.view.CircleProgressViewActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页面
 */
public class MainActivity extends AppCompatActivity implements SwichLayoutInterFace {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.button4)
    Button button4;
    @Bind(R.id.button5)
    Button button5;
    @Bind(R.id.button6)
    Button button6;
    @Bind(R.id.button7)
    Button button7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setEnterSwichLayout();
        // SDK初始化，第三方程序启动时，都要进行SDK初始化工作
        Log.d("GetuiSdkDemo", "initializing sdk...");
        PushManager.getInstance().initialize(this.getApplicationContext());
    }

    @OnClick({R.id.button1, R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, CircleProgressViewActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this, WeatherActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(MainActivity.this, com.lidong.demo.gpush.GetuiSdkDemoActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(MainActivity.this, RulerActivity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(MainActivity.this, SwitchLayoutDemoActivity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(MainActivity.this, SwipeListViewActivity.class));
                break;
            case R.id.button7:
                startActivity(new Intent(MainActivity.this, ShufflingPagerActivity.class));
                break;
        }
    }

    @Override
    public void setEnterSwichLayout() {
        SwitchLayout.getSlideFromLeft(this, false,
                BaseEffects.getReScrollEffect());
    }

    @Override
    public void setExitSwichLayout() {

    }
}
