package com.cloud.jsproducerremittance.entity;

import java.io.Serializable;

/**
 * (Makeremittance)实体类
 *
 * @author makejava
 * @since 2018-12-24 16:20:37
 */
public class Makeremittance implements Serializable {
    private static final long serialVersionUID = -76036021215507083L;
    //预约汇款表
    private Integer makeremittanceId;
    //预约编号
    private String makeremittanceSerialnumber;
    //预约时间
    private String makeremittanceTime;
    //当前时间
    private String makeremittanceData;
    //预约汇款金额
    private String makeremittancePrice;
    //预约付款卡号
    private String makeremittancePaynumber;
    //收款人姓名
    private String makeremittanceName;
    //预约收款卡号
    private String makeremittanceNumber;
    //预约付款人ID
    private Integer makeremittancePayuserid;
    //预约汇款人 付款人
    private String makeremittancePayname;
    //预约信息状态1 待审核 2通过 3取消
    private Integer makeremittanceType;
    //预约网点  关联网点表
    private Integer makeremittanceBranch;
    //状态信息
    private String type;
    //网点信息 展示网点名称
    private String Branchname;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBranchname() {
        return Branchname;
    }

    public void setBranchname(String branchname) {
        Branchname = branchname;
    }

    public Integer getMakeremittanceId() {
        return makeremittanceId;
    }

    public void setMakeremittanceId(Integer makeremittanceId) {
        this.makeremittanceId = makeremittanceId;
    }

    public String getMakeremittanceSerialnumber() {
        return makeremittanceSerialnumber;
    }

    public void setMakeremittanceSerialnumber(String makeremittanceSerialnumber) {
        this.makeremittanceSerialnumber = makeremittanceSerialnumber;
    }

    public String getMakeremittanceTime() {
        return makeremittanceTime;
    }

    public void setMakeremittanceTime(String makeremittanceTime) {
        this.makeremittanceTime = makeremittanceTime;
    }

    public String getMakeremittanceData() {
        return makeremittanceData;
    }

    public void setMakeremittanceData(String makeremittanceData) {
        this.makeremittanceData = makeremittanceData;
    }

    public String getMakeremittancePrice() {
        return makeremittancePrice;
    }

    public void setMakeremittancePrice(String makeremittancePrice) {
        this.makeremittancePrice = makeremittancePrice;
    }

    public String getMakeremittancePaynumber() {
        return makeremittancePaynumber;
    }

    public void setMakeremittancePaynumber(String makeremittancePaynumber) {
        this.makeremittancePaynumber = makeremittancePaynumber;
    }

    public String getMakeremittanceName() {
        return makeremittanceName;
    }

    public void setMakeremittanceName(String makeremittanceName) {
        this.makeremittanceName = makeremittanceName;
    }

    public String getMakeremittanceNumber() {
        return makeremittanceNumber;
    }

    public void setMakeremittanceNumber(String makeremittanceNumber) {
        this.makeremittanceNumber = makeremittanceNumber;
    }

    public Integer getMakeremittancePayuserid() {
        return makeremittancePayuserid;
    }

    public void setMakeremittancePayuserid(Integer makeremittancePayuserid) {
        this.makeremittancePayuserid = makeremittancePayuserid;
    }

    public String getMakeremittancePayname() {
        return makeremittancePayname;
    }

    public void setMakeremittancePayname(String makeremittancePayname) {
        this.makeremittancePayname = makeremittancePayname;
    }

    public Integer getMakeremittanceType() {
        return makeremittanceType;
    }

    public void setMakeremittanceType(Integer makeremittanceType) {
        this.makeremittanceType = makeremittanceType;
    }

    public Integer getMakeremittanceBranch() {
        return makeremittanceBranch;
    }

    public void setMakeremittanceBranch(Integer makeremittanceBranch) {
        this.makeremittanceBranch = makeremittanceBranch;
    }

}