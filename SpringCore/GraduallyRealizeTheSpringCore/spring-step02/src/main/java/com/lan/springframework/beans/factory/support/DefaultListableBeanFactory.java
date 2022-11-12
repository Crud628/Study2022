package com.lan.springframework.beans.factory.support;

import java.util.HashMap;
import java.util.Map;

import com.lan.springframework.beans.factory.BeansException;
import com.lan.springframework.beans.factory.config.BeanDefinition;

/**
 * @author Keason
 * @version 创建时间：2022年11月12日 下午6:54:18
 * @TODO
 * 
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{

	private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
	
	@Override
	protected BeanDefinition getBeanDefinition(String beanName) {
		// TODO Auto-generated method stub
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		// TODO Auto-generated method stub
        beanDefinitionMap.put(beanName, beanDefinition);
	}

}
