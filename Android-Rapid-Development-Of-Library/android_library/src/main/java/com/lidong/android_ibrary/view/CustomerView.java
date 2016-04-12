package com.lidong.android_ibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.lidong.android_ibrary.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @类名 : CustomerView
 * @描述 : CustomerView 绘制验证码的实现
 * @时间 : 2016/4/11  16:00
 * @作者: 李东
 * @邮箱 : lidong@chni.com.cn
 * @company: chni
 */
public class CustomerView extends View {

    /**
     * 文本
     */
    private String mTitleText;
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private int mTitleTextSize;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    /**
     *画笔
     */
    private Paint mPaint;


    public CustomerView(Context context) {
        this(context, null);
    }

    public CustomerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 获得我自定义的样式属性
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CustomerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomerView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < a.length(); i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.CustomerView_titleText1) {
                mTitleText = a.getString(attr);

            } else if (attr == R.styleable.CustomerView_titleTextColor1) {// 默认颜色设置为黑色
                mTitleTextColor = a.getColor(attr, Color.BLACK);

            } else if (attr == R.styleable.CustomerView_titleTextSize1) {// 默认设置为16sp，TypeValue也可以把sp转化为px
                mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

            }
        }
        /**
         * 回收数组
         */
        a.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mPaint.setAntiAlias(false);
        // mPaint.setColor(mTitleTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
        /**
         * 为当前View添加点击事件
         */
        this.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                //设置文本内容
                mTitleText = randomText();
                //刷新界面
                postInvalidate();
            }

        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
//       super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        //获取到宽度的测量模式/
        // EXACTLY 希望父视图的大小应该由spaceSize的值来决定
        // UNSPECIFIED 没有任何限制，一般不会使用
        // /AT_MOST 表示子视图最多只能是SpceSzie的中指定的大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else
        {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        /**
         * 绘制矩形背景
         */
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        /**
         * 绘制文字
         */
        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }

    /**
     *
     * @return
     */
    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }


}
