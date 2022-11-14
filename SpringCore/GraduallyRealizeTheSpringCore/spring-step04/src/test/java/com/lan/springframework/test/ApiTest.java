package com.lan.springframework.test;

import org.junit.Test;

import com.lan.springframework.bean.PropertyValue;
import com.lan.springframework.bean.PropertyValues;
import com.lan.springframework.bean.factory.config.BeanDefinition;
import com.lan.springframework.bean.factory.config.BeanReference;
import com.lan.springframework.bean.factory.support.DefaultListableBeanFactory;
import com.lan.springframework.test.bean.UserDao;
import com.lan.springframework.test.bean.UserService;

/**
 * @author Keason
 * @version 创建时间：2022年11月14日 下午9:16:57
 * @TODO 测试类
 * 
 */
public class ApiTest {
	@Test
	public void test_BeanFactory() {
		// 1.初始化 BeanFactory
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 2. UserDao 注册
		beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

		// 3. UserService 设置属性[uId、userDao]
		PropertyValues propertyValues = new PropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
		propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

		// 4. UserService 注入bean
		BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
		beanFactory.registerBeanDefinition("userService", beanDefinition);

		// 5. UserService 获取bean
		UserService userService = (UserService) beanFactory.getBean("userService");
		userService.queryUserInfo();
	}
}
