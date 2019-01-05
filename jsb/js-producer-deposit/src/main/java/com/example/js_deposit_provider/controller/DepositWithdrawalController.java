package com.example.js_deposit_provider.controller;

import com.example.js_deposit_provider.entity.DepositWithdrawal;
import com.example.js_deposit_provider.service.DepositWithdrawalService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (DepositWithdrawal)表控制层
 *
 * @author makejava
 * @since 2018-12-19 14:45:35
 */
@RestController
@RequestMapping("depositWithdrawal")
public class DepositWithdrawalController {
    /**
     * 服务对象
     */
    @Resource
    private DepositWithdrawalService depositWithdrawalService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    public DepositWithdrawal selectOne(Integer id) {
        return this.depositWithdrawalService.queryById(id);
    }

}