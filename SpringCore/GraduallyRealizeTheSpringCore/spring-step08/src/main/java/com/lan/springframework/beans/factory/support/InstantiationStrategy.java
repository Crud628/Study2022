package com.lan.springframework.beans.factory.support;

import java.lang.reflect.Constructor;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.factory.config.BeanDefinition;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午7:50:09
 * @TODO
 * 
 */
public interface InstantiationStrategy {
	 Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
