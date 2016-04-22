package com.lidong.demo.baidu_map_first;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.clusterutil.clustering.ClusterItem;
import com.baidu.mapapi.clusterutil.clustering.ClusterManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;
import com.lidong.demo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

//AB:C6:D4:99:EA:3F:F9:A3:9C:B7:06:22:6A:56:C7:96:95:F3:F9:C0
public class BaiduMapFirstActivity extends AppCompatActivity implements BaiduMap.OnMapLoadedCallback {

    @SuppressWarnings("unused")
    private static final String LTAG = BaiduMapFirstActivity.class.getSimpleName();
    private MapView mMapView;
    private BaiduMap mBaiduMap;

    @Override
    public void onMapLoaded() {

    }

    /**
     * 构造广播监听类，监听 SDK key 验证以及网络异常广播
     */
    public class SDKReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            Log.d(LTAG, "action: " + s);
            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
               Toast.makeText(context,"key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置",Toast.LENGTH_SHORT).show();
            } else if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
                Toast.makeText(context,"key 验证成功! 功能可以正常使用",Toast.LENGTH_SHORT).show();
            }
            else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
                Toast.makeText(context,"网络出错",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private SDKReceiver mReceiver;

    MapStatus ms;
    private ClusterManager<MyItem> mClusterManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // 介绍如何使用个性化地图，需在MapView 构造前设置个性化地图文件路径
        // 注: 设置个性化地图，将会影响所有地图实例。
        // MapView.setCustomMapStylePath("个性化地图config绝对路径");
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.hasExtra("x") && intent.hasExtra("y")) {
            // 当用intent参数时，设置中心点为指定点
            Bundle b = intent.getExtras();
            LatLng p = new LatLng(b.getDouble("y"), b.getDouble("x"));
            mMapView = new MapView(this,
                    new BaiduMapOptions().mapStatus(new MapStatus.Builder()
                            .target(p).build()));
        } else {
            mMapView = new MapView(this, new BaiduMapOptions());
        }
        setContentView(mMapView);
        mBaiduMap = mMapView.getMap();


        mBaiduMap.setOnMapLoadedCallback(this);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(ms));
        // 定义点聚合管理类ClusterManager
        mClusterManager = new ClusterManager<MyItem>(this, mBaiduMap);
        // 添加Marker点
        addMarkers();
        // 设置地图监听，当地图状态发生改变时，进行点聚合运算
        mBaiduMap.setOnMapStatusChangeListener(mClusterManager);
        /**
         * 为标记物添加点击事件
         */
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng ll = marker.getPosition();
                Toast.makeText(BaiduMapFirstActivity.this,ll.toString() ,Toast.LENGTH_SHORT).show();
                final String ss = ll.latitude+","+ll.longitude;
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        getCityFromLngAndlat(ss);
                    }
                }.start();
                return false;
            }
        });


        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mReceiver = new SDKReceiver();
        registerReceiver(mReceiver, iFilter);
    }

    private void addMarkers() {
        // 添加Marker点
        // 添加Marker点
        LatLng llA = new LatLng(39.963175, 116.400244);
        LatLng llB = new LatLng(35.734417,107.693153);

        List<MyItem> items = new ArrayList<MyItem>();
        items.add(new MyItem(llA));
        items.add(new MyItem(llB));

        mClusterManager.addItems(items);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // activity 销毁时同时销毁地图控件
        mMapView.onDestroy();
        unregisterReceiver(mReceiver);
    }

    /**
     * 每个Marker点，包含Marker点坐标以及图标
     */
    public class MyItem implements ClusterItem {
        private final LatLng mPosition;

        public MyItem(LatLng latLng) {
            mPosition = latLng;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        @Override
        public BitmapDescriptor getBitmapDescriptor() {
            return BitmapDescriptorFactory
                    .fromResource(R.mipmap.icon_gcoding);
        }
    }


    public static void getCityFromLngAndlat(String ss)
    {
//       http://api.map.baidu.com/geocoder/v2/?ak=bAq10KCXfuek1pjblnjp9yiMZCSAeUiw&callback=renderReverse&location=39.983424,116.322987&output=json&pois=1
//        http://api.map.baidu.com/geocoder/v2/?ak=bAq10KCXfuek1pjblnjp9yiMZCSAeUiw&location=39.963175,116.400244&output=json&pois=1
//通过修改这里的location（经纬度）参数，即可得到相应经纬度的详细信息
        String url2 = "http://api.map.baidu.com/geocoder/v2/?ak=bAq10KCXfuek1pjblnjp9yiMZCSAeUiw&location="+ss+"&output=json&pois=1 ";
        URL myURL2 = null;
        URLConnection httpsConn2 = null;
        try {
            myURL2 = new URL(url2);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr2 = null;
        BufferedReader br2 = null;
        try {
            httpsConn2 = (URLConnection) myURL2.openConnection();// 不使用代理
            if (httpsConn2 != null) {
                insr2 = new InputStreamReader( httpsConn2.getInputStream(), "UTF-8");
                br2 = new BufferedReader(insr2);
                String data2 = br2.readLine();
                try
                {
                    //解析得到的json格式数据
                    JSONObject dataJson = new JSONObject(data2);
//                    JSONObject result = dataJson.getJSONObject("result");
//                    JSONObject addressComponent = result.getJSONObject("addressComponent");
//                    String city = addressComponent.getString("city");
                    System.out.println("city = " + data2);
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(insr2!=null){
                try {
                    insr2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br2!=null){
                try {
                    br2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
