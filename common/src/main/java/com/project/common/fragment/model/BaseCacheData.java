package com.project.common.fragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseCacheData<DATA> {

    @SerializedName("UserId")
    @Expose
    public String userId;

    @SerializedName("updateTimeInMillis")
    @Expose
    public long updateTimeInMillis;

    @SerializedName("data")
    @Expose
    public DATA data;
}
