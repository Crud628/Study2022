package com.lan.springframework.beans.factory;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午9:30:38
 * @TODO
 * 
 */
public interface DisposableBean {
	void destroy() throws Exception;
}