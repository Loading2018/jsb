package com.cloud.jsproducerremittance.controller;

import com.cloud.jsproducerremittance.entity.Remittancetransaction;
import com.cloud.jsproducerremittance.entity.Remittansel;
import com.cloud.jsproducerremittance.entity.Remittanvalue;
import com.cloud.jsproducerremittance.service.RemittancetransactionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Remittancetransaction)表控制层
 *
 * @author makejava
 * @since 2018-12-24 16:21:31
 */
@RestController
public class RemittancetransactionController {
    /**
     * 服务对象
     */
    @Resource
    private RemittancetransactionService remittancetransactionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Remittancetransaction selectOne(Integer id) {
        return this.remittancetransactionService.queryById(id);
    }

    /**
     * 添加汇款信息
     * @param remittancetransaction
     * @return
     */
    @RequestMapping(value = "/addremittan",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String addremittan(Remittancetransaction remittancetransaction){
        return remittancetransactionService.insert(remittancetransaction);
    }
    /**
     * 验证码通过开始汇款操作
     * @param remittanvalue
     * @return
     */
    @RequestMapping(value = "/SMSaddremittan",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String SMSinsertremittan(Remittanvalue remittanvalue) {
        return remittancetransactionService.sendSMS(remittanvalue);
    }

    /**
     * 单笔汇款明细查询
     * @param remittansel
     * @return
     */
    @RequestMapping(value = "/getRemitInfo",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String Singlerem(Remittansel remittansel){
        return remittancetransactionService.selALL(remittansel);
    }

    /**
     * 根据明细ID查询详细信息
     * @param remid
     * @return
     */
    @RequestMapping(value = "/getRemitInfoByID",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String selonerem(Integer remid){
        return remittancetransactionService.selone(remid);
    }
}