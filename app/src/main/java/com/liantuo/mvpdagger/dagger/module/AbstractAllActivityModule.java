package com.liantuo.mvpdagger.dagger.module;



import com.liantuo.baselib.dagger.component.BaseActivityComponent;
import com.liantuo.mvpdagger.task.login.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributesLoginActivityInjector();

}
