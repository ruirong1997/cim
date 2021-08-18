package com.project.order.ui.base;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.project.common.api.bean.order.MsgPushBean;
import com.project.common.api.bean.order.MsgPushDataBean;
import com.project.common.api.controller.order.OrderListUrl;
import com.project.common.fragment.BaseFragment;
import com.project.common.mode.OrderData;
import com.project.order.R;
import com.project.order.databinding.FragmentOrderBinding;
import com.project.order.databinding.FragmentOrderListBinding;
import com.project.order.ui.activity.OrderDescActivity;
import com.project.order.ui.adapter.OrderFragmentAdapter;
import com.project.utils.network.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class BaseRequestFragment<VIEW extends FragmentOrderListBinding> extends BaseFragment<VIEW> implements SwipeRefreshLayout.OnRefreshListener {

    private BaseQuickAdapter mAdapter;
    private CompositeDisposable mCompositeDisposable;
    List<MsgPushDataBean> msgPushDataList = new ArrayList<>();

    private volatile boolean mCanRefreshing = true;

    public abstract String getStates();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    public void initView() {
        Log.d(TAG,"initView");
        mCompositeDisposable = new CompositeDisposable();
        viewDataBinding.orderList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        mAdapter = new OrderFragmentAdapter(msgPushDataList);
        mAdapter.setAnimationEnable(true);
        viewDataBinding.orderList.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        Log.d(TAG,"initListener");
        viewDataBinding.srlView.setOnRefreshListener(this);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@androidx.annotation.NonNull BaseQuickAdapter<?, ?> adapter, @androidx.annotation.NonNull View view, int position) {
                if (position < msgPushDataList.size()){
                    Intent intent = new Intent(view.getContext(), OrderDescActivity.class);
                    intent.putExtra("data",msgPushDataList.get(position));
                    view.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public void Load(View view) {
        Log.d(TAG,"Load MSG");
        mCanRefreshing = false;
        viewDataBinding.srlView.setRefreshing(true);
        OrderListUrl orderListUrl;
        orderListUrl = RetrofitUtils.getOnlineCookeRetrofit().create(OrderListUrl.class);
//        if (LoginUserInfo.User_id != null){
//            return;
//        }
        orderListUrl.getOrderList("000001")
                .map(new Function<MsgPushBean, MsgPushBean>() {
                    @Override
                    public MsgPushBean apply(MsgPushBean msgPushBean) throws Throwable {
                        msgPushDataList.clear();
                        return msgPushBean;
                    }
                })
                .map(new Function<MsgPushBean, List<MsgPushDataBean>>() {  //排序
                    @Override
                    public List<MsgPushDataBean> apply(MsgPushBean msgPushBean) throws Throwable {
                        if (msgPushBean.getData().size() > 1) {
                            msgPushDataList.addAll(SortOrder(msgPushBean.getData(),getStates()));
                        } else {
                            //显示没任务
                            return null;
                        }
                        return msgPushDataList;
                    }
                })
                //缓存
                .map(new Function<List<MsgPushDataBean>, List<MsgPushDataBean>>() {
                    @Override
                    public List<MsgPushDataBean> apply(List<MsgPushDataBean> msgPushDataBeans) throws Throwable {
                        return msgPushDataBeans;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<MsgPushDataBean>, List<MsgPushDataBean>>() {
                    @Override
                    public List<MsgPushDataBean> apply(List<MsgPushDataBean> msgPushDataBeans) throws Throwable {
                        return msgPushDataBeans;
                    }
                })
                .subscribe(new Observer<List<MsgPushDataBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
                            mCompositeDisposable.add(d);
                        }
                    }

                    @Override
                    public void onNext(@NonNull List<MsgPushDataBean> msgPushDataBeans) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG,"onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onComplete");
                        mCanRefreshing = true;
                        Log.d(TAG, String.valueOf(msgPushDataList.size()));
                        viewDataBinding.srlView.setRefreshing(false);
                        mAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear(); // clear时网络请求会随即cancel
            mCompositeDisposable = null;
        }
    }


    public List<MsgPushDataBean> SortOrder(List<MsgPushDataBean> list,String type){
        List<MsgPushDataBean> orderList = new ArrayList<>();
        for (int i = 0;i < list.size();i++){
            if(list.get(i).getState().equals(type)){
                orderList.add(list.get(i));
            }
        }
        return orderList;
    }

    @Override
    public void onRefresh() {
        Log.d(TAG,"onRefresh");
        if (mCanRefreshing){
            Log.d("NoHandleTaskFragment123","mIsRefreshing :" + mCanRefreshing);
            Load(getView());
        }
        //如果5S一直没有加载出来，就加载失败，
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!mCanRefreshing){
                    mCanRefreshing = true;
                    viewDataBinding.srlView.setRefreshing(false);
                }
            }
        },5000);

    }
}
