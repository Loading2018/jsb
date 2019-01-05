package com.example.js_deposit_provider.service;

import com.example.js_deposit_provider.entity.DepositBusiness;
import java.util.List;

/**
 * (DepositBusiness)表服务接口
 *
 * @author makejava
 * @since 2018-12-19 14:45:32
 */
public interface DepositBusinessService {

    /**
     * 通过ID查询单条数据
     *
     * @param depositBusinessid 主键
     * @return 实例对象
     */
    DepositBusiness queryById(Integer depositBusinessid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DepositBusiness> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param depositBusiness 实例对象
     * @return 实例对象
     */
    DepositBusiness insert(DepositBusiness depositBusiness);

    /**
     * 修改数据
     *
     * @param depositBusiness 实例对象
     * @return 实例对象
     */
    DepositBusiness update(DepositBusiness depositBusiness);

    /**
     * 通过主键删除数据
     *
     * @param depositBusinessid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer depositBusinessid);

    /**
     * 查询所有存款业务信息并放入redis
     */
    void getAllBusinessInfo();

    /**
     * 根据类型ID获得业务信息
     * @param TypeID
     * @return
     */
    String getInfoByTypeId(String TypeID);

    /**
     * 根据业务ID获取业务信息
     * @param DepositID
     * @return
     */
    String getInfoByDepositID(String DepositID);

    /**
     * 根据业务ID获取业务利率
     * @param DepositID
     * @return
     */
    Double getLvByDepositID(String DepositID);

    /**
     * 根据业务ID修改业务利率
     * @param DepositID
     * @return
     */
    String updateLvByDepositID(String DepositID);
}