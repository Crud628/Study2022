package com.lan.springframework.bean.factory.config;
/**
 * @author Keason
 * @version 创建时间：2022年11月14日 下午8:26:11
 * @TODO Bean 引用
 * @since 0.04
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
