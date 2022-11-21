package com.lan.springframework.beans.factory.config;
/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午9:32:50
 * @TODO
 * 
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
