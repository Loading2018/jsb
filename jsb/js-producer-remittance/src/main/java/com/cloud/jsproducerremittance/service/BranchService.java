package com.cloud.jsproducerremittance.service;

import com.cloud.jsproducerremittance.entity.Branch;
import java.util.List;

/**
 * (Branch)表服务接口
 *
 * @author makejava
 * @since 2018-12-24 16:20:22
 */
public interface BranchService {

    /**
     * 通过ID查询单条数据
     *
     * @param branchId 主键
     * @return 实例对象
     */
    Branch queryById(Integer branchId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Branch> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param branch 实例对象
     * @return 实例对象
     */
    Branch insert(Branch branch);

    /**
     * 修改数据
     *
     * @param branch 实例对象
     * @return 实例对象
     */
    Branch update(Branch branch);

    /**
     * 通过主键删除数据
     *
     * @param branchId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer branchId);

}