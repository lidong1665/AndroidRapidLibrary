package com.lidong.demo.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidong.demo.R;
import com.lidong.demo.view.fragment.CharacterParser;
import com.lidong.demo.view.fragment.Friend;
import com.lidong.demo.view.fragment.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
*@类名 : ExtenpListViewActivity
*@描述 : 
*@时间 : 2016/4/15  9:00
*@作者: 李东
*@邮箱  : lidong@chni.com.cn
*@company: chni
*/
public class ExtenpListViewActivity extends AppCompatActivity {

    @Bind(R.id.elv_good_friend)
    ExpandableListView elvGoodFriend;

    MyExpandableListViewAdapter mMyExpandableListViewAdapter;
    /**
     * 组的数据
     */
    private static final String[] mGroup = new String[] {"我的好友","我的关注"};
    /**
     * 每个组的数据
     */
    private String[][] mGroupData = new String[][] {
            {  "王银1", "王银2", "王银3"},
            { "李东1", "王银1", "李东2", "王银2", "李东3", "王银3"}};
    private String TAG = ExtenpListViewActivity.class.getSimpleName();

    private Map<String,List<Friend>> mMap = new HashMap<>();

    private int mFlag = 1;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cicle);
        ButterKnife.bind(this);
        initData();

        pinyinComparator = PinyinComparator.getInstance();
        characterParser = CharacterParser.getInstance();
        mMyExpandableListViewAdapter = new MyExpandableListViewAdapter(this);
        elvGoodFriend.setAdapter(mMyExpandableListViewAdapter);
        elvGoodFriend.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.d(TAG, "onChildClick() called with: " + "parent = [" + parent + "], v = [" + v + "], groupPosition = [" + groupPosition + "], childPosition = [" + childPosition + "], id = [" + id + "]");

                return false;
            }
        });
    }

    private void initData() {
        List<Friend> list = new ArrayList<>();
        Friend f1 = new Friend();
        f1.setName("王银1");
        list.add(f1);

        mMap.put("我的好友",list);


        List<Friend> list2 = new ArrayList<>();
        Friend f2 = new Friend();
        f2.setName("王银2");
        list2.add(f2);
        Friend f3 = new Friend();
        f3.setName("李2");
        list2.add(f3);
        Friend f4 = new Friend();
        f4.setName("胡3");
        list2.add(f4);
        mMap.put("我的关注",list2);

        Log.d(TAG, "initData() called with: " + mMap.get("我的好友").toString()+"--"+mMap.get("我的关注").toString());
    }

    /**
    *@类名 : ExtenpListViewActivity
    *@描述 :
    *@时间 : 2016/4/12  13:56
    *@作者: 李东
    *@邮箱  : lidong@chni.com.cn
    *@company: chni
    */
    private class MyExpandableListViewAdapter extends BaseExpandableListAdapter{

        public MyExpandableListViewAdapter(Context context) {
            this.context = context;
        }

        private Context context;

        @Override
        public int getGroupCount() {
            return mGroup.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
                return 1;//注意返回值必须为1，不然会重复
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mGroup[groupPosition] ;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return mGroupData[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            int count = 0;
            count = mGroupData[groupPosition].length;
            return count;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            TextView text = (TextView) View.inflate(context, android.R.layout.simple_expandable_list_item_1, null);
            text.setText(mGroup[groupPosition]);
            return text;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (groupPosition==0){
                SubListView lv = (SubListView) View.inflate(context, R.layout.item_expend_listview, null);
                final List<Friend> mSourceDateList =  mMap.get("我的好友");
                ListViewAdaAdapter1 listViewAdaAdapter = new ListViewAdaAdapter1(mSourceDateList, context);
                lv.setAdapter(listViewAdaAdapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ExtenpListViewActivity.this, mSourceDateList.get(position).getName(),Toast.LENGTH_SHORT).show();
                    }
                });
                return lv;
            }else{
                SubListView lv = (SubListView) View.inflate(context, R.layout.item_expend_listview, null);
                final List<Friend> mSourceDateList =  mMap.get("我的关注");
                Log.d(TAG, "mSourceDateList: " + mSourceDateList.size());
                List<Friend> SourceDateList = new ArrayList<>();
                Log.d(TAG, "getChildView() called with: " + "groupPosition = [" + groupPosition + "], childPosition = [" + childPosition + "], convertView = [" + convertView + "],数据" + mSourceDateList.size());
                SourceDateList= filledData(mSourceDateList);
                // 根据a-z进行排序源数据
                Collections.sort(SourceDateList, pinyinComparator);
                ListViewAdaAdapter listViewAdaAdapter = new ListViewAdaAdapter(SourceDateList, context);
                lv.setAdapter(listViewAdaAdapter);
                final List<Friend> finalSourceDateList = SourceDateList;
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ExtenpListViewActivity.this, finalSourceDateList.get(position).getName(),Toast.LENGTH_SHORT).show();
                    }
                });
                return lv;
            }
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }

    private class ListViewAdaAdapter1 extends BaseAdapter{

        List<Friend> mName;
        Context mContext;

        public ListViewAdaAdapter1( List<Friend> name, Context context) {
            mName = name;
            mContext = context;
        }

        @Override
        public int getCount() {
            return mName.size();
        }

        @Override
        public Object getItem(int position) {
            return mName.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder =null;
            if (convertView ==null) {
                holder = new ViewHolder();
                convertView  = View.inflate(mContext, R.layout.item_listview_item1, null);
                holder.mTextView = (TextView) convertView.findViewById(R.id.tv_text);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            Log.d(TAG, "getView() called with: " + "position = [" + position + "], 数据"+mName.get(position).getName());
            holder.mTextView.setText(mName.get(position).getName());
            return convertView;
        }

        private class ViewHolder {
            TextView mTextView;
        }
    }




    private class ListViewAdaAdapter extends BaseAdapter{

       List<Friend> mName;
        Context mContext;

        public ListViewAdaAdapter( List<Friend> name, Context context) {
            mName = name;
            mContext = context;
        }

        @Override
        public int getCount() {
            return mName.size();
        }

        @Override
        public Object getItem(int position) {
            return mName.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder =null;
            if (convertView ==null) {
                holder = new ViewHolder();
                convertView  = View.inflate(mContext, R.layout.item_listview_item, null);
                holder.mTextView = (TextView) convertView.findViewById(R.id.tv_text);
                holder.mCatalog = (TextView) convertView.findViewById(R.id.catalog);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            Log.d(TAG, "getView() called with: " + "position = [" + position + "], 数据"+mName.get(position).getName());
            holder.mTextView.setText(mName.get(position).getName());
            holder.mCatalog.setText(mName.get(position).getLetters());
            return convertView;
        }

        private class ViewHolder {
            TextView mTextView,mCatalog;
        }
    }

    /**
     * 为ListView填充数据
     *
     * @param
     * @return
     */
    private List<Friend> filledData(List<Friend> lsit) {
        List<Friend> mFriendList = new ArrayList<>();

        for (int i = 0; i < lsit.size(); i++) {
            Friend friendModel = new Friend();
            friendModel.setName(lsit.get(i).getName());
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(lsit.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                friendModel.setLetters(sortString.toUpperCase());
            } else {
                friendModel.setLetters("#");
            }

            mFriendList.add(friendModel);
        }
        return mFriendList;

    }

}
