package com.liantuo.mvpdagger.data;

import com.liantuo.mvpdagger.data.request.Requests;
import com.liantuo.mvpdagger.data.bean.entity.LoginResponse;
import com.liantuo.mvpdagger.data.cache.Caches;

import java.util.Map;

import io.reactivex.Observable;

public class DataManager implements Requests, Caches {

    private Requests requests;
    private Caches caches;

    public DataManager(Requests requests, Caches caches) {
        this.requests = requests;
        this.caches = caches;
    }

    @Override
    public Observable<LoginResponse> login(Map<String, Object> request) {
        return requests.login(request);
    }
}
