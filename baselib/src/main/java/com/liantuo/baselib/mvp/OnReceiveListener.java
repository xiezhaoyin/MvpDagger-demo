package com.liantuo.baselib.mvp;

public interface OnReceiveListener {
    void onReceive(boolean isSticky, String eventTag, Object event);
}
