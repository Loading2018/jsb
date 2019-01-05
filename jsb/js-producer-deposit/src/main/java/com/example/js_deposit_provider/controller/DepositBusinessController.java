package com.example.js_deposit_provider.controller;

import com.example.js_deposit_provider.entity.DepositBusiness;
import com.example.js_deposit_provider.service.DepositBusinessService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (DepositBusiness)表控制层
 *
 * @author makejava
 * @since 2018-12-19 14:45:32
 */
@RestController
public class DepositBusinessController {
    /**
     * 服务对象
     */
    @Resource
    private DepositBusinessService depositBusinessService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    public DepositBusiness selectOne(Integer id) {
        return this.depositBusinessService.queryById(id);
    }

    /**
     * 查询所有存款业务信息并放入redis
     */
    @RequestMapping(value = "getAllBusinessInfo",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public void getAllBusinessInfo(){
        depositBusinessService.getAllBusinessInfo();
    }

    /**
     * 根据类型ID获得业务信息
     * @param typeID
     * @return
     */
    @RequestMapping(value = "getBusinessInfoByType",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String getBusinessInfoByType(@RequestParam("typeID")String typeID){
        return depositBusinessService.getInfoByTypeId(typeID);
    }
}