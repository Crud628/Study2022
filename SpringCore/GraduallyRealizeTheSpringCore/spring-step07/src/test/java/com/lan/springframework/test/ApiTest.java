package com.lan.springframework.test;

import org.junit.Test;

import com.lan.springframework.context.support.ClassPathXmlApplicationContext;
import com.lan.springframework.test.bean.UserService;

/**
 * @author Keason
 * @version 创建时间：2022年11月19日 上午11:42:06
 * @TODO
 * 
 */
public class ApiTest {
    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_hook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close！")));
    }
}
