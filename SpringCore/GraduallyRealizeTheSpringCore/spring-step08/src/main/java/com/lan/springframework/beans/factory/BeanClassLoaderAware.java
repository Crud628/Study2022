package com.lan.springframework.beans.factory;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午12:11:05
 * @TODO
 * 
 */
public interface BeanClassLoaderAware extends Aware {

	void setBeanClassLoader(ClassLoader classLoader);

}
