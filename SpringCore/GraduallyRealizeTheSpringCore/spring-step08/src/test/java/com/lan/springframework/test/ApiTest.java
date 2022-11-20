package com.lan.springframework.test;

import org.junit.Test;

import com.lan.springframework.context.support.ClassPathXmlApplicationContext;
import com.lan.springframework.test.bean.UserService;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午8:13:57
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

        System.out.println("ApplicationContextAware："+userService.getApplicationContext());
        System.out.println("BeanFactoryAware："+userService.getBeanFactory());
    }
}