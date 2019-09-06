package com.liantuo.mvpdagger.task.login;

import com.liantuo.baselib.mvp.IBasePresenter;
import com.liantuo.baselib.mvp.IBaseView;
import com.liantuo.mvpdagger.data.bean.LoginRequest;
import com.liantuo.mvpdagger.data.bean.LoginResponse;

public class LoginContract {

    public interface View extends IBaseView {

        void loginSuccess(LoginResponse response);

        void loginFail(String errCode, String errMsg);

        void enableRememberPwd();
    }

    public interface Presenter extends IBasePresenter<View> {
        void login(LoginRequest request);
    }
}
