package com.lidong.android_ibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
*@类名 : SubListView
*@描述 : 
*@时间 : 2016/4/12  14:54
*@作者: 李东
*@邮箱  : lidong@chni.com.cn
*@company: chni
*/
public class SubListView extends ListView {

    public SubListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
