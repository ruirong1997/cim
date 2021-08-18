package com.project.order.ui.activity;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.project.common.activity.BaseActivity;
import com.project.common.api.bean.fatal.ErrorDetailBean;
import com.project.common.api.bean.fatal.ErrorDetailDataBean;
import com.project.common.api.bean.order.MsgPushDataBean;
import com.project.common.api.controller.fatal.ErrorDetail;
import com.project.order.R;
import com.project.order.databinding.ActivityOrderDescBinding;
import com.project.order.ui.dialog.ShowCenterDialog;
import com.project.order.ui.utils.PowerMenuUtils;
import com.project.utils.network.RetrofitUtils;
import com.skydoves.powermenu.OnDismissedListener;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.project.common.dialog.status.widgets.AlertDialog.mAlertDialog;

public class OrderDescActivity extends BaseActivity<ActivityOrderDescBinding> {

    private ShowCenterDialog showCenterDialog = new ShowCenterDialog(this);;

    private ErrorDetail errorDetail;
    private PowerMenu mPowerMenu;

    private MsgPushDataBean msgPushDataBean;

    private boolean isFirstOnCreate = true;  //OnItemClick 刚开始会触发
    private OnMenuItemClickListener<PowerMenuItem> onHamburgerItemClickListener;
    private final OnDismissedListener onHamburgerMenuDismissedListener =
            () -> Log.d(TAG, "onDismissed hamburger menu");


    @Override
    public int getLayoutId() {
        return R.layout.activity_order_desc;
    }

    @Override
    public void initView() {
        msgPushDataBean = (MsgPushDataBean) getIntent().getSerializableExtra("data");
        viewDataBinding.setOrder(msgPushDataBean);
        Log.d(TAG,msgPushDataBean.toString());

        if (msgPushDataBean.getImg().equals("")){
            viewDataBinding.BtnImg1.setVisibility(View.GONE);
            viewDataBinding.tvPhoto.setVisibility(View.GONE);
        }


        getErrorDetails(msgPushDataBean.getFaultId());
    }

    @Override
    public void initListener() {
        onHamburgerItemClickListener = new OnMenuItemClickListener<PowerMenuItem>(){
            @Override
            public void onItemClick(int position, PowerMenuItem item) {
                Log.d(TAG,"isFirstOnCreate :" + isFirstOnCreate);
                if (!isFirstOnCreate){
                    if (item.getTitle().equals("响应")){
                        Log.d(TAG,"响应");
                        showCenterDialog.showAlertDialog("设置响应状态","处置中","响应",msgPushDataBean.getId());
                    }else if (item.getTitle().equals("延时")){

                    }else if (item.getTitle().equals("完成")){
                        showCenterDialog.showAlertDialog("提交工单状态","已完成","完成",msgPushDataBean.getId());
                    }else if (item.getTitle().equals("拒绝")){
//                        showAlertDialog(context,"提交工单状态","已拒绝","拒绝");
                    }
                }
                isFirstOnCreate = false;
            }
        };

        mPowerMenu =
                PowerMenuUtils.getHamburgerPowerMenu(
                        this, this, onHamburgerItemClickListener, onHamburgerMenuDismissedListener);

    }

    @Override
    protected boolean isClickView(MotionEvent ev) {
        return false;
    }

    private void getErrorDetails(int faultId){
        errorDetail = RetrofitUtils.getOnlineCookeRetrofit().create(ErrorDetail.class);
        errorDetail.GetErrorDetail(faultId)
                .map(new Function<ErrorDetailBean, ErrorDetailDataBean>() {
                    @Override
                    public ErrorDetailDataBean apply(ErrorDetailBean errorDetailBean) throws Throwable {
                        if (errorDetailBean.getCode().equals("success")){
                            if (errorDetailBean.getData() != null){
                                return errorDetailBean.getData();
                            }else {
                                cancel();
                                return null;
                            }
                        }else{
                            cancel();
                            return null;
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ErrorDetailDataBean, ErrorDetailDataBean>() {
                    @Override
                    public ErrorDetailDataBean apply(ErrorDetailDataBean errorDetailDataBean) throws Throwable {
                        if (errorDetailDataBean != null){
                            viewDataBinding.setError(errorDetailDataBean);
                            return errorDetailDataBean;
                        }else {
                            cancel();
                            return null;
                        }
                    }
                })
                .subscribe(new Observer<ErrorDetailDataBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull ErrorDetailDataBean errorDetailDataBean) {
                        if (!errorDetailDataBean.getImg().equals("")){
                            Glide.with(getApplicationContext())
                                    .load(errorDetailDataBean.getImg())
                                    .into( viewDataBinding.BtnImg1);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

/*-----------------点击事件--------------------*/
    public void back(View view) {
        onBackPressed();
    }

    public void onClickImg1(View view) {
        Log.d(TAG,"213123123");
    }

    public void choice_function(View view) {
        Log.d(TAG,"choice_function");
        if (mPowerMenu != null) {
            if (mPowerMenu.isShowing()) {
                mPowerMenu.dismiss();
                return;
            } else {
                mPowerMenu.showAsDropDown(view);
            }
        }
    }

    /*-----------------点击事件--------------------*/

    @Override
    protected void onDestroy() {
        showCenterDialog.cancel();
        mAlertDialog = null;
        super.onDestroy();
    }
}
