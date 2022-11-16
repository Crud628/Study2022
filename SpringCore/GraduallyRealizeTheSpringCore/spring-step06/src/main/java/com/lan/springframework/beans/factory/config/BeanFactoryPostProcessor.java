package com.lan.springframework.beans.factory.config;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午9:41:28
 * @TODO
 * 
 */
public interface BeanFactoryPostProcessor {
	/**
	 * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
	 *
	 * @param beanFactory
	 * @throws BeansException
	 */
	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
