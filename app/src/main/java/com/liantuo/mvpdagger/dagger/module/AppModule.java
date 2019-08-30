package com.liantuo.mvpdagger.dagger.module;


import com.liantuo.baselib.BaseApplication;
import com.liantuo.mvpdagger.MyApplication;
import com.liantuo.mvpdagger.data.DataManager;
import com.liantuo.mvpdagger.data.api.Requests;
import com.liantuo.mvpdagger.data.api.RequestsImpl;
import com.liantuo.mvpdagger.data.storage.Caches;
import com.liantuo.mvpdagger.data.storage.CachesImpl;

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
