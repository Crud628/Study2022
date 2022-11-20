package com.lan.springframework.beans.factory;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.lan.springframework.beans.factory.config.BeanDefinition;
import com.lan.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午7:47:29
 * @TODO
 * 
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
	BeanDefinition getBeanDefinition(String beanName) throws BeansException;

	void preInstantiateSingletons() throws BeansException;

}
