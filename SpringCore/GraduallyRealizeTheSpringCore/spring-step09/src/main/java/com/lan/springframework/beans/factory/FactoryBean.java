package com.lan.springframework.beans.factory;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午9:30:59
 * @TODO
 * 
 */
public interface FactoryBean<T> {

	T getObject() throws Exception;

	Class<?> getObjectType();

	boolean isSingleton();

}
