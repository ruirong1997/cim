package com.project.common.dialog.status.widgets;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.project.common.R;


/**
 * @Copyright (C)open
 * @ClassName: AlertDialog
 * @Description: $DESC$
 * @Author: seengene_lvTravler
 * @CreateDate: 2019/8/23 19:11
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/8/23 19:11
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class AlertDialog extends Dialog {


    private DialogParams mDialogParams;
    public static AlertDialog mAlertDialog;

    private TextView tvDialogCommonTitle;
    private TextView tvDialogOrderId;
    private TextView tvDialogOrderResponseTime;
    private TextView tvDialogOrderState;
    private TextView tvDialogCommonPositive;
    private TextView tvDialogCommonNegative;


    public TextView getTvDialogOrderId() {
        return tvDialogOrderId;
    }


    public TextView getTvDialogOrderResponseTime() {
        return tvDialogOrderResponseTime;
    }

    public TextView getTvDialogOrderState() {
        return tvDialogOrderState;
    }


    public AlertDialog() {
        mDialogParams = new DialogParams();
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.dialog_responsettime;
    }

    public static Builder with(AppCompatActivity activity) {
        return new Builder(activity);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvDialogCommonTitle       = view.findViewById(R.id.tv_dialog_common_title);
        tvDialogOrderId           = view.findViewById(R.id.tv_dialog_common_id);
        tvDialogOrderResponseTime = view.findViewById(R.id.tv_dialog_common_responseTime);
        tvDialogOrderState        = view.findViewById(R.id.tv_dialog_common_state);
        tvDialogCommonPositive    = view.findViewById(R.id.tv_dialog_common_positive);
        tvDialogCommonNegative    = view.findViewById(R.id.tv_dialog_common_negative);

        //填充数据
        if (isNonEmpty(mDialogParams.title)) {
            tvDialogCommonTitle.setText(mDialogParams.title);
        }
        if (isNonEmpty(mDialogParams.Id)) {
            tvDialogOrderId.setText(mDialogParams.Id);
        }
        if (isNonEmpty(mDialogParams.Time)) {
            tvDialogOrderResponseTime.setText(mDialogParams.Time);
        }
        if (isNonEmpty(mDialogParams.State)) {
            tvDialogOrderState.setText(mDialogParams.State);
        }
        if (isNonEmpty(mDialogParams.positive)) {
            tvDialogCommonPositive.setText(mDialogParams.positive);
        }
        if (isNonEmpty(mDialogParams.negative)) {
            tvDialogCommonNegative.setText(mDialogParams.negative);
        }

        //监听
        tvDialogCommonPositive.setOnClickListener(this);
        tvDialogCommonNegative.setOnClickListener(this);
        tvDialogOrderId.setOnClickListener(this);
        tvDialogOrderResponseTime.setOnClickListener(this);
        tvDialogOrderState.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_dialog_common_id ) {
            if (mDialogParams.onIdClickListener != null) {
                mDialogParams.onIdClickListener.onClick(v);
            }
        }else if (id == R.id.tv_dialog_common_responseTime){
            if (mDialogParams.onTimeClickListener != null) {
                mDialogParams.onTimeClickListener.onClick(v);
            }
        }
        else if (id == R.id.tv_dialog_common_state) {
            if (mDialogParams.onStateClickListener != null) {
                mDialogParams.onStateClickListener.onClick(v);
            }
        } else if (id == R.id.tv_dialog_common_positive) {
            dismiss();
            if (mDialogParams.onPositiveClickListener != null) {
                mDialogParams.onPositiveClickListener.onClick(v);
            }
        } else if (id == R.id.tv_dialog_common_negative) {
            dismiss();
            if (mDialogParams.onNegativeClickListener != null) {
                mDialogParams.onNegativeClickListener.onClick(v);
            }
        }
    }

    @Override
    protected boolean setCancelable() {
        return mDialogParams.isCancelable;
    }

    public class DialogParams {
        String title;
        String positive;
        String negative;
        String Id;
        String Time;
        String State;
        boolean isCancelable = true;
        View.OnClickListener onIdClickListener;
        View.OnClickListener onTimeClickListener;
        View.OnClickListener onStateClickListener;
        View.OnClickListener onPositiveClickListener;
        View.OnClickListener onNegativeClickListener;
    }

    public static class Builder {
        AppCompatActivity activity;
        DialogParams P;
        AlertDialog alertDialog;

        public Builder(AppCompatActivity activity) {
            alertDialog = new AlertDialog();
            mAlertDialog = alertDialog;
            this.P = alertDialog.mDialogParams;
            this.activity = activity;
        }

        public Builder setTitle(String val) {
            P.title = val;
            return this;
        }

        public Builder setId(String val) {
            P.Id = val;
            return this;
        }

        public Builder setTime(String val) {
            P.Time = val;
            return this;
        }

        public Builder setState(String val) {
            P.State = val;
            return this;
        }


        public Builder setCancelable(boolean val) {
            P.isCancelable = val;
            return this;
        }

        public Builder setIdClick(String Id, View.OnClickListener onClickListener) {
            P.onIdClickListener = onClickListener;
            P.Id = Id;
            return this;
        }

        public Builder setResponseTimeClick(String time, View.OnClickListener onClickListener) {
            P.onTimeClickListener = onClickListener;
            P.Time  = time;
            return this;
        }

        public Builder setStatesClick(String State, View.OnClickListener onClickListener) {
            P.onStateClickListener = onClickListener;
            P.State = State;
            return this;
        }

        public Builder setPositiveButton(String positive, View.OnClickListener onClickListener) {
            P.onPositiveClickListener = onClickListener;
            P.positive = positive;
            return this;
        }

        public Builder setNegativeButton(String negative, View.OnClickListener onClickListener) {
            P.onNegativeClickListener = onClickListener;
            P.negative = negative;
            return this;
        }

        public Builder setPositiveButton(@StringRes int positive, View.OnClickListener onClickListener) {
            P.onPositiveClickListener = onClickListener;
            P.positive = activity.getString(positive);
            return this;
        }

        public Builder setNegativeButton(@StringRes int negative, View.OnClickListener onClickListener) {
            P.onNegativeClickListener = onClickListener;
            P.negative = activity.getString(negative);
            return this;
        }



        public AlertDialog show() {
            alertDialog.show(activity);
            return alertDialog;
        }
    }

}
