package com.lidong.demo.mvp.view;

import com.lidong.demo.mvp.bean.DreamData;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
public interface DreamView {

    void showProgress();
    void hideProgress();
    void loadDreamBean(List<DreamData.ResultBean>s);

}
