package com.project.chat.ui.adapter;

import android.util.Log;

import androidx.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.project.chat.R;
import com.project.chat.mode.MsgContext;
import com.project.chat.ui.fragment.ChatFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChatFragmentAdapter extends BaseQuickAdapter<MsgContext, BaseViewHolder> {

    public static final int ITEM_0_PAYLOAD = 101;

    private static final String TAG = "ChatFragmentAdapter";

    public ChatFragmentAdapter(int layoutResId) {
        super(layoutResId);
    }

    public ChatFragmentAdapter(@LayoutRes int layoutResId, @Nullable List<MsgContext> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MsgContext msgContext) {
        baseViewHolder.setText(R.id.msg_username,msgContext.getSendUid());
        baseViewHolder.setText(R.id.msg_context,msgContext.getContext());
        baseViewHolder.setText(R.id.msg_date,msgContext.getTime());
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, MsgContext item, @NotNull List<?> payloads) {
        for (Object p : payloads){
            int payload = (int) p;
            Log.d(TAG,"convert : "+ ITEM_0_PAYLOAD);
            if (payload == ITEM_0_PAYLOAD){
                holder.setText(R.id.msg_username,"item.getSendUid()");
            }
        }
    }
}
