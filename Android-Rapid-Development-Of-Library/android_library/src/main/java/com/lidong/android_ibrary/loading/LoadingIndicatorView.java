package com.lidong.android_ibrary.loading;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;

import com.lidong.android_ibrary.R;


/**
 * Created by lidongon 2016/1/31
 *
 .BallSpinFadeLoader,
 *
 */
public class LoadingIndicatorView extends View {


    //indicators 指示器
    public static final int BallSpinFadeLoader=22;

    @IntDef(flag = true,
            value = {
                    BallSpinFadeLoader,
            })
    public @interface Indicator{}

    //Sizes (with defaults in DP)
    public static final int DEFAULT_SIZE=45;

    //attrs
    int mIndicatorId;
    int mIndicatorColor;

    Paint mPaint;

    BaseIndicatorController mIndicatorController;

    private boolean mHasAnimation;


    public LoadingIndicatorView(Context context) {
        super(context);
        init(null, 0);
    }

    public LoadingIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public LoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        /**
         *获取TypedArray(属性的集合)
         */
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingIndicatorView);
        mIndicatorId=a.getInt(R.styleable.LoadingIndicatorView_indicator, BallSpinFadeLoader);//获取编号属性
        mIndicatorColor=a.getColor(R.styleable.LoadingIndicatorView_indicator_color, Color.WHITE);//获取颜色属性
        a.recycle();//回收属性的集合
        mPaint=new Paint();
        mPaint.setColor(mIndicatorColor);//设置画笔的颜色
        mPaint.setStyle(Paint.Style.FILL);//设置画笔的样式为填充
        mPaint.setAntiAlias(true);//去锯齿
        applyIndicator();//
    }

    private void applyIndicator(){
        switch (mIndicatorId){
            case BallSpinFadeLoader:
                mIndicatorController=new BallSpinFadeLoaderIndicator();
                break;
        }
        mIndicatorController.setTarget(this);//将控件设置到当前View
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width  = measureDimension(dp2px(DEFAULT_SIZE), widthMeasureSpec);//获取View的宽度
        int height = measureDimension(dp2px(DEFAULT_SIZE), heightMeasureSpec);//获取View的高度
        setMeasuredDimension(width, height);//
    }

    /**
     *测量的 维度
     * @param defaultSize 默认大小
     * @param measureSpec {@see widthMeasureSpec,heightMeasureSpec}
     * @return 返回测量的结果
     */
    private int measureDimension(int defaultSize,int measureSpec){
        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);//测量规范
        int specSize = MeasureSpec.getSize(measureSpec);//测量大小
        if (specMode == MeasureSpec.EXACTLY) {//父控件已经为子控件设置确定的大小，子控件会考虑父控件给他的大小，自己需要多大设置多大
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {//子控件可以设置自己希望的指定大小
            result = Math.min(defaultSize, specSize);//取最小值
        } else {
            result = defaultSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawIndicator(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!mHasAnimation){
            mHasAnimation=true;
            applyAnimation();
        }
    }

    void drawIndicator(Canvas canvas){
        mIndicatorController.draw(canvas,mPaint);
    }

    void applyAnimation(){
        mIndicatorController.createAnimation();
    }

    private int dp2px(int dpValue) {
        return (int) getContext().getResources().getDisplayMetrics().density * dpValue;
    }


}
