package com.cloud.jsproducerremittance.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.cloud.jsproducerremittance.config.GeneralRemittance;
import com.cloud.jsproducerremittance.dao.remittancetransactionDao;
import com.cloud.jsproducerremittance.entity.RemittanceTransaction;
import com.cloud.jsproducerremittance.service.GeneralRemittanceService;
import com.cloud.jsproducerremittance.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class GeneralRemittanceServiceImpl implements GeneralRemittanceService {
    @Autowired
    private remittancetransactionDao remittancetransactionDao;
    @Autowired
    private RedisUtil redisUtil;
    private int iteartor = 0;
    //持久化汇款信息
    @Override
    @Scheduled(cron = "0 0 0 * * ? ")
    public void addGeneralRemittance() {
        RemittanceTransaction remittanceTransaction = null;
        String cardnumber = null;
        Map<Object, Object> hmget1 = redisUtil.hmget(GeneralRemittance.REGULAR_PAY);
        for (Object card : hmget1.keySet()) {
            cardnumber = (String) card;
            Map<Object, Object> hmget = redisUtil.hmget(cardnumber);

            int count = 0;
            for (Object  message  : hmget.values()) {
                remittanceTransaction = (RemittanceTransaction)  message ;
                if (remittanceTransaction.getState() != 1){
                    remittancetransactionDao.saveAndFlush(remittanceTransaction);
                }
                System.out.println(remittanceTransaction.getRemittancetransactioncardnumber());
                count++;
            }
            System.out.println(count == hmget.size());
/*            if (count != hmget.size()){
                GeneralRemittanceServiceImpl grsi = new GeneralRemittanceServiceImpl();
                grsi.addGeneralRemittance(remittanceTransaction);
            }*/
        }

    }
    //储存汇款信息
    @Override
    public RemittanceTransaction addGeneralRemittancerecord(RemittanceTransaction remittanceTransaction) {
        boolean flage = true;
        redisUtil.hset(GeneralRemittance.REGULAR_PAY,remittanceTransaction.getRemittancetransactioncardnumber(),"");
        redisUtil.hset(GeneralRemittance.REGULAR_PAY,remittanceTransaction.getRemittancetransactionnumber(),"");
        boolean hset = redisUtil.hset(remittanceTransaction.getRemittancetransactioncardnumber(), "" + iteartor++, remittanceTransaction);
        boolean hset1 = redisUtil.hset(remittanceTransaction.getRemittancetransactionnumber(), "" + iteartor++, remittanceTransaction);
        Set<String> allkey = redisUtil.allkey("");
        for (String s : allkey) {
            System.out.println(s);
        }

        if (!(hset && hset1)){
            flage = false;
        }
        if (!flage){
            return null;
        }
        return remittanceTransaction;
    }

    @Override
    public boolean updateState(RemittanceTransaction remittanceTransaction) {
        Map<Object, Object> hmget1 = redisUtil.hmget(remittanceTransaction.getRemittancetransactioncurrent());
        for (Object o : hmget1.keySet()) {
            RemittanceTransaction o1 = (RemittanceTransaction) hmget1.get(o);
            if (o1.getRemittancetransactioncardnumber().equals(remittanceTransaction.getRemittancetransactioncardnumber())){
                o1.setState(2);
                return redisUtil.hset(remittanceTransaction.getRemittancetransactioncardnumber(), (String) o, o1);
            }

        }
        return false;
    }
}
