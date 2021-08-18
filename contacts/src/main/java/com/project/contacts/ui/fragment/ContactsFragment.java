package com.project.contacts.ui.fragment;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.project.common.Constance;
import com.project.common.api.bean.user.FriendsList;
import com.project.common.api.bean.user.FriendsListBean;
import com.project.common.api.controller.user.GetListFriends;
import com.project.common.fragment.BaseFragment;
import com.project.contacts.R;
import com.project.contacts.adapter.ContactsFragmentAdapter;
import com.project.contacts.controller.SideBar;
import com.project.contacts.databinding.FragmentContactsBinding;
import com.project.contacts.mode.Contacts;
import com.project.utils.network.RetrofitUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 联络
 */

@Route(path = Constance.FRAGMENT_CONTACTS)
public class ContactsFragment extends BaseFragment<FragmentContactsBinding> {
    private List<Contacts> mContacts = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_contacts;
    }

    @Override
    public void initView() {
        viewDataBinding.contactsList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mAdapter = new ContactsFragmentAdapter(getActivity(),mContacts);
        viewDataBinding.contactsList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {
        viewDataBinding.sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < mContacts.size(); i++) {
                    if (selectStr.equalsIgnoreCase(mContacts.get(i).getFirstLetter())) {
                        viewDataBinding.contactsList.scrollToPosition(i); // 选择到首字母出现的位置
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void Load(View view) {
        GetListFriends friends;
        friends = RetrofitUtils.getOnlineCookeRetrofit().create(GetListFriends.class);
        friends.GetFriendsList()
                .map(new Function<FriendsListBean[], FriendsListBean[]>() {
                    @Override
                    public FriendsListBean[] apply(FriendsListBean[] friendsListBeans) throws Throwable {
                        if (friendsListBeans.length > 0){
                            //第一个
                            for (int i = 0;i < friendsListBeans.length;i++){

                                mContacts.add(new Contacts(
                                        friendsListBeans[i].getUserId(),
                                        friendsListBeans[i].getUserName(),
                                        friendsListBeans[i].getImg()
                                ));
                            }
                            Collections.sort(mContacts); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
                            // 获取图片
                            // 单独去更新图片
                        }else {

                        }
                        return friendsListBeans;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendsListBean[]>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(FriendsListBean @NonNull [] friendsListBeans) {
                        if (friendsListBeans.length > 0){
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
