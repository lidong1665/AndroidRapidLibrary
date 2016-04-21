package com.lidong.demo.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lidong.android_ibrary.LoadingUIHelper;
import com.lidong.demo.AppComponent;
import com.lidong.demo.BaseActivity;
import com.lidong.demo.R;
import com.lidong.demo.mvp.bean.DreamData;
import com.lidong.demo.mvp.presenter.DreamPresenter;
import com.lidong.demo.mvp.presenter.DreamPresenterImpl;
import com.lidong.demo.mvp.view.DreamView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DreamDataActivity extends BaseActivity implements DreamView{

    @Bind(R.id.lv_dream)
    ListView mLvDream;
    List<String> mDreamType = new ArrayList<>();
    private DreamPresenter mDreamPresenter;
    private String TAG = DreamDataActivity.class.getSimpleName();
    List<DreamData.ResultBean> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream_data);
        ButterKnife.bind(this);
        setActivityTitle("周公解梦");
        mDreamPresenter = new DreamPresenterImpl(this);
        mDreamPresenter.getDreamData("c73b082b0c150b3bcba2cea1b96a8922");
        mLvDream.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String data = (String) parent.getAdapter().getItem(position);
                Toast.makeText(DreamDataActivity.this,data,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void showProgress() {
        LoadingUIHelper.showDialogForLoading(this, "获取数据", false);
    }

    @Override
    public void hideProgress() {
        LoadingUIHelper.hideDialogForLoading();
    }

    @Override
    public void loadDreamBean(List<DreamData.ResultBean> s) {
        mData = s;
      for (int i = 0;i<s.size();i++){
          mDreamType.add(s.get(i).getName());
      }
        ArrayAdapter  adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mDreamType);
        mLvDream.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dream_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_settings) {
           startActivity(new Intent(this,SearchDreamActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
