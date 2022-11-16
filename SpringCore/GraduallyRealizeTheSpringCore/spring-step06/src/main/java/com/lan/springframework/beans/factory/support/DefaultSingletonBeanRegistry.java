package com.lan.springframework.beans.factory.support;

import java.util.HashMap;
import java.util.Map;

import com.lan.springframework.beans.factory.config.SingletonBeanRegistry;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午9:53:38
 * @TODO
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
