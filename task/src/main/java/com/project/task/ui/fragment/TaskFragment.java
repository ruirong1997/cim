package com.project.task.ui.fragment;

import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.project.common.Constance;
import com.project.common.fragment.BaseFragment;
import com.project.task.R;
import com.project.task.databinding.FragmentTaskBinding;
import com.project.task.ui.activity.ReportActivity;

@Route(path = Constance.FRAGMENT_TASK)
public class TaskFragment extends BaseFragment<FragmentTaskBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_task;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        viewDataBinding.btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportActivity.start(v.getContext());
            }
        });
        viewDataBinding.btnFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewDataBinding.btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void Load(View view) {

    }

}
