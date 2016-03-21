package com.lidong.demo.eventbus.event;

/**
 * Created by Administrator on 2016/3/21.
 */
public class SimpleEvent3 {

    private String mMsg;

    public SimpleEvent3(String msg) {
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
