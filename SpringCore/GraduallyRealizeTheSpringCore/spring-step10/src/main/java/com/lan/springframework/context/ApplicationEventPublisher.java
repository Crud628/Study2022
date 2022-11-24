package com.lan.springframework.context;
/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:21:57
 * @TODO
 * 
 */
public interface ApplicationEventPublisher {
    /**
     * Notify all listeners registered with this application of an application
     * event. Events may be framework events (such as RequestHandledEvent)
     * or application-specific events.
     * @param event the event to publish
     */
    void publishEvent(ApplicationEvent event);
}
