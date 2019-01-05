package com.cloud.jsproducerremittance.dao;

import com.cloud.jsproducerremittance.entity.Makeremittance;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Makeremittance)表数据库访问层
 *
 * @author makejava
 * @since 2018-12-24 16:20:37
 */
public interface MakeremittanceDao {

    /**
     * 通过ID查询单条数据
     * @param Serialnumber 预约编号
     * @param userid 用户ID
     * @return
     */
    Makeremittance queryByIdd(@Param("Serialnumber") String Serialnumber,@Param("userid") Integer userid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Makeremittance> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param
     * @return 对象列表
     */
    List<Makeremittance> queryAll(@Param("onetime") String onetime,@Param("twotime") String twotime,@Param("userid") Integer userid);

    /**
     * 新增数据
     *
     * @param makeremittance 实例对象
     * @return 影响行数
     */
    int insert(Makeremittance makeremittance);

    /**
     * 修改数据 修改预约状态为取消  撤销操作
     *
     * @param Serialnumber
     * @return 影响行数
     */
    int update(@Param("Serialnumber") String Serialnumber,@Param("userid") Integer userid);

    /**
     * 通过主键删除数据
     *
     * @param makeremittanceId 主键
     * @return 影响行数
     */
    int deleteById(Integer makeremittanceId);

}