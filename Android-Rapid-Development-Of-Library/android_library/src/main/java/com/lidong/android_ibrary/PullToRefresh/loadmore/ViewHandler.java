package com.lidong.android_ibrary.PullToRefresh.loadmore;

import android.view.View;
import android.view.View.OnClickListener;

public interface ViewHandler {

	/**
	 * 
	 * @param contentView
	 * @param loadMoreView
	 * @param onClickLoadMoreListener
	 * @return 是否有 init ILoadMoreView
	 */
	 boolean handleSetAdapter(View contentView, ILoadViewMoreFactory.ILoadMoreView loadMoreView, OnClickListener onClickLoadMoreListener);

	 void setOnScrollBottomListener(View contentView, OnScrollBottomListener onScrollBottomListener);

}
