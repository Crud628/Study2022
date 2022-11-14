package com.lan.springframework.beans;

/**
 * 
 * @author Keason
 * @description 定义 Bean 异常
 * @since 0.02
 */
public class BeansException extends RuntimeException{

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
