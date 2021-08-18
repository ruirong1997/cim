package com.project.chat.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.project.chat.R;
import com.project.chat.databinding.ActivityChatBinding;
import com.project.chat.mode.MsgContext;
import com.project.chat.ui.adapter.ChatActivityAdapter;
import com.project.common.Constance;
import com.project.common.activity.BaseActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Route( path = Constance.ACTIVITY_CHAT)
public class ChatActivity extends BaseActivity<ActivityChatBinding> {

    private static final String TAG = "ChatActivity";

    private RelativeLayout relativeLayout;

    private List<MsgContext> msgList = new ArrayList<>();

    public static void start(Context context) {
        Intent starter = new Intent(context,ChatActivity.class);
        context.startActivity(starter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    public void initView() {
        for (int i = 0; i < 80 ; i++){
            if (i%2 == 0){
                msgList.add(new MsgContext("content:" + i,"IMgPath","000001","you","!","1","1","1"));
            }else{
                msgList.add(new MsgContext("content:" + i,"IMgPath","you","me","1","1","!","1"));
            }
        }
        viewDataBinding.msgContext.setLayoutManager(new LinearLayoutManager(this));
        viewDataBinding.msgContext.setAdapter(new ChatActivityAdapter(this,msgList));
        //viewDataBinding.etInput.requestFocus();
        viewDataBinding.msgContext.scrollToPosition(msgList.size() - 1);
        //根据用户ID查找数据库存有的消息内容


    }

    @Override
    public void initListener() {
        viewDataBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constance.APP_ACTIVITY_MAIN).navigation();
                finish();
            }
        });
        viewDataBinding.etInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewDataBinding.msgLayout.getViewTreeObserver(). addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int statusBarHeight = 0;
                Rect r = new Rect();
                // r will be populated with the coordinates of your view that area still visible.
                viewDataBinding.msgLayout.getWindowVisibleDisplayFrame(r);
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
                Log.d(TAG,"heightDiff : "+ heightDiff + "statusBarHeight :" + statusBarHeight);



            }
        });
    }

    @Override
    protected boolean isClickView(MotionEvent ev) {
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        //第一个参数：将要显示的Activity显示的动画，
        //第二个参数：将要消失的Activity消失的动画
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public void onBackPressed() {
        ARouter.getInstance().build(Constance.APP_ACTIVITY_MAIN).navigation();
    }
}
