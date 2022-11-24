package com.lan.springframework.beans.factory;

import java.util.Map;

import com.lan.springframework.beans.BeansException;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:15:28
 * @TODO
 * 
 */
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();

}
