package com.lan.springframework.context.event;

import com.lan.springframework.context.ApplicationContext;
import com.lan.springframework.context.ApplicationEvent;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:30:08
 * @TODO
 * 
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}
