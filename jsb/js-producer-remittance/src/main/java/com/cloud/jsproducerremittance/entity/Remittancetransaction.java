package com.cloud.jsproducerremittance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Remittancetransaction)实体类
 *
 * @author makejava
 * @since 2018-11-10 08:51:20
 */
@Entity
@Table(name = "REMITTANCETRANSACTION")
public class RemittanceTransaction implements Serializable {

    //汇款交易记录ID
    @Id
    @GeneratedValue
    private int remittancetransactionid = 0;
    //收款人姓名
    private String remittancetransactionname;
    //收款卡号
    private String remittancetransactioncardnumber;
    //收款银行1:中行2:建行3:工行4:农行5:交行6:民行7:招行8:邮行9:浦发10: 中信
    private Integer remittancetransactionblank;
    //汇款金额
    private Double remittancetransactionprice;
    //当前时间 (汇款时间)年月日
    private Integer remittancetransactiontime;
    //汇款时间1：实时汇款2：普通汇款3：次日汇款
    private String remittancetransactioncurrent;
    //付款卡号
    private String remittancetransactionnumber;
    //汇款人ID
    private Integer remittancetransactionpayuserid;
    //付款人电话
    private String phone;

    //手续费
    private String remittancetransactionprocedure;
    //状态
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public int getRemittancetransactionid() {
        return remittancetransactionid;
    }

    public void setRemittancetransactionid(int remittancetransactionid) {
        this.remittancetransactionid = remittancetransactionid;
    }

    public String getRemittancetransactionname() {
        return remittancetransactionname;
    }

    public void setRemittancetransactionname(String remittancetransactionname) {
        this.remittancetransactionname = remittancetransactionname;
    }

    public String getRemittancetransactioncardnumber() {
        return remittancetransactioncardnumber;
    }

    public void setRemittancetransactioncardnumber(String remittancetransactioncardnumber) {
        this.remittancetransactioncardnumber = remittancetransactioncardnumber;
    }

    public Integer getRemittancetransactionblank() {
        return remittancetransactionblank;
    }

    public void setRemittancetransactionblank(Integer remittancetransactionblank) {
        this.remittancetransactionblank = remittancetransactionblank;
    }

    public Double getRemittancetransactionprice() {
        return remittancetransactionprice;
    }

    public void setRemittancetransactionprice(Double remittancetransactionprice) {
        this.remittancetransactionprice = remittancetransactionprice;
    }

    public Integer getRemittancetransactiontime() {
        return remittancetransactiontime;
    }

    public void setRemittancetransactiontime(Integer remittancetransactiontime) {
        this.remittancetransactiontime = remittancetransactiontime;
    }

    public String getRemittancetransactioncurrent() {
        return remittancetransactioncurrent;
    }

    public void setRemittancetransactioncurrent(String remittancetransactioncurrent) {
        this.remittancetransactioncurrent = remittancetransactioncurrent;
    }

    public String getRemittancetransactionnumber() {
        return remittancetransactionnumber;
    }

    public void setRemittancetransactionnumber(String remittancetransactionnumber) {
        this.remittancetransactionnumber = remittancetransactionnumber;
    }

    public Integer getRemittancetransactionpayuserid() {
        return remittancetransactionpayuserid;
    }

    public void setRemittancetransactionpayuserid(Integer remittancetransactionpayuserid) {
        this.remittancetransactionpayuserid = remittancetransactionpayuserid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemittancetransactionprocedure() {
        return remittancetransactionprocedure;
    }

    public void setRemittancetransactionprocedure(String remittancetransactionprocedure) {
        this.remittancetransactionprocedure = remittancetransactionprocedure;
    }
}