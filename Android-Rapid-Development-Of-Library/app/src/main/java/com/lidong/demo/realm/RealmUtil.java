package com.lidong.demo.realm;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
/**
*
*@className:RealmUtil
*@desc:RealmUtil工具类
*@author:lidong
*@datetime:16/6/10 下午9:55

*/
public class RealmUtil {
    
    
    private static  RealmUtil sIntance;
    public final Context mContext;
    private String realmName = "realm_demo.realm";

    public RealmUtil(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 双检索单例
     * @param context
     * @return
     */
    public  static  RealmUtil getIntance(Context context){

        if (sIntance == null) {
            synchronized (RealmUtil.class) {
                if (sIntance == null) {
                    sIntance = new RealmUtil(context);
                }
            }
        }
        return  sIntance;
    }

    /**
     * 获取realm对象
     * @return
     */
    public  Realm getRealm(){
        Realm realm =
                Realm.getInstance(
                        new RealmConfiguration.Builder(mContext)
                                .name(realmName)
                                .build());
        return  realm;
    }
}
