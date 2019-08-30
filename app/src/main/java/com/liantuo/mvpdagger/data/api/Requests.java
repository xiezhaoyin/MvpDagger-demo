package com.liantuo.mvpdagger.data.api;

import com.liantuo.mvpdagger.data.bean.entity.LoginResponse;

import java.util.Map;

import io.reactivex.Observable;

public interface Requests {
    Observable<LoginResponse> login(Map<String, Object> request);
}
