package com.lidong.demo.util;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.lidong.demo.TApplication;
import com.umeng.analytics.MobclickAgent;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
*@类名 : ExceptionUtils
*@描述 : 
*@时间 : 2016/4/20  9:16
*@作者: 李东
*@邮箱  : lidong@chni.com.cn
*@company: chni
*/
public class ExceptionUtils {

    /**
     * 异常处理工具
     *
     * @param ctx 错误的上下文信息
     * @param e  异常
     */
    public static boolean handleException(final Context ctx, Throwable e) {
        if (ctx == null || e == null) {
            return false;
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String detailErrorInfo = stringWriter.toString();
        if (TApplication.release) {//发布之后这将异常传递到MobClickAgent错误列表中
            MobclickAgent.reportError(ctx, detailErrorInfo);
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(ctx, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
            }.start();
            return  true;
        } else {//没有发布时，将异常信息打印到Logcat控制台
            Log.e("detailErrorInfo", detailErrorInfo);
            return false;
        }

    }
}
