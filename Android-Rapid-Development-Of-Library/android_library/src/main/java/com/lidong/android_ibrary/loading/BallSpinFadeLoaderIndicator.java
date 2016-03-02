package com.lidong.android_ibrary.loading;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by lidongon 2016/1/31
 */
public class BallSpinFadeLoaderIndicator extends BaseIndicatorController {

    public static final float SCALE=1.0f;

    public static final int ALPHA=255;
    /**
     * 圆点的比例
     */
    float[] scaleFloats=new float[]{SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE};
    /**
     * 圆点的透明度集合
     */
    int[] alphas=new int[]{ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA};


    @Override
    public void draw(Canvas canvas, Paint paint) {
        float radius=getWidth()/10;
        for (int i = 0; i < 8; i++) {
            canvas.save();
            Point point=circleAt(getWidth(),getHeight(),getWidth()/2-radius,i*(Math.PI/4));
            canvas.translate(point.x,point.y);
            canvas.scale(scaleFloats[i],scaleFloats[i]);
            paint.setAlpha(alphas[i]);
            canvas.drawCircle(0,0,radius,paint);
            canvas.restore();
        }
    }

    /**
     * 圆O的圆心为(a,b),半径为R,点A与到X轴的为角α.
     *则点A的坐标为(a+R*cosα,b+R*sinα)
     * @param width
     * @param height
     * @param radius
     * @param angle
     * @return
     */
    Point circleAt(int width,int height,float radius,double angle){
        float x= (float) (width/2+radius*(Math.cos(angle)));
        float y= (float) (height/2+radius*(Math.sin(angle)));
        return new Point(x,y);
    }

    @Override
    public void createAnimation() {
        int[] delays= {0, 120, 240, 360, 480, 600, 720, 780, 840};
        for (int i = 0; i < 8; i++) {
            final int index=i;
            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.4f,1);//创建ValueAnimator对象
            scaleAnim.setDuration(1000);//设置动画的持续时间
            scaleAnim.setRepeatCount(-1);//设置动画是否重复
            scaleAnim.setStartDelay(delays[i]);//延迟启动动画
            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {//ValueAnimator只负责第一次的内容，因此必须通过监听来实现对象的相关属性的更新
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleFloats[index] = (float) animation.getAnimatedValue();//获取当前帧的值
                    postInvalidate();
                }
            });
            scaleAnim.start();//启动属性动画

            ValueAnimator alphaAnim=ValueAnimator.ofInt(255, 77, 255);//透明度动画
            alphaAnim.setDuration(1000);//
            alphaAnim.setRepeatCount(-1);
            alphaAnim.setStartDelay(delays[i]);
            alphaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    alphas[index] = (int) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            alphaAnim.start();
        }
    }

    final class Point{
        public float x;
        public float y;

        public Point(float x, float y){
            this.x=x;
            this.y=y;
        }
    }


}
