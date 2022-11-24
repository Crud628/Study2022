package com.lan.springframework.beans.factory;
/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:15:16
 * @TODO
 * 
 */
public interface InitializingBean {
    /**
     * Bean 处理了属性填充后调用
     * 
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
