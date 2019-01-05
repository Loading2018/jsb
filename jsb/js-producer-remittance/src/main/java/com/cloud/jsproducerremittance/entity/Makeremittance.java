package com.cloud.jsproducerremittance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Makeremittance)实体类
 *
 * @author makejava
 * @since 2018-11-22 21:01:21
 */
@Entity
@Table(name = "MAKEREMITTANCE")
public class MakeRemittance implements Serializable {

    //预约汇款表
    @Id
    @GeneratedValue
    private Integer makeremittanceid;
    //预约编号
    private String makeremittanceserialnumber;
    //预约时间
    private String makeremittancetime;
    //当前时间
    private String makeremittancedata;
    //预约汇款金额
    private String makeremittanceprice;
    //预约付款卡号
    private String makeremittancepaynumber;
    //收款人姓名
    private String makeremittancename;
    //预约收款卡号
    private String makeremittancenumber;
    //预约付款人ID
    private Integer makeremittancepayuserid;
    //预约汇款人 付款人
    private String makeremittancepayname;
    //预约信息状态1 待审核 2通过 3取消
    private Integer makeremittancetype;
    //状态信息
    private String type;
    //预约网点  关联网点表
    private Integer makeremittancebranch;
    //网点信息 展示网点名称
    private String branchname;

    public Integer getMakeremittanceid() {
        return makeremittanceid;
    }

    public void setMakeremittanceid(Integer makeremittanceid) {
        this.makeremittanceid = makeremittanceid;
    }

    public String getMakeremittanceserialnumber() {
        return makeremittanceserialnumber;
    }

    public void setMakeremittanceserialnumber(String makeremittanceserialnumber) {
        this.makeremittanceserialnumber = makeremittanceserialnumber;
    }

    public String getMakeremittancetime() {
        return makeremittancetime;
    }

    public void setMakeremittancetime(String makeremittancetime) {
        this.makeremittancetime = makeremittancetime;
    }

    public String getMakeremittancedata() {
        return makeremittancedata;
    }

    public void setMakeremittancedata(String makeremittancedata) {
        this.makeremittancedata = makeremittancedata;
    }

    public String getMakeremittanceprice() {
        return makeremittanceprice;
    }

    public void setMakeremittanceprice(String makeremittanceprice) {
        this.makeremittanceprice = makeremittanceprice;
    }

    public String getMakeremittancepaynumber() {
        return makeremittancepaynumber;
    }

    public void setMakeremittancepaynumber(String makeremittancepaynumber) {
        this.makeremittancepaynumber = makeremittancepaynumber;
    }

    public String getMakeremittancename() {
        return makeremittancename;
    }

    public void setMakeremittancename(String makeremittancename) {
        this.makeremittancename = makeremittancename;
    }

    public String getMakeremittancenumber() {
        return makeremittancenumber;
    }

    public void setMakeremittancenumber(String makeremittancenumber) {
        this.makeremittancenumber = makeremittancenumber;
    }

    public Integer getMakeremittancepayuserid() {
        return makeremittancepayuserid;
    }

    public void setMakeremittancepayuserid(Integer makeremittancepayuserid) {
        this.makeremittancepayuserid = makeremittancepayuserid;
    }

    public String getMakeremittancepayname() {
        return makeremittancepayname;
    }

    public void setMakeremittancepayname(String makeremittancepayname) {
        this.makeremittancepayname = makeremittancepayname;
    }

    public Integer getMakeremittancetype() {
        return makeremittancetype;
    }

    public void setMakeremittancetype(Integer makeremittancetype) {
        this.makeremittancetype = makeremittancetype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMakeremittancebranch() {
        return makeremittancebranch;
    }

    public void setMakeremittancebranch(Integer makeremittancebranch) {
        this.makeremittancebranch = makeremittancebranch;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }
}