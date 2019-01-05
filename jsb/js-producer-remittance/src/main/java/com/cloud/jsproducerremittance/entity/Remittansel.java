package com.cloud.jsproducerremittance.entity;

import java.io.Serializable;

public class Remittansel implements Serializable {

    private String Cardnumber;//卡号
    private String onetime;//开始时间
    private String twotime;//结束时间
    private Integer type;// 0付款 or 1收款
    private Integer index;
    private Integer pageSize;

    public String getCardnumber() {
        return Cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        Cardnumber = cardnumber;
    }

    public String getOnetime() {
        return onetime;
    }

    public void setOnetime(String onetime) {
        this.onetime = onetime;
    }

    public String getTwotime() {
        return twotime;
    }

    public void setTwotime(String twotime) {
        this.twotime = twotime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
