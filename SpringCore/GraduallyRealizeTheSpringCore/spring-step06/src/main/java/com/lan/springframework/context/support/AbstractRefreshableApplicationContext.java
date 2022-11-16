package com.lan.springframework.context.support;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.lan.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午9:57:54
 * @TODO
 * 
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
	private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
