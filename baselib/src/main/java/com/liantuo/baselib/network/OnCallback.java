package com.liantuo.baselib.network;


public interface OnCallback<T> {

    void onCall(T response);

    void onError(String errCode, String errMsg);
}
