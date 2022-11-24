package com.lan.springframework.context.event;

import com.lan.springframework.beans.factory.BeanFactory;
import com.lan.springframework.context.ApplicationEvent;
import com.lan.springframework.context.ApplicationListener;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:28:25
 * @TODO
 * 
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }

}
