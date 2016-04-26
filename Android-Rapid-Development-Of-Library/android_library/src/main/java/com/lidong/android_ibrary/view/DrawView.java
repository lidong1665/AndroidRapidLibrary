package com.lidong.android_ibrary.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
*@类名 : DrawView
*@描述 : 使用双缓存技术实现绘制
*@时间 : 2016/4/26  9:18
*@作者: 李东
*@邮箱  : lidong@chni.com.cn
*@company: chni
*/
public class DrawView extends View {

    float preX;
    float preY;
    private Path path;
    private Paint paint = null;
    private int VIEW_WIDTH = 800;
    private int VIEW_HEIGHT = 600;
    //定义一个内存中图片，将他作为缓冲区
    Bitmap cacheBitmap = null;
    //定义缓冲区Cache的Canvas对象
    Canvas cacheCanvas = null;

    public DrawView(Context context) {
        this(context,null);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //创建一个与该VIew相同大小的缓冲区
        cacheBitmap = Bitmap.createBitmap(VIEW_WIDTH,VIEW_HEIGHT,Bitmap.Config.ARGB_8888);
        //创建缓冲区Cache的Canvas对象
        cacheCanvas = new Canvas();
        path = new Path();
        //设置cacheCanvas将会绘制到内存的bitmap上
        cacheCanvas.setBitmap(cacheBitmap);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setFlags(Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       Paint p = new Paint();
        //将cacheBitmap绘制到该View
        canvas.drawBitmap(cacheBitmap,0,0,p);
        canvas.drawPath(path,paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取拖动时间的发生位置
         float x = event.getX();
         float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX,preY,x,y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_UP:
                //这是是调用了cacheBitmap的Canvas在绘制
                cacheCanvas.drawPath(path,paint);
                path.reset();
                break;
        }
        invalidate();//在UI线程刷新VIew
        return true;
    }
}
