package com.lan.springframework.core.io;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午9:25:06
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
