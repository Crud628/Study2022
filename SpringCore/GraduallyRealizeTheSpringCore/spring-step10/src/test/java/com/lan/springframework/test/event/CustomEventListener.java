package com.lan.springframework.test.event;

import java.util.Date;

import com.lan.springframework.context.ApplicationListener;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:35:27
 * @TODO
 * 
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }

}
