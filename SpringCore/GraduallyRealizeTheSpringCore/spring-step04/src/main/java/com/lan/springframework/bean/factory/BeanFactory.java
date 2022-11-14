package com.lan.springframework.bean.factory;

import com.lan.springframework.bean.BeansException;

/**
 * @author Keason
 * @version 创建时间：2022年11月14日 下午8:24:18
 * @TODO 定义 Bean 工厂接口
 * 
 */
public interface BeanFactory {
	/**
     * 返回 Bean 的实例对象
     * @param name 要检索的bean的名称
     * @return 实例化的 Bean 对象
     * @throws BeansException 不能获取 Bean 对象，则抛出异常
     */
    Object getBean(String name) throws BeansException;

    /**
     * 返回含构造函数的 Bean 实例对象
     * @param name 要检索的bean的名称
     * @param args 构造函数入参
     * @return 实例化的 Bean 对象
     * @throws BeansException 不能获取 Bean 对象，则抛出异常
     */
    Object getBean(String name, Object... args) throws BeansException;
}