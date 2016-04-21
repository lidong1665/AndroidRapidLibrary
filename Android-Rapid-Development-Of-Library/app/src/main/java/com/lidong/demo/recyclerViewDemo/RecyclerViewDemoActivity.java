package com.lidong.demo.recyclerViewDemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lidong.android_ibrary.PullToRefresh.PtrClassicFrameLayout;
import com.lidong.android_ibrary.PullToRefresh.PtrDefaultHandler;
import com.lidong.android_ibrary.PullToRefresh.PtrFrameLayout;
import com.lidong.android_ibrary.PullToRefresh.loadmore.OnLoadMoreListener;
import com.lidong.android_ibrary.PullToRefresh.recyclerview.RecyclerAdapterWithHF;
import com.lidong.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView上拉刷新和上拉加载的实现
 */
public class RecyclerViewDemoActivity extends Activity {

    private RecyclerView mRecyclerView;
    //支持下拉刷新的ViewGroup
    private PtrClassicFrameLayout mPtrFrame;
    //List数据
    private List<String> title = new ArrayList<>();
    //RecyclerView自定义Adapter
    private RvAdapter adapter;
    //添加Header和Footer的封装类
    private RecyclerAdapterWithHF mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_recycler_view_demo);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RvAdapter(RecyclerViewDemoActivity.this, title);
        mAdapter = new RecyclerAdapterWithHF(adapter);

        mRecyclerView.setAdapter(mAdapter);

        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_list_view_frame);
        //下拉刷新支持时间
//        mPtrFrame.setLastUpdateTimeRelateObject(this);
//        //下拉刷新一些设置 详情参考文档
//        mPtrFrame.setResistance(1.7f);
//        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
//        mPtrFrame.setDurationToClose(200);
//        mPtrFrame.setDurationToCloseHeader(1000);
//        // default is false
//        mPtrFrame.setPullToRefresh(false);
//        // default is true
//        mPtrFrame.setKeepHeaderWhenRefresh(true);
        //进入Activity就进行自动下拉刷新
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);

        //下拉刷新
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {


            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                title.clear();
                //模拟数据
                for (int i = 0; i <= 5; i++) {
                    title.add(String.valueOf("下拉刷新"+i));
                }
                //模拟联网 延迟更新列表
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        mPtrFrame.refreshComplete();
                        mPtrFrame.setLoadMoreEnable(true);

                    }
                }, 1000);


            }
        });
        //上拉加载
        mPtrFrame.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {

                //模拟联网延迟更新数据
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        //模拟数据
                        for (int i = 0; i <= 5; i++) {
                            title.add(String.valueOf("加载更多"+i));
                        }
                        mAdapter.notifyDataSetChanged();
                        mPtrFrame.loadMoreComplete(true);
                        Toast.makeText(RecyclerViewDemoActivity.this, "load more complete", Toast.LENGTH_SHORT)
                                .show();
                    }
                }, 1000);
            }
        });
    }
}

