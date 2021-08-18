package com.project.common.dialog.status;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

public class ShowStatusDialog {

    private StatusDialog mProgressDialog;
    public AppCompatActivity activity;

    public ShowStatusDialog(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void showProgressDialog() {
        if (mProgressDialog != null){
            mProgressDialog.dismiss();
        }
        mProgressDialog = StatusDialog.with(activity)
                .setCancelable(true)
                .setPrompt("提交中")
                .setType(StatusDialog.Type.PROGRESS)
                .show();
    }

    public void showErrorDialog() {
        StatusDialog.with(activity)
                .setCancelable(true)
                .setPrompt("提交失败")
                .setType(StatusDialog.Type.ERROR)
                .show();
        if (mProgressDialog != null){
            mProgressDialog.dismiss();
        }

    }

    public void showSuccessDialog() {
        StatusDialog.with(activity)
                .setCancelable(true)
                .setPrompt("提交成功")
                .setType(StatusDialog.Type.SUCCESS)
                .show();
        if (mProgressDialog != null){
            mProgressDialog.dismiss();
        }
    }
}
