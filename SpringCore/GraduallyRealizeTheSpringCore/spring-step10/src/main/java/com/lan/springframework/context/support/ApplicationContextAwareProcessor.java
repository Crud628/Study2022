package com.lan.springframework.context.support;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.factory.config.BeanPostProcessor;
import com.lan.springframework.context.ApplicationContext;
import com.lan.springframework.context.ApplicationContextAware;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:27:38
 * @TODO
 * 
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
