package com.example.js_deposit_provider.dao;

import com.example.js_deposit_provider.entity.DepositWithdrawal;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (DepositWithdrawal)表数据库访问层
 *
 * @author makejava
 * @since 2018-12-19 14:45:34
 */
public interface DepositWithdrawalDao {

    /**
     * 通过ID查询单条数据
     *
     * @param depositWithdrawalid 主键
     * @return 实例对象
     */
    DepositWithdrawal queryById(Integer depositWithdrawalid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DepositWithdrawal> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param depositWithdrawal 实例对象
     * @return 对象列表
     */
    List<DepositWithdrawal> queryAll(DepositWithdrawal depositWithdrawal);

    /**
     * 新增数据
     *
     * @param depositWithdrawal 实例对象
     * @return 影响行数
     */
    int insert(DepositWithdrawal depositWithdrawal);

    /**
     * 修改数据
     *
     * @param depositWithdrawal 实例对象
     * @return 影响行数
     */
    int update(DepositWithdrawal depositWithdrawal);

    /**
     * 通过主键删除数据
     *
     * @param depositWithdrawalid 主键
     * @return 影响行数
     */
    int deleteById(Integer depositWithdrawalid);

    /**
     * 通过用户id进行取款查询
     * @param userid
     * @return
     */
    List<DepositWithdrawal> getWithdrawalInfoByUserId(int userid);

    /**
     * 查询所有取款信息
     * @return
     */
    List<DepositWithdrawal> getAllWithdrawalInfo();

    /**
     * 通过取款记录id修改取款订单状态
     * @param type
     * @param withdrawalid
     * @return
     */
    int updateWithdrawalByID(int type,int withdrawalid);

}