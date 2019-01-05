package com.example.jsconsumerdeposit.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * (DepositInform)实体类
 *
 * @author makejava
 * @since 2018-12-19 14:45:33
 */
@Component
public class DepositInform implements Serializable {
    private static final long serialVersionUID = -71494458616827602L;
    //通知存款订单id
    private Integer depositInformid;
    //通知存款订单名字
    private String depositInformnumber;
    //通知存款订单存款金额
    private Double depositInformmoney;
    //通知存款订单状态 1为通知存款中/
    private String depositInformstate;
    //通知存款订单关联卡号
    private String depositInformcardid;
    //用户id
    private String depositInformuserid;
    //通知存款业务id
    private Integer depositInformtype;
    //通知存款存入时间
    private Date depositInformtime;


    public Integer getDepositInformid() {
        return depositInformid;
    }

    public void setDepositInformid(Integer depositInformid) {
        this.depositInformid = depositInformid;
    }

    public String getDepositInformnumber() {
        return depositInformnumber;
    }

    public void setDepositInformnumber(String depositInformnumber) {
        this.depositInformnumber = depositInformnumber;
    }

    public Double getDepositInformmoney() {
        return depositInformmoney;
    }

    public void setDepositInformmoney(Double depositInformmoney) {
        this.depositInformmoney = depositInformmoney;
    }

    public String getDepositInformstate() {
        return depositInformstate;
    }

    public void setDepositInformstate(String depositInformstate) {
        this.depositInformstate = depositInformstate;
    }

    public String getDepositInformcardid() {
        return depositInformcardid;
    }

    public void setDepositInformcardid(String depositInformcardid) {
        this.depositInformcardid = depositInformcardid;
    }

    public String getDepositInformuserid() {
        return depositInformuserid;
    }

    public void setDepositInformuserid(String depositInformuserid) {
        this.depositInformuserid = depositInformuserid;
    }

    public Integer getDepositInformtype() {
        return depositInformtype;
    }

    public void setDepositInformtype(Integer depositInformtype) {
        this.depositInformtype = depositInformtype;
    }

    public Date getDepositInformtime() {
        return depositInformtime;
    }

    public void setDepositInformtime(Date depositInformtime) {
        this.depositInformtime = depositInformtime;
    }

}