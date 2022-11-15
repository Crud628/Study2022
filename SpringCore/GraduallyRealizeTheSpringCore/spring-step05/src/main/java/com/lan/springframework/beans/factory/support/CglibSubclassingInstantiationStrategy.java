package com.lan.springframework.beans.factory.support;

import java.lang.reflect.Constructor;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.factory.config.BeanDefinition;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * @author Keason
 * @version 创建时间：2022年11月15日 下午10:54:19
 * @TODO
 * 
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
	 @Override
	    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
	        Enhancer enhancer = new Enhancer();
	        enhancer.setSuperclass(beanDefinition.getBeanClass());
	        enhancer.setCallback(new NoOp() {
	            @Override
	            public int hashCode() {
	                return super.hashCode();
	            }
	        });
	        if (null == ctor) return enhancer.create();
	        return enhancer.create(ctor.getParameterTypes(), args);
	    }
}
