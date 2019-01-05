package com.example.js_deposit_provider.controller;

import com.example.js_deposit_provider.entity.DepositType;
import com.example.js_deposit_provider.service.DepositTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (DepositType)表控制层
 *
 * @author makejava
 * @since 2018-12-19 14:45:34
 */
@RestController
@RequestMapping("depositType")
public class DepositTypeController {
    /**
     * 服务对象
     */
    @Resource
    private DepositTypeService depositTypeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    public DepositType selectOne(Integer id) {
        return this.depositTypeService.queryById(id);
    }

}