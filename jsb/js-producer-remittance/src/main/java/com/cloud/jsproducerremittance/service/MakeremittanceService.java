package com.cloud.jsproducerremittance.service;

import com.cloud.jsproducerremittance.entity.Makeremittance;
import java.util.List;

/**
 * (Makeremittance)表服务接口
 *
 * @author makejava
 * @since 2018-12-24 16:20:37
 */
public interface MakeremittanceService {


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Makeremittance> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param makeremittance 实例对象
     * @return 实例对象
     */
    String insert(Makeremittance makeremittance);

    /**
     * 修改数据
     *
     * @param
     * @return 实例对象
     */
    String update(String updatamaker);

    /**
     * 通过主键删除数据
     *
     * @param makeremittanceId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer makeremittanceId);

    /**
     * 通过时间 预约编号 用户ID 查询预约信息表
     * @param selmaker
     * @return
     */
    String queryAll(String selmaker);

    /**
     * 根据预约编号 和用户ID
     * @param selonemaker
     * @return
     */
    String selonemaker(String selonemaker);

}