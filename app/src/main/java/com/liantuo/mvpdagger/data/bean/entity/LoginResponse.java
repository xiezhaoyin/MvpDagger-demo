package com.liantuo.mvpdagger.data.bean.entity;

public class LoginResponse extends BaseResponse {

    /**
     * merchantCode : EW_N5964614567
     * merchantName : 小精灵商户通门店
     * merchantId : 10526986
     * operatorId : 10587633
     * operatorName : 小精灵语音王
     * appId : EW_N2176107738
     * role : 1
     * key : ce5a9bea61696ede97d0c697cde824cc
     * loginTime : 20190626192752
     */

    private String merchantCode;
    private String merchantName;
    private String merchantId;
    private String operatorId;
    private String operatorName;
    private String appId;
    private int role;
    private String key;
    private String loginTime;

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "merchantCode='" + merchantCode + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", merchantId=" + merchantId +
                ", operatorId='" + operatorId + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", appId='" + appId + '\'' +
                ", role=" + role +
                ", key='" + key + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }
}
