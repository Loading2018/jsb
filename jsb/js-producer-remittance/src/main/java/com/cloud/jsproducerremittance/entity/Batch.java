package com.cloud.jsproducerremittance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Batch)实体类
 *
 * @author makejava
 * @since 2018-11-10 08:51:40
 */
@Entity
@Table(name = "BATCH")
public class Batch implements Serializable {

    //批量汇款ID
    @Id
    @GeneratedValue
    private Integer batchid;
    //收款人姓名
    private String batchname = "";
    //收款人卡号
    private String batchnumber;
    //收款人地区
    private String batchsite;
    //汇款金额
    private String batchprice;
    //短信通知电话
    private String batchphone;
    //付款人姓名
    private String batchpayname;
    //付款卡号
    private String batchpaynumber;
    //付款人ID
    private Integer batchpayuserid;
    //当前交易时间
    private String batchtime;
    //币种
    private String batchcurrency;
    //短信留言
    private String smsmessage;

    public Integer getBatchid() {
        return batchid;
    }

    public void setBatchid(Integer batchid) {
        this.batchid = batchid;
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }

    public String getBatchnumber() {
        return batchnumber;
    }

    public void setBatchnumber(String batchnumber) {
        this.batchnumber = batchnumber;
    }

    public String getBatchsite() {
        return batchsite;
    }

    public void setBatchsite(String batchsite) {
        this.batchsite = batchsite;
    }

    public String getBatchprice() {
        return batchprice;
    }

    public void setBatchprice(String batchprice) {
        this.batchprice = batchprice;
    }

    public String getBatchphone() {
        return batchphone;
    }

    public void setBatchphone(String batchphone) {
        this.batchphone = batchphone;
    }

    public String getBatchpayname() {
        return batchpayname;
    }

    public void setBatchpayname(String batchpayname) {
        this.batchpayname = batchpayname;
    }

    public String getBatchpaynumber() {
        return batchpaynumber;
    }

    public void setBatchpaynumber(String batchpaynumber) {
        this.batchpaynumber = batchpaynumber;
    }

    public Integer getBatchpayuserid() {
        return batchpayuserid;
    }

    public void setBatchpayuserid(Integer batchpayuserid) {
        this.batchpayuserid = batchpayuserid;
    }

    public String getBatchtime() {
        return batchtime;
    }

    public void setBatchtime(String batchtime) {
        this.batchtime = batchtime;
    }

    public String getBatchcurrency() {
        return batchcurrency;
    }

    public void setBatchcurrency(String batchcurrency) {
        this.batchcurrency = batchcurrency;
    }

    public String getSmsmessage() {
        return smsmessage;
    }

    public void setSmsmessage(String smsmessage) {
        this.smsmessage = smsmessage;
    }
}