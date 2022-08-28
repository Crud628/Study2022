package com.lan.springbootmall.common;

import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

/**
 * @author Keason
 * @Description: 常量类
 * @date 2022/8/28 17:34
 */
public class Constants {
 /**
  * md5盐
  */
 public static final String MD5_SALT = "sjfha23suh_dfboia!buo657ik,.";

 /**
  * 用户密码最大长度
  */
 public static final int PASSWORD_MAX_LENGTH = 8;

 /**
  * 登录Session的KEY
  */
 public static final String SESSION_KEY_MALL_USER = "MALL_USER";
}
