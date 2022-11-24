package com.lan.springframework.beans.factory.config;

import com.lan.springframework.beans.BeansException;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:08:41
 * @TODO
 * 
 */
public interface BeanPostProcessor {
	/**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
