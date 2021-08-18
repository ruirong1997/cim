package com.project.common.api.controller.user;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Register{
    @GET("register")
    Call<Integer> Register(@Query("username") String username, @Query("password") String password);
}