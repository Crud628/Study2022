package com.lan.springframework.beans.factory.support;

import com.lan.springframework.beans.factory.config.BeanDefinition;

/**
 * 
 * @author Keason
 * @description Bean 定义注册接口
 * @since 0.02
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName       Bean 名称
     * @param beanDefinition Bean 定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
