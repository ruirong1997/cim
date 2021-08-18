package com.project.task.ui.activity;

import android.view.MotionEvent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.project.common.Constance;
import com.project.common.activity.BaseActivity;
import com.project.task.R;
import com.project.task.databinding.ActivityHistoryBinding;


public class HistoryActivity extends BaseActivity<ActivityHistoryBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_history;
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
}
