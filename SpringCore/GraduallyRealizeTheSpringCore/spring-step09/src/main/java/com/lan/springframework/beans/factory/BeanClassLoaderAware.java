package com.lan.springframework.beans.factory;
/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午9:29:29
 * @TODO
 * 
 */
public interface BeanClassLoaderAware {
	void setBeanClassLoader(ClassLoader classLoader);
}
