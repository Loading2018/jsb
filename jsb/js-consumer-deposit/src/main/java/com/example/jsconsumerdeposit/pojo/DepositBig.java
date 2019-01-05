package com.example.jsconsumerdeposit.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * (DepositBig)实体类
 *
 * @author makejava
 * @since 2018-12-19 14:44:56
 */
@Component
public class DepositBig implements Serializable {
    private static final long serialVersionUID = -32291505472537358L;
    //大额存款id
    private Integer depositBigid;
    //订单号
    private String depositBignumber;
    //存款对应卡号
    private String depositBigcardid;
    //转入金额
    private Double depositBigmoney;
    //利率
    private Double depositBiglv;
    //大额存款业务名
    private Integer depositBigbustype;
    //存入时间
    private Date depositBigintime;
    //存的时长
    private Integer depositBiglong;
    //到期时间
    private Date depositBigouttime;
    
    private Integer depositBiguserid;
    //订单状态
    private Integer depositBigstate;


    public Integer getDepositBigid() {
        return depositBigid;
    }

    public void setDepositBigid(Integer depositBigid) {
        this.depositBigid = depositBigid;
    }

    public String getDepositBignumber() {
        return depositBignumber;
    }

    public void setDepositBignumber(String depositBignumber) {
        this.depositBignumber = depositBignumber;
    }

    public String getDepositBigcardid() {
        return depositBigcardid;
    }

    public void setDepositBigcardid(String depositBigcardid) {
        this.depositBigcardid = depositBigcardid;
    }

    public Double getDepositBigmoney() {
        return depositBigmoney;
    }

    public void setDepositBigmoney(Double depositBigmoney) {
        this.depositBigmoney = depositBigmoney;
    }

    public Double getDepositBiglv() {
        return depositBiglv;
    }

    public void setDepositBiglv(Double depositBiglv) {
        this.depositBiglv = depositBiglv;
    }

    public Integer getDepositBigbustype() {
        return depositBigbustype;
    }

    public void setDepositBigbustype(Integer depositBigbustype) {
        this.depositBigbustype = depositBigbustype;
    }

    public Date getDepositBigintime() {
        return depositBigintime;
    }

    public void setDepositBigintime(Date depositBigintime) {
        this.depositBigintime = depositBigintime;
    }

    public Integer getDepositBiglong() {
        return depositBiglong;
    }

    public void setDepositBiglong(Integer depositBiglong) {
        this.depositBiglong = depositBiglong;
    }

    public Date getDepositBigouttime() {
        return depositBigouttime;
    }

    public void setDepositBigouttime(Date depositBigouttime) {
        this.depositBigouttime = depositBigouttime;
    }

    public Integer getDepositBiguserid() {
        return depositBiguserid;
    }

    public void setDepositBiguserid(Integer depositBiguserid) {
        this.depositBiguserid = depositBiguserid;
    }

    public Integer getDepositBigstate() {
        return depositBigstate;
    }

    public void setDepositBigstate(Integer depositBigstate) {
        this.depositBigstate = depositBigstate;
    }

}