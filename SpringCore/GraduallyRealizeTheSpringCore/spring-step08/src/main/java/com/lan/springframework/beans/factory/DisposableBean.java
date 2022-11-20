package com.lan.springframework.beans.factory;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午12:21:10
 * @TODO
 * 
 */
public interface DisposableBean {
	void destroy() throws Exception;
}
