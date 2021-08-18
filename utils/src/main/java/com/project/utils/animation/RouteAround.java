package com.project.utils.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class RouteAround {

    public RouteAround() {
    }

    //绕自身旋转不停止
    public Animation setRouteAround(){
        Animation animation = new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setFillAfter(false);
        animation.setDuration(1000);//动画时长
        animation.setRepeatCount(Animation.INFINITE);//执行次数函数
        animation.setInterpolator(new AccelerateInterpolator());//插值器
        return animation;
    }
}
