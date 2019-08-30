package com.liantuo.mvpdagger;

import com.liantuo.baselib.BaseApplication;
import com.liantuo.mvpdagger.dagger.component.AppComponent;
import com.liantuo.mvpdagger.dagger.component.DaggerAppComponent;
import com.liantuo.mvpdagger.dagger.module.AppModule;
import com.liantuo.mvpdagger.dagger.module.HttpModule;

public class MyApplication extends BaseApplication {

    private volatile static AppComponent appComponent;
    private MyApplication instance = null;

    @Override
    protected void init() {
        instance = this;

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .httpModule(new HttpModule())
                .build();

        appComponent.inject(instance);

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
