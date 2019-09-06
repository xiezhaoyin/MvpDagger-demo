package com.liantuo.mvpdagger.data.bean;

public class BaseRequest {

    private String appId;
    private String random;

    // key只是参与sign签名过程，不是上送参数，
    // 在参数拦截器里生成sign添加到上送参数表单里，并拦截key参数上送
    private String key;
//    private String sign;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

//    public String getSign() {
//        return sign;
//    }
//
//    public void setSign(String sign) {
//        this.sign = sign;
//    }

}
