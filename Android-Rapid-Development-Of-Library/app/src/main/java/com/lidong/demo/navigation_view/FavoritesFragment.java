package com.lidong.demo.navigation_view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lidong.demo.R;
import com.lidong.demo.eventbus.EventBusDemo1Activity;
import com.lidong.demo.gpush.GetuiSdkDemoActivity;
import com.lidong.demo.greendao.GreenDaoActivity;
import com.lidong.demo.mvp.WeiXinWebViewActivity;
import com.lidong.demo.realm.DemoRealmActivity;
import com.lidong.demo.recyclerViewDemo.RecyclerViewDemoActivity;
import com.lidong.demo.view.GestureFilpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
*@packageName:com.lidong.demo.navigation_view
*@className:FavoritesFragment
*@desc:
*@author:lidong
*@datetime:16/6/14 下午9:55
*@company:chni
*@qq:1561281670
*/
public class FavoritesFragment extends Fragment {

    @Bind(R.id.btn_event_layout)
    Button mBtnEventLayout;
    @Bind(R.id.btn_greendao_layout)
    Button mBtnGreendaoLayout;
    @Bind(R.id.btn_gpush_layout)
    Button mBtnGpushLayout;
    @Bind(R.id.btn_recycleview_layout)
    Button mBtnRecycleviewLayout;
    @Bind(R.id.btn_broadcast)
    Button mBtnBroadcast;
    @Bind(R.id.btn_snswer_layout)
    Button mBtnSnswerLayout;

    public static FavoritesFragment newInstance(String param1) {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public FavoritesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_event_layout, R.id.btn_greendao_layout, R.id.btn_recycleview_layout,
            R.id.btn_gpush_layout,R.id.btn_snswer_layout,R.id.btn_realm_demo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_event_layout:
                startActivity(new Intent(getActivity(), EventBusDemo1Activity.class));
                break;
            case R.id.btn_greendao_layout:
                startActivity(new Intent(getActivity(), GreenDaoActivity.class));
                break;
            case R.id.btn_gpush_layout:
                startActivity(new Intent(getActivity(), GetuiSdkDemoActivity.class));
                break;
            case R.id.btn_recycleview_layout:
                startActivity(new Intent(getActivity(), RecyclerViewDemoActivity.class));
                break;
            case R.id.btn_snswer_layout:
                startActivity(new Intent(getActivity(), GestureFilpActivity.class));
                break;
            case R.id.btn_realm_demo:
                startActivity(new Intent(getActivity(), DemoRealmActivity.class));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.btn_broadcast)
    public void onClick() {
        Intent intent = new Intent(getActivity(), WeiXinWebViewActivity.class);
        intent.putExtra("url", "http://blog.csdn.net/u010046908/article/details/50697441");
        intent.putExtra("title", "Android Broadcast 做的简单封装");
        startActivity(intent);
    }
}
