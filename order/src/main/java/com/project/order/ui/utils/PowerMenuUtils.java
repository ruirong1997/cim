package com.project.order.ui.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.project.order.R;
import com.project.order.ui.adapter.CenterMenuAdapter;
import com.skydoves.powermenu.CircularEffect;
import com.skydoves.powermenu.CustomPowerMenu;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnDismissedListener;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

public class PowerMenuUtils {

    public static PowerMenu getHamburgerPowerMenu(
            Context context,
            LifecycleOwner lifecycleOwner,
            OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener,
            OnDismissedListener onDismissedListener) {
        return new PowerMenu.Builder(context)
                .addItem(new PowerMenuItem("响应", false))
                .addItem(new PowerMenuItem("延时", false))
                .addItem(new PowerMenuItem("完成", false))
                .addItem(new PowerMenuItem("拒绝", false))
//        .addItem(new PowerMenuItem("Travel", false))
                .setAutoDismiss(true)
                .setLifecycleOwner(lifecycleOwner)
                .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT)
                .setCircularEffect(CircularEffect.BODY)
                .setMenuRadius(10f)
                .setMenuShadow(10f)
                .setTextColor(ContextCompat.getColor(context, R.color.md_grey_800))
                .setTextSize(18)
                .setTextGravity(Gravity.CENTER)
                .setTextTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD))
                .setSelectedTextColor(Color.WHITE)
                .setMenuColor(Color.WHITE)
                .setSelectedMenuColor(ContextCompat.getColor(context, R.color.mainColor))
                .setOnMenuItemClickListener(onMenuItemClickListener)
                .setOnDismissListener(onDismissedListener)
                .setPreferenceName("HamburgerPowerMenu")
                .setInitializeRule(Lifecycle.Event.ON_CREATE, 0)
                .build();
    }

//  public static PowerMenu getProfilePowerMenu(
//      Context context,
//      LifecycleOwner lifecycleOwner,
//      OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener) {
//    return new PowerMenu.Builder(context)
//        .setHeaderView(R.layout.item_title_header)
//        .addItem(new PowerMenuItem("Profile", false))
//        .addItem(new PowerMenuItem("Board", false))
//        .addItem(new PowerMenuItem("Logout", false))
//        .setLifecycleOwner(lifecycleOwner)
//        .setAnimation(MenuAnimation.SHOWUP_TOP_RIGHT)
//        .setMenuRadius(10f)
//        .setMenuShadow(10f)
//        .setTextColor(ContextCompat.getColor(context, R.color.md_grey_800))
//        .setTextGravity(Gravity.CENTER)
//        .setMenuColor(Color.WHITE)
//        .setSelectedEffect(false)
//        .setShowBackground(false)
//        .setFocusable(true)
//        .setOnMenuItemClickListener(onMenuItemClickListener)
//        .build();
//  }

    public static CustomPowerMenu<String, CenterMenuAdapter> getWritePowerMenu(
            Context context,
            LifecycleOwner lifecycleOwner,
            OnMenuItemClickListener<String> onMenuItemClickListener) {
        ColorDrawable drawable =
                new ColorDrawable(ContextCompat.getColor(context, R.color.md_blue_grey_300));
        return new CustomPowerMenu.Builder<>(context, new CenterMenuAdapter())
                .addItem("提交")
                .addItem("延时")
                .addItem("转单")
                .addItem("拒绝")
                .setLifecycleOwner(lifecycleOwner)
                .setAnimation(MenuAnimation.FADE)
                .setCircularEffect(CircularEffect.BODY)
                .setMenuRadius(10f)
                .setMenuShadow(10f)
                .setDivider(drawable)
                .setDividerHeight(1)
                .setOnMenuItemClickListener(onMenuItemClickListener)
                .build();
    }

    public static CustomPowerMenu<String, CenterMenuAdapter> getAlertPowerMenu(
            Context context,
            LifecycleOwner lifecycleOwner,
            OnMenuItemClickListener<String> onMenuItemClickListener) {
        return new CustomPowerMenu.Builder<>(context, new CenterMenuAdapter())
                .addItem("You need to login!")
                .setLifecycleOwner(lifecycleOwner)
                .setAnimation(MenuAnimation.ELASTIC_CENTER)
                .setMenuRadius(10f)
                .setMenuShadow(10f)
                .setFocusable(true)
                .setOnMenuItemClickListener(onMenuItemClickListener)
                .setOnBackgroundClickListener(view -> {})
                .build();
    }

//  public static PowerMenu getIconPowerMenu(
//      Context context,
//      LifecycleOwner lifecycleOwner,
//      OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener) {
//
//    Context styledContext = new ContextThemeWrapper(context, R.style.PopupCardThemeOverlay);
//
//    return new PowerMenu.Builder(styledContext)
//        .addItem(new PowerMenuItem("WeChat", R.drawable.ic_wechat))
//        .addItem(new PowerMenuItem("Facebook", R.drawable.ic_facebook))
//        .addItem(new PowerMenuItem("Twitter", R.drawable.ic_twitter))
//        .addItem(new PowerMenuItem("Line", R.drawable.ic_line))
//        .addItem(new PowerMenuItem("Other"))
//        .setLifecycleOwner(lifecycleOwner)
//        .setOnMenuItemClickListener(onMenuItemClickListener)
//        .setAnimation(MenuAnimation.FADE)
//        .setMenuRadius(context.getResources().getDimensionPixelSize(R.dimen.menu_corner_radius))
//        .setMenuShadow(context.getResources().getDimensionPixelSize(R.dimen.menu_elevation))
//        .setIsMaterial(true)
//        .build();
//  }

//  public static PowerMenu getDialogPowerMenu(Context context, LifecycleOwner lifecycleOwner) {
//    return new PowerMenu.Builder(context)
//        .setHeaderView(R.layout.layout_dialog_header)
//        .setFooterView(R.layout.layout_dialog_footer)
//        .addItem(new PowerMenuItem("This is DialogPowerMenu", false))
//        .setLifecycleOwner(lifecycleOwner)
//        .setAnimation(MenuAnimation.SHOW_UP_CENTER)
//        .setMenuRadius(10f)
//        .setMenuShadow(10f)
//        .setPadding(14)
//        .setWidth(600)
//        .setSelectedEffect(false)
//        .build();
//  }

//  public static CustomPowerMenu<NameCardMenuItem, CustomDialogMenuAdapter> getCustomDialogPowerMenu(
//      Context context, LifecycleOwner lifecycleOwner) {
//    return new CustomPowerMenu.Builder<>(context, new CustomDialogMenuAdapter())
//        .setHeaderView(R.layout.layout_custom_dialog_header)
//        .setFooterView(R.layout.layout_custom_dialog_footer)
//        .addItem(
//            new NameCardMenuItem(
//                ContextCompat.getDrawable(context, R.drawable.face3),
//                "Sophie",
//                context.getString(R.string.board3)))
//        .setLifecycleOwner(lifecycleOwner)
//        .setAnimation(MenuAnimation.SHOW_UP_CENTER)
//        .setWidth(800)
//        .setMenuRadius(10f)
//        .setMenuShadow(10f)
//        .build();
//  }
}
