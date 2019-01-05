package com.cloud.jsproducerremittance.service;

import com.cloud.jsproducerremittance.entity.Batch;
import com.cloud.jsproducerremittance.entity.Batchvalue;

import java.util.List;

/**
 * (Batch)表服务接口
 *
 * @author makejava
 * @since 2018-12-24 16:19:48
 */
public interface BatchService {

    /**
     * 通过ID查询单条数据
     *
     * @param batchId 主键
     * @return 实例对象
     */
    String queryById(Integer batchId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Batch> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param batch 实例对象
     * @return 实例对象
     */
    String insert(String batch);

    /**
     * 修改数据
     *
     * @param batch 实例对象
     * @return 实例对象
     */
    Batch update(Batch batch);

    /**
     * 通过主键删除数据
     *
     * @param batchId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer batchId);

    /**
     * 验证输入是银行卡号
     * @param number
     * @return
     */
    String verifycard(String number);

    /**
     * 根据卡号和时间段查询批量明细
     * @param ba
     * @return
     */
    String queryAll(Batchvalue ba);

}