package com.lan.springframework.test;

import org.junit.Test;

import com.lan.springframework.context.support.ClassPathXmlApplicationContext;
import com.lan.springframework.test.event.CustomEvent;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:35:56
 * @TODO
 * 
 */
public class ApiTest {
    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }
}
