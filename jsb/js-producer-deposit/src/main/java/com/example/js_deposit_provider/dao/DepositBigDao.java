package com.example.js_deposit_provider.dao;

import com.example.js_deposit_provider.entity.DepositBig;
import com.example.js_deposit_provider.entity.DepositFixation;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (DepositBig)表数据库访问层
 *
 * @author makejava
 * @since 2018-12-19 14:44:58
 */
public interface DepositBigDao {

    /**
     * 通过ID查询单条数据
     *
     * @param depositBigid 主键
     * @return 实例对象
     */
    DepositBig queryById(Integer depositBigid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DepositBig> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param depositBig 实例对象
     * @return 对象列表
     */
    List<DepositBig> queryAll(DepositBig depositBig);

    /**
     * 新增数据
     *
     * @param depositBig 实例对象
     * @return 影响行数
     */
    int insert(DepositBig depositBig);

    /**
     * 修改数据
     *
     * @param depositBig 实例对象
     * @return 影响行数
     */
    int update(DepositBig depositBig);

    /**
     * 通过主键删除数据
     *
     * @param depositBigid 主键
     * @return 影响行数
     */
    int deleteById(Integer depositBigid);

    /**
     * 根据订单ID取款
     * @param bigid
     * @param bigmoney
     * @return
     */
    int getBigWithdrawal(@Param("bigid")int bigid, @Param("bigmoney")int bigmoney);

    /**
     * 根据用户ID查询定期存款订单
     * @param userid
     * @return
     */
    List<DepositBig> getAllBigInfoByID(Integer userid);

    /**
     * 根据订单ID查询订单余额
     * @param bigOrderid
     * @return
     */
    DepositBig getBigRemainMoney(@Param("bigOrderid")int bigOrderid);

    /**
     * 根据订单ID修改订单状态
     * @param biginfoid
     * @return
     */
    int changOrderState(int biginfoid);

    /**
     * 根据日期获取订单信息
     * @param time
     * @return
     */
    List<DepositBig> getAllBigInfoByDay(String time);

    /**
     * 修改状态
     * @param businessId
     * @return
     */
    int updateOrderState(@Param("businessId")int businessId);
}