package com.lan.springframework.beans.factory.config;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午9:44:10
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
