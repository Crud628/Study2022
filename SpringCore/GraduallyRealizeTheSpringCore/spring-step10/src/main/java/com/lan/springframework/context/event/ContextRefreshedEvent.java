package com.lan.springframework.context.event;
/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:28:44
 * @TODO
 * 
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }

}
