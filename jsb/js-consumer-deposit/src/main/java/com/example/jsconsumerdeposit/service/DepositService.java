package com.example.jsconsumerdeposit.service;

import com.example.jsconsumerdeposit.pojo.DepositBig;
import com.example.jsconsumerdeposit.pojo.DepositFixation;
import com.example.jsconsumerdeposit.pojo.DepositInform;
import com.example.jsconsumerdeposit.service.impl.DepositServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@FeignClient(name = "js-producer-deposit",fallback = DepositServiceImpl.class)
public interface DepositService {
    /**
     * 添加大额存款订单
     * @param depositBig
     * @return
     */
    @RequestMapping(value = "addbigOrder",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String addBiginform(@RequestBody DepositBig depositBig);

    /**
     * 查看用户的所有大额订单信息
     * @param userid
     * @return
     */
    @RequestMapping(value = "getBigInfoByUserid",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String getInformById(@RequestParam("userid") String userid,@RequestParam("index")String index);

    /**
     * 取款
     * @param bigid
     * @param bigmoney
     * @return
     */
    @RequestMapping(value = "getBigWithdrawal",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    String getmoney(@RequestParam("bigid") String bigid ,@RequestParam("bigmoney") String bigmoney);

    /**
     * 查询所有存款业务信息并放入redis
     */
    @RequestMapping(value = "getAllBusinessInfo",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    void getAllBusinessInfo();

    /**
     * 根据类型ID获得业务信息
     * @param typeID
     * @return
     */
    @RequestMapping(value = "getBusinessInfoByType",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String getBusinessInfoByType(@RequestParam("typeID")String typeID);

    /**
     * 增加定期存款订单
     * @param depositFixation
     * @return
     */
    @RequestMapping(value = "addFixOrder",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    String addFixInfo(@RequestBody DepositFixation depositFixation);

    /**
     * 根据用户ID查所有定期存款信息
     * @param userid
     * @return
     */
    @RequestMapping(value = "getFixInfoByID",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String getFixInfoByID(@RequestParam("userid") String userid,@RequestParam("index") String index);

    /**
     * 根据订单ID取款
     * @param infoId
     * @param money
     * @return
     */
    @RequestMapping(value = "getWithdrawal",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    String getWithdrawal(@RequestParam("infoId") String infoId,@RequestParam("money") String money);

    /**
     * 添加存款订单
     * 此处需要调服务判断余额是否足够
     * @param depositInform
     * @return
     */
    @RequestMapping(value = "addInformDeposit",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String addinfo(@RequestBody DepositInform depositInform);

    /**
     * 查询用户存款订单
     * @param userid
     * @return
     */
    @RequestMapping(value = "getUserDepositInform",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String getinformbyUserId(@RequestParam("userid") String userid,@RequestParam("index") String index);

    /**
     * 普通取款接口
     * @param informid
     * @param money
     * @return
     */
    @RequestMapping(value = "withdrawal",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String Onlywithdrawal(@RequestParam("informid") String informid,@RequestParam("money") String money);

    /**
     * 预约取款接口
     * @param inforid //订单id
     * @param money  //取款金额
     * @param begintime // 预约开始时间
     * @param endtime //预约结束时间
     * @return
     */
    @RequestMapping(value = "reWithdrawal",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String subwithdrawal(@RequestParam("inforid") String inforid,@RequestParam("money") String money,@RequestParam("begintime") String begintime,@RequestParam("endtime") String endtime);

    /**
     * 查看个人取款预约信息接口
     * @param userid
     * @return
     */
    @RequestMapping(value = "getReWithdrawal",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String getwithdrawal(@RequestParam("userid") String userid);

    /**
     * 删除预约接口
     * @param withdrawalId
     * @return
     */
    @RequestMapping(value = "cancelReservation",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String  cancelSubscribe(@RequestParam("withdrawalId") String withdrawalId);

    /**
     * 通知存款的取款
     * @param userid
     * @param businessId
     * @return
     */
    @RequestMapping(value = "getOutInfoMoney",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String getOutMoney(@RequestParam("userid") String userid,@RequestParam("businessId") String businessId);

    /**
     * 定期存款的取款
     * @param userid
     * @param businessid
     * @return
     */
    @RequestMapping(value = "getOutFixMoney",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String getOutFixMoney(@RequestParam("userid") String userid,@RequestParam("businessid") String businessid);

    /**
     * 大额存款的取款
     * @param userid
     * @param businessid
     * @return
     */
    @RequestMapping(value = "getOutBigMoney",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String getOutBigMoney(@RequestParam("userid") String userid,@RequestParam("businessid") String businessid);
}
