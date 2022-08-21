package com.lan.springbootrabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Keason
 * @Description: 消费者1
 * @date 2022/8/21 21:58
 */
@Component
@RabbitListener(queues = "queue1")
public class Receiver1 {

    @RabbitHandler
    public void process(String message) {
       System.out.println("receiver1: " + message);
    }
}
