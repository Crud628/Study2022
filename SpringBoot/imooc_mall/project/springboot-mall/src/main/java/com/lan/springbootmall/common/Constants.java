package com.lan.springbootmall.common;

import com.google.common.collect.Sets;
import com.lan.springbootmall.exception.MallException;
import com.lan.springbootmall.exception.MallExceptionEnum;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

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

 public interface ProductListOrderBy {
  Set<String> PRICE_ORDER_ENUM = Sets.newHashSet("price desc", "price asc");
 }
 public interface SaleStatus {

  int NOT_SALE = 0;//商品下架状态
  int SALE = 1;//商品上架状态
 }

 public interface Cart {

  int UN_SELECTED = 0;//购物车未选中状态
  int SELECTED = 1;//购物车选中状态
 }

 /**
  * 订单状态
  */
 public enum OrderStatusEnum {
  CANCELED(0, "用户已取消"),
  NOT_PAID(10, "未付款"),
  PAID(20, "已付款"),
  DELIVERED(30, "已发货"),
  FINISHED(40, "交易完成");

  private String value;
  private int code;

  OrderStatusEnum(int code, String value) {
   this.value = value;
   this.code = code;
  }

  public static OrderStatusEnum codeOf(int code) {
   for (OrderStatusEnum orderStatusEnum : values()) {
    if (orderStatusEnum.getCode() == code) {
     return orderStatusEnum;
    }
   }
   throw new MallException(MallExceptionEnum.NO_ENUM);
  }

  public String getValue() {
   return value;
  }

  public void setValue(String value) {
   this.value = value;
  }

  public int getCode() {
   return code;
  }

  public void setCode(int code) {
   this.code = code;
  }
 }

 public static String ICODE;

 @Value("${icode}")
 public void setICODE(String icode) {
  ICODE = icode;
 }
}
