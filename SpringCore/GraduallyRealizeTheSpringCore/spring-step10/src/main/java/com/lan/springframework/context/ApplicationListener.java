package com.lan.springframework.context;

import java.util.EventListener;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:22:10
 * @TODO
 * 
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);

}