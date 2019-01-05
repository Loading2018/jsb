package com.example.jsconsumerdeposit.service.impl;

import com.example.jsconsumerdeposit.pojo.DepositBig;
import com.example.jsconsumerdeposit.pojo.DepositFixation;
import com.example.jsconsumerdeposit.pojo.DepositInform;
import com.example.jsconsumerdeposit.service.DepositService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class DepositServiceImpl implements DepositService {

    @Override
    public String addBiginform(DepositBig depositBig) {
        return "访问出错，请重试！";
    }

    @Override
    public String getInformById(String userid, String index) {
        return "访问出错，请重试！";
    }


    @Override
    public String getmoney(String bigid, String bigmoney) {
        return "访问出错，请重试！";
    }

    @Override
    public void getAllBusinessInfo() {

    }

    @Override
    public String getBusinessInfoByType(String typeID) {
        return "访问出错，请重试！";
    }

    @Override
    public String addFixInfo(DepositFixation depositFixation) {
        return "访问出错，请重试！";
    }

    @Override
    public String getFixInfoByID(String userid,String index) {
        return "访问出错，请重试！";
    }

    @Override
    public String getWithdrawal(String infoId, String money) {
        return "访问出错，请重试！";
    }

    @Override
    public String addinfo(DepositInform depositInform) {
        return "访问出错，请重试！";
    }

    @Override
    public String getinformbyUserId(String userid,String index) {
        return "访问出错，请重试！";
    }

    @Override
    public String Onlywithdrawal(String informid, String money) {
        return "访问出错，请重试！";
    }

    @Override
    public String subwithdrawal(String inforid, String money, String begintime, String endtime) {
        return "访问出错，请重试！";
    }

    @Override
    public String getwithdrawal(String userid) {
        return "访问出错，请重试！";
    }

    @Override
    public String cancelSubscribe(String withdrawalId) {
        return "访问出错，请重试！";
    }

    @Override
    public String getOutMoney(String userid, String businessId) {
        return "访问出错，请重试！";
    }

    @Override
    public String getOutFixMoney(String userid, String businessid) {
        return "访问出错，请重试！";
    }

    @Override
    public String getOutBigMoney(String userid, String businessid) {
        return "访问出错，请重试！";
    }

}
