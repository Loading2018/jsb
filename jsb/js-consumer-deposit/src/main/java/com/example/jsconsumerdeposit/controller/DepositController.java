package com.example.jsconsumerdeposit.controller;

import com.example.jsconsumerdeposit.pojo.DepositBig;
import com.example.jsconsumerdeposit.pojo.DepositFixation;
import com.example.jsconsumerdeposit.pojo.DepositInform;
import com.example.jsconsumerdeposit.service.DepositService;
import com.example.jsconsumerdeposit.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
public class DepositController {
    @Resource
    private DepositService depositService;
    @Resource
    private LoginService loginService;

    /**
     * 添加大额存款订单
     * @param depositBig
     * @return
     */
    @RequestMapping(value = "add_bigOrder",method = RequestMethod.POST)
    @ResponseBody
    public String addBiginform(@RequestBody DepositBig depositBig){
        System.out.println(depositBig.getDepositBigmoney());
        String s = depositService.addBiginform(depositBig);
        System.out.println(s);
        return s;
    }

    /**
     * 查看用户的所有大额订单信息
     * @param userid
     * @return
     */
    @RequestMapping(value = "get_BigInfoByUserid",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    @ResponseBody
    String getInformById(@RequestParam("userid") String userid,@RequestParam("index")String index){
        return depositService.getInformById(userid,index);
    }

    /**
     * 取款
     * @param bigid
     * @param bigmoney
     * @return
     */
    @RequestMapping(value = "get_BigWithdrawal",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    String getmoney(@RequestParam("bigid") String bigid ,@RequestParam("bigmoney") String bigmoney){
        return depositService.getmoney(bigid,bigmoney);
    }

    @RequestMapping(value = "getOut_BigMoney",method = RequestMethod.POST)
    @ResponseBody
    String getOutBigMoney(@RequestParam("userid") String userid,@RequestParam("businessid") String businessid){
        return depositService.getOutBigMoney(userid, businessid);
    }

    /**
     * 查询所有存款业务信息并放入redis
     */
    @RequestMapping(value = "getAllBusinessInfo",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    void getAllBusinessInfo(){ }

    /**
     * 根据类型ID获得业务信息
     * @param typeID
     * @return
     */
    @RequestMapping(value = "get_BusinessInfoByType",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    @ResponseBody
    String getBusinessInfoByType(@RequestParam("typeID")String typeID,HttpServletResponse response){
        return depositService.getBusinessInfoByType(typeID);
    }

    /**
     * 增加定期存款订单
     * @param depositFixation
     * @return
     */
    @RequestMapping(value = "add_FixOrder",method = RequestMethod.POST)
    @ResponseBody
    String addFixInfo(@RequestBody DepositFixation depositFixation){
        return depositService.addFixInfo(depositFixation);
    }

    /**
     * 根据用户ID查所有定期存款信息
     * @param userid
     * @return
     */
    @RequestMapping(value = "get_FixInfoByID",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    @ResponseBody
    String getFixInfoByID(@RequestParam("userid") String userid,@RequestParam("index") String index){
        return depositService.getFixInfoByID(userid,index);
    }

    /**
     * 定期存款取款
     * @param userid
     * @param businessid
     * @return
     */
    @RequestMapping(value = "getOut_FixMoney",method = RequestMethod.POST)
    @ResponseBody
    String getOutFixMoney(@RequestParam("userid") String userid,@RequestParam("businessid") String businessid){
        System.out.println(userid);
        System.out.println(businessid);
        return depositService.getOutFixMoney(userid, businessid);
    }
    /**
     * 根据订单ID取款
     * @param infoId
     * @param money
     * @return
     */
    @RequestMapping(value = "get_Withdrawal",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    String getWithdrawal(@RequestParam("infoId") String infoId,@RequestParam("money") String money){
        return depositService.getWithdrawal(infoId, money);
    }

    /**
     * 添加存款订单
     *
     * @param depositInform
     * @return
     */
    @RequestMapping(value = "add_InformDeposit",method = RequestMethod.POST)
    @ResponseBody
    public String addinfo(@RequestBody DepositInform depositInform){
        System.out.println(depositInform.getDepositInformmoney()+"钱");
        return depositService.addinfo(depositInform);
    }

    /**
     * 查询用户的通知存款订单
     * @param userid
     * @return
     */
    @RequestMapping(value = "get_UserDepositInform",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    @ResponseBody
    public  String getinformbyUserId(@RequestParam("userid") String userid,@RequestParam("index")String index){
        return depositService.getinformbyUserId(userid,index);
    }

    /**
     * 通知存款取款
     * @param userid
     * @param businessId
     * @return
     */
    @RequestMapping(value = "getOut_InfoMoney",method = RequestMethod.POST)
    @ResponseBody
    public String getOutMoney(@RequestParam("userid") String userid,@RequestParam("businessid") String businessId){
        return depositService.getOutMoney(userid, businessId);
    }

    /**
     * 普通取款接口
     * @param informid
     * @param money
     * @return
     */
    @RequestMapping(value = "with_drawal",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String Onlywithdrawal(@RequestParam("informid") String informid,@RequestParam("money") String money){
        return depositService.Onlywithdrawal(informid, money);
    }

    /**
     * 预约取款接口
     * @param inforid //订单id
     * @param money  //取款金额
     * @param begintime // 预约开始时间
     * @param endtime //预约结束时间
     * @return
     */
    @RequestMapping(value = "re_Withdrawal",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String subwithdrawal(@RequestParam("inforid") String inforid,@RequestParam("money") String money,@RequestParam("begintime") String begintime,@RequestParam("endtime") String endtime){
        return depositService.subwithdrawal(inforid, money, begintime, endtime);
    }

    /**
     * 查看个人取款预约信息接口
     * @param userid
     * @return
     */
    @RequestMapping(value = "get_ReWithdrawal",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String getwithdrawal(@RequestParam("userid") String userid){
        return depositService.getwithdrawal(userid);
    }

    /**
     * 删除预约接口
     * @param withdrawalId
     * @return
     */
    @RequestMapping(value = "cancel_Reservation",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public  String  cancelSubscribe(@RequestParam("withdrawalId") String withdrawalId){
        return depositService.cancelSubscribe(withdrawalId);
    }

    /*
    扣款
     */
    @RequestMapping(value = "/upbalance",method =RequestMethod.POST)
    public String ss(@RequestParam("balance") Double balance,@RequestParam("banknumber") String banknumber){
        return loginService.ss(balance, banknumber);
    }
    /**
     * 查用户信息
     * @param uid
     * @return
     */
    @RequestMapping(value = "/ReadInfo",method =RequestMethod.POST)
    @ResponseBody
    public String read(HttpServletResponse response,@RequestParam("te")Integer uid){
        System.out.println(uid+"传入的参数");
        System.out.println(response);
//        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println(loginService.read(response,uid)+"1111111111111111111");
        return loginService.read(response,uid);
    }
}
