package com.project.common.fragment.model;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.project.common.preference.BasicDataPreferenceUtil;
import com.project.common.utils.GenericUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class BaseModel<NETWORK_DATA,RESULT_DATA> implements DataObserver<NETWORK_DATA> {

    private CompositeDisposable compositeDisposable;
    protected WeakReference<IBaseModelListener> mReferenceIBaseModelListener;
    private boolean mIsLoading;
    private String mCachedPreferenceKey;
    private String mApkPredefinedData;

    public BaseModel(String cachedPreferenceKey, String apkPredefinedData,int type){
        this.mCachedPreferenceKey = cachedPreferenceKey;
        this.mApkPredefinedData   = apkPredefinedData;
    }

    public void register(IBaseModelListener listener){
        if (listener != null){
            mReferenceIBaseModelListener = new WeakReference<>(listener);
        }
    }

    public abstract void load();

    public void refresh(){
        if (!mIsLoading){
            mIsLoading = true;
            load();
        }
    }

    protected boolean isNeedToUpdate(long cachedTimeSlot) {
        return true;
    }

    public void getCachedDataAndLoad(){
        if (!mIsLoading){
            mIsLoading = true;
            if (mCachedPreferenceKey != null){
                String saveDataString = BasicDataPreferenceUtil.getInstance().getString(mCachedPreferenceKey);
                if (!TextUtils.isEmpty(saveDataString)){
                    try {
                        NETWORK_DATA saveData = new Gson().fromJson(new JSONObject(saveDataString).getString("data"),(Class<NETWORK_DATA>) GenericUtils.getGenericType(this));
                        if (saveData != null){
                            onSuccess(saveData,true);
                        }
                        long timeSlot = Long.parseLong(new JSONObject(saveDataString).getString("updateTimeInMillis"));
                        if(isNeedToUpdate(timeSlot)) {
                            load();
                            return;
                        }
                    } catch (JSONException e) {
                        Log.e("BaseModel",e.getMessage());
                        e.printStackTrace();
                    }
                }

                if (mApkPredefinedData != null){
                    NETWORK_DATA savedData = new Gson().fromJson(mApkPredefinedData, (Class<NETWORK_DATA>) GenericUtils.getGenericType(this));
                    if(savedData != null) {
                        onSuccess(savedData, true);
                    }
                }
            }
            load();
        }
    }

    protected void saveDataToPreference(NETWORK_DATA data) {
        if(data != null) {
            BaseCacheData<NETWORK_DATA> cachedData = new BaseCacheData<>();
            cachedData.userId = "000001";
            cachedData.updateTimeInMillis = System.currentTimeMillis();
            cachedData.data = data;
            BasicDataPreferenceUtil.getInstance().setString(mCachedPreferenceKey, new Gson().toJson(cachedData));
        }
    }

    protected void notifyResultToListener(NETWORK_DATA networkData,RESULT_DATA resultData,boolean isFromCache){
        IBaseModelListener listener = mReferenceIBaseModelListener.get();
        if (listener != null){
            listener.onLoadSuccess(this, resultData);
            if (!isFromCache){
                saveDataToPreference(networkData);
            }
        }
        if(!isFromCache) {
            mIsLoading = false;
        }
    }



    public void cancel() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void addDisposable(Disposable d){
        if (d == null){
            return;
        }
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(d);
    }
}
