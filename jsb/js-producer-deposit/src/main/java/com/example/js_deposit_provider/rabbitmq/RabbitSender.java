package com.example.js_deposit_provider.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendSeckillOrderQueue(String message) {
//        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_SECKILL_ORDER, message);
    }
}
