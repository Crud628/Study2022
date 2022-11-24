package com.lan.springframework.context;

import com.lan.springframework.beans.factory.HierarchicalBeanFactory;
import com.lan.springframework.beans.factory.ListableBeanFactory;
import com.lan.springframework.core.io.ResourceLoader;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:20:49
 * @TODO
 * 
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher  {
}
