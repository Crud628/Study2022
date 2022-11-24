package com.lan.springframework.context;

import java.util.EventObject;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:21:32
 * @TODO
 * 
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }

}
