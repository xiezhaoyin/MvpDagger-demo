package com.liantuo.baselib.mvp;

public interface IBasePresenter<V extends IBaseView> {

    void createPresenter(V view);

    void destroyPresenter();

}
