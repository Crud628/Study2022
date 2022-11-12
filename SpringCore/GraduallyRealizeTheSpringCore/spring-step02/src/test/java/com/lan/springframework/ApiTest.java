package com.lan.springframework;

import org.junit.jupiter.api.Test;

import com.lan.springframework.bean.UserService;
import com.lan.springframework.beans.factory.config.BeanDefinition;
import com.lan.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author Keason
 * @version 创建时间：2022年11月12日 下午7:42:43
 * @TODO
 * 
 */
public class ApiTest {
	@Test
	public void test_BeanFactory() {
		// 1.初始化 BeanFactory
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 2.注册 bean
		BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
		beanFactory.registerBeanDefinition("userService", beanDefinition);

		// 3.第一次获取 bean
		UserService userService = (UserService) beanFactory.getBean("userService");
		userService.queryUserInfo();

		// 4.第二次获取 bean from Singleton
		UserService userService_singleton = (UserService) beanFactory.getBean("userService");
		userService_singleton.queryUserInfo();
	}
}
