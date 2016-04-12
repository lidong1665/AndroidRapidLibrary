package com.lidong.android_ibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.lidong.android_ibrary.R;

/**
 * @类名 : CustomImageView
 * @描述 :
 * @时间 : 2016/4/12  9:13
 * @作者: 李东
 * @邮箱 : lidong@chni.com.cn
 * @company: chni
 */
public class CustomImageView extends View {

    private String TAG = CustomImageView.class.getSimpleName();

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
     * 图片
     */
    private Bitmap mImage;

    /**
     *
     */
    private int mImageScaleType;

    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 控制整体布局
     */
    private Rect mRect;

    /**
     * 文字的区域
     */
    private Rect mTextBound;
    /**
     * 宽度
     */
    private int mWidth;
    /**
     * 高度
     */
    private int mHeight;
    private static final int IMAGE_SCALE_FITXY = 1;


    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 获取自定义属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomerImageView, defStyleAttr, 0);
        int indexCount = a.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            //获取数组中的自定义值
            int attr = a.getIndex(i);

            if (attr == R.styleable.CustomerImageView_titleText2) {
                mTitleText = a.getString(attr);
            }
            if (attr == R.styleable.CustomerImageView_titleTextColor2) {
                mTitleTextColor = a.getInt(attr, Color.BLACK);
            }

            if (attr == R.styleable.CustomerImageView_titleTextSize2) {
                mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                        16, getResources().getDisplayMetrics()));
            }

            if (attr == R.styleable.CustomerImageView_image2) {
                mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
            }

            if (attr == R.styleable.CustomerImageView_imageScaleType2) {
                mImageScaleType = a.getInt(attr, 0);
            }
        }
        a.recycle();
        //初始化画笔和矩形区域
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mPaint.setAntiAlias(true);
        mRect = new Rect();
        mTextBound = new Rect();
        //计算描绘文字的范围
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 测量的模式
         */
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {//march_parent,
            mWidth = size;
            Log.d(TAG, "onMeasure()" + "EXACTLY ");
        } else {

            // 由图片决定的宽
            int desireByImg = getPaddingLeft() + getPaddingRight() + mImage.getWidth();
            // 由字体决定的宽
            int desireByTitle = getPaddingLeft() + getPaddingRight() + mTextBound.width();
            if (mode == MeasureSpec.AT_MOST) {//wrap_context

                Log.d(TAG, "onMeasure()" + "AT_MOST  ");

                int desire = Math.max(desireByImg, desireByTitle);

                mWidth = Math.min(size, desire);
            }
        }

        int height_size = MeasureSpec.getSize(heightMeasureSpec);
        int height_mode = MeasureSpec.getMode(heightMeasureSpec);

        if (height_mode == MeasureSpec.EXACTLY) {
            mHeight = height_size;
            Log.d(TAG, "onMeasure()" + "EXACTLY ");
        } else {
            //由图片决定的高度
            int desreByImge = getPaddingTop() + getPaddingBottom() + mImage.getHeight();
            //由文字决定高度
            int desreByText = getPaddingTop() + getPaddingBottom() + mTextBound.width();

            if (height_mode == MeasureSpec.AT_MOST) {
                int max = Math.max(desreByImge, desreByText);
                mHeight = Math.min(max, height_size);
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        //先绘制边框
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mRect.top = getPaddingTop();
        mRect.left = getPaddingLeft();
        mRect.bottom = mHeight - getPaddingBottom();
        mRect.right = mWidth - getPaddingRight();

        //绘制文字
        mPaint.setColor(mTitleTextColor);
        mPaint.setStyle(Paint.Style.FILL);

        /**
         * 当前设置的宽度小于字体需要的宽度，将字体改为xxx...
         */

        if (mTextBound.width() > mWidth) {
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitleText, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);
        } else {
            //正常情况，将字体居中
            canvas.drawText(mTitleText, (mWidth - mTextBound.width() * 1.0f / 2), mHeight - getPaddingBottom(), mPaint);
        }
        //取消使用掉的块
        mRect.bottom -= mTextBound.height();

        //绘制图片

        if (mImageScaleType == IMAGE_SCALE_FITXY) {//如果是拉伸
            canvas.drawBitmap(mImage, null, mRect, mPaint);
        } else {
            //计算居中的矩形范围
            mRect.top = (mHeight-mTextBound.height())/2 - mImage.getHeight()/2;
            mRect.left =(mWidth - mImage.getWidth()) /2;
            mRect.right = mWidth/2 + mImage.getWidth()/2;
            mRect.bottom =  (mHeight-mTextBound.height())/2 + mImage.getHeight()/2;
            canvas.drawBitmap(mImage,null,mRect,mPaint);
        }


    }
}
