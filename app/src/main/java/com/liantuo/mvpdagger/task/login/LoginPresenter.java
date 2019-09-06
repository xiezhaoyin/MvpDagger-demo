package com.liantuo.mvpdagger.task.login;

import android.util.Log;

import com.liantuo.baselib.mvp.BasePresenter;
import com.liantuo.baselib.network.BaseObserver;
import com.liantuo.baselib.network.Obj2MapUtil;
import com.liantuo.baselib.network.OnCallback;
import com.liantuo.mvpdagger.data.DataManager;
import com.liantuo.mvpdagger.data.bean.LoginRequest;
import com.liantuo.mvpdagger.data.bean.LoginResponse;
import com.trello.rxlifecycle2.LifecycleProvider;


import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void login(LoginRequest request) {

        view.showProgress("正在登录");
        addSubscribe(dataManager.login(Obj2MapUtil.objectToMap(request))
                .compose(rxSchedulingThread())
                .compose(bindToLifecycle((LifecycleProvider) view))
                .subscribeWith(new BaseObserver<LoginResponse>(new OnCallback<LoginResponse>() {
                    @Override
                    public void onCall(LoginResponse response) {
                        Log.d("LoginPresenter", "onCall .. ");
                        if ("SUCCESS".equals(response.getCode())) {
                            view.loginSuccess(response);
                        } else {
                            view.loginFail(response.getSubCode(), response.getSubMsg());
                        }
                        view.hideProgress();
                    }

                    @Override
                    public void onError(String errCode, String errMsg) {
                        view.loginFail(errCode, errMsg);
                        view.hideProgress();
                    }
                }))
        );

    }

}
