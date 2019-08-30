package com.liantuo.mvpdagger.dagger.module;


import com.liantuo.baselib.dagger.qualifier.HttpEngine;
import com.liantuo.baselib.network.RetrofitFactory;
import com.liantuo.mvpdagger.data.api.RequestsApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class HttpModule {

    // 线上
    private static final String URL_ON_LINE = "http://api.liantuofu.com/open/";
    // 灰度
    private static final String URL_ON_GRAY = "http://intshop.51ebill.com/open/";
    // 测试
    private static final String URL_ON_TEST = "http://testclubshop.liantuobank.com/open/";

    @Singleton
    @Provides
    RequestsApi provideRequestsApi(@HttpEngine Retrofit retrofit) {
        return retrofit.create(RequestsApi.class);
    }

    @Singleton
    @Provides
    @HttpEngine
    Retrofit provideRetrofit() {
        return RetrofitFactory.getRetrofit(URL_ON_TEST, URL_ON_LINE);
    }
}
