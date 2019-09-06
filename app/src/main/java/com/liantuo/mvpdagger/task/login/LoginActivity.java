package com.liantuo.mvpdagger.task.login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.liantuo.baselib.mvp.BaseActivity;
import com.liantuo.mvpdagger.data.bean.LoginRequest;
import com.liantuo.mvpdagger.data.bean.LoginResponse;
import com.liantuo.mvpdagger.task.main.MainActivity;
import com.liantuo.mvpdagger.R;
import com.liantuo.baselib.util.DialogUtil;
import com.liantuo.baselib.util.LogUtil;
import com.liantuo.baselib.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.edt_name)
    TextInputEditText edtName;
    @BindView(R.id.edt_pwd)
    TextInputEditText edtPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.checkbox_pwd)
    CheckBox checkboxPwd;

    @Override
    public int createView() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void resumeView() {
    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onReceive(boolean isSticky, String eventTag, Object event) {

    }

    @OnClick({R.id.card_login, R.id.btn_login})
    public void onClick(View v) {
        if (TextUtils.isEmpty(edtName.getText().toString().trim())) {
            showToast("用户名不能为空");
            return;
        }

        if (TextUtils.isEmpty(edtPwd.getText().toString().trim())) {
            showToast("用户密码不能为空");
            return;
        }

        presenter.login(new LoginRequest(edtName.getText().toString().trim(), edtPwd.getText().toString().trim()));
    }


    @Override
    public void loginSuccess(LoginResponse response) {
        LogUtil.d(TAG,"loginSuccess ... ");
        gotoActivity(MainActivity.class, null);
    }

    @Override
    public void loginFail(String errCode, String errMsg) {
        showToast(errMsg);
    }

    @Override
    public void enableRememberPwd() {

    }

    @Override
    public void showDialog(String msg, OnDialogCallback callback) {
    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showProgress(String msg) {
        DialogUtil.showProgress(this, msg);
    }

    @Override
    public void hideProgress() {
        DialogUtil.hideProgress();
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(this, msg);
    }

}
