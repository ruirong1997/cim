package com.project.common.api.controller.order;


import com.project.common.api.bean.order.OrderDetailBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderDetail {

    @GET("/order/list/userId/{userId}")
    Observable<OrderDetailBean> GetOrderTail(@Path("userId") String userId);

}
