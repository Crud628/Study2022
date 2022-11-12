package com.lan.springframework.beans.factory.support;

import java.util.HashMap;
import java.util.Map;

import com.lan.springframework.beans.factory.config.SingletonBeanRegistry;

/**
 * @author Keason
 * @version 创建时间：2022年11月12日 下午6:49:31
 * @TODO 通用的注册表实现
 * 
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();
	
	@Override
	public Object getSingleton(String beanName) {
		// TODO Auto-generated method stub
		return singletonObjects.get(beanName);
	}

	@Override
	public void registerSingleton(String beanName, Object singletonObject) {
		// TODO Auto-generated method stub
        singletonObjects.put(beanName, singletonObject);
	}

}
