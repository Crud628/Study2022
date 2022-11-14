package com.lan.springframework;
/**
 * @author Keason
 * @version 创建时间：2022年11月12日 下午1:15:17
 * @TODO Bean 对象信息定义
 * @since 0.01
 */
public class BeanDefinition {
	private Object bean;

	public BeanDefinition(Object bean) {
		this.bean = bean;
	}

	public Object getBean() {
		return bean;
	}
}
