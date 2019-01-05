package com.cloud.jsproducerremittance.service.impl;

import com.cloud.jsproducerremittance.config.GeneralRemittance;
import com.cloud.jsproducerremittance.dao.makeremittanceDao;
import com.cloud.jsproducerremittance.entity.MakeRemittance;
import com.cloud.jsproducerremittance.entity.RemittanceTransaction;
import com.cloud.jsproducerremittance.service.MakeAnAppointmentToTheRemittanceService;
import com.cloud.jsproducerremittance.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
@Service
public class MakeAnAppointmentToTheRemittanceServiceImpl implements MakeAnAppointmentToTheRemittanceService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private makeremittanceDao makeremittanceDao;
    private int iteartor = 0;
    //持久化汇款信息
    @Override
    @Scheduled(cron = "0 0 0 * * ? ")
    public MakeRemittance addMakeAnAppointmentToTheRemittance() {
        MakeRemittance makeRemittance = null;
        boolean flage = true;
        redisUtil.hset(GeneralRemittance.REGULAR_PAY,makeRemittance.getMakeremittancepaynumber(),"");
        redisUtil.hset(GeneralRemittance.REGULAR_PAY,makeRemittance.getMakeremittancenumber(),"");
        boolean hset = redisUtil.hset(makeRemittance.getMakeremittancepaynumber(), "" + iteartor++, makeRemittance);
        boolean hset1 = redisUtil.hset(makeRemittance.getMakeremittancenumber(), "" + iteartor++, makeRemittance);
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
        return makeRemittance;
    }
    //储存汇款信息
    @Override
    public void addMakeAnAppointment(MakeRemittance makeRemittance) {
        String cardnumber = null;
        Map<Object, Object> hmget1 = redisUtil.hmget(GeneralRemittance.REGULAR_PAY);
        for (Object card : hmget1.keySet()) {
            cardnumber = (String) card;
            Map<Object, Object> hmget = redisUtil.hmget(cardnumber);

            int count = 0;
            for (Object  message  : hmget.values()) {

                makeRemittance = (MakeRemittance)  message ;
                System.out.println(makeRemittance.getMakeremittancepaynumber());
                makeremittanceDao.saveAndFlush(makeRemittance);
                count++;
            }
            System.out.println(count == hmget.size());
            if (count != hmget.size()){
                MakeAnAppointmentToTheRemittanceServiceImpl grsi = new MakeAnAppointmentToTheRemittanceServiceImpl();
                grsi.addMakeAnAppointment(makeRemittance);
            }
        }

    }
}
