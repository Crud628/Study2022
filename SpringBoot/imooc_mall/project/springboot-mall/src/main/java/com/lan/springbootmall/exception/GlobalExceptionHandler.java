package com.lan.springbootmall.exception;

import com.lan.springbootmall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Keason
 * @Description: 处理统一异常的handle
 * @date 2022/8/28 17:10
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 日志
     */
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常处理
     * @param e 系统异常
     * @return 系统异常信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e) {
        log.error("Default Exception: ", e);
        return ApiRestResponse.error(MallExceptionEnum.SYSTEM_ERROR);
    }

    /**
     * 业务异常处理
     * @param e 业务异常
     * @return 业务异常信息
     */
    @ExceptionHandler(MallException.class)
    @ResponseBody
    public Object handleException(MallException e) {
        log.error("Default Exception: ", e);
        return ApiRestResponse.error(e.getCode(), e.getMessage());
    }
}
