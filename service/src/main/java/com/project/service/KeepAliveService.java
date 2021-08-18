package com.project.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.project.common.Constance;

@Route(path = Constance.SERVICE_KEEP_ALIVE)
public class KeepAliveService extends Service {

    private static final String TAG = "KeepAliveService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind - Thread ID = " + Thread.currentThread().getId());
        return null;
    }
}
