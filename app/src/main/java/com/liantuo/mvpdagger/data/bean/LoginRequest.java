package com.liantuo.mvpdagger.data.bean;

public class LoginRequest {

    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public LoginRequest() {
    }

    public LoginRequest(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
