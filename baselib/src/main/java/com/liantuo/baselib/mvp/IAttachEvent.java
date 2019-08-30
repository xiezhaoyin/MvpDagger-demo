package com.liantuo.baselib.mvp;

public interface IAttachEvent {

    EventDispatch attachEvent(EventDispatch dispatch, OnReceiveListener listener);

    void detachEvent();
}
