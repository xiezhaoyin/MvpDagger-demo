package com.liantuo.baselib.network;


import com.liantuo.baselib.BuildConfig;

import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitFactory {

    // 线上
    private static final String URL_ON_LINE = "http://api.liantuofu.com/open/";
    // 灰度
    private static final String URL_ON_GRAY = "http://intshop.51ebill.com/open/";
    // 测试
    private static final String URL_ON_TEST = "http://testclubshop.liantuobank.com/open/";

    private static Converter.Factory converter = GsonConverterFactory.create();
    private static CallAdapter.Factory callAdapter = RxJava2CallAdapterFactory.create();

    public static Retrofit getRetrofit(String testUrl, String lineUrl) {
        String url = "";
        if (BuildConfig.DEBUG) {
            url = testUrl;
        } else {
            url = lineUrl;
        }

        return new Retrofit.Builder()
                .baseUrl(url)
                // 添加Json转换器
                .addConverterFactory(converter)
                // 添加Retrofit到RxJava的转换器
                .addCallAdapterFactory(callAdapter)
                .client(OkHttpHelper.getOkHttpClient(url))
                .build();
    }

}



