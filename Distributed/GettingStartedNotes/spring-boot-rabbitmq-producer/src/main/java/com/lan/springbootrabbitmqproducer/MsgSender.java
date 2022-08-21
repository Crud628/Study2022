package com.lan.springbootrabbitmqproducer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Keason
 * @Description: 发送消息
 * @date 2022/8/21 21:50
 */
@Component
public class MsgSender {

  @Autowired
  private AmqpTemplate rabbitTemplate;

  public void send1() {
   String message = "This is message 1, routing key is dog.red";
   System.out.println("发送了：" + message);
   this.rabbitTemplate.convertAndSend("bootExchange", "dog.red", message);
  }
 public void send2() {
  String message = "This is message 2, routing key is dog.black";
  System.out.println("发送了：" + message);
  this.rabbitTemplate.convertAndSend("bootExchange", "dog.black", message);
 }
}
