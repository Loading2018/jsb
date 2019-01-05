package com.example.js_deposit_provider.service;

import com.example.js_deposit_provider.entity.DepositInform;
import java.util.List;

/**
 * (DepositInform)表服务接口
 *
 * @author makejava
 * @since 2018-12-19 14:45:33
 */
public interface DepositInformService {

    /**
     * 通过ID查询单条数据
     *
     * @param depositInformid 主键
     * @return 实例对象
     */
    DepositInform queryById(Integer depositInformid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DepositInform> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param depositInform 实例对象
     * @return 实例对象
     */
    String insert(DepositInform depositInform);

    /**
     * 修改数据
     *
     * @param depositInform 实例对象
     * @return 实例对象
     */
    DepositInform update(DepositInform depositInform);

    /**
     * 通过主键删除数据
     *
     * @param depositInformid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer depositInformid);

    /**
     * 普通取款接口
     * @param informid
     * @param money
     * @return
     */
    String withdrawal(String informid,String money);

    /**
     * 通过用户id查询用户的通知存款信息
     * @param userid
     * @return
     */
    String getWithdrawalInfoByUserid(String userid,String index);

    /**
     * 预约存款接口
     * @param infoid //订单id
     * @param money  //取款金额
     * @param begintime // 预约开始时间
     * @param endtime //预约结束时间
     * @return
     */
    String reWithdrawal(String infoid,String money,String begintime,String endtime);

    /**
     * 定时任务
     */
    void timingWithdrawal();

    /**
     * 根据用户ID查询用户预约存款信息
     * @param userid
     * @return
     */
    String getReWithdrawalInfo(String userid);

    /**
     * 根据预约存款id取消预约
     * @param userid
     * @return
     */
    String cancelReservation(String userid);

    /**
     * 新增订单
     * @param BusinessID 业务ID
     * @param inMoney 存钱
     * @param outMoney 取钱
     * @param outDate 取出时间
     * @return
     */
    String addInfo(String BusinessID,String inMoney,String outMoney,String outDate,String cardNum,String userID);

    /**
     * 取钱
     * @param userid
     * @param infoid 订单ID
     * @return
     */
    String getOutMoney(String userid,String infoid);
}