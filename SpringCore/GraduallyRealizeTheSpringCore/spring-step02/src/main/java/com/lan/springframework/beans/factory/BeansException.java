package com.lan.springframework.beans.factory;
/**
 * @author Keason
 * @version 创建时间：2022年11月12日 下午6:45:27
 * @TODO 定义 Bean 异常
 * 
 */
public class BeansException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = -246183456531650755L;

	public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
