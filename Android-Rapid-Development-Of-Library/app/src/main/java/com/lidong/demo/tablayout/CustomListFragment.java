package com.lidong.demo.tablayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.lidong.android_ibrary.LoadingUIHelper;
import com.lidong.demo.R;

/**
 * Created by Chen on 2015/10/27.
 */
public class CustomListFragment extends BaseFragment {

    private static final String FRAGMENT_INDEX = "fragment_index";
    private String url;

    private LinearLayout mFragmentView;

    private int mCurIndex = -1;
    /** 标志位，标志已经初始化完成 */
    private boolean isPrepared;
    /** 是否已被加载过一次，第二次就不再去请求数据了 */
    private boolean mHasLoadedOnce;
    private WebView wb;

    /**
     * 创建新实例
     *
     * @param index
     * @return
     */
    public static CustomListFragment newInstance(int index,String url1) {
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_INDEX, index);
        bundle.putString("URL", url1);
        CustomListFragment fragment = new CustomListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mFragmentView == null) {
            mFragmentView = (LinearLayout) inflater.inflate(R.layout.fragment_webview, container, false);
            wb = (WebView) mFragmentView.findViewById(R.id.webView);
            //获得索引值
            Bundle bundle = getArguments();
            if (bundle != null) {
                mCurIndex = bundle.getInt(FRAGMENT_INDEX);
                url = bundle.getString("URL");
            }
            isPrepared = true;
            lazyLoad();
        }

        //因为共用一个Fragment视图，所以当前这个视图已被加载到Activity中，必须先清除后再加入Activity
        ViewGroup parent = (ViewGroup)mFragmentView.getParent();
        if(parent != null) {
            parent.removeView(mFragmentView);
        }
        return mFragmentView;
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }

        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //显示加载进度对话框
                LoadingUIHelper.showDialogForLoading(getActivity(), "正在加载...", true);
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
//                    Thread.sleep(2000);
                    //在这里添加调用接口获取数据的代码
                    doSomething(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccess) {
                if (isSuccess) {
                    // 加载成功
                    mHasLoadedOnce = true;
                    Snackbar.make(mFragmentView, " 加载成功", Snackbar.LENGTH_SHORT).show();
                } else {
                    // 加载失败
                    Snackbar.make(mFragmentView, "加载失败", Snackbar.LENGTH_SHORT).show();
                }
                //关闭对话框
                LoadingUIHelper.hideDialogForLoading();
            }
        }.execute();
    }

    private void doSomething(final String url) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                wb.loadUrl(url);
                wb.setWebViewClient(new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                        view.loadUrl(url);
                        return true;
                    }
                });
            }
        });
    }

}
