package com.project.common.fragment.model;

public interface DataObserver<DATA> {
    void onSuccess(DATA data,boolean isFromCache);
    void onFailure(Throwable e);
}
