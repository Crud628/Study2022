package com.lan.springframework.beans.factory;
/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:14:47
 * @TODO
 * 
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
