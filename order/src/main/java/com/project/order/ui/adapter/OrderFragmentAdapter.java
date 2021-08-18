package com.project.order.ui.adapter;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.project.common.api.bean.order.MsgPushDataBean;
import com.project.common.mode.ItemEntity;
import com.project.order.R;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrderFragmentAdapter extends BaseMultiItemQuickAdapter<MsgPushDataBean, BaseViewHolder> {

    private final static String TAG = "OrderFragmentAdapter";

    public OrderFragmentAdapter(List<MsgPushDataBean> order_list){
        super(order_list);
        Log.d(TAG,"OrderFragmentAdapter");
        addItemType(ItemEntity.ITEM_Picture, R.layout.item_task);
        addItemType(ItemEntity.ITEM_NOTING, R.layout.item_task_nopicture);
    }


    @Override
    public void onBindViewHolder(@NotNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, MsgPushDataBean item) {
        Log.d(TAG,"convert");
        switch (helper.getItemViewType()){
            //有图片
            case ItemEntity.ITEM_Picture:{
                if (!item.getImg().equals("")){  //如果显示的item内图片存在
                    Glide.with(getContext())
                            .load(item.getImg())
                            .into((ImageView) helper.itemView.findViewById(R.id.imageView));
                }
                break;
            }
            case ItemEntity.ITEM_NOTING:{
                break;
            }
        }
        helper.setText(R.id.tv_event, "订单Id :" + item.getId());
        helper.setText(R.id.tv_content,"内容 :"+ item.getFaultCd());
        helper.setText(R.id.tv_sendInfo,item.getDispatchTime());
        if (item.getState() != null){
            if (item.getState().equals("未处置")){
                helper.setTextColor(R.id.tv_type, Color.rgb(243,156,18));
            }else if (item.getState().equals("处置中")){
                helper.setTextColor(R.id.tv_type,Color.rgb(51,150,251));
            }else if (item.getState().equals("已完成")){
                helper.setTextColor(R.id.tv_type,Color.rgb(15,184,0));
            }else if (item.getState().equals("已拒绝")){
                helper.setTextColor(R.id.tv_type,Color.rgb(255,0,0));
            }
            helper.setText(R.id.tv_type,item.getState());
        }
    }
}
