package com.project.common.api.controller.order;


import com.project.common.api.bean.order.MsgPushBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 根据用户Id获取订单列表
 * /order/list/userId/{userId}
 */
public interface OrderListUrl{
    @GET("/order/list/v2/userId/{user_id}")
    Observable<MsgPushBean> getOrderList(@Path("user_id") String user_id);
}
