package com.lan.springframework.beans.factory.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.factory.DisposableBean;
import com.lan.springframework.beans.factory.config.SingletonBeanRegistry;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午8:07:03
 * @TODO
 * 
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
	private Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

}
