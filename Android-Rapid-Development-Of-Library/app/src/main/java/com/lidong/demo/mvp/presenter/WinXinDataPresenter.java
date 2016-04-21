package com.lidong.demo.mvp.presenter;

/**
 * Created by Administrator on 2016/4/20.
 */
public interface WinXinDataPresenter {

    /**
     *
     * @param pno
     * @param ps
     * @param key
     */
    void getWeiXinData(int pno, String ps, String key) throws Exception;
}
