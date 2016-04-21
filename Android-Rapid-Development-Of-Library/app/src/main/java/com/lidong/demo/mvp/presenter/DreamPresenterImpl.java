package com.lidong.demo.mvp.presenter;

import com.lidong.demo.mvp.bean.DreamData;
import com.lidong.demo.mvp.model.DreamModel;
import com.lidong.demo.mvp.model.DreamModelImpl;
import com.lidong.demo.mvp.view.DreamView;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
public class DreamPresenterImpl  implements DreamPresenter ,DreamModelImpl.DreamOnlistener{

    private DreamModel mDreamModel;
    private DreamView mDreamView;

    public DreamPresenterImpl(DreamView dreamView) {
        this.mDreamView = dreamView;
        this.mDreamModel = new DreamModelImpl(this);
    }

    @Override
    public void getDreamData(String key) {
        mDreamView.showProgress();
        mDreamModel.getDreamData(key);

    }

    @Override
    public void onSuccess(List<DreamData.ResultBean> s) {
        mDreamView.loadDreamBean(s);
        mDreamView.hideProgress();
    }

    @Override
    public void onFailure(Throwable e) {
        mDreamView.hideProgress();
    }
}
