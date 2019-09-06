package com.liantuo.mvpdagger.data.request;

import com.liantuo.mvpdagger.data.bean.LoginResponse;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RequestsImpl implements Requests {

    private RequestsApi requestsApi;

    @Inject
    RequestsImpl(RequestsApi requestsApi) {
        this.requestsApi = requestsApi;
    }

    @Override
    public Observable<LoginResponse> login(Map<String, Object> request) {
        return requestsApi.login(request);
    }
}
