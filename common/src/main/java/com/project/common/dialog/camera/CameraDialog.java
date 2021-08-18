package com.project.common.dialog.camera;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
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

public class CameraDialog extends Dialog implements View.OnClickListener {

    private final static String TAG = "CameraDialog";

    private volatile boolean isExist = false;

    private int layoutResID;

    /**
     * 要监听的控件id
     */
    private int[] listenedItems;

    private Context mContext;
    private List<String> mItem = new ArrayList<>();
    private OnBottomMenuItemClickListener listener;

    public CameraDialog(Context context, int layoutResID, int[] listenedItems) {
        super(context, R.style.BottomDialog);
        this.mContext = context;
        this.layoutResID = layoutResID;
        this.listenedItems = listenedItems;
    }


    public CameraDialog(@NonNull Context context) {
        super(context);
    }

    public CameraDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CameraDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();

    }

    private void initListener() {
        for (int id : listenedItems) {
            findViewById(id).setOnClickListener(this);
        }
    }

    private void initView() {
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.bottom_menu_animation); // 添加动画
        setContentView(layoutResID);
        WindowManager windowManager = ((Activity) mContext).getWindowManager(); // 宽度全屏
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth()*9/10; // 设置宽度
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true); // 点击Dialog外部消失
    }

    public interface OnBottomMenuItemClickListener {

        void onBottomMenuItemClick(CameraDialog dialog, View view);

    }

    public void setOnBottomMenuItemClickListener(OnBottomMenuItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        dismiss();
        listener.onBottomMenuItemClick(this, view);
    }
}

