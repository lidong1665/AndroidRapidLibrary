package com.lidong.demo.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lidong.demo.R;
import com.lidong.demo.eventbus.event.SimpleEvent;
import com.lidong.demo.eventbus.event.SimpleEvent2;
import com.lidong.demo.eventbus.event.SimpleEvent3;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * EventBusDemo1Activity的使用
 */
public class EventBusDemo1Activity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btn_open_page1)
    Button btnOpenPage1;
    @Bind(R.id.tv_display)
    TextView tvDisplay;
    private String TAG = EventBusDemo1Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_demo1);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        //注册EventBus
        EventBus.getDefault().register(this);
    }

    @OnClick({R.id.btn_open_page1})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_open_page1:
                startActivity(new Intent(EventBusDemo1Activity.this,EventBusDemo2Activity.class));
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)//在主线程
    public void onUseThread(SimpleEvent event) {

        String msg = "onUseThread收到了消息：" + event.getMsg();
        Log.d(TAG, msg);
        tvDisplay.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)//在主线程
    public void onEventUse(SimpleEvent event) {
        Log.d(TAG, "onEventMainThread收到了消息：" + event.getMsg());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)//在主线程
    public void onEventUse(SimpleEvent2 event) {

        Log.d(TAG, "onEventMainThread收到了消息：" + event.getMsg());
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)//在后台线程
    public void onEventUse(SimpleEvent3 event){
        Log.d(TAG, "onEventBackground收到了消息：" + event.getMsg());
    }
    @Subscribe(threadMode = ThreadMode.ASYNC)//强制在后台线程执行
    public void onEventUseAsync(SimpleEvent2 event){
        Log.d(TAG, "onEventAsync收到了消息：" + event.getMsg());
    }
    @Subscribe(threadMode = ThreadMode.POSTING)//默认方式, 在发送线程执行
    public void onEventUser(SimpleEvent3 event) {
        Log.d(TAG, "OnEvent收到了消息：" + event.getMsg());
    }



    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);//解除EventBus
    }
}
