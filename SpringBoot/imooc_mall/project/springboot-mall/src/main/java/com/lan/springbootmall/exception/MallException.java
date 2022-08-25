package com.lan.springbootmall.exception;

/**
 * @author Keason
 * @Description: 统一异常
 * @date 2022/8/26 0:03
 */
public class MallException extends Exception{
    private final Integer code;
    private final String message;

    public MallException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public MallException(MallExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
