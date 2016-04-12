package com.lidong.demo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lidong.android_ibrary.view.CircleProgressView;
import com.lidong.demo.R;

public class CircleProgressViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CircleProgressView circleProgressbar = (CircleProgressView) findViewById(R.id.circleProgressbar);
        circleProgressbar.setmTxtHint1("步数");
        circleProgressbar.setmTxtHint2("今天走了100步");
        circleProgressbar.setProgress(10);
    }

}
