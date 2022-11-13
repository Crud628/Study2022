package com.lan.springframework.beans.factory.support;

import java.util.HashMap;
import java.util.Map;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.factory.config.BeanDefinition;

/**
 * 
 * @author Keason
 * @description 默认的Bean工厂实现类
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

}
