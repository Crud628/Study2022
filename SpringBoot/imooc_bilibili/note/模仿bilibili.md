# 一.  导学

## 1.1  项目架构

### 1.1.1  项目架构分类

#### 1.1.1.1  业务（功能）架构

顶层：用户服务，如注册登录、大会员权限、查找感兴趣视频等

中间层：在线视频流播放 + 实时弹幕

底层：管理后台，如视频上传、数据统计、系统推送

#### 1.1.1.2  技术架构

技术选型：SpringBoot2.x + MySQL + MyBatis + Maven

开发模式：MVC

#### 1.1.1.2  部署架构

前端：服务转发 + 负载均衡

后端：业务处理 + 功能实现

工具：缓存、队列

## 1.2  开发环境搭建

多模块：MVC

## 热部署



# 二.  Restful风格和通用模块



## 三.用户注册与登录

数据库表设计：用户表、用户信息表

```sql
-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '盐值',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` bigint DEFAULT NULL COMMENT '用户id',
  `nick` varchar(100) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `sign` text COMMENT '签名',
  `gender` varchar(2) DEFAULT NULL COMMENT '性别：0男 1女 2未知',
  `birth` varchar(20) DEFAULT NULL COMMENT '生日',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户基本信息表';
```



相关接口（API）：获取RSA公匙、用户注册、用户登录

### 基于JWT的用户token验证



#### 基于session的用户身份验证

验证过程：服务端验证浏览器携带的用户名和密码，验证通过后生成用户平成保存在服务端（session），浏览器再次访问时，服务端查询session，实现登录状态保持。

缺点：随着用户的增多，服务端压力增大；若浏览器cookie被攻击者拦截，容易受到跨站请求未遭攻击；分布式系统下扩展性不强。

#### 基于token的用户身份验证

验证过程：服务端验证浏览器携带的用户名和密码，验证通过后生成用户令牌（token）并返回给浏览器，浏览器再次访问时携带token，服务端校验token并返回相关数据

优点：token不储存在服务器，不会造成服务器压力；token可以存储在非cookie中，安全性高；分布式系统下扩展性强



#### JWT

全称是JSON Web Token，JWT是一个规范，用于在空间受限环境下安全传递“声明”。

JWT的组成：JWT分成三部分，第一部分是头部（header），第二部分是载荷（payload），第三部分是签名（signature）

- JWT头部：声明的类型、声明的加密算法（通常使用SHA256）
- JWT载荷：存放有效信息，一般包含签发者、所面向的用户、接受方、过期时间、签发时间以及唯一身份标识
- JWT签名：主要由头部、载荷以及秘钥组合加密而成

JWT优点：跨语言支持、便于传输、易于扩展























