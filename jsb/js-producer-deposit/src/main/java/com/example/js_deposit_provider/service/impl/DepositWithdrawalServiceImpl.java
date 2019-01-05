package com.example.js_deposit_provider.service.impl;

import com.example.js_deposit_provider.entity.DepositWithdrawal;
import com.example.js_deposit_provider.dao.DepositWithdrawalDao;
import com.example.js_deposit_provider.service.DepositWithdrawalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DepositWithdrawal)表服务实现类
 *
 * @author makejava
 * @since 2018-12-19 14:45:34
 */
@Service("depositWithdrawalService")
public class DepositWithdrawalServiceImpl implements DepositWithdrawalService {
    @Resource
    private DepositWithdrawalDao depositWithdrawalDao;

    /**
     * 通过ID查询单条数据
     *
     * @param depositWithdrawalid 主键
     * @return 实例对象
     */
    @Override
    public DepositWithdrawal queryById(Integer depositWithdrawalid) {
        return this.depositWithdrawalDao.queryById(depositWithdrawalid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<DepositWithdrawal> queryAllByLimit(int offset, int limit) {
        return this.depositWithdrawalDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param depositWithdrawal 实例对象
     * @return 实例对象
     */
    @Override
    public DepositWithdrawal insert(DepositWithdrawal depositWithdrawal) {
        this.depositWithdrawalDao.insert(depositWithdrawal);
        return depositWithdrawal;
    }

    /**
     * 修改数据
     *
     * @param depositWithdrawal 实例对象
     * @return 实例对象
     */
    @Override
    public DepositWithdrawal update(DepositWithdrawal depositWithdrawal) {
        this.depositWithdrawalDao.update(depositWithdrawal);
        return this.queryById(depositWithdrawal.getDepositWithdrawalid());
    }

    /**
     * 通过主键删除数据
     *
     * @param depositWithdrawalid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer depositWithdrawalid) {
        return this.depositWithdrawalDao.deleteById(depositWithdrawalid) > 0;
    }
}