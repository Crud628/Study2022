package com.lan.springframework.beans.factory.support;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.core.io.Resource;
import com.lan.springframework.core.io.ResourceLoader;


/**
 * @author Keason
 * @version 创建时间：2022年11月15日 下午10:55:09
 * @TODO
 * 
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
