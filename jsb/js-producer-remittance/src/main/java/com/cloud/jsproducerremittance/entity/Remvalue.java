package com.cloud.jsproducerremittance.entity;

import java.io.Serializable;

public class Remvalue implements Serializable {

    //汇款交易记录ID
    private Integer remittancetransactionId;
    //收款人姓名
    private String remittancetransactionName;
    //收款卡号
    private String remittancetransactionCardnumber;
    //收款银行1:中行2:建行3:工行4:农行5:交行6:民行7:招行8:邮行9:浦发10: 中信
    private String remittancetransactionBlank;
    //汇款金额
    private String remittancetransactionPrice;
    //汇款时间1：实时汇款2：普通汇款3：次日汇款
    private String remittancetransactionTime;
    //当前时间 (汇款时间)年月日
    private String remittancetransactionCurrent;
    //付款卡号
    private String remittancetransactionNumber;
    //汇款人ID
    private Integer remittancetransactionPayuserid;
    //付款人电话
    private String phone;

    public Integer getRemittancetransactionId() {
        return remittancetransactionId;
    }

    public void setRemittancetransactionId(Integer remittancetransactionId) {
        this.remittancetransactionId = remittancetransactionId;
    }

    public String getRemittancetransactionName() {
        return remittancetransactionName;
    }

    public void setRemittancetransactionName(String remittancetransactionName) {
        this.remittancetransactionName = remittancetransactionName;
    }

    public String getRemittancetransactionCardnumber() {
        return remittancetransactionCardnumber;
    }

    public void setRemittancetransactionCardnumber(String remittancetransactionCardnumber) {
        this.remittancetransactionCardnumber = remittancetransactionCardnumber;
    }



    public String getRemittancetransactionPrice() {
        return remittancetransactionPrice;
    }

    public void setRemittancetransactionPrice(String remittancetransactionPrice) {
        this.remittancetransactionPrice = remittancetransactionPrice;
    }

    public String getRemittancetransactionBlank() {
        return remittancetransactionBlank;
    }

    public void setRemittancetransactionBlank(String remittancetransactionBlank) {
        this.remittancetransactionBlank = remittancetransactionBlank;
    }

    public String getRemittancetransactionTime() {
        return remittancetransactionTime;
    }

    public void setRemittancetransactionTime(String remittancetransactionTime) {
        this.remittancetransactionTime = remittancetransactionTime;
    }

    public String getRemittancetransactionCurrent() {
        return remittancetransactionCurrent;
    }

    public void setRemittancetransactionCurrent(String remittancetransactionCurrent) {
        this.remittancetransactionCurrent = remittancetransactionCurrent;
    }

    public String getRemittancetransactionNumber() {
        return remittancetransactionNumber;
    }

    public void setRemittancetransactionNumber(String remittancetransactionNumber) {
        this.remittancetransactionNumber = remittancetransactionNumber;
    }

    public Integer getRemittancetransactionPayuserid() {
        return remittancetransactionPayuserid;
    }

    public void setRemittancetransactionPayuserid(Integer remittancetransactionPayuserid) {
        this.remittancetransactionPayuserid = remittancetransactionPayuserid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
