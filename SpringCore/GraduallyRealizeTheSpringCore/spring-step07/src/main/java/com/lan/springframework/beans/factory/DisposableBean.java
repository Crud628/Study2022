package com.lan.springframework.beans.factory;

/**
 * @author Keason
 * @version 创建时间：2022年11月19日 上午11:46:45
 * @TODO
 * 
 */
public interface DisposableBean {

	void destroy() throws Exception;

}
