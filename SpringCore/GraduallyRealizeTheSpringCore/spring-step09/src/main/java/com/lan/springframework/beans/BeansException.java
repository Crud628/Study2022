package com.lan.springframework.beans;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午9:22:19
 * @TODO
 * 
 */
public class BeansException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4449662621849578492L;

	public BeansException(String msg) {
		super(msg);
	}

	public BeansException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
