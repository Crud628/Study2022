package com.lan.springbootmall.common;

import com.lan.springbootmall.exception.MallExceptionEnum;

/**
 * @author Keason
 * @Description: 通用返还对象
 * @date 2022/8/25 22:37
 */
public class ApiRestResponse<T> {

 /**
  * 状态
  */
 private Integer status;

 /**
  * 提示信息
  */
 private String msg;

 /**
  * 返回数据
  */
 private T data;

 /**
  * 成功返回代码
  */
 private static final int OK_CODE = 10000;

 /**
  * 成功返回信息
  */
 private static final String OK_MSG = "SUCCESS";

 /**
  * 满参数构造器
  * @param status 状态
  * @param msg 消息
  * @param data 数据
  */
 public ApiRestResponse(Integer status, String msg, T data) {
  this.status = status;
  this.msg = msg;
  this.data = data;
 }

 /**
  * 满参数构造器
  * @param status 状态
  * @param msg 消息
  */
 public ApiRestResponse(Integer status, String msg) {
  this.status = status;
  this.msg = msg;
 }

 /**
  * 无参构造
  */
 public ApiRestResponse() {
  this(OK_CODE, OK_MSG);
 }

 /**
  * 成功参数构造
  *
  * @param <T> 数据
  * @return 空数据
  */
 public static <T> ApiRestResponse<T> success() {
   return  new ApiRestResponse<>();
 }

 /**
  * 带返回结果的成功构造函数
  * @param result 结果
  * @param <T> 包装后的结果
  * @return 包装后的结果
  */
 public static <T> ApiRestResponse<T> success(T result) {
  ApiRestResponse restResponse = new ApiRestResponse<>();
  restResponse.setData(result);
  return restResponse;
 }

 /**
  * 带返回结果的失败构造函数
  * @param code 结果代码
  * @param msg 结果消息
  * @param <T> 结果数据
  * @return 包装后的结果数据
  */
 public static <T> ApiRestResponse<T> error(Integer code, String msg) {
  return new ApiRestResponse<>(code, msg);
 }

 /**
  * 带返回结果的失败构造函数
  *
  * @param ex 异常
  * @param <T> 封装数据
  * @return 封装数据
  */
 public static <T> ApiRestResponse<T> error(MallExceptionEnum ex) {
  return new ApiRestResponse<>(ex.getCode(), ex.getMsg());
 }

 /**
  * 状态
  * @return 状态
  */
 public Integer getStatus() {
  return status;
 }

 /**
  * 状态
  * @param status 状态
  */
 public void setStatus(Integer status) {
  this.status = status;
 }

 /**
  * 数据
  * @return 数据
  */
 public T getData() {
  return data;
 }

 /**
  * 数据
  * @param data 数据
  */
 public void setData(T data) {
  this.data = data;
 }

 /**
  * 消息
  * @return 消息
  */
 public String getMsg() {
  return msg;
 }

 /**
  * 消息
  * @param msg 消息
  */
 public void setMsg(String msg) {
  this.msg = msg;
 }

 /**
  * 序列化输出
  * @return 序列化后的结果
  */
 @Override
 public String toString() {
  return "ApiRestResponse{" +
          "status=" + status +
          ", msg='" + msg + '\'' +
          ", data=" + data +
          '}';
 }
}
