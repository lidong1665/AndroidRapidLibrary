package com.lidong.demo.navigation_view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lidong.demo.R;
import com.lidong.demo.baidu_map_first.BaiduMapFirstActivity;
import com.lidong.demo.mvp.DWeatherActivity;
import com.lidong.demo.mvp_dagger2.WeatherActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookFragment extends Fragment {


    @Bind(R.id.btn_mvp_layout)
    Button mBtnMvpLayout;
    @Bind(R.id.btn_mvp1_layout)
    Button mBtnMvp1Layout;
    @Bind(R.id.btn_baidu_map_1)
    Button mBtnBaiduMap1;

    public static BookFragment newInstance(String param1) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public BookFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_mvp_layout, R.id.btn_mvp1_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mvp_layout:
                startActivity(new Intent(getActivity(), DWeatherActivity.class));
                break;
            case R.id.btn_mvp1_layout:
                startActivity(new Intent(getActivity(), WeatherActivity.class));
                break;
        }
    }

    @OnClick(R.id.btn_baidu_map_1)
    public void onClick() {
        Intent intent = new Intent(getActivity(), BaiduMapFirstActivity.class);
        startActivity(intent);
    }
}
