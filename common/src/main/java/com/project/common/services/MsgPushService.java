package com.project.common.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MsgPushService extends Service {

    private static final String TAG = "MsgPushService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind - Thread ID = " + Thread.currentThread().getId());
        return null;
    }
}
