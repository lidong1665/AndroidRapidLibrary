package com.lidong.demo.mvp.model;

/**
*@类名 : WinXinDataModel
*@描述 :
*@时间 : 2016/4/20  14:05
*@作者: 李东
*@邮箱  : lidong@chni.com.cn
*@company: chni
*/
public interface WinXinDataModel {
    /**
     *
     * @param pno
     * @param ps
     * @param key
     */
   void getWeiXinData(int pno, String ps, String key) throws Exception;
}
