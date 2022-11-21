package com.lan.springframework.context;

import com.lan.springframework.beans.BeansException;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午9:50:49
 * @TODO
 * 
 */
public interface ApplicationContextAware {
	void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
