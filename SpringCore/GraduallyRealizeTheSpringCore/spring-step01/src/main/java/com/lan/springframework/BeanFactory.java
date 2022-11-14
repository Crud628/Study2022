package com.lan.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Keason
 * @version 创建时间：2022年11月12日 下午1:16:26
 * @TODO 简单的 Bean 工厂
 * 用于生成和使用的Bean工厂
 * @since 0.01
 */
public class BeanFactory {
	
	/**
	 * 容器
	 */
	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
	
	/**
	 * 获取对象
	 * @param name
	 * @return
	 */
    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    /**
     * 注册对象
     * @param name
     * @param beanDefinition
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
