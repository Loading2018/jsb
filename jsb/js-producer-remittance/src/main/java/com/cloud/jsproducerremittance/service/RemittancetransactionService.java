package com.cloud.jsproducerremittance.service;

import com.cloud.jsproducerremittance.entity.Remittancetransaction;
import com.cloud.jsproducerremittance.entity.Remittansel;
import com.cloud.jsproducerremittance.entity.Remittanvalue;

import java.util.List;

/**
 * (Remittancetransaction)表服务接口
 *
 * @author makejava
 * @since 2018-12-24 16:21:31
 */
public interface RemittancetransactionService {

    /**
     * 通过ID查询单条数据
     *
     * @param remittancetransactionId 主键
     * @return 实例对象
     */
    Remittancetransaction queryById(Integer remittancetransactionId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Remittancetransaction> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param rm 实例对象
     * @return 实例对象
     */
    String insert(Remittancetransaction rm);

    /**
     * 修改数据
     *
     * @param remittancetransaction 实例对象
     * @return 实例对象
     */
    Remittancetransaction update(Remittancetransaction remittancetransaction);

    /**
     * 通过主键删除数据
     *
     * @param remittancetransactionId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer remittancetransactionId);

    /**
     * 汇款发送验证码  验证验证码
     * @param
     * @return
     */
    String sendSMS(Remittanvalue remittanvalue);

    /**
     * 查询单笔汇款明细
     * @param remittansel
     * @return
     */
    String selALL(Remittansel remittansel);

    /**
     * 根据明细ID查询详细信息
     * @param id
     * @return
     */
    String selone(Integer id);
}