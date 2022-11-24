package com.lan.springframework.test.event;

import com.lan.springframework.context.ApplicationListener;
import com.lan.springframework.context.event.ContextClosedEvent;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:34:30
 * @TODO
 * 
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }

}
