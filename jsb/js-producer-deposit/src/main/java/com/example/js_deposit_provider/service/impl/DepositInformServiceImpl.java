package com.example.js_deposit_provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.js_deposit_provider.dao.DepositBusinessDao;
import com.example.js_deposit_provider.dao.DepositWithdrawalDao;
import com.example.js_deposit_provider.entity.*;
import com.example.js_deposit_provider.dao.DepositInformDao;
import com.example.js_deposit_provider.feign.MoneyFeign;
import com.example.js_deposit_provider.rabbitmq.RabbitConfig;
import com.example.js_deposit_provider.service.DepositInformService;
import com.example.js_deposit_provider.util.DateUtil;
import com.example.js_deposit_provider.util.OrderNumberUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thoughtworks.xstream.mapper.Mapper;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

/**
 * (DepositInform)表服务实现类
 *
 * @author makejava
 * @since 2018-12-19 14:45:33
 */
@Service("depositInformService")
public class DepositInformServiceImpl implements DepositInformService {
    @Resource
    private DepositInformDao depositInformDao;
    @Resource
    private DepositWithdrawalDao depositWithdrawalDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    private DepositBusinessDao depositBusinessdao;
    @Resource
    private MoneyFeign moneyFeign;

    /**
     * 通过ID查询单条数据
     *
     * @param depositInformid 主键
     * @return 实例对象
     */
    @Override
    public DepositInform queryById(Integer depositInformid) {
        return this.depositInformDao.queryById(depositInformid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<DepositInform> queryAllByLimit(int offset, int limit) {
        return this.depositInformDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param depositInform 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class )
    public String insert(DepositInform depositInform) {
        System.out.println(depositInform.getDepositInformcardid()+"1111111111111111111");
        try{
            String uid = depositInform.getDepositInformuserid();
            int userid = Integer.parseInt(uid);
            String read = moneyFeign.read(userid);
            Information_card javaBean = JSON.parseObject(read, Information_card.class);
            double balance = javaBean.getBalance();
            if (depositInform.getDepositInformmoney()<balance){
                //扣钱
                String ss = moneyFeign.ss(depositInform.getDepositInformmoney(), userid);
                String nowDate = DateUtil.getNowDate();
                Date date = DateUtil.SchangeD(nowDate);
                depositInform.setDepositInformstate("0");
                depositInform.setDepositInformtime(date);
                String number = OrderNumberUtil.createNumber(depositInform.getDepositInformcardid());
                String cardNumber = number.substring(0,17);
                depositInform.setDepositInformnumber(cardNumber);//订单号
                return JSON.toJSONString(this.depositInformDao.insert(depositInform));
            }else {
                return "401";
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * 修改数据
     *
     * @param depositInform 实例对象
     * @return 实例对象
     */
    @Override
    public DepositInform update(DepositInform depositInform) {
        this.depositInformDao.update(depositInform);
        return this.queryById(depositInform.getDepositInformid());
    }

    /**
     * 通过主键删除数据
     *
     * @param depositInformid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer depositInformid) {
        return this.depositInformDao.deleteById(depositInformid) > 0;
    }

    /**
     * 普通取款接口
     * @param informid 订单ID
     * @param money 取款金额
     * @return
     */
    @Override
    public String withdrawal(String informid, String money) {
        List<Withdrawal> withdrawals = new ArrayList<Withdrawal>();
        if (informid != null && informid != "" && money != null && money != ""){
            DepositInform depositInform = depositInformDao.getOrderMoney(Integer.parseInt(informid));
            Double orderMoney = depositInform.getDepositInformmoney();//获得存款的金额

            String stat = depositInform.getDepositInformstate();
            int i = Integer.parseInt(money);//取出的金额
            if (orderMoney>i){
                depositInformDao.updateWithdrawalByOrderID(Integer.parseInt(informid),i);//取款
                String nowDate = DateUtil.getNowDate();
                Date depositInformtime = depositInform.getDepositInformtime();
                String starttime = DateUtil.DchangeS(depositInformtime);
                long daySub = DateUtil.getDaySub(starttime, nowDate);//存款天数
                double getmoney = (i+((i*0.3*0.01)*daySub)/365);
                DecimalFormat df = new DecimalFormat(".00");
                double interest = Double.parseDouble(df.format(getmoney));//利息
                Withdrawal withdrawal = new Withdrawal();
                withdrawal.setBusinessName("通知存款取款");
                withdrawal.setCardid(depositInform.getDepositInformcardid());
                withdrawal.setMoney(interest);
                withdrawal.setState(1);//1取款
                withdrawals.add(withdrawal);
                return "200";
            }else {
                return "101";//余额不足
            }
        }
        return null;
    }

    /**
     * 通过用户id查询用户的通知存款信息
     * @param userid
     * @return
     */
    @Override
    public String getWithdrawalInfoByUserid(String userid,String index) {
        if (userid != null && userid != ""){
            int i = Integer.parseInt(index);
            PageHelper.startPage(i,5);
            List<DepositInform> depositInfoByUserid = depositInformDao.getDepositInfoByUserid(userid);
            if (depositInfoByUserid.size()>0){
                PageInfo pageInfo = new PageInfo(depositInfoByUserid);
                return JSON.toJSONString(pageInfo);
            }else {
                return "101";//不存在存款信息
            }
        }else {
            return "102";//没有用户id或用户未登陆
        }
    }

    /**
     * 预约取款接口
     * @param infoid //订单id
     * @param money  //取款金额
     * @param begintime // 预约开始时间
     * @param endtime //预约结束时间
     * @return
     */
    @Override
    public String reWithdrawal(String infoid, String money, String begintime, String endtime) {
        //先将预约存款信息存入表中
        if (infoid != null && infoid != "" && money != null && money != "" && begintime != null && begintime != "" && endtime != null && endtime != ""){
            DepositInform depositInform = depositInformDao.queryById(Integer.parseInt(infoid));
            Double depositInformmoney = depositInform.getDepositInformmoney();//已存金额
            if (depositInformmoney>Integer.parseInt(money)){
                DepositWithdrawal depositWithdrawal = new DepositWithdrawal();
                depositWithdrawal.setDepositWithdrawalorderid(Integer.parseInt(infoid));
                depositWithdrawal.setDepositWithdrawalmoney(Integer.parseInt(money));
                depositWithdrawal.setDepositWithdrawalstate(0);
                depositWithdrawal.setDepositWithdrawaluserid(Integer.parseInt(depositInform.getDepositInformuserid()));
                depositWithdrawal.setDepositWithdrawalintime(DateUtil.SchangeD(begintime));
                depositWithdrawal.setDepositWithdrawaouttime(DateUtil.SchangeD(endtime));
                System.out.println(depositWithdrawal.getDepositWithdrawalintime());
                int insert = depositWithdrawalDao.insert(depositWithdrawal);
                if(insert > 0){
                    DepositInform depositInfo = new DepositInform();
                    depositInfo.setDepositInformid(Integer.parseInt(infoid));
                    depositInfo.setDepositInformstate("1");
                    depositInformDao.update(depositInfo);
                    return "200"; //通知存款申请成功
                }else {
                    return "400"; //申请失败
                }
            }else {
                return "401"; //余额不足
            }

        }
        return null;
    }

    /**
     * 定时任务进行预约取款处理
     */
    @Override
    @Scheduled(cron = "0 0 2 * * ?")
    public void timingWithdrawal() {
        Map<Integer,DepositWithdrawal> changeList = new HashMap<Integer, DepositWithdrawal>();
        //得到所有预约信息
        List<DepositWithdrawal> allWithdrawalInfo = depositWithdrawalDao.getAllWithdrawalInfo();
        List<Withdrawal> withdrawals = new ArrayList<Withdrawal>();
        String nowDate = DateUtil.getNowDate();
        Date date = DateUtil.SchangeD(nowDate);
        if (allWithdrawalInfo.size()>0){
            //先筛选除所有的当天订单
            for (DepositWithdrawal d:allWithdrawalInfo) {
                Date depositWithdrawaouttime = d.getDepositWithdrawaouttime();
                boolean sameDate = DateUtil.isSameDate(depositWithdrawaouttime, date);
                if (sameDate){
                    changeList.put(d.getDepositWithdrawalid(),d);
                    continue;
                }
            }
            //再进行操作
            Set<Integer> integers = changeList.keySet();
            for (Integer i:integers) {
                //从集合种得到对应订单对象
                DepositWithdrawal depositWithdrawal = changeList.get(i);
                Integer depositWithdrawalmoney = depositWithdrawal.getDepositWithdrawalmoney();//取款金额
                //得到订单信息
                DepositInform depositInform = depositInformDao.getOrderMoney(depositWithdrawal.getDepositWithdrawalid());
                Double depositInformmoney = depositInform.getDepositInformmoney();//存款金额
                if (depositInformmoney>depositWithdrawalmoney){
                    //更新订单状态 1取款成功
                    depositWithdrawalDao.updateWithdrawalByID(1,i);
                    //从用户存款中扣钱
                    depositInformDao.updateWithdrawalByOrderID(depositWithdrawal.getDepositWithdrawalorderid(),depositWithdrawalmoney);
                    //得到业务信息
                    DepositBusiness depositBusiness = depositBusinessdao.queryById(depositInform.getDepositInformtype());
                    //得到存入时间
                    Date depositInformtime = depositInform.getDepositInformtime();
                    //算出存入天数
                    long daySub = DateUtil.getDaySub(DateUtil.DchangeS(depositInformtime), nowDate);
                    //将结果存入withdrawal对象
                    Withdrawal withdrawal = new Withdrawal();
                    withdrawal.setBusinessName(depositBusiness.getDepositBusinessname()+"到期");
                    double v = Double.parseDouble(depositBusiness.getDepositBusinessrate());
                    withdrawal.setCardid(depositInform.getDepositInformcardid());
                    double mon = (depositWithdrawalmoney + ((depositWithdrawalmoney * v * 0.01) * daySub) / 365);
                    DecimalFormat df = new DecimalFormat(".00");
                    double interest = Double.parseDouble(df.format(mon));//利息
                    withdrawal.setMoney(interest);
                    withdrawal.setState(1); //1为取款
                    withdrawals.add(withdrawal);
                }else {
                    //改变状态为取款失败
                    depositWithdrawalDao.updateWithdrawalByID(2,i);
                }
            }
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_WITHDRAWAL,JSON.toJSONString(withdrawals));
        }
    }

    /**
     * 根据用户ID查询用户预约存款信息
     * @param userid
     * @return
     */
    @Override
    public String getReWithdrawalInfo(String userid) {
        int i = Integer.parseInt(userid);
        List<DepositWithdrawal> withdrawalInfoByUserId = depositWithdrawalDao.getWithdrawalInfoByUserId(i);
        return JSON.toJSONString(withdrawalInfoByUserId);
    }

    /**
     * 根据预约存款id取消预约
     * @param userid
     * @return
     */
    @Override
    public String cancelReservation(String userid) {
        int i = Integer.parseInt(userid);
        int i1 = depositWithdrawalDao.deleteById(i);
        return JSON.toJSONString(i1);
    }

    @Override
    public String addInfo(String BusinessID, String inMoney, String outMoney, String outDate,String cardNum,String userID) {
        //存款
        DepositInform depositInform = new DepositInform();
        String nowDate = DateUtil.getNowDate();
        Date date = DateUtil.SchangeD(nowDate);
        depositInform.setDepositInformstate("0");
        depositInform.setDepositInformtime(date);
        String number = OrderNumberUtil.createNumber(depositInform.getDepositInformcardid());
        String cardNumber = number.substring(0,17);
        depositInform.setDepositInformnumber(cardNumber);//订单号
        Double inmoney = Double.parseDouble(inMoney);
        int typeID = Integer.parseInt(BusinessID);
        depositInform.setDepositInformmoney(inmoney);
        depositInform.setDepositInformcardid(cardNum);
        depositInform.setDepositInformuserid(userID);
        depositInform.setDepositInformtype(typeID);
        depositInformDao.insert(depositInform);

        //取款
        DepositWithdrawal depositWithdrawal = new DepositWithdrawal();

        return null;
    }

    /**
     * 取钱
     * @param userid
     * @param businessId 订单ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class )
    public String getOutMoney(String userid, String businessId) {
        try{
            int orderid = Integer.parseInt(businessId);
            DepositInform depositInform = depositInformDao.queryById(orderid);
            String stat = depositInform.getDepositInformstate();
            if (stat.equals("0")){
                //取出的钱
                Double money = depositInform.getDepositInformmoney();
                String nowDate = DateUtil.getNowDate();
                Date depositInformtime = depositInform.getDepositInformtime();
                String starttime = DateUtil.DchangeS(depositInformtime);
                long daySub = DateUtil.getDaySub(starttime, nowDate);//存款天数
                double getmoney = (money+((money*0.3*0.01)*daySub)/365);
                DecimalFormat df = new DecimalFormat(".00");
                double interest = Double.parseDouble(df.format(getmoney));//本息
                depositInformDao.updateOrderState(orderid);//修改状态
                moneyFeign.insmoney(interest,Integer.parseInt(userid));
                return "200";
            }else{
                return "401";
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}