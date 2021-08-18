package com.project.task.ui.activity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.project.common.LoginUserInfo;
import com.project.common.activity.BaseActivity;
import com.project.common.api.bean.fatal.AddErrorResponse;
import com.project.common.api.bean.fatal.ErrorForm;
import com.project.common.api.controller.fatal.SubmitErrorFrom;
import com.project.common.api.controller.fatal.SubmitErrorPhoto;
import com.project.common.dialog.bottom.BottomDialog;
import com.project.common.dialog.camera.CameraDialog;
import com.project.common.dialog.status.ShowStatusDialog;
import com.project.common.mode.ShowImage;
import com.project.task.R;
import com.project.task.databinding.ActivityErrorDevicesBinding;
import com.project.task.mode.DialogMode;
import com.project.utils.network.RetrofitUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.project.utils.CameraUtils.SCAN_OPEN_PHONE;
import static com.project.utils.CameraUtils.TAKE_PHOTO;
import static com.project.utils.CameraUtils.getRealFilePath;
import static com.project.utils.CameraUtils.mCameraUri;
import static com.project.utils.CameraUtils.openCamera;
import static com.project.utils.CameraUtils.openGallery;

public class ErrorDevActivity extends BaseActivity<ActivityErrorDevicesBinding> implements CameraDialog.OnBottomMenuItemClickListener {


    private BottomDialog mBottomDialog;
    private CameraDialog mCameraDialog;

    private SubmitErrorFrom mAddErrorMsg;
    private SubmitErrorPhoto mAddErrorPhoto;
    private ShowStatusDialog showStatusDialog;


    private List<ShowImage> mImageList = new ArrayList<>();
    private List<String>    mImagePath = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_error_devices;
    }

    @Override
    public void initView() {
        mImageList.add(new ShowImage(viewDataBinding.BtnImg1,false,false));
        mImageList.add(new ShowImage(viewDataBinding.BtnImg2,false,false));
        mImageList.add(new ShowImage(viewDataBinding.BtnImg3,false,false));
        mImageList.add(new ShowImage(viewDataBinding.BtnImg4,false,false));
        mImageList.add(new ShowImage(viewDataBinding.BtnImg5,false,false));
        mImageList.add(new ShowImage(viewDataBinding.BtnImg6,false,false));

    }

    @Override
    public void initListener() {

        showStatusDialog = new ShowStatusDialog(ErrorDevActivity.this);
        viewDataBinding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    commit_error(v);
                }
            }
        });
    }


   @Override
    protected boolean isClickView(MotionEvent ev) {
        return false;
    }

    /*------点击事件------*/

    public void back(View view) {
        onBackPressed();
    }

    public void select_station(View view) {
        select_item(viewDataBinding.tv2Station,new DialogMode().getStation());
    }

    public void select_dev(View view) {
        select_item(viewDataBinding.tv2Dev,new DialogMode().getDev());
    }

    public void select_errorType(View view) {
        select_item(viewDataBinding.tv2ErrorType,new DialogMode().getErrorType());
    }

    public void select_errorLevel(View view) {
        select_item(viewDataBinding.tv2ErrorLevel,new DialogMode().getErrorLevel());
    }

    public void select_item(TextView tv,List<String> mList){
        if (mBottomDialog != null){
            if (mBottomDialog.isShowing()){
                return;
            }
        }
        mBottomDialog = new BottomDialog(tv.getContext(),mList);
        mBottomDialog.setOnItemClickListener(new BottomDialog.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                tv.setText(mList.get(position));
                mBottomDialog.cancel();
            }
        });
        mBottomDialog.show();
    }


    public void onClickImg1(View view) {
        setFlags(0);
        select_pictureMode(viewDataBinding.BtnImg1);
    }

    public void onClickImg2(View view) {
        setFlags(1);
        select_pictureMode(viewDataBinding.BtnImg2);
    }

    public void onClickImg3(View view) {
        setFlags(2);
        select_pictureMode(viewDataBinding.BtnImg3);
    }

    public void onClickImg4(View view) {
        setFlags(3);
        select_pictureMode(viewDataBinding.BtnImg4);
    }

    public void onClickImg5(View view) {
        setFlags(4);
        select_pictureMode(viewDataBinding.BtnImg5);
    }

    public void onClickImg6(View view) {
        setFlags(5);
        select_pictureMode(viewDataBinding.BtnImg6);
    }

    public void select_pictureMode(ImageButton imgBtn){
        if (mCameraDialog != null){
            if (!mCameraDialog.isShowing()){
                mCameraDialog.show();
            }
        }else{
            mCameraDialog = new CameraDialog(this,R.layout.dialog_bottom_camera,new int[]{R.id.pick_photo_album, R.id.pick_photo_camera, R.id.pick_photo_cancel});
            mCameraDialog.setOnBottomMenuItemClickListener(this);
            mCameraDialog.show();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void commit_error(View view) {
        Log.d(TAG,"onclick");
//        if (viewDataBinding.tv2Station.getText().toString().equals("")){
//            Toast.makeText(this, "请选择车站", Toast.LENGTH_SHORT).show();
//            return;
//        }else if (viewDataBinding.tv2Dev.getText().toString().equals("")){
//            Toast.makeText(this, "请选择设备类型", Toast.LENGTH_SHORT).show();
//            return;
//        }else if (viewDataBinding.tvDevNumber.getText().toString().equals("")){
//            Toast.makeText(this, "请输入设备编号", Toast.LENGTH_SHORT).show();
//            return;
//        }else if (viewDataBinding.tv2ErrorType.getText().toString().equals("")){
//            Toast.makeText(this, "请选择故障类型", Toast.LENGTH_SHORT).show();
//            return;
//        }else if (viewDataBinding.tv2ErrorLevel.getText().toString().equals("")){
//            Toast.makeText(this, "请选择故障等级", Toast.LENGTH_SHORT).show();
//            return;
//        }
        ErrorForm error_form = new ErrorForm();
                error_form.setDevId("0002");//四位数
                error_form.setLocation("东莞火车站");
                error_form.setDevType("BOM");
                error_form.setFaultCd("电池故障");
                error_form.setFaultDegree("三级");
                error_form.setFaultDesc("测试信息");
//                error_form.setImg(NULL);
                error_form.setOriginal("终端");
                error_form.setFaultState("未分配");
                error_form.setReportUser("000001");
//                error_form.setFrom(NULL);
//        error_form.setDevId(viewDataBinding.tvDevNumber.getText().toString());//四位数
//        error_form.setLocation(viewDataBinding.tv2Station.getText().toString());
//        error_form.setDevType(viewDataBinding.tv2Dev.getText().toString());
//        error_form.setFaultCd(viewDataBinding.tvErrorType.getText().toString());
//        error_form.setFaultDegree(viewDataBinding.tv2ErrorLevel.getText().toString());
//        error_form.setFaultDesc(viewDataBinding.tvErrorDetail.getText().toString());
//        error_form.setFaultState("未分配");
//        error_form.setOriginal("终端");
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();
        form.format(date);
        error_form.setReportTime(form.format(date));
        error_form.setReportUser(LoginUserInfo.User_id);
        error_form.setReportUser("000001");

        showStatusDialog.showProgressDialog();

        Gson gson=new Gson();
        String jsonObject = gson.toJson(error_form);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonObject);
        mAddErrorMsg = RetrofitUtils.getOnlineCookeRetrofit().create(SubmitErrorFrom.class);
        mAddErrorMsg.SubmitErrorMsg(body)
                .map(new Function<AddErrorResponse, AddErrorResponse>() {
                    @Override
                    public AddErrorResponse apply(AddErrorResponse addErrorResponse) throws Throwable {
                        if (addErrorResponse.getCode().equals("success")){
                            Log.d(TAG, String.valueOf(addErrorResponse.getData()));
                            //showSuccessDialog();
                            if (mImagePath.size() == 0){
                                if (showStatusDialog != null){
                                    showStatusDialog.showSuccessDialog();
                                }
                            }else {
                                Log.d(TAG,"addErrorResponse.getData()" + addErrorResponse.getData());
                                SubmitPhoto(addErrorResponse.getData());
                            }
                        }else {
                            if (showStatusDialog != null){
                                showStatusDialog.showErrorDialog();
                            }

                            Toast.makeText(ErrorDevActivity.this, addErrorResponse.getErrorMes(), Toast.LENGTH_SHORT).show();
                        }
                        return addErrorResponse;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddErrorResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull AddErrorResponse addErrorResponse) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG,"onError : "+ e.toString());
                        if (showStatusDialog != null){
                            showStatusDialog.showErrorDialog();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    /*------点击事件(END)------*/


    @Override
    public void onBottomMenuItemClick(CameraDialog dialog, View view) {
        int id = view.getId();
        if (id == R.id.pick_photo_album) {
            Toast.makeText(this, "从相册选取", Toast.LENGTH_SHORT).show();
            openGallery(this);
        } else if (id == R.id.pick_photo_camera) {
            Toast.makeText(this, "拍照", Toast.LENGTH_SHORT).show();
            openCamera(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"onActivityResult :"+ resultCode);
        Log.d(TAG,"requestCode :"+ requestCode);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case TAKE_PHOTO:
                    for (int i = 0;i < mImageList.size();i++){
                        if (mImageList.get(i).shouldShow) {
                            Glide.with(this)
                                    .load(mCameraUri)
                                    .apply(RequestOptions.bitmapTransform(new CenterCrop()))
                                    .into(mImageList.get(i).imageButton);
                           mImagePath.add(getRealFilePath(this,mCameraUri));

                            if (i != (mImageList.size() - 1)) {
                                mImageList.get(i + 1).imageButton.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    break;
                case SCAN_OPEN_PHONE:
                    if (data != null){
                        for (int i = 0;i < mImageList.size();i++){
                            if (mImageList.get(i).shouldShow){
                                Glide.with(this)
                                        .load(data.getData())
                                        //.override(90,90)
                                        .apply(RequestOptions.bitmapTransform(new CenterCrop()))
                                        .into(mImageList.get(i).imageButton);
                                mImagePath.add(getRealFilePath(this,data.getData()));
                                if (i != (mImageList.size() - 1)){
                                    mImageList.get(i + 1).imageButton.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }else {
                        Toast.makeText(this, "图片损坏，请重新选择", Toast.LENGTH_SHORT).show();
                    }
                    Log.e(TAG, "onActivityResult: SCAN_OPEN_PHONE:" + data.getData().toString());
                    //cropPhoto(data.getData(), false);
                    break;
            }
        }
    }

    /**
     * @param index
     * 设置标记位，哪个应该显示出来
     */
    public void setFlags(int index){
        for (int i = 0; i < mImageList.size(); i++){
            if (i == index){
                mImageList.get(index).setShouldShow(true);
            }else {
                mImageList.get(i).setShouldShow(false);
            }

        }
    }

    public void SubmitPhoto(int faultId){
        if (mImagePath.size() == 0){
            return;
        }
        File file = null;
        file = new File(mImagePath.get(0));
        Log.d(TAG,"mImagePath.size :" + mImagePath.size());
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        //builder.addFormDataPart("file", file.getName(), requestBody1);

        mAddErrorPhoto = RetrofitUtils.getOnlineCookeRetrofit().create(SubmitErrorPhoto.class);
        mAddErrorPhoto.SubmitErrorPhoto(faultId,filePart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.d(TAG,"onNext :" + String.valueOf(integer));
                        if (integer == 1){
                            showStatusDialog.showSuccessDialog();
                        }else {
                            if (showStatusDialog != null){
                                showStatusDialog.showErrorDialog();
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG,"Photo onError :" + e.toString());
                        if (showStatusDialog != null){
                            showStatusDialog.showErrorDialog();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
