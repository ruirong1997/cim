package com.project.common.api.controller.fatal;



import com.project.common.api.bean.fatal.ErrorDetailBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *  根据故障id获取明细
 *
 *  /fault/detail/faultId/{faultId}
 */

public interface ErrorDetail {
    @GET("/fault/detail/faultId/{faultId}")
    Observable<ErrorDetailBean> GetErrorDetail(@Path("faultId") Integer faultId);

}
