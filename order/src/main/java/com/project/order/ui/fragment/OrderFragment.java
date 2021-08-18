package com.project.order.ui.fragment;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.project.common.Constance;
import com.project.common.fragment.BaseFragment;
import com.project.order.R;
import com.project.order.databinding.FragmentOrderBinding;
import com.project.order.ui.fragment.order.FinishFragment;
import com.project.order.ui.fragment.order.HandlingFragment;
import com.project.order.ui.fragment.order.NoHandlerFragment;
import com.project.order.ui.fragment.order.RefuseFragment;

import java.util.ArrayList;

@Route(path = Constance.FRAGMENT_ORDER)
public class OrderFragment extends BaseFragment<FragmentOrderBinding> {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ViewPagerFragmentStateAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void initView() {
        mFragments.add(new NoHandlerFragment());
        mFragments.add(new HandlingFragment());
        mFragments.add(new FinishFragment());
        mFragments.add(new RefuseFragment());
        mAdapter = new ViewPagerFragmentStateAdapter(getChildFragmentManager(),getLifecycle(),mFragments );
        viewDataBinding.viewPager2.setAdapter(mAdapter);
        viewDataBinding.viewPager2.setUserInputEnabled(true);
    }

    @Override
    public void initListener() {
        viewDataBinding.tabHead.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewDataBinding.viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewDataBinding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                viewDataBinding.tabHead.getTabAt(position).select();
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public void Load(View view) {

    }

    @Override
    public void onResume() {
        Log.d(TAG,"onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
