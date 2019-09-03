package com.liantuo.mvpdagger.dagger.module;


import com.liantuo.baselib.dagger.qualifier.HttpEngine;
import com.liantuo.baselib.network.RetrofitFactory;
import com.liantuo.mvpdagger.util.FileUtil;
import com.liantuo.mvpdagger.MyApplication;
import com.liantuo.mvpdagger.data.cache.CachesApi;
import com.liantuo.mvpdagger.data.request.RequestsApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class HttpModule {

    // 线上
    private static final String URL_ON_LINE = "http://api.liantuofu.com/open/";

    // 测试
    private static final String URL_ON_TEST = "http://testclubshop.liantuobank.com/open/";

    private static String cacheDir = null;

    public HttpModule(MyApplication application) {
        cacheDir = FileUtil.getStorageDirectory(application)
                + FileUtil.FOLDER_PATH;
    }

    @Singleton
    @Provides
    CachesApi provideCachesApi() {
        return RetrofitFactory.getRxCache(cacheDir).using(CachesApi.class);
    }

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
