package com.project.chat.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.project.chat.R;
import com.project.chat.databinding.ItemChatContextBinding;
import com.project.chat.mode.MsgContext;
import com.project.common.LoginUserInfo;

import java.util.List;

public class ChatActivityAdapter extends RecyclerView.Adapter<ChatActivityAdapter.ChatViewHolder>{

    private static final String TAG = "ChatActivityAdapter";

    private ItemChatContextBinding viewDataBinding;

    private Context context;
    private List<MsgContext> msgContexts;

    public ChatActivityAdapter(Context context, List<MsgContext> msgContexts) {
        this.context = context;
        this.msgContexts = msgContexts;
    }

    @NonNull
    @Override
    public ChatActivityAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(this.context),R.layout.item_chat_context,parent,false);
        return new ChatViewHolder(viewDataBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ChatActivityAdapter.ChatViewHolder holder, int position) {
        Log.d(TAG,"LoginUserInfo.User_id :" + msgContexts.get(position).getSendUid().equals(LoginUserInfo.User_id));
        viewDataBinding = DataBindingUtil.getBinding(holder.itemView);
        //如果是 本用户 发送消息 则隐藏左部局
        if(msgContexts.get(position).getSendUid().equals(LoginUserInfo.User_id)){
            viewDataBinding.msgRight.setText(msgContexts.get(position).getContext());
            viewDataBinding.leftLayout.setVisibility(View.GONE);
            viewDataBinding.rightLayout.setVisibility(View.VISIBLE);
        }else {
            viewDataBinding.msgLeft.setText(msgContexts.get(position).getContext());
            viewDataBinding.leftLayout.setVisibility(View.VISIBLE);
            viewDataBinding.rightLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return msgContexts  == null ? 0 : msgContexts.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {



        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
