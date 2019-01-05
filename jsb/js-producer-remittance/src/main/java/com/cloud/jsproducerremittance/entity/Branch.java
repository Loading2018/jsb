package com.cloud.jsproducerremittance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Branch)实体类
 *
 * @author makejava
 * @since 2018-11-10 08:52:16
 */
@Entity
@Table(name = "BRANCH")
public class Branch implements Serializable {

    //网点ID
    @Id
    @GeneratedValue
    private Integer branchid;
    //网点名称
    private String branchname;
    //网点地址
    private String branchsite;
    //受理业务的时间段  {"one":"00:00 - 11:11","two":"14:30 - 15:30"}
    private String branchdata;
    //经度
    private String branchlongitude;
    //维度
    private String branchdimensionality;


    public Integer getBranchid() {
        return branchid;
    }

    public void setBranchid(Integer branchid) {
        this.branchid = branchid;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getBranchsite() {
        return branchsite;
    }

    public void setBranchsite(String branchsite) {
        this.branchsite = branchsite;
    }

    public String getBranchdata() {
        return branchdata;
    }

    public void setBranchdata(String branchdata) {
        this.branchdata = branchdata;
    }

    public String getBranchlongitude() {
        return branchlongitude;
    }

    public void setBranchlongitude(String branchlongitude) {
        this.branchlongitude = branchlongitude;
    }

    public String getBranchdimensionality() {
        return branchdimensionality;
    }

    public void setBranchdimensionality(String branchdimensionality) {
        this.branchdimensionality = branchdimensionality;
    }
}