package com.lan.springframework.beans.factory;

import com.lan.springframework.beans.BeansException;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午9:29:54
 * @TODO
 * 
 */
public interface BeanFactoryAware extends Aware {

	void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
