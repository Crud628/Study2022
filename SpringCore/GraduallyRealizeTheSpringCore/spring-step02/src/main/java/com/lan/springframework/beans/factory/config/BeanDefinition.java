package com.lan.springframework.beans.factory.config;
/**
 * @author Keason
 * @version 创建时间：2022年11月12日 下午6:23:41
 * @TODO 定义 Bean 实例信息
 *  只注册类信息、不直接注册实例化信息  Object->Class
 */
public class BeanDefinition {
	/**
	 * 从Object升级为class
	 */
    private Class beanClass;

    /**
     * 构造函数
     * @param beanClass
     */
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
