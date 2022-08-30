package com.lan.springbootmall.common;

import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Keason
 * @Description: 常量类
 * @date 2022/8/28 17:34
 */
@Component
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

 /**
  * 用户角色：管理员用户
  */
 public static final int USER_ROLE_ADMIN = 2;

 /**
  * 文件上传路径
  */
 public static String FILE_UPLOAD_DIR;

 /**
  * 静态字符串需要方法初始化，并在类上加@Component注解
  * @param fileUploadDir 文件路径  来自@Value("${file.upload.dir}")
  */
 @Value("${file.upload.dir}")
 public void setFileUploadDir(String fileUploadDir) {
  FILE_UPLOAD_DIR = fileUploadDir;
 }
}
