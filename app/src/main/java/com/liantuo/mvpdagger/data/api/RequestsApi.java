package com.liantuo.mvpdagger.data.api;

import com.liantuo.mvpdagger.data.bean.entity.LoginResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RequestsApi {

    @FormUrlEncoded
    @POST(HttpPath.LOGIN)
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<LoginResponse> login(@FieldMap Map<String, Object> request) ;
}
