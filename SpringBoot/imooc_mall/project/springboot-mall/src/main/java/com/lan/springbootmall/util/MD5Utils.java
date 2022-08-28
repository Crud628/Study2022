package com.lan.springbootmall.util;

import com.lan.springbootmall.common.Constants;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Keason
 * @Description: 加密工具
 * @date 2022/8/28 17:25
 */
public class MD5Utils {

 /**
  * Base64加密
  * @param StrValue 要加密的字符串
  * @return 加密后的字符串
  * @throws NoSuchAlgorithmException MD5工具实例生成捕获的失败异常
  */
 public static String getMD5Str(String StrValue) throws NoSuchAlgorithmException {
  MessageDigest md5 = MessageDigest.getInstance("MD5");
  return Base64.encodeBase64String(md5.digest((StrValue + Constants.MD5_SALT).getBytes()));
 }
}
