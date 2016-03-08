package com.lidong.demo.switchLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lidong.android_ibrary.switchlayout.BaseEffects;
import com.lidong.android_ibrary.switchlayout.SwichLayoutInterFace;
import com.lidong.android_ibrary.switchlayout.SwitchLayout;
import com.lidong.demo.R;

/**
 * Activity的动画实现
 */
public class SwitchLayoutDemoActivity extends AppCompatActivity implements SwichLayoutInterFace {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_layout_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setEnterSwichLayout();
    }

    @Override
    public void setEnterSwichLayout() {// 设置进入动画
        SwitchLayout.get3DRotateFromRight(this, false,
                BaseEffects.getQuickToSlowEffect());
    }

    @Override
    public void setExitSwichLayout() {// 设置退出动画
        SwitchLayout.getSlideToLeft(this, true, null);
    }
}
