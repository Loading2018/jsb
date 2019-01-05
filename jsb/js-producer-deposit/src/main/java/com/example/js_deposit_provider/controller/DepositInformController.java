package com.example.js_deposit_provider.controller;

import com.example.js_deposit_provider.entity.DepositInform;
import com.example.js_deposit_provider.service.DepositInformService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (DepositInform)表控制层
 *
 * @author makejava
 * @since 2018-12-19 14:45:33
 */
@RestController
public class DepositInformController {
    /**
     * 服务对象
     */
    @Resource
    private DepositInformService depositInformService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    public DepositInform selectOne(Integer id) {
        return this.depositInformService.queryById(id);
    }

    /**
     * 添加通知存款订单
     * 此处需要调服务判断余额是否足够
     * @param depositInform
     * @return
     */
    @RequestMapping(value = "addInformDeposit",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String addinfo(@RequestBody DepositInform depositInform){
        return  depositInformService.insert(depositInform);
    }

    /**
     * 查询用户存款订单
     * @param userid
     * @return
     */
    @RequestMapping(value = "getUserDepositInform",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public  String getinformbyUserId(@RequestParam("userid") String userid,@RequestParam("index") String index){
        return depositInformService.getWithdrawalInfoByUserid(userid,index);
    }

    /**
     * 普通取款接口
     * @param informid
     * @param money
     * @return
     */
    @RequestMapping(value = "withdrawal",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String Onlywithdrawal(@RequestParam("informid") String informid,@RequestParam("money") String money){
        return  depositInformService.withdrawal(informid,money);
    }

    /**
     * 预约取款接口
     * @param inforid //订单id
     * @param money  //取款金额
     * @param begintime // 预约开始时间
     * @param endtime //预约结束时间
     * @return
     */
    @RequestMapping(value = "reWithdrawal",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String subwithdrawal(@RequestParam("inforid") String inforid,@RequestParam("money") String money,@RequestParam("begintime") String begintime,@RequestParam("endtime") String endtime){
        return depositInformService.reWithdrawal(inforid,money,begintime,endtime);
    }

    /**
     * 查看个人取款预约信息接口
     * @param userid
     * @return
     */
    @RequestMapping(value = "getReWithdrawal",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String getwithdrawal(@RequestParam("userid") String userid){
        return depositInformService.getReWithdrawalInfo(userid);
    }

    /**
     * 删除预约接口
     * @param withdrawalId
     * @return
     */
    @RequestMapping(value = "cancelReservation",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public  String  cancelSubscribe(@RequestParam("withdrawalId") String withdrawalId){
        return depositInformService.cancelReservation(withdrawalId);
    }

    /**
     * 取钱
     * @param userid
     * @param businessId
     * @return
     */
    @RequestMapping(value = "getOutInfoMoney",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String getOutMoney(@RequestParam("userid") String userid,@RequestParam("businessId") String businessId){
        return depositInformService.getOutMoney(userid,businessId);
    }
}