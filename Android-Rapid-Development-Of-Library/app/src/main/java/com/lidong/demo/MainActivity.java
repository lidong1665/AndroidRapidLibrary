package com.lidong.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.lidong.demo.mvp.WeatherActivity;
import com.lidong.demo.view.CircleProgressViewActivity;
import com.lidong.demo.view.TestWebViewFragmentActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button3)
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.button1, R.id.button2,R.id.button3})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, CircleProgressViewActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this, WeatherActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(MainActivity.this, TestWebViewFragmentActivity.class));
                break;
        }
    }

}
