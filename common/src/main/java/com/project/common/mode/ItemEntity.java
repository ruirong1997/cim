package com.project.common.mode;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ItemEntity implements MultiItemEntity {

    public static final int ITEM_Picture = 1; //有图片
    public static final int ITEM_NOTING  = 2; //没图片
    public int Status;

    public ItemEntity(final int status){
        Status = status;
    }


    @Override
    public int getItemType() {
        return Status;
    }
}
