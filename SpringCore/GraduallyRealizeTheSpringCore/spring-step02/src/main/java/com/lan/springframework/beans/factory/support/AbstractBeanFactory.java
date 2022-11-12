package com.lan.springframework.beans.factory.support;

import com.lan.springframework.beans.factory.BeanFactory;
import com.lan.springframework.beans.factory.BeansException;
import com.lan.springframework.beans.factory.config.BeanDefinition;

/**
 * @author Keason
 * @version 创建时间：2022年11月12日 下午6:55:21
 * @TODO 抽象的 Bean 工厂基类，定义模板方法
 * 
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

	protected abstract BeanDefinition getBeanDefinition(String beanName);

	/**
	 * 由AbstractAutowireCapableBeanFactory抽象类来实现
	 * 
	 * @param beanName
	 * @param beanDefinition
	 * @return
	 */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);
	
	@Override
	public Object getBean(String name) throws BeansException {
		// TODO Auto-generated method stub
		Object bean = getSingleton(name);
		if (bean != null) {
			return bean;
		}
		BeanDefinition beanDefinition = getBeanDefinition(name);
		return createBean(name, beanDefinition);
	}

}
