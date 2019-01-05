package com.example.js_deposit_provider.controller;

import com.example.js_deposit_provider.entity.DepositFixation;
import com.example.js_deposit_provider.service.DepositFixationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (DepositFixation)表控制层
 *
 * @author makejava
 * @since 2018-12-19 14:45:33
 */
@RestController
public class DepositFixationController {
    /**
     * 服务对象
     */
    @Resource
    private DepositFixationService depositFixationService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    public DepositFixation selectOne(Integer id) {
        return this.depositFixationService.queryById(id);
    }

    /**
     * 增加定期存款订单
     * @param depositFixation
     * @return
     */
    @RequestMapping(value = "addFixOrder",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String addFixInfo(@RequestBody DepositFixation depositFixation){
        return depositFixationService.insert(depositFixation);
    }

    /**
     * 根据用户ID查所有定期存款信息
     * @param userid
     * @return
     */
    @RequestMapping(value = "getFixInfoByID",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String getFixInfoByID(@RequestParam("userid") String userid,@RequestParam("index") String index){
        return depositFixationService.getAllFixInfoByID(userid,index);
    }

    /**
     * 根据订单ID取款
     * @param infoId
     * @param money
     * @return
     */
    @RequestMapping(value = "getWithdrawal",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    public String getWithdrawal(@RequestParam("infoId") String infoId,@RequestParam("money") String money){
        return depositFixationService.getWithdrawal(infoId,money);
    }

    /**
     * 取钱
     * @param userid
     * @param businessid
     * @return
     */
    @RequestMapping(value = "getOutFixMoney",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String getOutFixMoney(@RequestParam("userid") String userid,@RequestParam("businessid") String businessid){
        System.out.println(userid);
        System.out.println(businessid);
        return depositFixationService.getOutMoney(userid, businessid);
    }
}