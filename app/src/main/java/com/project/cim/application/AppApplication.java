package com.project.cim.application;


import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.project.common.application.BaseApplication;

public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
