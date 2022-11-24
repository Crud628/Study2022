package com.lan.springframework.test.event;

import com.lan.springframework.context.ApplicationListener;
import com.lan.springframework.context.event.ContextRefreshedEvent;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:34:48
 * @TODO
 * 
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }

}
