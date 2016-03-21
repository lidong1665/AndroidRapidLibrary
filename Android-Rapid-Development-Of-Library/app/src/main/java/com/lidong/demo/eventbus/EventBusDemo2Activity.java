package com.lidong.demo.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.lidong.demo.R;
import com.lidong.demo.eventbus.event.SimpleEvent;
import com.lidong.demo.eventbus.event.SimpleEvent2;
import com.lidong.demo.eventbus.event.SimpleEvent3;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusDemo2Activity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btn_click_send_message)
    Button btnClickSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_demo2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_click_send_message})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_click_send_message:
                EventBus.getDefault().post(
                        new SimpleEvent("你好，EventBus！---1"));
                EventBus.getDefault().post( new SimpleEvent2("你好，EventBus！----2"));
                EventBus.getDefault().post( new SimpleEvent3("你好，EventBus！---3"));
                break;
        }
    }


}
