package com.project.common.services;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.List;

public class ServiceManager {

    private final static String TAG = "ServiceManager";

    public static boolean isServiceExisted(Context context, String className) {
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList =activityManager.getRunningServices(Integer.MAX_VALUE);
        if(!(serviceList.size() > 0)) {
            return false;
        }
        for(int i = 0; i < serviceList.size(); i++) {
            ActivityManager.RunningServiceInfo serviceInfo = serviceList.get(i);
            ComponentName serviceName = serviceInfo.service;
            if(serviceName.getClassName().equals(className)) { return true; }
        }
        return false;
    }

    public static void startServices(Context context){
        //查询服务是否已经开启
        //消息推送服务
        if (!isServiceExisted(context,"com.project.common.services.MsgPushService")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(new Intent(context, MsgPushService.class));
            } else {
                context.startService(new Intent(context, MsgPushService.class));
            }
        }
        //监测保活服务
        if (!isServiceExisted(context,"com.project.common.services.KeepAliveService")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(new Intent(context, KeepAliveService.class));
            } else {
                context.startService(new Intent(context, KeepAliveService.class));
            }
        }
    }

}
