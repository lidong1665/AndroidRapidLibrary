package com.lidong.demo.mvp;

import android.os.Bundle;

import com.lidong.demo.AppComponent;
import com.lidong.demo.BaseActivity;
import com.lidong.demo.R;

public class SearchDreamActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dream);
        setActivityTitle("查找");
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

}
