package com.lan.springframework.bean.factory.support;

import com.lan.springframework.bean.BeansException;
import com.lan.springframework.bean.factory.BeanFactory;
import com.lan.springframework.bean.factory.config.BeanDefinition;

/**
 * @author Keason
 * @version 创建时间：2022年11月14日 下午8:31:04
 * @TODO
 * @since 0.02
 */
public abstract class AbstractBeanFactory  extends DefaultSingletonBeanRegistry implements BeanFactory {
	 @Override
	    public Object getBean(String name) throws BeansException {
	        return doGetBean(name, null);
	    }

	    @Override
	    public Object getBean(String name, Object... args) throws BeansException {
	        return doGetBean(name, args);
	    }

	    protected <T> T doGetBean(final String name, final Object[] args) {
	        Object bean = getSingleton(name);
	        if (bean != null) {
	            return (T) bean;
	        }

	        BeanDefinition beanDefinition = getBeanDefinition(name);
	        return (T) createBean(name, beanDefinition, args);
	    }

	    protected abstract BeanDefinition getBeanDefinition(String beanName);

	    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);
}
