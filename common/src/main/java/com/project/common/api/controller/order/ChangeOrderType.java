package com.project.common.api.controller.order;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ChangeOrderType {

    /**
     *  /order/update/id/{id}/state/{state}/completeTime/{completeTime}
     * 修改工单状态及增加完成时间 + 拒绝
     *
     */

    public interface CFinishTime{
        @PUT("/order/update/id/{id}/state/{state}/completeTime/{completeTime}")
        Observable<Boolean> SetFinishTime(@Path("id") String id, @Path("state") String state, @Path("completeTime") String completeTime);
    }

    /**
     *
     * /order/update/id/{id}/state/{state}/responseTime/{responseTime}
     * 修改工单状态及增加响应时间
     */
    public interface CResponseTime{

        @PUT("/order/update/id/{id}/state/{state}/responseTime/{responseTime}")
        Observable<Boolean> SetResponseTime(@Path("id") String id,@Path("state") String state,@Path("responseTime") String responseTime);
    }

    /**
     * /order/update/id/{id}/fixUser/{fixUser}
     * 转单（修改工单维修员）
     */
    public interface UpdateOrder{

        @PUT("/order/update/id/{id}/fixUser/{fixUser}")
        Observable<Boolean> SetUpdateOrder(@Path("id") String id,@Path("fixUser") String fixUser);

    }

}
