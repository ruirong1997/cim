package com.project.common.mode;

import android.widget.ImageButton;

public class ShowImage {
    public ImageButton imageButton;  //控件ID
    public Boolean     shouldShow;   //应该展示图片
    public Boolean     isShow;       //是否正在展示

    public ShowImage(ImageButton imageButton, Boolean shouldShow, Boolean isShow) {
        this.imageButton = imageButton;
        this.shouldShow = shouldShow;
        this.isShow = isShow;
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }

    public Boolean getShouldShow() {
        return shouldShow;
    }

    public void setShouldShow(Boolean shouldShow) {
        this.shouldShow = shouldShow;
    }
    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }
}

