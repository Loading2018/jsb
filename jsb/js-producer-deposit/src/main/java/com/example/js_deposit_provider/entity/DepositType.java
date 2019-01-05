package com.example.js_deposit_provider.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * (DepositType)实体类
 *
 * @author makejava
 * @since 2018-12-19 14:45:34
 */
@Component
public class DepositType implements Serializable {
    private static final long serialVersionUID = 330560138481485225L;
    //存款类型id
    private Integer depositTypeid;
    //存款类型名字
    private String depositTypename;


    public Integer getDepositTypeid() {
        return depositTypeid;
    }

    public void setDepositTypeid(Integer depositTypeid) {
        this.depositTypeid = depositTypeid;
    }

    public String getDepositTypename() {
        return depositTypename;
    }

    public void setDepositTypename(String depositTypename) {
        this.depositTypename = depositTypename;
    }

}