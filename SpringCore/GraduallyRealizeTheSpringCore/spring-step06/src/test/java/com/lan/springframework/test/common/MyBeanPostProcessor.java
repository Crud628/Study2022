package com.lan.springframework.test.common;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.factory.config.BeanPostProcessor;
import com.lan.springframework.test.bean.UserService;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午10:03:03
 * @TODO
 * 
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
