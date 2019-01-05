package com.example.js_deposit_provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.js_deposit_provider.dao.DepositBusinessDao;
import com.example.js_deposit_provider.entity.DepositBig;
import com.example.js_deposit_provider.dao.DepositBigDao;
import com.example.js_deposit_provider.entity.DepositBusiness;
import com.example.js_deposit_provider.entity.Information_card;
import com.example.js_deposit_provider.entity.Withdrawal;
import com.example.js_deposit_provider.feign.MoneyFeign;
import com.example.js_deposit_provider.rabbitmq.RabbitConfig;
import com.example.js_deposit_provider.service.DepositBigService;
import com.example.js_deposit_provider.service.DepositBusinessService;
import com.example.js_deposit_provider.util.DateUtil;
import com.example.js_deposit_provider.util.OrderNumberUtil;
import com.example.js_deposit_provider.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
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
 * (DepositBig)表服务实现类
 *
 * @author makejava
 * @since 2018-12-19 14:44:59
 */
@Service("depositBigService")
public class DepositBigServiceImpl implements DepositBigService {
    @Resource
    private DepositBigDao depositBigDao;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private DepositBusinessDao depositBusinessDao;
    @Resource
    private MoneyFeign moneyFeign;

    /**
     * 通过ID查询单条数据
     *
     * @param depositBigid 主键
     * @return 实例对象
     */
    @Override
    public DepositBig queryById(Integer depositBigid) {
        return this.depositBigDao.queryById(depositBigid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<DepositBig> queryAllByLimit(int offset, int limit) {
        return this.depositBigDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param depositBig 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class )
    public String insert(DepositBig depositBig) {
        try{
            if (depositBig.getDepositBigbustype() != null
                    && depositBig.getDepositBigcardid() != null
                    && depositBig.getDepositBigmoney() != null
                    && depositBig.getDepositBiguserid() != null){
                Integer userid = depositBig.getDepositBiguserid();
                String read = moneyFeign.read(userid);
                Information_card javaBean = JSON.parseObject(read, Information_card.class);
                double balance = javaBean.getBalance();
                if (depositBig.getDepositBigmoney()<balance){
                    //扣钱
                    String ss = moneyFeign.ss(depositBig.getDepositBigmoney(),userid);
                    String nowDate = DateUtil.getNowDate();
                    Date date = DateUtil.SchangeD(nowDate);
                    DepositBusiness db = depositBusinessDao.getInfoByDepositBusinessID(depositBig.getDepositBigbustype());
                    String depositBusinessrate = db.getDepositBusinessrate();
                    Double lv = Double.parseDouble(depositBusinessrate);
                    Date getfuture = DateUtil.getfuture(date,db.getDepositBusinesslong());
                    depositBig.setDepositBiglv(lv);
                    depositBig.setDepositBigintime(date);
                    depositBig.setDepositBigouttime(getfuture);
                    depositBig.setDepositBigstate(0);
                    depositBig.setDepositBignumber(OrderNumberUtil.createNumber(depositBig.getDepositBiguserid().toString()));
                    depositBig.setDepositBiglong(db.getDepositBusinesslong());
                    return JSON.toJSONString(depositBigDao.insert(depositBig));
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
     * @param depositBig 实例对象
     * @return 实例对象
     */
    @Override
    public DepositBig update(DepositBig depositBig) {
        this.depositBigDao.update(depositBig);
        return this.queryById(depositBig.getDepositBigid());
    }

    /**
     * 通过主键删除数据
     *
     * @param depositBigid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer depositBigid) {
        return this.depositBigDao.deleteById(depositBigid) > 0;
    }


    /**
     * 取钱
     * @param bigid
     * @param bigmoney
     * @return
     */
    @Override
    public String getBigWithdrawal(String bigid, String bigmoney) {
        try{
            List<Withdrawal> wdList = new ArrayList<Withdrawal>();
            if (bigid != null && bigid != "" && bigmoney != null && bigmoney != ""){
                int id = Integer.parseInt(bigid);
                int mmoney = Integer.parseInt(bigmoney);
                DepositBig remainMoney = depositBigDao.getBigRemainMoney(id);
                if (remainMoney.getDepositBigmoney()>mmoney){
                    int withdrawal = depositBigDao.getBigWithdrawal(id, mmoney);
                    if (withdrawal>0){
                        Withdrawal wd = new Withdrawal();
                        wd.setMoney(mmoney*0.3);
                        wd.setCardid(remainMoney.getDepositBigcardid());
                        wd.setBusinessName("大额存款取款");
                        wd.setState(1);
                        wdList.add(wd);
                        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_WITHDRAWAL,JSON.toJSONString(wdList));
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
            return "102";
        }

    }

    /**
     * 根据用户ID查询信息
     * @param userid
     * @return
     */
    @Override
    public String getAllBigInfoByID(String userid,String index) {
        try{
            if (userid != "" && userid != null){
                int i = Integer.parseInt(userid);
                int i1 = Integer.parseInt(index);
                PageHelper.startPage(i1,5);
                List<DepositBig> allBigInfoByID = depositBigDao.getAllBigInfoByID(i);
                if (allBigInfoByID.size()>0){
                    PageInfo pageInfo = new PageInfo(allBigInfoByID);
                    return JSON.toJSONString(pageInfo);
                }else {
                    return "101";//没有订单
                }
            }else{
                return "400 ";//参数为空
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
            DepositBig depositBig = depositBigDao.queryById(orderid);
            Integer depositBigstate = depositBig.getDepositBigstate();
            if (depositBigstate==0){
                Double money = depositBig.getDepositBigmoney();
                String nowDate = DateUtil.getNowDate();
                System.out.println(nowDate+"现在时间");
                Date depositBigintime = depositBig.getDepositBigintime();
                Date depositBigouttime = depositBig.getDepositBigouttime();
                String intime = DateUtil.DchangeS(depositBigintime);
                System.out.println(intime+"\t存入时间");
                String outtime = DateUtil.DchangeS(depositBigouttime);
                System.out.println(outtime+"\t取出时间");
                long daySub = DateUtil.getDaySub(intime, outtime);//计划存的时间
                long daySub1 = DateUtil.getDaySub(intime, nowDate);//实际存的时间
                System.out.println(daySub+"\t计划");
                System.out.println(daySub1+"\t实际");
                Double lv;
                if (daySub1>daySub){
                    lv=depositBig.getDepositBiglv();
                }else{
                    lv=0.3;
                }
                double getmoney = (money+((money*lv*0.01)*daySub1)/365);
                DecimalFormat df = new DecimalFormat(".00");
                double interest = Double.parseDouble(df.format(getmoney));//本息
                depositBigDao.updateOrderState(orderid);//修改状态
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

    @Scheduled(cron = "0 0 1 * * ?")
    public void expireWithdrawal(){
        List<Withdrawal> wdList = new ArrayList<Withdrawal>();
        String nowDate = DateUtil.getNowDate();
        List<DepositBig> allBigInfoByDay = depositBigDao.getAllBigInfoByDay(nowDate);
        Map<Object, Object> hmget = redisUtil.hmget("3");
        for (DepositBig df:allBigInfoByDay) {
            depositBigDao.changOrderState(df.getDepositBigid());
            DepositBusiness db = (DepositBusiness)hmget.get(df.getDepositBigbustype());
            Integer longtime = db.getDepositBusinesslong(); //定期
            Withdrawal withdrawal = new Withdrawal();
            withdrawal.setState(1);
            withdrawal.setBusinessName("大额存款到期");
            withdrawal.setCardid(df.getDepositBigcardid());
            Double lv = df.getDepositBiglv();
            Double money = df.getDepositBigmoney();//存款
            withdrawal.setMoney(money+(money*lv*0.01)*(longtime/12));
            wdList.add(withdrawal);
        }
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_WITHDRAWAL,JSON.toJSONString(wdList));
    }
}