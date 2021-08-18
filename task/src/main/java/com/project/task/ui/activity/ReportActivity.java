package com.project.task.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.project.common.Constance;
import com.project.common.activity.BaseActivity;
import com.project.task.R;
import com.project.task.databinding.ActivityReportBinding;

@Route( path = Constance.ACTIVITY_REPORT)
public class ReportActivity extends BaseActivity<ActivityReportBinding> {

    public static void start(Context context) {
        Intent starter = new Intent(context, ReportActivity.class);
        context.startActivity(starter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_report;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    protected boolean isClickView(MotionEvent ev) {
        return false;
    }

    public void back(View view) {
        onBackPressed();
    }

    /**
     * 设备故障
     * @param view
     */
    public void error_dev(View view) {
        Log.d(TAG,"error_dev");
        Intent intent = new Intent(this,ErrorDevActivity.class);
        this.startActivity(intent);
    }
}
