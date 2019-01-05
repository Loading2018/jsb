package com.example.js_deposit_provider.rabbitmq;


import com.alibaba.fastjson.JSON;
import com.example.js_deposit_provider.entity.Withdrawal;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SeckillListener {

   @RabbitListener(queues = {RabbitConfig.QUEUE_WITHDRAWAL})
    public void receive(String msg){
       List<Withdrawal> q = JSON.parseArray(msg,Withdrawal.class);
       for (Withdrawal a : q){
           System.out.println(a.getMoney());
       }
   }
}
