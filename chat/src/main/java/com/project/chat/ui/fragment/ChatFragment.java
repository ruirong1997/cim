package com.project.chat.ui.fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.project.chat.R;
import com.project.chat.databinding.FragmentChatBinding;
import com.project.chat.mode.MsgContext;
import com.project.chat.ui.activity.ChatActivity;
import com.project.chat.ui.adapter.ChatFragmentAdapter;
import com.project.chat.ui.decoration.ChatDecoration;
import com.project.common.Constance;
import com.project.common.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = Constance.FRAGMENT_CHAT)
public class ChatFragment extends BaseFragment<FragmentChatBinding> {

    private BaseQuickAdapter mAdapter;

    private List<MsgContext> chat_msg_list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    public void initView() {
        viewDataBinding.msgList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        viewDataBinding.msgList.addItemDecoration(new ChatDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        mAdapter = new ChatFragmentAdapter(R.layout.item_msg,chat_msg_list);
        mAdapter.setAnimationEnable(true);
        viewDataBinding.msgList.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        viewDataBinding.srlView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewDataBinding.srlView.setRefreshing(false);
                    }
                },500);
            }
        });

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Log.d(TAG,"onItemClick " + position);
                String nameId = chat_msg_list.get(position).getSendUid();
                ChatActivity.start(getContext());
            }
        });
    }

    @Override
    public void Load(View view) {
        for (int i = 0; i < 100 ; i++){
            chat_msg_list.add(new MsgContext("1321421","test"+i , "msg"+ i , "12311891","testUser" ,"132","1323","123"));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.getData().set(0, new MsgContext("123343122421","test" , "msg" , "名字","testUser" ,"132","1323","123"));
        mAdapter.notifyItemChanged(0,ChatFragmentAdapter.ITEM_0_PAYLOAD);
        mAdapter.removeAt(0);
    }
}
