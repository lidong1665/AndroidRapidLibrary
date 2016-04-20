package com.lidong.demo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.igexin.sdk.PushManager;
import com.lidong.android_ibrary.switchlayout.BaseEffects;
import com.lidong.android_ibrary.switchlayout.SwichLayoutInterFace;
import com.lidong.android_ibrary.switchlayout.SwitchLayout;
import com.lidong.demo.AndFix.bean.PatchBean;
import com.lidong.demo.AndFix.bean.util.GsonUtils;
import com.lidong.demo.AndFix.bean.util.RepairBugUtil;
import com.lidong.demo.AndFix.bean.util.WeakHandler;
import com.lidong.demo.navigation_view.BookFragment;
import com.lidong.demo.navigation_view.FavoritesFragment;
import com.lidong.demo.navigation_view.FindFragment;
import com.lidong.demo.navigation_view.LocationFragment;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import com.umeng.onlineconfig.OnlineConfigLog;
import com.umeng.onlineconfig.UmengOnlineConfigureListener;

import org.json.JSONObject;

import butterknife.ButterKnife;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity implements SwichLayoutInterFace,BottomNavigationBar.OnTabSelectedListener {

    private String TAG =MainActivity.class.getSimpleName();
    private BottomNavigationBar bottomNavigationBar;
    private int lastSelectedPosition = 0;
    private LocationFragment mLocationFragment;
    private FindFragment mFindFragment;
    private FavoritesFragment mFavoritesFragment;
    private BookFragment mBookFragment;
    private Toolbar mToolbar;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ButterKnife.bind(this);
        initData();
        registerMessageReceiver();
        setEnterSwichLayout();
        // SDK初始化，第三方程序启动时，都要进行SDK初始化工作
        Log.d("GetuiSdkDemo", "initializing sdk...");
        PushManager.getInstance().initialize(this.getApplicationContext());
        //初始化友盟在线参数
        initUmeng();
        getUmengParamAndFix();
    }

    private void initData() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_CLASSIC);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_location_on_white_24dp, "UI").setActiveColor(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.ic_find_replace_white_24dp, "网络").setActiveColor(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp, "进阶").setActiveColor(R.color.green))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "综合").setActiveColor(R.color.blue))
                .setFirstSelectedPosition(lastSelectedPosition )//设置默认选中
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    @Override
    public void initToolBar(Toolbar toolbar) {
        super.initToolBar(toolbar);
//        toolbar.setNavigationIcon(null);
        tv_title = (TextView) toolbar.findViewById(R.id.tv_title);
        tv_title.setText("UI");
        mToolbar = toolbar;
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mLocationFragment = LocationFragment.newInstance("UI界面");
        transaction.replace(R.id.tb, mLocationFragment);
        transaction.commit();
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

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

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mLocationFragment == null) {
                    mLocationFragment = LocationFragment.newInstance("UI界面");
                }
                transaction.replace(R.id.tb, mLocationFragment);
                tv_title.setText("UI界面");
                break;
            case 1:
                if (mFindFragment == null) {
                    mFindFragment = FindFragment.newInstance("网络");
                }
                transaction.replace(R.id.tb, mFindFragment);
                tv_title.setText("网络");
                break;
            case 2:
                if (mFavoritesFragment == null) {
                    mFavoritesFragment = FavoritesFragment.newInstance("进阶");
                }
                transaction.replace(R.id.tb, mFavoritesFragment);
                tv_title.setText("进阶");
                break;
            case 3:
                if (mBookFragment == null) {
                    mBookFragment = BookFragment.newInstance("综合");
                }
                transaction.replace(R.id.tb, mBookFragment);
                tv_title.setText("综合");
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

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
