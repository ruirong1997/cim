package com.project.common.api.controller.fatal;

import com.project.common.api.bean.fatal.AddErrorResponse;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 新增故障信息
 * /fault/add
 */
public interface SubmitErrorFrom {


    @Headers({"accept: */*",
             "Content-Type: application/json"
    })
    @POST("/fault/add")
    Observable<AddErrorResponse> SubmitErrorMsg(@Body RequestBody errorAdd);
}