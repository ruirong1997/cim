package com.project.common.api.controller.fatal;

import com.project.common.api.bean.fatal.ReportHistoryData;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReportHistory {

    @GET("/fault/queryByUser/id/{id}")
    Observable<ReportHistoryData> getHistory(@Path("id") String userId);

}
