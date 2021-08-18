package com.project.common.dialog.bottom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.common.R;
import com.project.common.databinding.DialogBottomBinding;
import com.project.common.dialog.adapter.BottomDialogAdapter;

import java.util.ArrayList;
import java.util.List;

public class BottomDialog extends Dialog implements View.OnClickListener {

    private final static String TAG = "BottomDialog";

    private static volatile Boolean isBottomExist = false;

    protected DialogBottomBinding viewDataBinding;

    private BottomDialogAdapter mAdapter;
    private BottomDialog.OnItemClickListener mOnItemClickListener;

    private Context mContext;
    private List<String> mItem = new ArrayList<>();

    public BottomDialog(@NonNull Context context, List<String> item_text) {
        super(context, R.style.BottomDialog);
        this.mContext       = context;
        this.mItem     = item_text;
    }


    public BottomDialog(@NonNull Context context) {
        super(context);
    }

    public BottomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BottomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        viewDataBinding.dialogItem.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new BottomDialogAdapter(getContext(),mItem);
        viewDataBinding.dialogItem.setAdapter(mAdapter);
        initListener();

    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new BottomDialogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mOnItemClickListener.onItemClick(view,position);
            }
        });

        viewDataBinding.dialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBottomExist = false;
                dismiss();
            }
        });
    }

    private void initView() {
        viewDataBinding= DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_bottom, null, false);
        setContentView(viewDataBinding.getRoot());

        Window window = getWindow();
        //Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_menu_animation);
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.9); // 宽度设置为屏幕宽度的80%
        //lp.dimAmount=0.0f;//外围遮罩透明度0.0f-1.0f
        //dialogWindow.setWindowAnimations(R.style.bottom_menu_animation);
        window.setAttributes(lp);
        setCanceledOnTouchOutside(true); // 点击Dialog外部消失
        //dialogWindow.setGravity(Gravity.BOTTOM);//内围区域底部显示
    }

    @Override
    public void onClick(View v) {

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(BottomDialog.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
