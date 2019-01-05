package com.cloud.jsproducerremittance.controller;

import com.cloud.jsproducerremittance.entity.Makeremittance;
import com.cloud.jsproducerremittance.service.MakeremittanceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Makeremittance)表控制层
 *
 * @author makejava
 * @since 2018-12-24 16:20:37
 */
@RestController
public class MakeremittanceController {
    /**
     * 服务对象
     */
    @Resource
    private MakeremittanceService makeremittanceService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */

//    public Makeremittance selectOne(Integer id) {
//        return this.makeremittanceService.queryByIdd(id);
//    }

}