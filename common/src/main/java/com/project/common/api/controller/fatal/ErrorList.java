package com.project.common.api.controller.fatal;


import com.project.common.api.bean.fatal.ErrorListBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ErrorList {

    @GET("/fault/list")
    Observable<ErrorListBean[]> GetErrorList();
}
