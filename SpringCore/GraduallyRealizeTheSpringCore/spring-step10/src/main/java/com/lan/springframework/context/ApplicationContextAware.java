package com.lan.springframework.context;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.factory.Aware;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:21:17
 * @TODO
 * 
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
