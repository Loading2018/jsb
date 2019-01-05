package com.cloud.jsproducerremittance.entity;

import java.io.Serializable;

/**
 * (Batch)实体类
 *
 * @author makejava
 * @since 2018-12-24 16:19:46
 */
public class Batch implements Serializable {
    private static final long serialVersionUID = 737776001575639618L;
    //批量汇款ID
    private Integer batchId;
    //收款人姓名
    private String batchName;
    //收款人卡号
    private String batchNumber;
    //收款人地区
    private String batchSite;
    //汇款金额
    private String batchPrice;
    //短信通知电话
    private String batchPhone;
    //付款人姓名
    private String batchPayname;
    //付款卡号
    private String batchPaynumber;
    //付款人ID
    private Integer batchPayuserid;
    //当前交易时间
    private String batchTime;
    //币种
    private String batchCurrency;


    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getBatchSite() {
        return batchSite;
    }

    public void setBatchSite(String batchSite) {
        this.batchSite = batchSite;
    }

    public String getBatchPrice() {
        return batchPrice;
    }

    public void setBatchPrice(String batchPrice) {
        this.batchPrice = batchPrice;
    }

    public String getBatchPhone() {
        return batchPhone;
    }

    public void setBatchPhone(String batchPhone) {
        this.batchPhone = batchPhone;
    }

    public String getBatchPayname() {
        return batchPayname;
    }

    public void setBatchPayname(String batchPayname) {
        this.batchPayname = batchPayname;
    }

    public String getBatchPaynumber() {
        return batchPaynumber;
    }

    public void setBatchPaynumber(String batchPaynumber) {
        this.batchPaynumber = batchPaynumber;
    }

    public Integer getBatchPayuserid() {
        return batchPayuserid;
    }

    public void setBatchPayuserid(Integer batchPayuserid) {
        this.batchPayuserid = batchPayuserid;
    }

    public String getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(String batchTime) {
        this.batchTime = batchTime;
    }

    public String getBatchCurrency() {
        return batchCurrency;
    }

    public void setBatchCurrency(String batchCurrency) {
        this.batchCurrency = batchCurrency;
    }

}