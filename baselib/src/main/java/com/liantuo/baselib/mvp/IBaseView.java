package com.liantuo.baselib.mvp;

/**
 * Created by user on 2019/6/19.
 */

public interface IBaseView {

    void showDialog(String msg, OnDialogCallback callback);

    void hideDialog();

    void showProgress(String msg);

    void hideProgress();

    void showToast(String msg);

    interface OnDialogCallback {

        void onSure();

        void onCancel();
    }


}


