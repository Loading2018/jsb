package com.example.js_deposit_provider.service;

import com.example.js_deposit_provider.entity.DepositBig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (DepositBig)表服务接口
 *
 * @author makejava
 * @since 2018-12-19 14:44:58
 */
public interface DepositBigService {

    /**
     * 通过ID查询单条数据
     *
     * @param depositBigid 主键
     * @return 实例对象
     */
    DepositBig queryById(Integer depositBigid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DepositBig> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param depositBig 实例对象
     * @return 实例对象
     */
    String insert(DepositBig depositBig);

    /**
     * 修改数据
     *
     * @param depositBig 实例对象
     * @return 实例对象
     */
    DepositBig update(DepositBig depositBig);

    /**
     * 通过主键删除数据
     *
     * @param depositBigid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer depositBigid);

    /**
     * 根据订单ID取款
     * @param bigid
     * @param bigmoney
     * @return
     */
    String getBigWithdrawal(@Param("bigid")String bigid, @Param("bigmoney")String bigmoney);

    /**
     * 根据用户ID查询定期存款订单
     * @param userid
     * @return
     */
    String getAllBigInfoByID(String userid,String index);

    /**
     * 取钱
     * @param userid
     * @param infoid 订单ID
     * @return
     */
    String getOutMoney(String userid,String infoid);

}