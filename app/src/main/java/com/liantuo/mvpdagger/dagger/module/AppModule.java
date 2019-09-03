package com.liantuo.mvpdagger.dagger.module;


import com.liantuo.mvpdagger.MyApplication;
import com.liantuo.mvpdagger.data.DataManager;
import com.liantuo.mvpdagger.data.request.Requests;
import com.liantuo.mvpdagger.data.request.RequestsImpl;
import com.liantuo.mvpdagger.data.cache.Caches;
import com.liantuo.mvpdagger.data.cache.CachesImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    MyApplication provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Requests provideRequests(RequestsImpl requests) {
        return requests;
    }

    @Provides
    @Singleton
    Caches provideCaches(CachesImpl caches) {
        return caches;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(Requests requests, Caches caches) {
        return new DataManager(requests, caches);
    }

}
