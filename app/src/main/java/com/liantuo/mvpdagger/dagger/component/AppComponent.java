package com.liantuo.mvpdagger.dagger.component;


import com.liantuo.baselib.BaseApplication;
import com.liantuo.mvpdagger.MyApplication;
import com.liantuo.mvpdagger.dagger.module.AbstractAllActivityModule;
import com.liantuo.mvpdagger.dagger.module.AbstractAllFragmentModule;
import com.liantuo.mvpdagger.dagger.module.AppModule;
import com.liantuo.mvpdagger.dagger.module.HttpModule;
import com.liantuo.mvpdagger.data.DataManager;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AbstractAllActivityModule.class,
        AbstractAllFragmentModule.class,
        AppModule.class,
        HttpModule.class})
public interface AppComponent {

    void inject(BaseApplication application);

    MyApplication getApplication();

    DataManager getDataManager();
}
