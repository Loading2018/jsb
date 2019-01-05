package com.example.js_deposit_provider.controller;

import com.example.js_deposit_provider.entity.DepositBig;
import com.example.js_deposit_provider.service.DepositBigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (DepositBig)表控制层
 *
 * @author makejava
 * @since 2018-12-19 14:44:59
 */
@RestController
public class DepositBigController {
    /**
     * 服务对象
     */
    @Resource
    private DepositBigService depositBigService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    public DepositBig selectOne(Integer id) {
        return this.depositBigService.queryById(id);
    }

    /**
     * 添加大额存款订单
     * @param depositBig
     * @return
     */
    @RequestMapping(value = "addbigOrder",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public  String addBiginform(@RequestBody DepositBig depositBig){
        System.out.println(depositBig.getDepositBigmoney());
        return depositBigService.insert(depositBig);
    }

    /**
     * 查看用户的所有大额订单信息
     * @param userid
     * @return
     */
    @RequestMapping(value = "getBigInfoByUserid",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String getInformById(@RequestParam("userid") String userid,@RequestParam("index")String index){
        return depositBigService.getAllBigInfoByID(userid,index);
    }

    /**
     * 取款
     * @param bigid
     * @param bigmoney
     * @return
     */
    @RequestMapping(value = "getBigWithdrawal",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    public String getmoney(@RequestParam("bigid") String bigid ,@RequestParam("bigmoney") String bigmoney){
        return  depositBigService.getBigWithdrawal(bigid,bigmoney);
    }

    /**
     * 取钱
     * @param userid
     * @param businessid
     * @return
     */
    @RequestMapping(value = "getOutBigMoney",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String getOutBigMoney(@RequestParam("userid") String userid,@RequestParam("businessid") String businessid){
        System.out.println(userid);
        System.out.println(businessid);
        return depositBigService.getOutMoney(userid, businessid);
    }
}