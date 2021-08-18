package com.project.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.project.common.R;
import com.project.utils.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class PhotoUtils {

    private static final String TAG = "PhotoUtils";

    /**
     * 调用时候开启线程去调用
     *
     * @param view
     * @param path_list  key:网络图片路径 value：图片名字
     */
    public static void GetHttpBitmapS(View view, List<String> path_list) {
        for (int i = 0;i < path_list.size(); i++){
            String httpUrl = path_list.get(i);
            String filePath = FileUtils.file_p + "/" +FileUtils.SplitHttpUrl(httpUrl);
            Observable.just(filePath)
                    .map(new Function<String, Boolean>() {
                        @Override
                        public Boolean apply(String s) throws Throwable {
                            if (FileUtils.IsCacheExist(filePath)){
                                //存在缓存
                                Log.d(TAG,"isExistCache");
                                return true;
                            }else {
                                Log.d(TAG,"NoCache");
                                //不存在缓存
                                return false;
                            }
                        }
                    })
                    .map(new Function<Boolean, Bitmap>() {
                        @Override
                        public Bitmap apply(Boolean isExistCache) throws Throwable {
                            if (isExistCache){
                                Log.d(TAG,"isExistCache From File");
                                FileInputStream fis = new FileInputStream(filePath);
                                Bitmap bitmap = BitmapFactory.decodeStream(fis);
                                return bitmap;
                            }else {
                                //不存在则申请
                                Log.d(TAG,"isExistCache From Http");
                                URL url = new URL(httpUrl);
                                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                                httpURLConnection.setConnectTimeout(5000);
                                int responseCode = httpURLConnection.getResponseCode(); // 才开始 request
                                Log.d(TAG,"isExistCache From Http :" + responseCode);
                                if(responseCode == HttpURLConnection.HTTP_OK) {
                                    InputStream inputStream = httpURLConnection.getInputStream();
                                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                    FileUtils.CacheToFile(filePath,bitmap);
                                    return bitmap;
                                }
                                return null;
                            }
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Bitmap>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull Bitmap bitmap) {
                            if (bitmap != null){
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                                byte[] bytes = baos.toByteArray();

                                Glide.with(view.getContext())
                                        .load(bytes)
                                        .apply(RequestOptions.bitmapTransform(new CenterCrop()))
                                        .into((ImageView) view);
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
