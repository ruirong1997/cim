package com.project.common.api.controller.fatal;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * 根据故障ID上传图片
 *
 * http://47.115.145.130:8080/fault/addImage/id/377
 */
public interface SubmitErrorPhoto {

    @Multipart
    @POST("/fault/addImage/id/{faultId}")
    Observable<Integer> SubmitErrorPhoto(@Path("faultId") Integer faultId, @Part MultipartBody.Part errorAdd);
}
