package com.lidong.demo.rule;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidong.demo.R;

public class RulerHeightActivity extends AppCompatActivity {

    private HorizontalScrollView ruler;
    private LinearLayout rulerlayout;
    private TextView user_height_value;
    /**
     * 起点身高
     */
    private int beginHeight = 0;
    /**
     * 屏幕宽度（尺子的长度）
     */
    private int screenWidth;
    /**
     * 标记
     */
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user_height_value = (TextView) findViewById(R.id.user_birth_value);
        /**
         * 设置默认身高
         */
        user_height_value.setText("170");
        ruler = (HorizontalScrollView) findViewById(R.id.birthruler);
        rulerlayout = (LinearLayout) findViewById(R.id.ruler_layout);
        /**
         * 监听尺子滑动
         */
        ruler.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                user_height_value.setText(String.valueOf(beginHeight
                        + (int) Math.ceil((ruler.getScrollX()) / 20)));
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                user_height_value.setText(String.valueOf(beginHeight
                                        + (int) Math.ceil((ruler.getScrollX()) / 20)));
                            }
                        }, 1000);
                        break;
                }
                return false;
            }

        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (isFirst) {
            screenWidth = ruler.getWidth();
            constructRuler();
            isFirst = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scroll();
            }
        }, 100);
    }

    /**
     * 设置默认值身高
     */
    private void scroll() {
        ruler.smoothScrollTo((170 - beginHeight) * 20, 0);
    }

    /**
     * 构造尺子
     */
    private void constructRuler() {
        View leftview = (View) LayoutInflater.from(this).inflate(
                R.layout.blankhrulerunit, null);
        leftview.setLayoutParams(new ViewGroup.LayoutParams(screenWidth / 2,
                ViewGroup.LayoutParams.MATCH_PARENT));
        rulerlayout.addView(leftview);
        for (int i = 0; i < 22; i++) {
            View view = (View) LayoutInflater.from(this).inflate(
                    R.layout.hrulerunit, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(200,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            TextView tv = (TextView) view.findViewById(R.id.hrulerunit);
            tv.setText(String.valueOf(beginHeight + i * 10));
            rulerlayout.addView(view);
        }
        View rightview = (View) LayoutInflater.from(this).inflate(
                R.layout.blankhrulerunit, null);
        rightview.setLayoutParams(new ViewGroup.LayoutParams(screenWidth / 2,
                ViewGroup.LayoutParams.MATCH_PARENT));
        rulerlayout.addView(rightview);
    }

}
