package com.example.js_deposit_provider.service;

import com.example.js_deposit_provider.entity.DepositType;
import java.util.List;

/**
 * (DepositType)表服务接口
 *
 * @author makejava
 * @since 2018-12-19 14:45:34
 */
public interface DepositTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param depositTypeid 主键
     * @return 实例对象
     */
    DepositType queryById(Integer depositTypeid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DepositType> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param depositType 实例对象
     * @return 实例对象
     */
    DepositType insert(DepositType depositType);

    /**
     * 修改数据
     *
     * @param depositType 实例对象
     * @return 实例对象
     */
    DepositType update(DepositType depositType);

    /**
     * 通过主键删除数据
     *
     * @param depositTypeid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer depositTypeid);

}