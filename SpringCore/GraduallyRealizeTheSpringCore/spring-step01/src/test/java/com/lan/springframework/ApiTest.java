package com.lan.springframework;

import org.junit.jupiter.api.Test;

import com.lan.springframework.bean.UserService;

/**
 * @author Keason
 * @version 创建时间：2022年11月12日 下午1:21:52
 * @TODO
 * @since 0.01
 */
public class ApiTest {
	@Test
	public void testBeanFactory() {
        // 1.初始化 BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
	}
}
