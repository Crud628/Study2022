package com.lan.springframework.test.common;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.PropertyValue;
import com.lan.springframework.beans.PropertyValues;
import com.lan.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.lan.springframework.beans.factory.config.BeanDefinition;
import com.lan.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午10:02:53
 * @TODO
 * 
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
