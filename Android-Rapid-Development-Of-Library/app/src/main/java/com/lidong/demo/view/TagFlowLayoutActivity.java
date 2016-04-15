package com.lidong.demo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lidong.android_ibrary.tagflowlayout.FlowLayout;
import com.lidong.android_ibrary.tagflowlayout.TagAdapter;
import com.lidong.android_ibrary.tagflowlayout.TagFlowLayout;
import com.lidong.demo.R;

import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
*@类名 : TagFlowLayoutActivity
*@描述 : 
*@时间 : 2016/4/15  8:54
*@作者: 李东
*@邮箱  : lidong@chni.com.cn
*@company: chni
*/
public class TagFlowLayoutActivity extends AppCompatActivity {

    @Bind(R.id.id_flowlayout)
    TagFlowLayout mIdFlowlayout;

    private String[] mVals = new String[]
            {"高血压", "糖尿病", "消化系统", "心脏病", "白血病", "白虚标",
                    "高血压", "不错啊", "大家好","发送的","方式", "关系吧", "冠心病","互信大","发的发",
                    "高血压","写沙发上", "不错啊啊", "不错啊啊", "冠心病","互信大","发的发"};
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_flow_layout);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setAdapter();
    }

    private void setAdapter() {
        mIdFlowlayout.setAdapter(new TagAdapter(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) View.inflate(getApplicationContext(),R.layout.tv_tag_flow_item,null);
                tv.setText(mVals[position]);
                return tv;
            }
        });
        mIdFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
        {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent)
            {
                Toast.makeText(getApplicationContext(), mVals[position], Toast.LENGTH_SHORT).show();
                //view.setVisibility(View.GONE);
                return true;
            }
        });


        mIdFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener()
        {
            @Override
            public void onSelected(Set<Integer> selectPosSet)
            {
                toolbar.setTitle("choose:" + selectPosSet.toString());
            }
        });
    }


}
