package com.project.order.ui.fragment.order;

import android.view.View;

import com.project.common.fragment.BaseFragment;
import com.project.order.R;
import com.project.order.databinding.FragmentOrderListBinding;
import com.project.order.ui.base.BaseRequestFragment;

public class NoHandlerFragment extends BaseRequestFragment<FragmentOrderListBinding> {
    @Override
    public String getStates() {
        return "未处置";
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_list;
    }

}
