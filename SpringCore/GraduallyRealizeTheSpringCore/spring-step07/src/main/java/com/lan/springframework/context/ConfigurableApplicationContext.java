package com.lan.springframework.context;

import com.lan.springframework.beans.BeansException;

/**
 * @author Keason
 * @version 创建时间：2022年11月19日 上午11:50:31
 * @TODO
 * 
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
	/**
	 * 刷新容器
	 *
	 * @throws BeansException
	 */
	void refresh() throws BeansException;

	void registerShutdownHook();

	void close();
}
