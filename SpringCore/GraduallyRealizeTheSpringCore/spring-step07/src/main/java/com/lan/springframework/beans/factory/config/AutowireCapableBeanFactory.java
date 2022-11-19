package com.lan.springframework.beans.factory.config;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.factory.BeanFactory;

/**
 * @author Keason
 * @version 创建时间：2022年11月19日 上午11:52:52
 * @TODO
 * 
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
	/**
	 * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
	 *
	 * @param existingBean
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

	/**
	 * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
	 *
	 * @param existingBean
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
