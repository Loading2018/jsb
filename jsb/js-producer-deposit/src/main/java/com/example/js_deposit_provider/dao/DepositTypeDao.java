package com.example.js_deposit_provider.dao;

import com.example.js_deposit_provider.entity.DepositType;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (DepositType)表数据库访问层
 *
 * @author makejava
 * @since 2018-12-19 14:45:34
 */
public interface DepositTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param depositTypeid 主键
     * @return 实例对象
     */
    DepositType queryById(Integer depositTypeid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DepositType> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param depositType 实例对象
     * @return 对象列表
     */
    List<DepositType> queryAll(DepositType depositType);

    /**
     * 新增数据
     *
     * @param depositType 实例对象
     * @return 影响行数
     */
    int insert(DepositType depositType);

    /**
     * 修改数据
     *
     * @param depositType 实例对象
     * @return 影响行数
     */
    int update(DepositType depositType);

    /**
     * 通过主键删除数据
     *
     * @param depositTypeid 主键
     * @return 影响行数
     */
    int deleteById(Integer depositTypeid);

}