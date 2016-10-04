package com.lidong.demo.mvp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lidong.android_ibrary.LoadingUIHelper;
import com.lidong.demo.AppComponent;
import com.lidong.demo.BaseActivity;
import com.lidong.demo.R;
import com.lidong.demo.mvp.bean.WeatherData;
import com.lidong.demo.mvp.presenter.WeatherPresenter;
import com.lidong.demo.mvp.presenter.WeatherPresenterImp;
import com.lidong.demo.mvp.view.WeatherView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DWeatherActivity extends BaseActivity implements WeatherView {

    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.textView6)
    TextView textView6;
    @Bind(R.id.textView7)
    TextView textView7;
    @Bind(R.id.textView8)
    TextView textView8;
    @Bind(R.id.textView9)
    TextView textView9;
    @Bind(R.id.button2)
    Button mButton2;
    private WeatherPresenter mWeatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_weather);
        ButterKnife.bind(this);
        setActivityTitle("Android 中MVP模式的使用");
        mWeatherPresenter = new WeatherPresenterImp(this);
        mWeatherPresenter.getWeatherData("2", "苏州");


    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }


    @SuppressLint("NewApi")
    public static void hideSystemUI(View view) {
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @SuppressLint("NewApi")
    public static void showSystemUI(View view) {
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


    @Override
    public void showProgress() {
        LoadingUIHelper.showDialogForLoading(this, "获取数据", false);
    }

    @Override
    public void hideProgress() {
        LoadingUIHelper.hideDialogForLoading();
        Toast.makeText(this,"你的免费数据已经用完",Toast.LENGTH_SHORT).show();
        mWeatherPresenter.onUnsubscribe();
    }

    @Override
    public void loadWeather(WeatherData weatherData) {
        Log.d("weatherData", "weatherData==" + weatherData.toString());
        textView1.setText("城市：" + weatherData.getResult().getToday().getCity());
        textView2.setText("日期：" + weatherData.getResult().getToday().getWeek());
        textView3.setText("今日温度：" + weatherData.getResult().getToday().getTemperature());
        textView4.setText("天气标识：" + WeatherIDUtils.transfer(weatherData.getResult().getToday().getWeather_id().getFa()));
        textView5.setText("穿衣指数：" + weatherData.getResult().getToday().getDressing_advice());
        textView6.setText("干燥指数：" + weatherData.getResult().getToday().getDressing_index());
        textView7.setText("紫外线强度：" + weatherData.getResult().getToday().getUv_index());
        textView8.setText("穿衣建议：" + weatherData.getResult().getToday().getDressing_advice());
        textView9.setText("旅游指数：" + weatherData.getResult().getToday().getTravel_index());
    }

    @OnClick(R.id.button2)
    public void onClick() {
        View view = getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.red));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeatherPresenter.onUnsubscribe();
    }


}
