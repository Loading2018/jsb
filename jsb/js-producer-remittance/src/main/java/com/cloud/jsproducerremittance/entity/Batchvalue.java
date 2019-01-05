package com.cloud.jsproducerremittance.entity;

public class Batchvalue {

    private String batchNumber;
    private String oneTime;
    private String twoTime;
    private Integer type;// 0付款 or 1收款
    private Integer index;
    private Integer pageSize;

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }


    public String getOneTime() {
        return oneTime;
    }

    public void setOneTime(String oneTime) {
        this.oneTime = oneTime;
    }

    public String getTwoTime() {
        return twoTime;
    }

    public void setTwoTime(String twoTime) {
        this.twoTime = twoTime;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
