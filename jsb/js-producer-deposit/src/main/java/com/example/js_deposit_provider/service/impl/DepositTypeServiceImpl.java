package com.example.js_deposit_provider.service.impl;

import com.example.js_deposit_provider.entity.DepositType;
import com.example.js_deposit_provider.dao.DepositTypeDao;
import com.example.js_deposit_provider.service.DepositTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DepositType)表服务实现类
 *
 * @author makejava
 * @since 2018-12-19 14:45:34
 */
@Service("depositTypeService")
public class DepositTypeServiceImpl implements DepositTypeService {
    @Resource
    private DepositTypeDao depositTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param depositTypeid 主键
     * @return 实例对象
     */
    @Override
    public DepositType queryById(Integer depositTypeid) {
        return this.depositTypeDao.queryById(depositTypeid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<DepositType> queryAllByLimit(int offset, int limit) {
        return this.depositTypeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param depositType 实例对象
     * @return 实例对象
     */
    @Override
    public DepositType insert(DepositType depositType) {
        this.depositTypeDao.insert(depositType);
        return depositType;
    }

    /**
     * 修改数据
     *
     * @param depositType 实例对象
     * @return 实例对象
     */
    @Override
    public DepositType update(DepositType depositType) {
        this.depositTypeDao.update(depositType);
        return this.queryById(depositType.getDepositTypeid());
    }

    /**
     * 通过主键删除数据
     *
     * @param depositTypeid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer depositTypeid) {
        return this.depositTypeDao.deleteById(depositTypeid) > 0;
    }
}