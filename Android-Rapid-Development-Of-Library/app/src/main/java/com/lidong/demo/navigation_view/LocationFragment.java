package com.lidong.demo.navigation_view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lidong.demo.R;
import com.lidong.demo.rule.RulerActivity;
import com.lidong.demo.segmentcontrol.SegmentControlActivity;
import com.lidong.demo.shuffling_pages.ShufflingPagerActivity;
import com.lidong.demo.tablayout.TestTabLayoutActivity;
import com.lidong.demo.view.CircleProgressViewActivity;
import com.lidong.demo.view.ExtenpListViewActivity;
import com.lidong.demo.view.SearchViewActivity;
import com.lidong.demo.view.TagFlowLayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationFragment extends Fragment {

    @Bind(R.id.btn_circle_progress_view)
    Button mBtnCircleProgressView;
    @Bind(R.id.btn_tag_layout)
    Button mBtnTagLayout;
    @Bind(R.id.btn_tab_layout)
    Button mBtnTabLayout;
    @Bind(R.id.btn_expandable_layout)
    Button mBtnExpandableLayout;
    @Bind(R.id.btn_fengdaun_layout)
    Button mBtnFengdaunLayout;
    @Bind(R.id.btn_ruler_layout)
    Button mBtnRulerLayout;
    @Bind(R.id.btn_lun_bo_layout)
    Button mBtnLunBoLayout;
    @Bind(R.id.btn_search_menu_layout)
    Button mBtnSearchMenu;

    public static LocationFragment newInstance(String param1) {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public LocationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_circle_progress_view, R.id.btn_tag_layout, R.id.btn_tab_layout
            , R.id.btn_expandable_layout, R.id.btn_fengdaun_layout, R.id.btn_ruler_layout,R.id.btn_search_menu_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_circle_progress_view:
                startActivity(new Intent(getActivity(), CircleProgressViewActivity.class));
                break;
            case R.id.btn_tag_layout:
                startActivity(new Intent(getActivity(), TagFlowLayoutActivity.class));
                break;
            case R.id.btn_tab_layout:
                startActivity(new Intent(getActivity(), TestTabLayoutActivity.class));
                break;
            case R.id.btn_expandable_layout:
                startActivity(new Intent(getActivity(), ExtenpListViewActivity.class));
                break;
            case R.id.btn_fengdaun_layout:
                startActivity(new Intent(getActivity(), SegmentControlActivity.class));
                break;
            case R.id.btn_ruler_layout:
                startActivity(new Intent(getActivity(), RulerActivity.class));
                break;
            case R.id.btn_search_menu_layout:
                startActivity(new Intent(getActivity(), SearchViewActivity.class));
                break;
        }
    }

    @OnClick(R.id.btn_lun_bo_layout)
    public void onClick() {
        startActivity(new Intent(getActivity(), ShufflingPagerActivity.class));
    }
}
