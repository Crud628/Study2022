package com.lan.springframework.beans.factory.support;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.factory.config.BeanDefinition;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午9:49:30
 * @TODO
 * 
 */
public interface BeanDefinitionRegistry {
	/**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName       Bean 名称
     * @param beanDefinition Bean 定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 使用Bean名称查询BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
