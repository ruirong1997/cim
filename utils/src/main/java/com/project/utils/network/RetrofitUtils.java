package com.project.utils.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.project.utils.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static final String TAG = "RetrofitUtils";
    public static String Service_URL = BuildConfig.Service;

    public static Retrofit getOnlineCookeRetrofit(){
       // OKHttp客户端
       OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
       // 各种参数配置
       OkHttpClient okHttpClient = httpBuilder
               .addNetworkInterceptor(new StethoInterceptor())
               .readTimeout(10000, TimeUnit.SECONDS)
               .connectTimeout(10000, TimeUnit.SECONDS)
               .writeTimeout(10000, TimeUnit.SECONDS)
               .build();

       return new Retrofit.Builder().baseUrl(Service_URL)
               // TODO 请求用 OKhttp
               .client(okHttpClient)
               // TODO 响应RxJava
               // 添加一个json解析的工具
               .addConverterFactory(GsonConverterFactory.create(new Gson()))
               // 添加rxjava处理工具
               .addCallAdapterFactory(RxJava3CallAdapterFactory.create())

               .build();
   }

}
