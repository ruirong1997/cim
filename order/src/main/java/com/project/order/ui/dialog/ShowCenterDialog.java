package com.project.order.ui.dialog;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.common.api.controller.order.ChangeOrderType;
import com.project.common.dialog.date.DateDialog;
import com.project.common.dialog.status.ShowStatusDialog;
import com.project.common.dialog.status.StatusDialog;
import com.project.common.dialog.status.widgets.AlertDialog;
import com.project.utils.network.RetrofitUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.project.common.dialog.status.widgets.AlertDialog.mAlertDialog;

public class ShowCenterDialog {

    private static final String TAG = "ShowCenterDialog";

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private ShowStatusDialog mShowStatusDialog;
    public AppCompatActivity mActivity;

    private ChangeOrderType.CResponseTime mResponseTime;  //响应
    private ChangeOrderType.CFinishTime mFinishTime;      //完成


    public ShowCenterDialog(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    //Task 用
    public void showAlertDialog(String title,String State,String Type,int mId) {
        Log.d(TAG,"showAlertDialog");
        mShowStatusDialog = new ShowStatusDialog(mActivity);
        AlertDialog
                .with(mActivity)
                .setCancelable(false)
                .setTitle(title)
                .setIdClick(String.valueOf(mId), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG,"a.getTvDialogOrderId() :" +mAlertDialog.getTvDialogOrderId().getText() );
                    }
                })
                .setResponseTimeClick("", new View.OnClickListener() {//日期选择器
                    @Override
                    public void onClick(View v) {
                        new DateDialog(v.getContext(),mAlertDialog.getTvDialogOrderResponseTime());
                    }
                })
                .setStatesClick(State, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setPositiveButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mShowStatusDialog.showErrorDialog();
                    }
                })
                .setNegativeButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mAlertDialog.getTvDialogOrderResponseTime().getText().toString() == null){
                            Toast.makeText(view.getContext(),"请选择响应时间",Toast.LENGTH_SHORT).show();
                            return;
                        }else if (mAlertDialog.getTvDialogOrderState().getText().toString() == null){
                            Toast.makeText(view.getContext(),"请选择响应状态",Toast.LENGTH_SHORT).show();
                            return;
                        }


                        if (Type.equals("响应")){
                            Log.d(TAG,"响应 :" + mId);
                            GetResponseResult(String.valueOf(mId) ,State,mAlertDialog.getTvDialogOrderResponseTime().getText().toString());
                        }else if (Type.equals("完成") || (Type.equals("拒绝"))){
                            Log.d(TAG,"完成 " + mId);
                            GetFinishResult(String.valueOf(mId) ,State,mAlertDialog.getTvDialogOrderResponseTime().getText().toString());
                        }
                        //

                    }
                }).show();
    }

    /**
     * 响应
     * @param id
     * @param state
     * @param time
     */
    private void GetResponseResult(String id, String state, String time){
        mShowStatusDialog.showProgressDialog();
        mResponseTime = RetrofitUtils.getOnlineCookeRetrofit().create(ChangeOrderType.CResponseTime.class);
        mResponseTime.SetResponseTime(id,state,time + ":00")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                        Log.d(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Boolean bool) {
                        Log.d(TAG,"GetResponseResult :"+id);
                        if (bool == true){
                            mShowStatusDialog.showSuccessDialog();
                        }else {
                            mShowStatusDialog.showErrorDialog();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onComplete");
                    }
                });
    }

    /**
     * 完成
     * @param id
     * @param state
     * @param time
     */
    private void GetFinishResult(String id, String state, String time){
        mShowStatusDialog.showProgressDialog();
        mFinishTime = RetrofitUtils.getOnlineCookeRetrofit().create(ChangeOrderType.CFinishTime.class);
        mFinishTime.SetFinishTime(id,state,time + ":00")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Boolean bool) {

                        if (bool == true){
                            mShowStatusDialog.showSuccessDialog();
                        }else {
                            mShowStatusDialog.showErrorDialog();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mShowStatusDialog.showErrorDialog();
                        Log.d(TAG,"onError : " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onComplete");
                    }
                });
    }

    public void cancel(){
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear(); // clear时网络请求会随即cancel
            mCompositeDisposable = null;
        }
    }

}
