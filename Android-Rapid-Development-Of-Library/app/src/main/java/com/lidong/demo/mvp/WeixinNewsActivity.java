package com.lidong.demo.mvp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidong.android_ibrary.PullToRefresh.loadmore.OnLoadMoreListener;
import com.lidong.android_ibrary.PullToRefresh.loadmore.SwipeRefreshHelper;
import com.lidong.demo.AppComponent;
import com.lidong.demo.BaseActivity;
import com.lidong.demo.R;
import com.lidong.demo.mvp.bean.WinXinData;
import com.lidong.demo.mvp.presenter.WinXinDataPresenter;
import com.lidong.demo.mvp.presenter.WinXinDataPressnterImpl;
import com.lidong.demo.mvp.view.WeixinNewsView;
import com.lidong.demo.util.ExceptionUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @类名 : WeixinNewsActivity
 * @描述 :
 * @时间 : 2016/4/20  13:50
 * @作者: 李东
 * @邮箱 : lidong@chni.com.cn
 * @company: chni
 */
public class WeixinNewsActivity extends BaseActivity implements WeixinNewsView,
        SwipeRefreshHelper.OnSwipeRefreshListener,OnLoadMoreListener,AdapterView.OnItemClickListener {


    @Bind(R.id.lv_swipe_listview1)
    ListView mLvSwipeListview;
    @Bind(R.id.sryt_swipe_listview1)
    SwipeRefreshLayout mSrytSwipeListview;
    private WinXinDataPresenter mWinXinDataPresenter;
    private String TAG = WeixinNewsActivity.class.getSimpleName();

    private ListViewAdapter mAdapter;
    private SwipeRefreshHelper mSwipeRefreshHelper;

    private int page = 1;
    private Handler mHandler = new Handler();

    List<WinXinData.ResultBean.ListBean> mListBeen = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news);
        ButterKnife.bind(this);
        setActivityTitle("微信新闻");
        mSrytSwipeListview.setColorSchemeColors(Color.BLUE);
        mWinXinDataPresenter = new WinXinDataPressnterImpl(this);
        mSwipeRefreshHelper = new SwipeRefreshHelper(mSrytSwipeListview);

        mSrytSwipeListview.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshHelper.autoRefresh();
            }
        });

        mSwipeRefreshHelper.setOnSwipeRefreshListener(this);
        mSwipeRefreshHelper.setOnLoadMoreListener(this);
        mLvSwipeListview.setOnItemClickListener(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


    @Override
    public void loadWeiXinData(List<WinXinData.ResultBean.ListBean> s) {
        Log.d(TAG, "loadWeiXinData: " + s.toString());
        if (page==1){
            mListBeen.clear();
            mListBeen.addAll(s);
            mAdapter = new ListViewAdapter(WeixinNewsActivity.this,mListBeen);
            mLvSwipeListview.setAdapter(mAdapter);
            mSwipeRefreshHelper.refreshComplete();
            mSwipeRefreshHelper.setLoadMoreEnable(true);
        }else {
            mListBeen.addAll(s);
            mSwipeRefreshHelper.loadMoreComplete(true);
            mSwipeRefreshHelper.setLoadMoreEnable(true);
        }
    }

    @Override
    public void loadMore() {
        page++;
        try {
            mWinXinDataPresenter.getWeiXinData(page,"10","f16af393a63364b729fd81ed9fdd4b7d");
        } catch (Exception e) {
            ExceptionUtils.handleException(this,e);
        }
    }

    @Override
    public void onfresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                try {
                    mWinXinDataPresenter.getWeiXinData(1,"10","f16af393a63364b729fd81ed9fdd4b7d");
                } catch (Exception e) {
                    ExceptionUtils.handleException(WeixinNewsActivity.this,e);
                }
            }
        },2000);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in= new Intent(this,WeiXinWebViewActivity.class);
        WinXinData.ResultBean.ListBean item = (WinXinData.ResultBean.ListBean) parent.getAdapter().getItem(position);
        in.putExtra("url",item.getUrl());
        in.putExtra("title",item.getTitle());
        startActivity(in);
    }

    /**
    *@类名 : WeixinNewsActivity
    *@描述 : 
    *@时间 : 2016/4/20  17:26
    *@作者: 李东
    *@邮箱  : lidong@chni.com.cn
    *@company: chni
    */
    public class ListViewAdapter extends BaseAdapter {
        private List<WinXinData.ResultBean.ListBean> datas;
        private LayoutInflater inflater;

        public ListViewAdapter(Context context, List<WinXinData.ResultBean.ListBean> s) {
            super();
            inflater = LayoutInflater.from(context);
            datas = s;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public WinXinData.ResultBean.ListBean getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_listview_weixin_data, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            WinXinData.ResultBean.ListBean listBean = datas.get(position);

            if (!TextUtils.isEmpty(listBean.getFirstImg())) {
                Picasso.with(WeixinNewsActivity.this)
                        .load(listBean.getFirstImg())
                        .placeholder(R.mipmap.img_feed_back_default)
                        .resize(50, 50)
                        .error(R.mipmap.img_feed_back_default)
                        .into(viewHolder.mImageView3);
            }
            viewHolder.mTvContext.setText(listBean.getTitle());
            viewHolder.mTvDate.setText(listBean.getSource());

            return convertView;
        }

        class ViewHolder {
            @Bind(R.id.imageView3)
            ImageView mImageView3;
            @Bind(R.id.tv_context)
            TextView mTvContext;
            @Bind(R.id.tv_date)
            TextView mTvDate;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
