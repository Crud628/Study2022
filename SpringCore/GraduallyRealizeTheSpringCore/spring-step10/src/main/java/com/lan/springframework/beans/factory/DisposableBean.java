package com.lan.springframework.beans.factory;
/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:14:29
 * @TODO
 * 
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
