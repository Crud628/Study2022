package com.lan.springframework.beans.factory;
/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:13:09
 * @TODO
 * 
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}