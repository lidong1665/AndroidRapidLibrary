package com.lidong.demo.mvp.view;

import com.lidong.demo.mvp.bean.WinXinData;

import java.util.List;

/**
*@类名 : WeixinNewsView
*@描述 : 
*@时间 : 2016/4/20  13:57
*@作者: 李东
*@邮箱  : lidong@chni.com.cn
*@company: chni
*/
public interface WeixinNewsView {
    /**
     *
     * @param s
     */
    void loadWeiXinData(List<WinXinData.ResultBean.ListBean> s) throws Exception;

}
