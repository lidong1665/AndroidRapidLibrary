package com.lidong.demo.mvp.model;

import com.lidong.demo.mvp.api.ApiManager;
import com.lidong.demo.mvp.bean.WinXinData;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
*@类名 : WinXinDataModelImpl
*@描述 : 
*@时间 : 2016/4/20  14:06
*@作者: 李东
*@邮箱  : lidong@chni.com.cn
*@company: chni
*/
public class WinXinDataModelImpl implements WinXinDataModel {

    private WeiXinDataOnListener mWeixinDataOnlistener;

    public WinXinDataModelImpl(WeiXinDataOnListener weixinDataOnlistener) {
        mWeixinDataOnlistener = weixinDataOnlistener;
    }

    @Override
    public void getWeiXinData(int pno, String ps, String key) throws Exception {

        ApiManager.getWeiXinData(pno,ps,key).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<WinXinData>() {
            @Override
            public void call(WinXinData winXinData) {
                if (winXinData!=null && "success".equals(winXinData.getReason())){
                        mWeixinDataOnlistener.onSuccess(winXinData.getResult().getList());
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mWeixinDataOnlistener.onFailure(throwable);
            }
        });
        
    }


    public interface WeiXinDataOnListener{
        void onSuccess(List<WinXinData.ResultBean.ListBean> s);
        void onFailure(Throwable e);
    }

}
