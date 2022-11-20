package com.lan.springframework.core.io;

/**
 * @author Keason
 * @version 创建时间：2022年11月20日 下午7:53:38
 * @TODO
 * 
 */
public interface ResourceLoader {
	/**
	 * Pseudo URL prefix for loading from the class path: "classpath:"
	 */
	String CLASSPATH_URL_PREFIX = "classpath:";

	Resource getResource(String location);
}