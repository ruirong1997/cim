package com.project.cim;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.project.cim.databinding.ActivityLoginBinding;
import com.project.common.Constance;
import com.project.common.activity.BaseActivity;
import com.project.common.api.bean.user.LoginBean;
import com.project.common.api.bean.user.LoginDataBean;
import com.project.common.api.controller.user.Login;
import com.project.common.services.ServiceManager;
import com.project.utils.FileUtils;
import com.project.utils.network.RetrofitUtils;

import java.io.IOException;
import java.lang.reflect.Field;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Route(path = Constance.APP_ACTIVITY_LOGIN)
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    private ConstraintLayout firstLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        firstLayout  = findViewById(R.id.login_parent);
    }

    @Override
    public void initListener() {
        firstLayout.getViewTreeObserver(). addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int statusBarHeight = 0;
                Rect r = new Rect();
                // r will be populated with the coordinates of your view that area still visible.
                firstLayout.getWindowVisibleDisplayFrame(r);
                int screenHeight = getWindow().getDecorView().getRootView().getHeight();
                int heightDiff = screenHeight - (r.bottom - r.top);
                if (heightDiff > 100)
                    // if more than 100 pixels, its probably a keyboard
                    // get status bar height
                    statusBarHeight = 0;
                try {
                    Class<?> c = Class.forName("com.android.internal.R$dimen");
                    Object obj = c.newInstance();
                    Field field = c.getField("status_bar_height");
                    int x = Integer.parseInt(field.get(obj).toString());
                    statusBarHeight = getApplication().getResources().getDimensionPixelSize(x);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int realKeyboardHeight = heightDiff - statusBarHeight;

                viewDataBinding.loginParent.setPadding(0,0,0,0);
            }
        });
    }

    @Override
    protected boolean isClickView(MotionEvent ev) {
        int[] l = {0, 0};
        viewDataBinding.login.getLocationInWindow(l);
        int left = l[0],
                top = l[1],
                bottom = top + viewDataBinding.login.getHeight(),
                right = left + viewDataBinding.login.getWidth();
        if (ev.getX() > left && ev.getX() < right
                && ev.getY() > top && ev.getY() < bottom){
            return true;
        }else {
            return false;
        }
    }

    public void login(View view) throws IOException {
        ServiceManager.startServices(getApplicationContext());

        Log.d(TAG,"onClick");
        Log.d(TAG, String.valueOf(viewDataBinding.loginUser.getText()));
        FileUtils.CreateCacheFile(this,"000001");
        Login loginApi;
        loginApi = RetrofitUtils.getOnlineCookeRetrofit().create(Login.class);
//        loginApi.Login(String.valueOf(viewDataBinding.loginUser.getText()),
//                String.valueOf(viewDataBinding.loginPwd.getText()))
        loginApi.Login("000001",
               "123456")
                .map(new Function<LoginBean, Boolean>() {
                    @Override
                    public Boolean apply(LoginBean loginBean) throws Throwable {
                        if (loginBean.getCode().equals("0")){
                            return true;
                        }else {
                            return false;
                        }
                    }
                })
                .map(new Function<Boolean, Boolean>() {
                    @Override
                    public Boolean apply(Boolean aBoolean) throws Throwable {
                        if (aBoolean){
                            //存入数据库
                        }else{
                            //返回错误
                        }
                        return aBoolean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {
                        if (aBoolean){
                            MainActivity.start(view.getContext());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG,e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
