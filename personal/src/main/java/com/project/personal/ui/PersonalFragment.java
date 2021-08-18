package com.project.personal.ui;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.project.common.Constance;

import com.project.common.fragment.BaseFragment;
import com.project.personal.R;

import com.project.personal.databinding.FragmentPersonalBinding;

import jp.wasabeef.glide.transformations.BlurTransformation;


@Route(path = Constance.FRAGMENT_PERSONAL)
public class PersonalFragment extends BaseFragment<FragmentPersonalBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        viewDataBinding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void Load(View view) {

        String PATH ="http://47.115.145.130:8080/images/cc242c07a7b948dd967923fb2f5fcdaf.jpg";

        Glide.with(this)
                .load(R.drawable.sun_shine)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(10,10)))
                .into(viewDataBinding.backgroundPhoto);


        Glide.with(view.getContext())
                .load(PATH)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(viewDataBinding.headPhoto);
    }

}
