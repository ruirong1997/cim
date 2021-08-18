package com.project.common.fragment.model;

public interface IBaseModelListener<DATA> {
    void onLoadSuccess(BaseModel model,DATA data);
    void onLoadFail(BaseModel model,String message);
}
