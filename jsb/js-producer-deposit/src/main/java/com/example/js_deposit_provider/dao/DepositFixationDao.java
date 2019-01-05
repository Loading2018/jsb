package com.example.js_deposit_provider.dao;

import com.example.js_deposit_provider.entity.DepositFixation;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (DepositFixation)表数据库访问层
 *
 * @author makejava
 * @since 2018-12-19 14:45:33
 */
public interface DepositFixationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param depositFixationid 主键
     * @return 实例对象
     */
    DepositFixation queryById(Integer depositFixationid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DepositFixation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param depositFixation 实例对象
     * @return 对象列表
     */
    List<DepositFixation> queryAll(DepositFixation depositFixation);

    /**
     * 新增数据
     *
     * @param depositFixation 实例对象
     * @return 影响行数
     */
    int insert(DepositFixation depositFixation);

    /**
     * 修改数据
     *
     * @param depositFixation 实例对象
     * @return 影响行数
     */
    int update(DepositFixation depositFixation);

    /**
     * 通过主键删除数据
     *
     * @param depositFixationid 主键
     * @return 影响行数
     */
    int deleteById(Integer depositFixationid);

    /**
     * 根据用户ID查询定期存款订单
     * @param userid
     * @return
     */
    List<DepositFixation> getAllFixInfoByID(Integer userid);

    /**
     * 根据订单ID取款
     * @param infoid
     * @param money
     * @return
     */
    int getWithdrawal(@Param("infoid")int infoid, @Param("money")int money);

    /**
     * 根据订单ID查询订单余额
     * @param infoid
     * @return
     */
    DepositFixation getRemainMoney(@Param("informid")int infoid);

    /**
     * 根据订单ID修改订单状态
     * @param infoid
     * @return
     */
    int changOrderState(int infoid);

    /**
     * 根据日期获取订单信息
     * @param time
     * @return
     */
    List<DepositFixation> getAllFixInfoByDay(String time);

    /**
     * 修改状态
     * @param businessId
     * @return
     */
    int updateOrderState(@Param("businessId")int businessId);
}