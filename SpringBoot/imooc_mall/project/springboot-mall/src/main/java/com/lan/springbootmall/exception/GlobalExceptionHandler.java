package com.lan.springbootmall.exception;

import com.lan.springbootmall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 参数效验统一处理
     *
     * @param e 参数效验异常
     * @return 封装结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiRestResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: " ,e);
        return handleExceptionResult(e.getBindingResult());
    }

    /**
     * 多个语句处理
     *
     * @param result
     * @return
     */
    private ApiRestResponse handleExceptionResult(BindingResult result) {
        // 把异常处理为对外暴露的提示
        List<String> list = new ArrayList<String>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (int i = 0; i < allErrors.size(); i++) {
                ObjectError objectError = allErrors.get(i);
                String message = objectError.getDefaultMessage();
                list.add(message);
            }
        }
        if (ObjectUtils.isEmpty(list)) {
            return ApiRestResponse.error(MallExceptionEnum.REQUEST_PARAM_ERROR);
        }
        return ApiRestResponse.error(MallExceptionEnum.REQUEST_PARAM_ERROR.getCode(), list.toString());
    }
}
