package com.lidong.demo.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.lidong.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * TabLayout的实现
 */
public class TestTabLayoutActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private LayoutInflater mInflater;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private CustomListFragment view1, view2, view3, view4, view5;
    private List<View> mViewList1 = new ArrayList<View>();//页卡视图集合
    private View mView1,mView2,mView3,mView4,mView5;//页卡视图
    private List<Fragment> mViewList = new ArrayList<Fragment>();//Fragment集合


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tab_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.vp_view);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        mInflater = LayoutInflater.from(this);
        view1 =CustomListFragment.newInstance(0,"https://github.com/lidong1665");
        view2 =CustomListFragment.newInstance(1,"http://blog.csdn.net/u010046908");
        view3 = CustomListFragment.newInstance(2,"https://github.com/lidong1665");
        view4 = CustomListFragment.newInstance(3,"http://blog.csdn.net/u010046908");
        view5 =CustomListFragment.newInstance(4,"https://github.com/lidong1665");

        mView1 = mInflater.inflate(R.layout.activity_main, null);
        mView2 = mInflater.inflate(R.layout.activity_main, null);
        mView3 = mInflater.inflate(R.layout.activity_main, null);
        mView4 = mInflater.inflate(R.layout.activity_main, null);
        mView5 = mInflater.inflate(R.layout.activity_main, null);

        //添加页卡视图
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        mViewList.add(view4);
        mViewList.add(view5);

        mViewList1.add(mView1);
        mViewList1.add(mView2);
        mViewList1.add(mView3);
        mViewList1.add(mView4);
        mViewList1.add(mView5);

        //添加页卡标题
        mTitleList.add("No:1");
        mTitleList.add("No:2");
        mTitleList.add("No:3");
        mTitleList.add("No:4");
        mTitleList.add("No:5");


        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(3)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(4)));


        ListFragmentPagerAdapter mAdapter = new ListFragmentPagerAdapter(getSupportFragmentManager(),mViewList);
        // 设置ViewPager的数据等
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        //关闭预加载，默认一次只加载一个Fragment
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(mViewList1);
        mTabLayout.setTabsFromPagerAdapter(myPagerAdapter);//给Tabs设置适配器
    }

    //ViewPager适配器
    class MyPagerAdapter extends PagerAdapter {
        private List<View> mViewList;

        public MyPagerAdapter(List<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();//页卡数
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;//官方推荐写法
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));//添加页卡
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));//删除页卡
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);//页卡标题
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
