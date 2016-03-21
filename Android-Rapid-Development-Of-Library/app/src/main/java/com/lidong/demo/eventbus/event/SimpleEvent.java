package com.lidong.demo.eventbus.event;

/**
 * Created by Administrator on 2016/3/21.
 */
public class SimpleEvent {

    private String mMsg;

    public SimpleEvent(String msg) {
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
