package com.project.common.fragment.viewmodel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.project.common.fragment.model.BaseModel;
import com.project.common.fragment.model.IBaseModelListener;

import java.util.List;

public abstract class BaseViewModel<MODEL extends BaseModel,DATA> extends ViewModel implements LifecycleObserver, IBaseModelListener<List<DATA>> {

    public MutableLiveData<List<DATA>> dataList = new MutableLiveData<>();
    protected MODEL model;
    public MutableLiveData<ViewStatus> viewStatusLiveData = new MutableLiveData();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public BaseViewModel() {

    }

    public abstract MODEL createModel();

    public void refresh() {
        viewStatusLiveData.setValue(ViewStatus.LOADING);
        createAndRegisterModel();
        if (model != null) {
            model.refresh();
        }
    }

    private void createAndRegisterModel() {
        if (model == null) {
            model = createModel();
            if (model != null) {
                model.register(this);
            } else {
                // Throw exception.
            }
        }
    }

    public void getCachedDataAndLoad() {
        viewStatusLiveData.setValue(ViewStatus.LOADING);
        createAndRegisterModel();
        if (model != null) {
            model.getCachedDataAndLoad();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (model != null){
            model.cancel();
        }
    }

    @Override
    public void onLoadSuccess(BaseModel model, List<DATA> data) {
        dataList.postValue(data);
        viewStatusLiveData.postValue(ViewStatus.SHOW_CONTENT);

    }


    @Override
    public void onLoadFail(BaseModel model, String message) {
        errorMessage.postValue(message);
        viewStatusLiveData.postValue(ViewStatus.REFRESH_ERROR);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume() {
        if(dataList == null || dataList.getValue() == null || dataList.getValue().size() == 0) {
            createAndRegisterModel();
            model.getCachedDataAndLoad();
        } else {
            dataList.postValue(dataList.getValue());
            viewStatusLiveData.postValue(viewStatusLiveData.getValue());
        }
    }
}
