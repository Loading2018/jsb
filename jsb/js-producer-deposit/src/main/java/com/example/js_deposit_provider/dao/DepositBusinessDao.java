package com.example.js_deposit_provider.dao;

import com.example.js_deposit_provider.entity.DepositBusiness;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (DepositBusiness)表数据库访问层
 *
 * @author makejava
 * @since 2018-12-19 14:45:32
 */
public interface DepositBusinessDao {

    /**
     * 通过ID查询单条数据
     *
     * @param depositBusinessid 主键
     * @return 实例对象
     */
    DepositBusiness queryById(Integer depositBusinessid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DepositBusiness> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param depositBusiness 实例对象
     * @return 对象列表
     */
    List<DepositBusiness> queryAll(DepositBusiness depositBusiness);

    /**
     * 新增数据
     *
     * @param depositBusiness 实例对象
     * @return 影响行数
     */
    int insert(DepositBusiness depositBusiness);

    /**
     * 修改数据
     *
     * @param depositBusiness 实例对象
     * @return 影响行数
     */
    int update(DepositBusiness depositBusiness);

    /**
     * 通过主键删除数据
     *
     * @param depositBusinessid 主键
     * @return 影响行数
     */
    int deleteById(Integer depositBusinessid);

    /**
     * 查询全部存款业务数据
     * @return
     */
    List<DepositBusiness> getAllBussinessInfo();

    /**
     * 根据业务ID查业务信息
     * @param depositid
     * @return
     */
    DepositBusiness getInfoByDepositBusinessID(int depositid);

    /**
     * 根据业务ID查业务利率
     * @param depositid
     * @return
     */
    DepositBusiness getLvByDepositBusinessID(int depositid);

    /**
     * 根据业务ID修改业务利率
     * @param depositid
     * @return
     */
    DepositBusiness updateLvByDepositBusinessID(int depositid);
}