package com.lan.springframework.context.support;

import com.lan.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.lan.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:27:23
 * @TODO
 * 
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();

}

