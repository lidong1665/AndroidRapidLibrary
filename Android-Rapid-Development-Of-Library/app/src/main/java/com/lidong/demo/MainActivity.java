package com.lidong.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.lidong.android_ibrary.switchlayout.BaseEffects;
import com.lidong.android_ibrary.switchlayout.SwichLayoutInterFace;
import com.lidong.android_ibrary.switchlayout.SwitchLayout;
import com.lidong.demo.AndFix.bean.PatchBean;
import com.lidong.demo.AndFix.bean.util.GsonUtils;
import com.lidong.demo.AndFix.bean.util.RepairBugUtil;
import com.lidong.demo.AndFix.bean.util.WeakHandler;
import com.lidong.demo.eventbus.EventBusDemo1Activity;
import com.lidong.demo.gpush.GetuiSdkDemoActivity;
import com.lidong.demo.greendao.GreenDaoActivity;
import com.lidong.demo.mvp.WeatherActivity;
import com.lidong.demo.navigation_view.BottomNavigationBarDemoActivity;
import com.lidong.demo.recyclerViewDemo.SwipeListViewActivity;
import com.lidong.demo.rule.RulerHeightActivity;
import com.lidong.demo.segmentcontrol.SegmentControlActivity;
import com.lidong.demo.shuffling_pages.ShufflingPagerActivity;
import com.lidong.demo.switchLayout.SwitchLayoutDemoActivity;
import com.lidong.demo.tablayout.TestTabLayoutActivity;
import com.lidong.demo.view.CircleProgressViewActivity;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import com.umeng.onlineconfig.OnlineConfigLog;
import com.umeng.onlineconfig.UmengOnlineConfigureListener;

import org.json.JSONObject;

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
    @Bind(R.id.button8)
    Button button8;
    @Bind(R.id.button9)
    Button button9;
    @Bind(R.id.button10)
    Button button10;
    @Bind(R.id.button11)
    Button button11;
    @Bind(R.id.button12)
    Button button12;
    private String TAG =MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        registerMessageReceiver();
        setEnterSwichLayout();
        // SDK初始化，第三方程序启动时，都要进行SDK初始化工作
        Log.d("GetuiSdkDemo", "initializing sdk...");
        PushManager.getInstance().initialize(this.getApplicationContext());
        //初始化友盟在线参数
        initUmeng();
        getUmengParamAndFix();
        test("ssss");
    }

    private void test(String test) {
        Log.d(TAG, "test() called with: " + "----");
        Toast.makeText(MainActivity.this, test, Toast.LENGTH_SHORT).show();
    }

    private void getUmengParamAndFix() {
        String value = OnlineConfigAgent.getInstance().getConfigParams(this, "hiApk");
        Log.d(TAG, "onCreate() called with: " + "value = [" + value + "]");
        if (!TextUtils.isEmpty(value)){
            PatchBean onLineBean = GsonUtils.getInstance().parseIfNull(PatchBean.class , value);
            try {
                //进行判断当前版本是否有补丁需要下载更新
                RepairBugUtil.getInstance().comparePath(this, onLineBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initUmeng() {
        OnlineConfigAgent.getInstance().updateOnlineConfig(this);
        OnlineConfigAgent.getInstance().setDebugMode(true);
        OnlineConfigAgent.getInstance().setOnlineConfigListener(configureListener);

    }

    UmengOnlineConfigureListener configureListener = new UmengOnlineConfigureListener() {
        @Override
        public void onDataReceived(JSONObject json) {
            OnlineConfigLog.d("OnlineConfig", "json=" + json);
        }
    };


    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.button10, R.id.button11,R.id.button12})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, CircleProgressViewActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this, WeatherActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(MainActivity.this, GetuiSdkDemoActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(MainActivity.this, RulerHeightActivity.class));
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
            case R.id.button8:
                startActivity(new Intent(MainActivity.this, TestTabLayoutActivity.class));
                break;
            case R.id.button9:
                startActivity(new Intent(MainActivity.this, SegmentControlActivity.class));
                break;
            case R.id.button10:
                startActivity(new Intent(MainActivity.this, EventBusDemo1Activity.class));
                break;
            case R.id.button11:
                startActivity(new Intent(MainActivity.this, GreenDaoActivity.class));
                break;
            case R.id.button12:
                startActivity(new Intent(MainActivity.this, BottomNavigationBarDemoActivity.class));
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
        SwitchLayout.getSlideFromRight(this, false,
                BaseEffects.getReScrollEffect());
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);//进入页面统计时长
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);//进入页面统计时长
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        RepairBugUtil.getInstance().release();
        super.onDestroy();
    }



    private WeakHandler mHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == MSG_WHAT_DOWNLOAD){
                String message = (String) msg.obj;
                if (TextUtils.isEmpty(message)) return false;
                try {
                    PatchBean bean = GsonUtils.getInstance().parse(PatchBean.class, message);
                    RepairBugUtil.getInstance().comparePath(MainActivity.this, bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    });

    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.lidong.demo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final int MSG_WHAT_DOWNLOAD = 0x111;
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String message = intent.getStringExtra(KEY_MESSAGE);
                Message msg = new Message();
                msg.what = MSG_WHAT_DOWNLOAD;
                msg.obj = message;
                mHandler.sendMessage(msg);
            }
        }
    }
}
