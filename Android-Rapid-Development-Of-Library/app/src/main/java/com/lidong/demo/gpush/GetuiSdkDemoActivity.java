package com.lidong.demo.gpush;

/*
 * 推送SDK演示工程
 * 
 * SDK Ver: 2.0.0.0
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.Tag;
import com.lidong.demo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetuiSdkDemoActivity extends AppCompatActivity implements OnClickListener {

    /**
     * 第三方应用Master Secret，修改为正确的值
     */
    private static final String MASTERSECRET = "4GfbjSDIRCApCtt0Z5dAy9";

    // 菜单
    private static final int ADDTAG = 100;
    private static final int VERSION = 101;
    private static final int SILENTTIME = 102;
    private static final int EXIT = 103;
    private static final int GETCLIENTID = 106;

    // UI控件
    private Button clearBtn;
    private Button serviceBtn;
    private Button bindAliasBtn;
    private Button unbindAliasBtn;
    private TextView appKeyView;
    private TextView appSecretView;
    private TextView appIdView;
    private TextView masterSecretView;
    public static TextView tView;
    public static TextView tLogView;
    /**
     * 透传测试
     */
    private Button transmissionBtn;

    /**
     * 通知测试
     */
    private Button notifactionBtn;

    /**
     * SDK服务是否启动
     */
    private boolean isServiceRunning = true;
    private Context context;
    private SimpleDateFormat formatter;
    private Date curDate;

    // SDK参数，会自动从Manifest文件中读取，第三方无需修改下列变量，请修改AndroidManifest.xml文件中相应的meta-data信息。
    // 修改方式参见个推SDK文档
    private String appkey = "";
    private String appsecret = "";
    private String appid = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // UI初始化
        setContentView(R.layout.main);
        context = this;
        isServiceRunning = true;
        clearBtn = (Button) findViewById(R.id.btn_clear);
        clearBtn.setOnClickListener(this);
        serviceBtn = (Button) findViewById(R.id.btn_service);
        serviceBtn.setOnClickListener(this);
        bindAliasBtn = (Button) findViewById(R.id.btn_bind_alias);
        bindAliasBtn.setOnClickListener(this);
        unbindAliasBtn = (Button) findViewById(R.id.btn_unbind_alias);
        unbindAliasBtn.setOnClickListener(this);
        tView = (TextView) findViewById(R.id.tvclientid);
        appKeyView = (TextView) findViewById(R.id.tvappkey);
        appSecretView = (TextView) findViewById(R.id.tvappsecret);
        masterSecretView = (TextView) findViewById(R.id.tvmastersecret);
        appIdView = (TextView) findViewById(R.id.tvappid);
        tLogView = (EditText) findViewById(R.id.tvlog);
        tLogView.setInputType(InputType.TYPE_NULL);
        tLogView.setSingleLine(false);
        tLogView.setHorizontallyScrolling(false);
        transmissionBtn = (Button) findViewById(R.id.btn_pmsg);
        transmissionBtn.setOnClickListener(this);
        notifactionBtn = (Button) findViewById(R.id.btn_psmsg);
        notifactionBtn.setOnClickListener(this);

        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 从AndroidManifest.xml的meta-data中读取SDK配置信息
        String packageName = getApplicationContext().getPackageName();
        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                appid = appInfo.metaData.getString("PUSH_APPID");
                appsecret = appInfo.metaData.getString("PUSH_APPSECRET");
                appkey = (appInfo.metaData.get("PUSH_APPKEY") != null) ? appInfo.metaData.get("PUSH_APPKEY").toString() : null;
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        appKeyView.setText("AppKey=" + appkey);
        appSecretView.setText("AppSecret=" + appsecret);
        masterSecretView.setText("MasterSecret=" + MASTERSECRET);
        appIdView.setText("AppID=" + appid);

//        // SDK初始化，第三方程序启动时，都要进行SDK初始化工作
//        Log.d("GetuiSdkDemo", "initializing sdk...");
//        PushManager.getInstance().initialize(this.getApplicationContext());

        /**
         * 应用未启动, 个推 service已经被唤醒,显示该时间段内离线消息
         */
        if (PushDemoReceiver.payloadData != null) {
            tLogView.append(PushDemoReceiver.payloadData);
        }
    }


    @Override
    public void onDestroy() {
        Log.d("GetuiSdkDemo", "onDestroy()");
        PushDemoReceiver.payloadData.delete(0, PushDemoReceiver.payloadData.length());
        super.onDestroy();
    }

    @Override
    public void onStop() {
        Log.d("GetuiSdkDemo", "onStop()");
        super.onStop();
    }

    public void onClick(View v) {
        if (v == clearBtn) {
            tLogView.setText("");
            PushDemoReceiver.payloadData.delete(0, PushDemoReceiver.payloadData.length());
        } else if (v == serviceBtn) {
            if (isServiceRunning) {
                // 当前为运行状态，停止SDK服务
                Log.d("GetuiSdkDemo", "stopping sdk...");
                PushManager.getInstance().stopService(this.getApplicationContext());

                // UI更新
                tView.setText(getResources().getString(R.string.no_clientid));
                serviceBtn.setText(getResources().getString(R.string.start));

                isServiceRunning = false;
            } else {
                // 当前未运行状态，启动SDK服务
                Log.d("GetuiSdkDemo", "reinitializing sdk...");

                // 重新初始化sdk
                PushManager.getInstance().initialize(this.getApplicationContext());

                // UI更新
                serviceBtn.setText(getResources().getString(R.string.stop));
                isServiceRunning = true;
            }
        } else if (v == bindAliasBtn) {
            final EditText editText = new EditText(GetuiSdkDemoActivity.this);
            new AlertDialog.Builder(GetuiSdkDemoActivity.this).setTitle(R.string.bind_alias).setView(editText)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (editText.getEditableText() != null) {
                                String alias = editText.getEditableText().toString();
                                if (alias != null && alias.length() > 0) {
                                    PushManager.getInstance().bindAlias(GetuiSdkDemoActivity.this, alias);
                                    System.out.println("bind alias:" + editText.getEditableText().toString());
                                    return;
                                }
                            }
                        }
                    }).setNegativeButton(android.R.string.cancel, null).show();
        } else if (v == unbindAliasBtn) {
            final EditText editText = new EditText(GetuiSdkDemoActivity.this);
            new AlertDialog.Builder(GetuiSdkDemoActivity.this).setTitle(R.string.unbind_alias).setView(editText)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String alias = editText.getEditableText().toString();
                            if (alias != null && alias.length() > 0) {
                                //true为只解绑自己，false为解绑全部
                                PushManager.getInstance().unBindAlias(GetuiSdkDemoActivity.this, alias, true);
                                System.out.println("unbind alias:" + editText.getEditableText().toString());
                                return;
                            }

                        }
                    }).setNegativeButton(android.R.string.cancel, null).show();
        } else if (v == transmissionBtn) {
            if (isNetworkConnected()) {
                // !!!!!!注意：以下为个推服务端API1.0接口，仅供测试。不推荐在现网系统使用1.0版服务端接口，请参考最新的个推服务端API接口文档，使用最新的2.0版接口
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("action", "pushmessage"); // pushmessage为接口名，注意全部小写
                /*---以下代码用于设定接口相应参数---*/
                param.put("appkey", appkey);
                param.put("appid", appid);
                // 注：透传内容后面需用来验证接口调用是否成功，假定填写为hello girl~
                param.put("data", "收到一条透传测试消息");

                curDate = new Date(System.currentTimeMillis());
                param.put("time", formatter.format(curDate)); // 当前请求时间，可选
                param.put("clientid", PushManager.getInstance().getClientid(this)); // 您获取的ClientID
                param.put("expire", 3600); // 消息超时时间，单位为秒，可选

                // 生成Sign值，用于鉴权
                param.put("sign", GetuiSdkHttpPost.makeSign(MASTERSECRET, param));

                GetuiSdkHttpPost.httpPost(param);
            } else {
                Toast.makeText(this, "对不起，当前网络不可用!", Toast.LENGTH_SHORT).show();
            }
        } else if (v == notifactionBtn) {
            if (isNetworkConnected()) {
                // !!!!!!注意：以下为个推服务端API1.0接口，仅供测试。不推荐在现网系统使用1.0版服务端接口，请参考最新的个推服务端API接口文档，使用最新的2.0版接口
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("action", "pushSpecifyMessage"); // pushSpecifyMessage为接口名，注意大小写
                /*---以下代码用于设定接口相应参数---*/
                param.put("appkey", appkey);
                param.put("type", 2); // 推送类型： 2为消息
                param.put("pushTitle", "通知栏测试"); // pushTitle请填写您的应用名称

                // 推送消息类型，有TransmissionMsg、LinkMsg、NotifyMsg三种，此处以LinkMsg举例
                param.put("pushType", "LinkMsg");

                param.put("offline", true); // 是否进入离线消息

                param.put("offlineTime", 72); // 消息离线保留时间
                param.put("priority", 1); // 推送任务优先级

                List<String> cidList = new ArrayList<String>();
                cidList.add(tView.getText().toString()); // 您获取的ClientID
                param.put("tokenMD5List", cidList);

                // 生成Sign值，用于鉴权，需要MasterSecret，请务必填写
                param.put("sign", GetuiSdkHttpPost.makeSign(MASTERSECRET, param));

                // LinkMsg消息实体
                Map<String, Object> linkMsg = new HashMap<String, Object>();
                linkMsg.put("linkMsgIcon", "push.png"); // 消息在通知栏的图标
                linkMsg.put("linkMsgTitle", "通知栏测试"); // 推送消息的标题
                linkMsg.put("linkMsgContent", "您收到一条测试消息，点击访问www.igetui.com！"); // 推送消息的内容
                linkMsg.put("linkMsgUrl", "http://www.igetui.com/"); // 点击通知跳转的目标网页
                param.put("msg", linkMsg);

                GetuiSdkHttpPost.httpPost(param);
            } else {
                Toast.makeText(this, "对不起，当前网络不可用!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ADDTAG: {
                // 测试addTag接口
                final View view = new EditText(this);
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setTitle("设置Tag").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tagText = (TextView) view;

                        Log.d("GetuiSdkDemo", "设置tag:" + tagText.getText().toString());

                        String[] tags = tagText.getText().toString().split(",");
                        Tag[] tagParam = new Tag[tags.length];
                        for (int i = 0; i < tags.length; i++) {
                            Tag t = new Tag();
                            t.setName(tags[i]);
                            tagParam[i] = t;
                        }

                        int i = PushManager.getInstance().setTag(context, tagParam);
                        String text = "ERROR";

                        switch (i) {
                            case PushConsts.SETTAG_SUCCESS:
                                text = "设置标签成功";
                                break;

                            case PushConsts.SETTAG_ERROR_COUNT:
                                text = "设置标签失败，tag数量过大";
                                break;

                            default:
                                text = "设置标签失败，setTag异常";
                                break;
                        }

                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                        Log.d("GetuiSdkDemo", text);

                        dialog.dismiss();
                    }
                }).setView(view);
                alertBuilder.create().show();

                break;
            }

            case VERSION: {
                // 测试getVersion获取版本号接口
                String version = PushManager.getInstance().getVersion(this);
                Toast.makeText(this, "当前sdk版本为：" + version, Toast.LENGTH_SHORT).show();
                break;
            }

            case SILENTTIME: {
                // 测试setSilentTime设置静默时间接口
                final View view = LayoutInflater.from(this).inflate(R.layout.silent_setting, null);
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setTitle("设置静默时间段").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        TextView beginText = (TextView) view.findViewById(R.id.beginText);
                        TextView durationText = (TextView) view.findViewById(R.id.durationText);

                        try {
                            int beginHour = Integer.valueOf(String.valueOf(beginText.getText()));
                            int durationHour = Integer.valueOf(String.valueOf(durationText.getText()));

                            boolean result = PushManager.getInstance().setSilentTime(context, beginHour, durationHour);

                            if (result) {
                                Toast.makeText(context, "设置静默时间段 begin:" + beginHour + " duration:" + durationHour, Toast.LENGTH_SHORT).show();
                                Log.d("GetuiSdkDemo", "设置静默时间段 begin:" + beginHour + " duration:" + durationHour);
                            } else {
                                Toast.makeText(context, "设置静默时间段失败，取值超范围 begin:" + beginHour + " duration:" + durationHour, Toast.LENGTH_SHORT)
                                        .show();
                                Log.d("GetuiSdkDemo", "设置静默时间段失败，取值超范围 begin:" + beginHour + " duration:" + durationHour);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, "设置静默时间只能设置整数时间！", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                    }
                }).setView(view);
                alertBuilder.create().show();
                break;
            }

            case GETCLIENTID: {
                // 手动获取cid

                 String cid = PushManager.getInstance().getClientid(this);
                 Toast.makeText(GetuiSdkDemoActivity.this, "当前应用的cid为：" + cid, Toast.LENGTH_LONG).show();
                 Log.d("GetuiSdkDemo", "当前应用的cid为：" + cid);
                break;
            }

            case EXIT: {
                // 结束
                finish();
                break;
            }

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ADDTAG, 0, "添加Tag");
        menu.add(0, VERSION, 1, "当前版本");
        menu.add(0, SILENTTIME, 2, "设置静默时间");
        menu.add(0, GETCLIENTID, 3, "手动获取cid");
        menu.add(0, EXIT, 4, "退出");

        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            // 返回键最小化程序
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            startActivity(intent);
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }

    public boolean isNetworkConnected() {
        // 判断网络是否连接
        ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return mNetworkInfo != null && mNetworkInfo.isAvailable();
    }

}
