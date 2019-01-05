package com.cloud.jsproducerremittance.dao;

import com.cloud.jsproducerremittance.entity.Remittancetransaction;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Remittancetransaction)表数据库访问层
 *
 * @author makejava
 * @since 2018-12-24 16:21:31
 */
public interface RemittancetransactionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param remittancetransactionId 主键
     * @return 实例对象
     */
    Remittancetransaction queryById(Integer remittancetransactionId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Remittancetransaction> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param
     * @return 对象列表
     */
    List<Remittancetransaction> queryAll(@Param("Cardnumber") String Cardnumber,@Param("Number") String Number,@Param("onetime") String onetime,@Param("twotime") String twotime);

    /**
     * 新增数据
     *
     * @param remittancetransaction 实例对象
     * @return 影响行数
     */
    int insert(Remittancetransaction remittancetransaction);

    /**
     * 修改数据
     *
     * @param remittancetransaction 实例对象
     * @return 影响行数
     */
    int update(Remittancetransaction remittancetransaction);

    /**
     * 通过主键删除数据
     *
     * @param remittancetransactionId 主键
     * @return 影响行数
     */
    int deleteById(Integer remittancetransactionId);

    /**
     * 根据明细ID查询详细信息
     * @param id
     * @return
     */
    Remittancetransaction getRMInfoByID(@Param("id") Integer id);
}