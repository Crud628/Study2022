package com.lan.springframework.context;

import com.lan.springframework.beans.BeansException;
import com.lan.springframework.beans.factory.Aware;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午7:55:45
 * @TODO
 * 
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}