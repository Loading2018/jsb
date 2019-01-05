package com.example.js_deposit_provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.js_deposit_provider.dao.DepositBusinessDao;
import com.example.js_deposit_provider.entity.*;
import com.example.js_deposit_provider.dao.DepositFixationDao;
import com.example.js_deposit_provider.feign.MoneyFeign;
import com.example.js_deposit_provider.rabbitmq.RabbitConfig;
import com.example.js_deposit_provider.service.DepositFixationService;
import com.example.js_deposit_provider.util.DateUtil;
import com.example.js_deposit_provider.util.OrderNumberUtil;
import com.example.js_deposit_provider.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (DepositFixation)表服务实现类
 *
 * @author makejava
 * @since 2018-12-19 14:45:33
 */
@Service("depositFixationService")
public class DepositFixationServiceImpl implements DepositFixationService {
    @Resource
    private DepositFixationDao depositFixationDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private DepositBusinessDao depositBusinessDao;
    @Resource
    private MoneyFeign moneyFeign;

    /**
     * 通过ID查询单条数据
     *
     * @param depositFixationid 主键
     * @return 实例对象
     */
    @Override
    public DepositFixation queryById(Integer depositFixationid) {
        return this.depositFixationDao.queryById(depositFixationid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<DepositFixation> queryAllByLimit(int offset, int limit) {
        return this.depositFixationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param depositFixation 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class )
    public String insert(DepositFixation depositFixation) {
        try{
            if(depositFixation.getDepositFixationbusinesstype() != null
                    && depositFixation.getDepositFixationcardid() != null
                    && depositFixation.getDepositFixationmoney() != null
                    && depositFixation.getDepositFixationuserid() != null){
                //在消费者放先判断卡中余额是否足够，足够先扣除卡中余额再进行订单生成操作
                String uid = depositFixation.getDepositFixationuserid();
                int userid = Integer.parseInt(uid);
                String read = moneyFeign.read(userid);
                Information_card javaBean = JSON.parseObject(read, Information_card.class);
                double balance = javaBean.getBalance();
                if (depositFixation.getDepositFixationmoney()<balance){
                    String ss = moneyFeign.ss(depositFixation.getDepositFixationmoney(), userid);
                    String nowDate = DateUtil.getNowDate();
                    Date date = DateUtil.SchangeD(nowDate);
                    DepositBusiness db = depositBusinessDao.getInfoByDepositBusinessID(depositFixation.getDepositFixationbusinesstype());
                    String depositBusinessrate = db.getDepositBusinessrate();
                    Double lv = Double.parseDouble(depositBusinessrate);
                    Integer longtime = db.getDepositBusinesslong();//存款时间
                    System.out.println(longtime);
                    Date getfuture = DateUtil.getfuture(date, longtime);
                    depositFixation.setDepositFixationlv(lv);
                    depositFixation.setDepositFixationintime(date);
                    depositFixation.setDepositFixationouttime(getfuture);
                    depositFixation.setDepositFixationnumber(OrderNumberUtil.createNumber(depositFixation.getDepositFixationuserid().toString()));
                    depositFixation.setDepositState(0);
                    return JSON.toJSONString(this.depositFixationDao.insert(depositFixation));
                }else {
                    return "401";
                }
            }else {
                return "400";
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
     * @param depositFixation 实例对象
     * @return 实例对象
     */
    @Override
    public DepositFixation update(DepositFixation depositFixation) {
        this.depositFixationDao.update(depositFixation);
        return this.queryById(depositFixation.getDepositFixationid());
    }

    /**
     * 通过主键删除数据
     *
     * @param depositFixationid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer depositFixationid) {
        return this.depositFixationDao.deleteById(depositFixationid) > 0;
    }

    /**
     * 根据用户ID查询定期存款订单
     * @param userid
     * @return
     */
    @Override
    public String getAllFixInfoByID(String userid,String index) {
        try{
            if (userid != "" && userid != null){
                int id = Integer.parseInt(userid);
                int i = Integer.parseInt(index);
                PageHelper.startPage(i,5);
                List<DepositFixation> fixList = depositFixationDao.getAllFixInfoByID(id);
                if(fixList.size()>0){
                    PageInfo pageInfo = new PageInfo(fixList);
                    return JSON.toJSONString(pageInfo);
                }else {
                    return "101";//无信息
                }
            }else{
                return "400";//参数为空
            }
        }catch (Exception e){
            return "400";
        }

    }

    /**
     * 根据订单ID取款
     * @param infoid
     * @param money
     * @return
     */
    @Override
    public String getWithdrawal(String infoid, String money) {
        List<Withdrawal> wdList = new ArrayList<Withdrawal>();
        try{
            if (infoid != null && infoid != "" && money != null && money != ""){
                int id = Integer.parseInt(infoid);
                int mmoney = Integer.parseInt(money);
                DepositFixation remainMoney = depositFixationDao.getRemainMoney(id);
                if (remainMoney.getDepositFixationmoney()>mmoney){
                    int withdrawal = depositFixationDao.getWithdrawal(id, mmoney);
                    if (withdrawal>0){
                        Withdrawal wd = new Withdrawal();
                        wd.setMoney(mmoney*0.3);
                        wd.setCardid(remainMoney.getDepositFixationcardid());
                        wd.setBusinessName("定期存款取款");
                        wd.setState(1);
                        wdList.add(wd);
                        return "200";
                    }else {
                        return "101";//取款失败
                    }
                }else {
                    return "余额不足";
                }
            }else {
                return "400";
            }
        }catch (Exception e){
            return "400";
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class )
    public String getOutMoney(String userid, String infoid) {
        try{
            int orderid = Integer.parseInt(infoid);
            DepositFixation depositFixation = depositFixationDao.queryById(orderid);
            int stat = depositFixation.getDepositState();
            if (stat==0){
                Double money = depositFixation.getDepositFixationmoney();
                String nowDate = DateUtil.getNowDate();
                //System.out.println(nowDate+"现在时间");
                Date depositFixationintime = depositFixation.getDepositFixationintime();
                Date depositFixationouttime = depositFixation.getDepositFixationouttime();
                String intime = DateUtil.DchangeS(depositFixationintime);
                //System.out.println(intime+"\t存入时间");
                String outtime = DateUtil.DchangeS(depositFixationouttime);
                //System.out.println(outtime+"\t取出时间");
                long daySub = DateUtil.getDaySub(intime, outtime);//计划存的时间
                long daySub1 = DateUtil.getDaySub(intime, nowDate);//实际存的时间
                //System.out.println(daySub+"\t计划");
                //System.out.println(daySub1+"\t实际");
                Double lv;//利率
                if (daySub1>daySub){
                    lv = depositFixation.getDepositFixationlv();
                }else{
                    lv = 0.3;
                }
                //System.out.println(lv+"\t利率");
                double getmoney = (money+((money*lv*0.01)*daySub1)/365);
                DecimalFormat df = new DecimalFormat(".00");
                double interest = Double.parseDouble(df.format(getmoney));//本息
                //System.out.println(interest+"\t本息和");
                depositFixationDao.updateOrderState(orderid);//修改状态
                moneyFeign.insmoney(interest,Integer.parseInt(userid));
                return "200";
            }else {
                return "401";
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public void expireWithdrawal(){
            List<Withdrawal> wdList = new ArrayList<Withdrawal>();
            String nowDate = DateUtil.getNowDate();
            List<DepositFixation> allFixInfoByDay = depositFixationDao.getAllFixInfoByDay(nowDate);
            Map<Object, Object> hmget = redisUtil.hmget("2");
            for (DepositFixation df:allFixInfoByDay) {
                depositFixationDao.changOrderState(df.getDepositFixationid());
                DepositBusiness db = (DepositBusiness)hmget.get(df.getDepositFixationbusinesstype());
                Integer longtime = db.getDepositBusinesslong(); //定期
                Withdrawal withdrawal = new Withdrawal();
                withdrawal.setState(1);
                withdrawal.setBusinessName("定期存款到期");
                withdrawal.setCardid(df.getDepositFixationcardid());
                Double lv = df.getDepositFixationlv();
                Double money = df.getDepositFixationmoney();//存款
                withdrawal.setMoney(money+(money*lv*0.01)*(longtime/12));
                wdList.add(withdrawal);
            }
            rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_WITHDRAWAL,JSON.toJSONString(wdList));
        }
}