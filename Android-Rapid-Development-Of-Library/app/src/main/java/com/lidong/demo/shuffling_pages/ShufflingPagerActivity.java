package com.lidong.demo.shuffling_pages;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidong.demo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 轮播效果的实现
 */
public class ShufflingPagerActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_pre)
    TextView tvPre;
    @Bind(R.id.tv_topic_from)
    TextView tvTopicFrom;
    @Bind(R.id.tv_topic)
    TextView tvTopic;
    @Bind(R.id.author_layout)
    RelativeLayout authorLayout;
    @Bind(R.id.v_dot0)
    View vDot0;
    @Bind(R.id.v_dot1)
    View vDot1;
    @Bind(R.id.v_dot2)
    View vDot2;
    @Bind(R.id.v_dot3)
    View vDot3;
    @Bind(R.id.v_dot4)
    View vDot4;
    @Bind(R.id.v_dot5)
    View vDot5;
    @Bind(R.id.v_dot6)
    View vDot6;
    @Bind(R.id.v_dot7)
    View vDot7;
    @Bind(R.id.v_dot8)
    View vDot8;


    private List<ImageView> imageViews;// 滑动的图片集合

    private List<View> dots; // 图片标题正文的那些点
    private List<View> dotList;
    // 轮播banner的数据
    private List<AdDomain> adList;
    private int currentItem = 0; // 当前图片的索引号
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            vp.setCurrentItem(currentItem);
        }
    };

    private ScheduledExecutorService scheduledExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuffling_pager);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initAdData();
        startAd();
    }

    private void initAdData() {
        // 广告数据
        adList = getBannerAd();
        imageViews = new ArrayList<>();
        // 点
        dots = new ArrayList<>();
        dotList = new ArrayList<>();
        dots.add(vDot0);
        dots.add(vDot1);
        dots.add(vDot2);
        dots.add(vDot3);
        dots.add(vDot4);
        dots.add(vDot5);
        dots.add(vDot6);
        dots.add(vDot7);
        dots.add(vDot8);
        vp.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        vp.setOnPageChangeListener(new MyPageChangeListener());
        addDynamicView();
    }
    private void addDynamicView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        for (int i = 0; i < adList.size(); i++) {
            ImageView imageView = new ImageView(this);
            // 异步加载图片
            Picasso.with(ShufflingPagerActivity.this)
                    .load(adList.get(i).getImgUrl())
                    .placeholder(R.mipmap.a_about_us)
                    .error(R.mipmap.a_about_us)
                    .into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            dots.get(i).setVisibility(View.VISIBLE);
            dotList.add(dots.get(i));
        }
    }

    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
                TimeUnit.SECONDS);
    }

    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (vp) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            AdDomain adDomain = adList.get(position);
            tvTitle.setText(adDomain.getTitle()); // 设置标题
            tvDate.setText(adDomain.getDate());
            tvTopicFrom.setText(adDomain.getTopicFrom());
            tvTopic.setText(adDomain.getTopic());
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }

    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return adList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView iv = imageViews.get(position);
            container.addView(iv);
            final AdDomain adDomain = adList.get(position);
            // 在这个方法里面设置图片的点击事件
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return iv;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }

    }


    /**
     * 轮播广播模拟数据
     *
     * @return
     */
    public static List<AdDomain> getBannerAd() {
        List<AdDomain> adList = new ArrayList<>();

        AdDomain adDomain = new AdDomain();
        adDomain.setId("10000091");
        adDomain.setDate("3月4日");
        adDomain.setTitle("运动可增加肺活量");
        adDomain.setTopicFrom("阿宅");
        adDomain.setTopic("运动增加肺活量，怎样运动才能更好的增加肺活量呢？");
        adDomain.setImgUrl("http://img1.imgtn.bdimg.com/it/u=3988325018,1531979319&fm=21&gp=0.jpg");
        adDomain.setAd(false);
        adList.add(adDomain);

        AdDomain adDomain2 = new AdDomain();
        adDomain2.setId("10000084");
        adDomain2.setDate("3月5日");
        adDomain2.setTitle("血糖升高的7大原因");
        adDomain2.setTopicFrom("小巫");
        adDomain2.setTopic("“要预防高血糖，务必从了解血糖高的原因这方面着手。一般情况下，血糖高的原因我们大致上可以分为四类：饮食习惯、胰岛素、情绪波动以及睡眠质量。”");
        adDomain2
                .setImgUrl("http://www.ithao123.cn/uploads/u/aa/39/aa3967b1582686221755d54021579e77.jpg");
        adDomain2.setAd(false);
        adList.add(adDomain2);

        AdDomain adDomain3 = new AdDomain();
        adDomain3.setId("10000071");
        adDomain3.setDate("3月6日");
        adDomain3.setTitle("骑自行车锻炼注意事项");
        adDomain3.setTopicFrom("旭东");
        adDomain3.setTopic("骑自行车锻炼，好处多，注意事项记心间");
        adDomain3
                .setImgUrl("http://imgs.jqueryfuns.com/2014/8/26/4257_c1e732f67447b90d167e7b39f563edea.png");
        adDomain3.setAd(false);
        adList.add(adDomain3);

        AdDomain adDomain4 = new AdDomain();
        adDomain4.setId("10000035");
        adDomain4.setDate("3月7日");
        adDomain4.setTitle("减肥，选择节食还是运动？");
        adDomain4.setTopicFrom("小软");
        adDomain4.setTopic("减肥，是现代爱美女性永不过时的话题，方法无非两种，节食和运动。那这两种减肥方式的效果一样吗？我们一起来探讨一下吧。");
        adDomain4
                .setImgUrl("http://img.htmleaf.com/1411/201411251442.jpg");
        adDomain4.setAd(false);
        adList.add(adDomain4);

        AdDomain adDomain5 = new AdDomain();
        adDomain5.setId("10000095");
        adDomain5.setDate("3月8日");
        adDomain5.setTitle("盘点冠心病7大元凶");
        adDomain5.setTopicFrom("大熊");
        adDomain5.setTopic("冠心病是一种常见的心脏疾病，冠心病的发病原因是什么呢?来了解一下冠心病的原因。");
        adDomain5
                .setImgUrl("http://img3.imgtn.bdimg.com/it/u=140268262,3525005711&fm=21&gp=0.jpg");
        adDomain5.setAd(true); // 代表是广告
        adList.add(adDomain5);

        AdDomain adDomain6 = new AdDomain();
        adDomain6.setId("10000102");
        adDomain6.setDate("3月8日");
        adDomain6.setTitle("补充7种元素应对骨质疏松");
        adDomain6.setTopicFrom("大熊");
        adDomain6.setTopic("补充哪7种元素能轻松应对骨质疏松呢？");
        adDomain6
                .setImgUrl("http://www.internetke.com/public/images/jsImg/inkbigImg_1352250790.jpg");
        adDomain6.setAd(true); // 代表是广告
        adList.add(adDomain6);

        AdDomain adDomain7 = new AdDomain();
        adDomain7.setId("10000108");
        adDomain7.setDate("3月8日");
        adDomain7.setTitle("生活中不可缺少的“第七大营养素”");
        adDomain7.setTopicFrom("大熊");
        adDomain7.setTopic("随着人们生活水平的提高，对食品的要求越来越精细，所摄入的食物中，粗纤维的含量越来越少，现代“文明病”诸如便秘、肥胖症、动脉硬化、心脑血管疾病、糖尿病等，严重地威胁着现代人的身体健康，在人们的食物中补充膳食纤维已成为当务之急。");
        adDomain7
                .setImgUrl("http://www.dream2004.com/images/goods/shopex/excustom/excus00236_details01.jpg");
        adDomain7.setAd(true); // 代表是广告
        adList.add(adDomain7);

        AdDomain adDomain8 = new AdDomain();
        adDomain8.setId("10000086");
        adDomain8.setDate("3月8日");
        adDomain8.setTitle("如何预防脑出血");
        adDomain8.setTopicFrom("大熊");
        adDomain8.setTopic("7种方法预防脑出血");
        adDomain8
                .setImgUrl("http://www.5imoban.net/uploads/allimg/140221/1-140221122915456.jpg");
        adDomain8.setAd(true); // 代表是广告
        adList.add(adDomain8);

        AdDomain adDomain9 = new AdDomain();
        adDomain9.setId("10000076");
        adDomain9.setDate("3月8日");
        adDomain9.setTitle("远离高血压8妙招");
        adDomain9.setTopicFrom("大熊");
        adDomain9.setTopic("随着我们生活水平的提高，三高人群也逐渐扩大及年轻化，其中高血压在我们生活中已经并不陌生，很多人都知道高血压应该吃什么，怎么做，但很少有人能详细的知道高血压的具体对策和预防措施，下面这篇文章将详细的为你介绍八个远离高血压的措施。");
        adDomain9
                .setImgUrl("http://img0.imgtn.bdimg.com/it/u=1076153902,2140538972&fm=21&gp=0.jpg");
        adDomain9.setAd(true); // 代表是广告
        adList.add(adDomain9);
        
        return adList;
    }
    
    
}
