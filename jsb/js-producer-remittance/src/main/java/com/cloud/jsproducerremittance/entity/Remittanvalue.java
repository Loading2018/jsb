package com.cloud.jsproducerremittance.entity;

public class Remittanvalue {
    private String phone;//电话
    private String auth;//验证码
    private String userid;//付款人ID

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
