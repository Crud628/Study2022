package com.lan.springframework.bean.factory.support;

import java.lang.reflect.Constructor;

import com.lan.springframework.bean.BeansException;
import com.lan.springframework.bean.factory.config.BeanDefinition;

/**
 * @author Keason
 * @version 创建时间：2022年11月14日 下午8:35:58
 * @TODO Bean 实例化策略接口
 * @since 0.03
 */
public interface InstantiationStrategy {
	
	Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
