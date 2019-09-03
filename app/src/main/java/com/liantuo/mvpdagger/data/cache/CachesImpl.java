package com.liantuo.mvpdagger.data.cache;

import javax.inject.Inject;

public class CachesImpl implements Caches {

    private CachesApi cachesApi;

    @Inject
    public CachesImpl(CachesApi cachesApi) {
        this.cachesApi = cachesApi;
    }
}
