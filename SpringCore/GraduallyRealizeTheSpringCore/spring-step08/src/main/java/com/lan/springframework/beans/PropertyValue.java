package com.lan.springframework.beans;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午12:00:51
 * @TODO
 * 
 */
public class PropertyValue {

	/** 属性名称 */
	private final String name;

	/** 属性值 */
	private final Object value;

	public PropertyValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

}
