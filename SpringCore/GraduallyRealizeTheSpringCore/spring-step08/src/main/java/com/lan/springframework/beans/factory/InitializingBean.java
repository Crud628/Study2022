package com.lan.springframework.beans.factory;
/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午12:22:58
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
