package com.lidong.demo.mvp.model;

import com.lidong.demo.mvp.api.ApiManager;
import com.lidong.demo.mvp.bean.DreamData;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/4/21.
 */
public class DreamModelImpl implements DreamModel {

    DreamOnlistener mDreamOnlistener;

    public DreamModelImpl(DreamOnlistener dreamOnlistener) {
        mDreamOnlistener = dreamOnlistener;
    }

    @Override
    public void getDreamData(String key) {
        ApiManager.getDreamData(key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DreamData>() {
                    @Override
                    public void call(DreamData dreamData) {
                        if (dreamData!=null && "successed".equals(dreamData.getReason())){
                            mDreamOnlistener.onSuccess(dreamData.getResult());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                       mDreamOnlistener.onFailure(throwable);
                    }
                });
    }

    public  interface  DreamOnlistener{
        void onSuccess(List<DreamData.ResultBean> s);
        void onFailure(Throwable e);
    }
}
