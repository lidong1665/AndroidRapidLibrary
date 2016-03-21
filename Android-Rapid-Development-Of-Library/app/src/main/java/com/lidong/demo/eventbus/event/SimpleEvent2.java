package com.lidong.demo.eventbus.event;

/**
 * Created by Administrator on 2016/3/21.
 */
public class SimpleEvent2 {

    private String mMsg;

    public SimpleEvent2(String msg) {
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
