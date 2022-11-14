package com.lan.springframework.bean;
/**
 * @author Keason
 * @version 创建时间：2022年11月14日 下午8:20:43
 * @TODO 定义 Bean 异常
 * @since 0.02
 */
public class BeansException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9033128998275700919L;

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
