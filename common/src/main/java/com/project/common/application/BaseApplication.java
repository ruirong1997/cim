package com.project.common.application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.project.common.preference.PreferencesUtil;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        boolean debug = false;  //调试用

        if(debug){
            ARouter.openLog();
            ARouter.openDebug();
        }

        ARouter.init(this);
        PreferencesUtil.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
