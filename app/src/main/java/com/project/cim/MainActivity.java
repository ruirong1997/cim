package com.project.cim;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.MotionEvent;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.project.cim.adapter.ViewPagerFragmentStateAdapter;
import com.project.cim.databinding.ActivityMainBinding;
import com.project.common.Constance;
import com.project.common.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * 主页
 */
@Route(path = Constance.APP_ACTIVITY_MAIN)
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
    final int bgColor = Color.parseColor("#F8F5F5");

    private List<Fragment> mFragments = new ArrayList<>();
    private ViewPagerFragmentStateAdapter mAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context,MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.icon_bottom_chat),
                        bgColor)
                        //.selectedIcon(getResources().getDrawable(R.drawable.ic_sixth))
                        .title("聊天")
                        .badgeTitle("msg")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.icon_bottom_appstore),
                        bgColor)
                        //.selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("工作台")
//                        .badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.icon_bottom_order),
                        bgColor)
                        //.selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
                        .title("订单")
                        .badgeTitle("111")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.icon_bottom_friends),
                        bgColor)
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("联系人")
                        .badgeTitle(null)
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.icon_bottom_person),
                        bgColor)
                        //.selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("个人")
                        .badgeTitle(null)
                        .build()
        );
        viewDataBinding.bottomBar.setModels(models);
        viewDataBinding.bottomBar.setModelIndex(0, true);
        Fragment fragment= (Fragment) ARouter.getInstance()
                .build(Constance.FRAGMENT_PERSONAL)
                .navigation();
        mFragments.add((Fragment) ARouter.getInstance().build(Constance.FRAGMENT_CHAT).navigation());
        mFragments.add((Fragment) ARouter.getInstance().build(Constance.FRAGMENT_TASK).navigation());
        mFragments.add((Fragment) ARouter.getInstance().build(Constance.FRAGMENT_ORDER).navigation());
        mFragments.add((Fragment) ARouter.getInstance().build(Constance.FRAGMENT_CONTACTS).navigation());
        mFragments.add((Fragment) ARouter.getInstance().build(Constance.FRAGMENT_PERSONAL).navigation());

        mAdapter = new ViewPagerFragmentStateAdapter(this.getSupportFragmentManager(),getLifecycle(),mFragments );

        viewDataBinding.viewPager2.setAdapter(mAdapter);
        viewDataBinding.viewPager2.setUserInputEnabled(false);
    }

    @Override
    public void initListener() {
        viewDataBinding.bottomBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(NavigationTabBar.Model model, int index) {

            }

            @Override
            public void onEndTabSelected(NavigationTabBar.Model model, int index) {
                viewDataBinding.bottomBar.getModels().get(index).hideBadge();
                viewDataBinding.viewPager2.setCurrentItem(index,false);
            }
        });
    }

    @Override
    protected boolean isClickView(MotionEvent ev) {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
