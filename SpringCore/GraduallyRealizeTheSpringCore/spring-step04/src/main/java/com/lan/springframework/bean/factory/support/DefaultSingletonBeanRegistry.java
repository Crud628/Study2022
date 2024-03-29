package com.lan.springframework.bean.factory.support;

import java.util.HashMap;
import java.util.Map;

import com.lan.springframework.bean.factory.config.SingletonBeanRegistry;

/**
 * @author Keason
 * @version 创建时间：2022年11月14日 下午8:30:11
 * @TODO 通用的注册表实现
 * 
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
	private Map<String, Object> singletonObjects = new HashMap<>();

	@Override
	public Object getSingleton(String beanName) {
		return singletonObjects.get(beanName);
	}

	@Override
	public void registerSingleton(String beanName, Object singletonObject) {
		singletonObjects.put(beanName, singletonObject);
	}
}
