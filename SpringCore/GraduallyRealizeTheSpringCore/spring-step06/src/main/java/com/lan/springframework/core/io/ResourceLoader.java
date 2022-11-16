package com.lan.springframework.core.io;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午9:17:25
 * @TODO 资源加载器
 * 
 */
public interface ResourceLoader {
	/**
	 * Pseudo URL prefix for loading from the class path: "classpath:"
	 */
	String CLASSPATH_URL_PREFIX = "classpath:";

	Resource getResource(String location);

}
