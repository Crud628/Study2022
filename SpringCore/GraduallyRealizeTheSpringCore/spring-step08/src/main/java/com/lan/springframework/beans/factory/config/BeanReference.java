package com.lan.springframework.beans.factory.config;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午12:24:32
 * @TODO
 * 
 */
public class BeanReference {
	private final String beanName;

	public BeanReference(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}
}
