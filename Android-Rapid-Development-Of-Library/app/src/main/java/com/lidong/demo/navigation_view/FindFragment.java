package com.lidong.demo.navigation_view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lidong.demo.R;
import com.lidong.demo.mvp.DreamDataActivity;
import com.lidong.demo.mvp.WeiXinWebViewActivity;
import com.lidong.demo.mvp.WeixinNewsActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindFragment extends Fragment {

    @Bind(R.id.btn_get)
    Button mBtnGet;
    @Bind(R.id.btn_post)
    Button mBtnPost;
    @Bind(R.id.btn_upload_file)
    Button mBtnUploadFile;
    @Bind(R.id.btn_upload_file_topic)
    Button mBtnUploadFileTopic;
    private View view;

    public static FindFragment newInstance(String param1) {
        FindFragment fragment = new FindFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public FindFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_get, R.id.btn_post, R.id.btn_upload_file})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                startActivity(new Intent(getActivity(), DreamDataActivity.class));
                break;
            case R.id.btn_post:
                startActivity(new Intent(getActivity(), WeixinNewsActivity.class));
                break;
            case R.id.btn_upload_file:
                Intent intent = new Intent(getActivity(), WeiXinWebViewActivity.class);
                intent.putExtra("url", "http://blog.csdn.net/u010046908/article/details/50608182");
                intent.putExtra("title", "多图上传");
                startActivity(intent);
                break;
        }
    }

    @OnClick(R.id.btn_upload_file_topic)
    public void onClick() {
        Intent intent = new Intent(getActivity(), WeiXinWebViewActivity.class);
        intent.putExtra("url", "http://blog.csdn.net/u010046908/article/details/50767904");
        intent.putExtra("title", "Android仿照微信发说说拍照选库");
        startActivity(intent);
    }
}
