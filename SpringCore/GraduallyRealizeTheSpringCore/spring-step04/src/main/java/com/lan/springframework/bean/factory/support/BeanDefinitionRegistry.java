package com.lan.springframework.bean.factory.support;

import com.lan.springframework.bean.factory.config.BeanDefinition;

/**
 * @author Keason
 * @version 创建时间：2022年11月14日 下午9:11:56
 * @TODO Bean 定义注册接口
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
