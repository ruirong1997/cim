package com.project.common.api.controller.user;

import com.project.common.api.bean.user.LoginBean;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 用户登录
 * /user/login/id/{id}/pass/{pass}
 */
public interface Login {
    @GET("/user/login/id/{id}/pass/{pass}")
    Observable<LoginBean> Login(@Path("id") String userId, @Path("pass") String password);
}
