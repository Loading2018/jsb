package com.example.js_deposit_provider.service;

import com.example.js_deposit_provider.entity.DepositWithdrawal;
import java.util.List;

/**
 * (DepositWithdrawal)表服务接口
 *
 * @author makejava
 * @since 2018-12-19 14:45:34
 */
public interface DepositWithdrawalService {

    /**
     * 通过ID查询单条数据
     *
     * @param depositWithdrawalid 主键
     * @return 实例对象
     */
    DepositWithdrawal queryById(Integer depositWithdrawalid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DepositWithdrawal> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param depositWithdrawal 实例对象
     * @return 实例对象
     */
    DepositWithdrawal insert(DepositWithdrawal depositWithdrawal);

    /**
     * 修改数据
     *
     * @param depositWithdrawal 实例对象
     * @return 实例对象
     */
    DepositWithdrawal update(DepositWithdrawal depositWithdrawal);

    /**
     * 通过主键删除数据
     *
     * @param depositWithdrawalid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer depositWithdrawalid);

}