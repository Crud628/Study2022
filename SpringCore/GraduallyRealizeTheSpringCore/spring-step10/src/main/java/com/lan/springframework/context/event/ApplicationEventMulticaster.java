package com.lan.springframework.context.event;

import com.lan.springframework.context.ApplicationEvent;
import com.lan.springframework.context.ApplicationListener;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:30:25
 * @TODO
 * 
 */
public interface ApplicationEventMulticaster {

    /**
     * Add a listener to be notified of all events.
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);

}
