package com.lidong.android_ibrary.loading;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by lidong 2016/1/31
 */
public abstract class BaseIndicatorController {

    private View mTarget;


    public void setTarget(View target){
        this.mTarget=target;
    }

    public View getTarget(){
        return mTarget;
    }

    /**
     * 得到View的宽度
     * @return
     */
    public int getWidth(){
        return mTarget.getWidth();
    }

    /**
     * 得到view的高度
     * @return
     */
    public int getHeight(){
        return mTarget.getHeight();
    }

    /**
     * 刷新view
     */
    public void postInvalidate(){
        mTarget.postInvalidate();
    }

    /**
     * draw indicator what ever
     * you want to draw
     * 绘制indicate
     * @param canvas
     * @param paint
     */
    public abstract void draw(Canvas canvas,Paint paint);

    /**
     * create animation or animations
     * ,and add to your indicator.
     * 创建动画或者动画集合，添加到indcator
     */
    public abstract void createAnimation();


}
