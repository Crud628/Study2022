package com.lan.springframework.context.event;
/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:20:33
 * @TODO
 * 
 */
public class ContextClosedEvent extends ApplicationContextEvent{

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }

}
