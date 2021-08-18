package com.project.common.api.controller.user;

import com.project.common.api.bean.user.FriendsListBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

/**
 * 获取用户列表
 * /user/list
 */
public interface GetListFriends {

    @GET("/user/list")
    Observable<FriendsListBean[]> GetFriendsList();

}

