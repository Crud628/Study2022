package com.lan.springframework.beans.factory.config;
/**
 * @author Keason
 * @version 创建时间：2022年11月15日 下午10:28:13
 * @TODO
 * 
 */
public interface SingletonBeanRegistry {
	/**
     * 返回在给定名称下注册的（原始）单例对象。
     * @param beanName 要查找的bean的名称
     * @return 返回注册的单例对象
     */
    Object getSingleton(String beanName);

    /**
     * 注册单利对象
     * @param beanName          Bean 对象名称
     * @param singletonObject   Bean 对象
     */
    void registerSingleton(String beanName, Object singletonObject);
}
