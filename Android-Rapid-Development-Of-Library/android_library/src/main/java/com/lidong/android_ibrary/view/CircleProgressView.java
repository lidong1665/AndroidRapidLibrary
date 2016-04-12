package com.lidong.android_ibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

/**
 * 圆形进度条的自定义
 * Created by Administrator on 2016/3/1.
 */
public class CircleProgressView   extends View {

    private static final String TAG = "CircleProgressBar";
    /**
     * 最大进度
     */
    private int mMaxProgress = 100;
    /**
     * 实际进度
     */
    private int mProgress = 0;
    /**
     * 设置画笔圆环的宽度
     */
    private final int mCircleLineStrokeWidth = 50;
    private final int mCircleLineStrokeWidth1 = 2;
    /**
     * 设置绘制文本线的宽度
     */
    private final int mTxtStrokeWidth = 2;

    /**
     * 画圆所在的距形区域
     */
    private final RectF mRectF,mRectF1;
    /**
     * 创建画笔对象
     */
    private final Paint mPaint,mPaint1;

    private final Context mContext;

    private String mTxtHint1;

    private String mTxtHint2;

    private int mGravity = Gravity.CENTER_HORIZONTAL;

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mRectF = new RectF();
        mRectF1 = new RectF();
        mPaint = new Paint();
        mPaint1 = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = this.getWidth();//获取View宽度
        int height = this.getHeight();//获取View高度

        if (width != height) {//如果高度不等于宽度，取最小值  同时对宽高分别设置最小值
            int min = Math.min(width, height);
            width = min;
            height = min;
        }

        // 设置画笔相关属性
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.rgb(0xe9, 0xe9, 0xe9));
        canvas.drawColor(Color.TRANSPARENT);
        mPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint1.setAntiAlias(true);
        mPaint1.setColor(Color.rgb(0xf8, 0x60, 0x30));
        canvas.drawColor(Color.TRANSPARENT);
        mPaint1.setStrokeWidth(mCircleLineStrokeWidth1);
        mPaint1.setStyle(Paint.Style.STROKE);

        // 位置
        mRectF.left = mCircleLineStrokeWidth / 2+50; // 左上角x
        mRectF.top = mCircleLineStrokeWidth / 2+50; // 左上角y
        mRectF.right = width - mCircleLineStrokeWidth / 2 -50; // 左下角x
        mRectF.bottom = height - mCircleLineStrokeWidth / 2-50; // 右下角y

        mRectF1.left = mCircleLineStrokeWidth1 / 2; // 左上角x
        mRectF1.top = mCircleLineStrokeWidth1 / 2; // 左上角y
        mRectF1.right = width - mCircleLineStrokeWidth1 / 2 ; // 左下角x
        mRectF1.bottom = height - mCircleLineStrokeWidth1 / 2; // 右下角y


        // 绘制最外面的圆环
        canvas.drawArc(mRectF1, -90, 360, false, mPaint1);


        // 绘制圆圈，进度条背景
        canvas.drawArc(mRectF, -90, 360, false, mPaint);
        mPaint.setColor(Color.rgb(0xf8, 0x60, 0x30));
        PathEffect effects = new DashPathEffect(new float[]{5,5,5,5},1);
        mPaint.setPathEffect(effects);//设置画笔为虚线
        canvas.drawArc(mRectF, -90, ((float) mProgress / mMaxProgress) * 360, false, mPaint);

        // 绘制进度文案显示
        mPaint.setStrokeWidth(mTxtStrokeWidth);
        String text = mProgress + "%";
        int textHeight = height / 8;
        mPaint.setTextSize(textHeight);
        int textWidth = (int) mPaint.measureText(text, 0, text.length());
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, width / 2 - textWidth / 2, height / 2 + textHeight / 2, mPaint);

        if (!TextUtils.isEmpty(mTxtHint1)) {
            mPaint.setStrokeWidth(mTxtStrokeWidth);
            text = mTxtHint1;
            textHeight = height / 12;
            mPaint.setTextSize(textHeight);
            mPaint.setColor(Color.rgb(0x99, 0x99, 0x99));
            textWidth = (int) mPaint.measureText(text, 0, text.length());
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawText(text, width / 2 - textWidth / 2, height / 4 + textHeight / 2+50, mPaint);
        }

        if (!TextUtils.isEmpty(mTxtHint2)) {
            mPaint.setStrokeWidth(mTxtStrokeWidth);
            text = mTxtHint2;
            textHeight = height / 16;
            mPaint.setTextSize(textHeight);
            textWidth = (int) mPaint.measureText(text, 0, text.length());
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawText(text, width / 2 - textWidth / 2, 3 * height / 4 + textHeight / 2-40, mPaint);
        }
    }

    public int getMaxProgress() {
        return mMaxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.mMaxProgress = maxProgress;
    }

    public void setProgress(int progress) {
        this.mProgress = progress;
        this.invalidate();
    }

    public void setProgressNotInUiThread(int progress) {
        this.mProgress = progress;
        this.postInvalidate();
    }

    public String getmTxtHint1() {
        return mTxtHint1;
    }

    public void setmTxtHint1(String mTxtHint1) {
        this.mTxtHint1 = mTxtHint1;
    }

    public String getmTxtHint2() {
        return mTxtHint2;
    }

    public void setmTxtHint2(String mTxtHint2) {
        this.mTxtHint2 = mTxtHint2;
    }

    public int getmGravity() {
        return mGravity;
    }

    public void setmGravity(int mGravity) {
        this.mGravity = mGravity;
    }


}
