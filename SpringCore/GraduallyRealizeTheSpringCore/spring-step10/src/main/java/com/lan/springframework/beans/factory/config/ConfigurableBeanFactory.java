package com.lan.springframework.beans.factory.config;

import com.lan.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:10:02
 * @TODO
 * 
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

}
