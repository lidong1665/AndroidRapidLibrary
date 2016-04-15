package com.lidong.demo.gpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.lidong.demo.MainActivity;

public class PushDemoReceiver extends BroadcastReceiver {

    /**
     * 应用未启动, 个推 service已经被唤醒,保存在该时间段内离线消息(此时 GetuiSdkDemoActivity.tLogView == null)
     */
    public static StringBuilder payloadData = new StringBuilder();

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d("GetuiSdkDemo", "onReceive() action=" + bundle.getInt("action"));

        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
            case PushConsts.GET_MSG_DATA:
                // 获取透传数据
                // String appid = bundle.getString("appid");
                byte[] payload = bundle.getByteArray("payload");

                String taskid = bundle.getString("taskid");
                Log.d("GetuiSdkDemo", "taskid : " + taskid);
                String messageid = bundle.getString("messageid");
                Log.d("GetuiSdkDemo", "messageid : " + messageid);
                String message = bundle.getString("messageContent");
                Log.d("GetuiSdkDemo", "messageid : " + message);
                // smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
                boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
                System.out.println("第三方回执接口调用" + (result ? "成功" : "失败"));

                if (payload != null) {
                    String data = new String(payload);

                    Log.d("GetuiSdkDemo", "receiver payload : " + data);
//                    processCustomMessage(context,data);

                    payloadData.append(data);
                    payloadData.append("\n");

                    if (GetuiSdkDemoActivity.tLogView != null) {
                        GetuiSdkDemoActivity.tLogView.append(data + "\n");
                    }
                }
                break;

            case PushConsts.GET_CLIENTID:
                // 获取ClientID(CID)
                // 第三方应用需要将CID上传到第三方服务器，并且将当前用户帐号和CID进行关联，以便日后通过用户帐号查找CID进行消息推送
                String cid = bundle.getString("clientid");
                Log.d("cid", "cid=" + cid);
                if (GetuiSdkDemoActivity.tView != null) {
                    GetuiSdkDemoActivity.tView.setText(cid);
                }
                Toast.makeText(context,cid,Toast.LENGTH_LONG).show();
                break;

            case PushConsts.THIRDPART_FEEDBACK:

                 String appid1 = bundle.getString("appid");
                 String taskid1 = bundle.getString("taskid");
                String actionid = bundle.getString("actionid");
                 String result1 = bundle.getString("result");
                long timestamp = bundle.getLong("timestamp");

                 Log.d("GetuiSdkDemo", "appid = " + appid1);
                Log.d("GetuiSdkDemo", "taskid = " + taskid1);
                Log.d("GetuiSdkDemo", "actionid = " + actionid);
                Log.d("GetuiSdkDemo", "result = " + result1);
                Log.d("GetuiSdkDemo", "timestamp = " + timestamp);

                break;

            default:
                break;
        }
    }

    /**
     * 发送通知去检查下载跟新bug
     * @param context
     * @param message
     */
    private void processCustomMessage(Context context, String message) {
        Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
        msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
        context.sendBroadcast(msgIntent);
    }
}
