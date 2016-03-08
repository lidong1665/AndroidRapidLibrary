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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 刻度尺的实现
 */
public class RulerActivity extends AppCompatActivity {

    private HorizontalScrollView ruler;
    private LinearLayout rulerlayout, all_layout;
    private TextView user_birth_value;
    private int beginYear;

    private String birthyear = "1970";
    private long time = 0;
    private int screenWidth;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user_birth_value = (TextView) findViewById(R.id.user_birth_value);
        user_birth_value.setText("1970");
        ruler = (HorizontalScrollView) findViewById(R.id.birthruler);
        rulerlayout = (LinearLayout) findViewById(R.id.ruler_layout);
        ruler.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                user_birth_value.setText(String.valueOf(beginYear
                        + (int) Math.ceil((ruler.getScrollX()) / 20)));
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                user_birth_value.setText(String.valueOf(beginYear
                                        + (int) Math.ceil((ruler.getScrollX()) / 20)));
                                birthyear = String.valueOf((int) (beginYear + Math
                                        .ceil((ruler.getScrollX()) / 20)));
                                try {
                                    time = (new SimpleDateFormat("yyyy")
                                            .parse(String.valueOf(birthyear)))
                                            .getTime();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
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

    private void scroll() {
        ruler.smoothScrollTo((1970 - beginYear) * 20, 0);
    }

    @SuppressWarnings("deprecation")
    private void constructRuler() {
        int year = new Date().getYear();
        if (year < 2015)
            year = 2010;
        beginYear = year / 10 * 10 - 150;
        View leftview = (View) LayoutInflater.from(this).inflate(
                R.layout.blankhrulerunit, null);
        leftview.setLayoutParams(new ViewGroup.LayoutParams(screenWidth / 2,
                ViewGroup.LayoutParams.MATCH_PARENT));
        rulerlayout.addView(leftview);
        for (int i = 0; i < 16; i++) {
            View view = (View) LayoutInflater.from(this).inflate(
                    R.layout.hrulerunit, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(200,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            TextView tv = (TextView) view.findViewById(R.id.hrulerunit);
            tv.setText(String.valueOf(beginYear + i * 10));
            rulerlayout.addView(view);
        }
        View rightview = (View) LayoutInflater.from(this).inflate(
                R.layout.blankhrulerunit, null);
        rightview.setLayoutParams(new ViewGroup.LayoutParams(screenWidth / 2,
                ViewGroup.LayoutParams.MATCH_PARENT));
        rulerlayout.addView(rightview);
    }

}
