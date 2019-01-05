package com.cloud.jsproducerremittance.entity;

import java.io.Serializable;

/**
 * (Branch)实体类
 *
 * @author makejava
 * @since 2018-12-24 16:20:22
 */
public class Branch implements Serializable {
    private static final long serialVersionUID = -24574103226661531L;
    //网点ID
    private Integer branchId;
    //网点名称
    private String branchName;
    //网点地址
    private String branchSite;
    //网点电话
    private String branchPhone;
    //1 3 5 7受理业务的时间段  {"one":"09:30 - 12:00","two":"14:30 - 16:30"}
    private String branchData;
    //2 4 6受理业务的时间段  {"one":"09:00 - 10:30","two":"15:30 - 17:00"}
    private String branchDatatwo;
    //网点可办理业务1:预约人民币大额提现
    private Integer branchBusinessone;
    //网点可办理业务1:外币现金取款
    private Integer branchBusinesstwo;
    //经度
    private String branchLongitude;
    //维度
    private String branchDimensionality;


    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchSite() {
        return branchSite;
    }

    public void setBranchSite(String branchSite) {
        this.branchSite = branchSite;
    }

    public String getBranchPhone() {
        return branchPhone;
    }

    public void setBranchPhone(String branchPhone) {
        this.branchPhone = branchPhone;
    }

    public String getBranchData() {
        return branchData;
    }

    public void setBranchData(String branchData) {
        this.branchData = branchData;
    }

    public String getBranchDatatwo() {
        return branchDatatwo;
    }

    public void setBranchDatatwo(String branchDatatwo) {
        this.branchDatatwo = branchDatatwo;
    }

    public Integer getBranchBusinessone() {
        return branchBusinessone;
    }

    public void setBranchBusinessone(Integer branchBusinessone) {
        this.branchBusinessone = branchBusinessone;
    }

    public Integer getBranchBusinesstwo() {
        return branchBusinesstwo;
    }

    public void setBranchBusinesstwo(Integer branchBusinesstwo) {
        this.branchBusinesstwo = branchBusinesstwo;
    }

    public String getBranchLongitude() {
        return branchLongitude;
    }

    public void setBranchLongitude(String branchLongitude) {
        this.branchLongitude = branchLongitude;
    }

    public String getBranchDimensionality() {
        return branchDimensionality;
    }

    public void setBranchDimensionality(String branchDimensionality) {
        this.branchDimensionality = branchDimensionality;
    }

}