package com.lan.springframework.bean.factory.support;

import java.util.HashMap;
import java.util.Map;

import com.lan.springframework.bean.BeansException;
import com.lan.springframework.bean.factory.config.BeanDefinition;

/**
 * @author Keason
 * @version 创建时间：2022年11月14日 下午9:13:51
 * @TODO
 * @since 0.02
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
	private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

	@Override
	protected BeanDefinition getBeanDefinition(String beanName) {
		BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
		if (beanDefinition == null)
			throw new BeansException("No bean named '" + beanName + "' is defined");
		return beanDefinition;
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(beanName, beanDefinition);
	}
}
