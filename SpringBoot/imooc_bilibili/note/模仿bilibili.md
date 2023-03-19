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

#### 1.1.1.3  部署架构

前端：服务转发 + 负载均衡

后端：业务处理 + 功能实现

工具：缓存、队列

## 1.2  开发环境搭建

### 1.2.1  资源

JDK下载：Oracle官网下载

Maven下载：可使用IntelliJ IDEA内置版本或自行官网下载

### 1.2.2  创建多模块、多环境项目

- 多模块：创建MVC模式多模块项目，控制层（Controller）、服务层（Service）、数据层（Dao）
- 多环境：添加不同环境的properties配置文件
- 在IDEA中配置配置JDK与Maven

### 1.2.3  配置JDK与Maven

- 在IDEA中配置JDK1.8
- 配置本地Maven的setting文件
- 在IDEA中重写Maven默认配置

### 1.2.4  运行你的仿哔哩哔哩后端项目

在pom文件中引入`Springboot`框架相关依赖

在子模块pom文件中添加模块间依赖关系

添加启动入口，启动项目

### 1.2.5  搭建数据库与持久层框架

数据库：选用`Mysql`数据库，特点是：体积小、速度快、开源

下载安装`Mysql`



在项目中配置数据库

持久层框架：`Mybatis`，特点是：XML形式管理、支持动态`SQL`

在项目中添加`Mybatis`框架依赖、在配置文件中配置`Mybatis`

## 1.3  热部署

热部署就是当应用程序正在运行的时候升级软件或修改某一部分代码、配置文件时，无需手动重启应用，即可使修改的部分生效

热部署方式： `spring-boot-devtools`工具+`IDEA`配置

开启IDEA自动构建（Build project automatically）选项，

在Registry中勾选`Compile autoMake allow when app running`

启动项中开启热部署（Running Application Update Policies）

pom文件中添加`spring-boot-devtools`，配置文件中添加配置

# 二.  Restful风格和通用模块

### 2.1  RESTful风格接口设计

RESTful架构、HTTP方法语义、HTTP方法幂等性、RESTful接口设计原则

用户模块开发概要：通用功能与通用配置、用户相关功能

REST全称是Representational State Transfer，中文为表述性状态转移，REST指的是一组架构约束条件和原则

RESTful表述的是资源的状态性转移，在Web中资源就是URI(Uniform Resource Identifier)

如果一个架构符合REST的约束条件和原则，我们就称它为RESTful架构，HTTP是目前与REST相关的唯一实例

RESTful架构应该遵循统一的接口原则，应该使用标准的HTTP方法如GET和POST，并且遵循这些方法的语义

HTTP方法的语义

| **方法** | **语义**                                                     |
| -------- | ------------------------------------------------------------ |
| GET      | 获取指定的资源                                               |
| DELETE   | 删除指定的资源                                               |
| POST     | 发送数据给服务器，依据HTTP  1.1规范中的描述，结合实际项目开发经验，POST经常为了以统一的方法来涵盖以下功能：     1 在公告板，新闻组，邮件列表或类似的文章组中发布消息     2 通过注册新增用户     3 向数据处理程序提供一批数据，例如提交一个表单 |
| PUT      | 使用请求中的负载创建或者替换目标资源。PUT和POST的区别在于PUT是幂等的，而POST不是。幂等的含义可以理解为调用一次与连续调用多次是等价的（没有副作用或副作用不变） |

POST和PUT的区别

比较容易混淆的是HTTP POST和PUT

POST和PUT的区别容易被简单地误认为“POST表示创建资源，PUT表示更新资源”

u而实际上，二者均可用于创建资源，更为本质的差别是在幂等性方面

HTTP的幂等性

| **方法** | **幂等性**   | **幂等性分析**                                               |
| -------- | ------------ | ------------------------------------------------------------ |
| GET      | 具备幂等性   | 用于获取资源，没有副作用，所以是幂等的。请注意，这里强调的是一次和N次并不是说每次请求的结果相同，而是每次请求不会产生不同的副作用。 |
| DELETE   | 具备幂等性   | 用于删除资源，有副作用，但它应该满足幂等性，调用一次和N次对系统产生的副作用是相同的 |
| POST     | 不具备幂等性 | POST所指向资源并非POST要创建的资源本身，而是POST创建资源的接收者，比如POST:/news的含义是在news新闻组这个资源分类下新建一条新的新闻，所以两次相同的POST请求会在服务器端创建两份新的资源，它们是不同的。所以，POST方法不具备幂等性 |
| PUT      | 具备幂等性   | PUT对应的资源是要创建或更新的资源本身，语义是创建或更新，对同一资源进行多次PUT的副作用和一次PUT是相同的，因此，PUT方法具有幂等性 |

RESTful接口URL命名原则

u命名原则1：HTTP方法后跟的URL必须是名词且统一成名词复数形式

u命名原则2：URL中不采用大小写混合的驼峰命名，尽量采用全小写单词，如果需要连接多个单词，则采用“-”连接

u示例：/users、/users-fans；反例：/getUsers、/getUsersFans

RESTful接口URL分级原则

u分级原则1：一级用来定位资源分类，如/users即表示需要定位到用户相关资源

u分级原则2：二级仍用来定位具体某个资源，如/users/20即表示id为20的用户，再如/users/20/fans/1即表示id为20的用户的id为1的粉丝

u一条小建议：原则是为了让我们的开发更加规范，但是不能成为束缚我们开发的枷锁！

RESTful接口命名示例

| **URI**   | **方法** | **功能**         |
| --------- | -------- | ---------------- |
| /users    | GET      | 获取用户列表     |
| /users/20 | GET      | 获取id为20的用户 |
| /users    | POST     | 创建用户         |
| /users/20 | PUT      | 修改id为20的用户 |
| /users    | PUT      | 批量修改用户     |
| /users/20 | DELETE   | 删除id为20的用户 |

| **URI**                                                  | **方法** | **功能**       |
| -------------------------------------------------------- | -------- | -------------- |
| /users?gender=male                                       | GET      | 过滤           |
| /users?sort=created-time-desc                            | GET      | 排序           |
| /users?gender=male&sort=created-time-desc                | GET      | 过滤+排序      |
| /users?name=hellostar&gender=male&sort=created-time-desc | GET      | 搜索+过滤+排序 |
| /users?size=10&no=1                                      | GET      | 分页           |



### 2.2  通用功能与配置

通用功能：加解密工具（AES、RSA、MD5）、请求数据返回类

通用配置：Json请求信息转换配置、全局异常处理配置

# 三.用户注册与登录

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

### 用户关注与动态提醒

用户关注

数据库表设计：用户关注表、用户关注分组表

相关接口（API）：关注用户、关注列表、粉丝列表、分页查询用户 

动态提醒

数据库表设计：用户动态表

相关接口（API） ：用户发布动态、用户查询订阅内容的动态

设计模式：订阅发布模式

![image-20230319131101715](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230319131101715.png)

订阅发布模式 VS 观察者模式

| **条目** | **订阅发布模式**                                             | **观察者模式**                                       |
| -------- | ------------------------------------------------------------ | ---------------------------------------------------- |
| 角色     | 发布者（Producer）、订阅者（Consumer）、代理人（Broker）     | 观察者（Observer）、主题（Subject）                  |
| 耦合性   | 发布者和订阅者之间是完全解耦的关系，他们彼此不知道对方，完全通过代理人来执行事项。 | 观察者和主题之间是松耦合的关系，他们之间没有代理人。 |
| 总结     | 订阅发布模式≠观察者模式                                      |                                                      |

`RocketMQ`：纯java编写的开源消息中间件，特点是：高性能、低延迟、分布式事务

`Redis`：高性能缓存工具，数据存储在内存中，读写速度非常快

### 用户权限控制

权限控制是什么：控制用户对系统资源（URI）的操作

前端的权限控制：对页面或页面元素的权限控制

后端的权限控制：对接口及数据的权限控制

#### B站会员等级权限

| **权限（资源）** | **Lv0** | Lv1  | Lv2  | Lv3  | Lv4  | Lv5    | Lv6    |
| ---------------- | ------- | ---- | ---- | ---- | ---- | ------ | ------ |
| **视频投稿**     | √       | √    | √    | √    | √    | √      | √      |
| **滚动弹幕**     | ×       | √    | √    | √    | √    | √      | √      |
| **发送私信**     | ×       | √    | √    | √    | √    | √      | √      |
| **发送动态**     | ×       | √    | √    | √    | √    | √      | √      |
| **彩色弹幕**     | ×       | √    | √    | √    | √    | √      | √      |
| **高级弹幕**     | ×       | ×    | √    | √    | √    | √      | √      |
| **视频评论**     | ×       | ×    | √    | √    | √    | √      | √      |
| **顶部弹幕**     | ×       | ×    | ×    | √    | √    | √      | √      |
| **底部弹幕**     | ×       | ×    | ×    | √    | √    | √      | √      |
| **风纪委员**     | ×       | ×    | ×    | ×    | √    | √      | √      |
| **点评功能**     | ×       | ×    | ×    | ×    | √    | √      | √      |
| **购买邀请码**   | ×       | ×    | ×    | ×    | ×    | 1个/月 | 2个/月 |

访问权限：哪些页面可以访问、哪些页面元素可见等等

操作权限：如页面按钮是否可点击、是否可以增删改查等等

接口与数据权限：接口是否可以调用、接口具体字段范围等等

会员等级权限控制分类

| **权限控制**     | **访问权限** | **操作权限** | **接口权限** | **数据权限** |
| ---------------- | ------------ | ------------ | ------------ | ------------ |
| **视频投稿**     |              | √            | √            |              |
| **发送私信**     |              | √            | √            |              |
| **发送动态**     |              |              | √            |              |
| **各种弹幕功能** |              | √            |              | √            |
| **视频评论**     |              | √            | √            |              |
| **风纪委员**     | √            |              |              |              |
| **点评功能**     |              | √            | √            |              |
| **购买邀请码**   | √            |              |              | √            |

#### RBAC权限控制模型

RBAC权限控制模型（Role-Based Access Control）：基于角色的权限控制

RBAC模型的层级：RBAC0、RBAC1、RBAC2、RBAC3

关键词：用户、角色、资源、权限、操作

用户：注册用户；角色：Lv0~Lv6会员

权限：视频投稿、发布动态、各种弹幕功能等等

资源：页面、页面元素；操作：点击、跳转、增删改查等等

数据库表设计：角色表、用户角色关联表、元素操作权限表、角色元素操作权限关联表、页面菜单权限表、角色页面菜单权限关联表

![image-20230319132710992](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230319132710992.png)

### Spring AOP

概念：Spring AOP是一种约定流程的编程

关键词：约定（AOP的核心）

典型的例子：数据库事务包括打开数据库，设置属性，执行sql语句，没有异常则提交事务，有异常则回滚事务，最后关闭数据库连接

Spring AOP术语

连接点（`join point`）：对应的被拦截的对象

切点（`point cut`）：通过正则或指示器的规则来适配连接点

切面（`aspect`）：可以定义切点、各类通知和引入的内容

通知（`advice`）：，分为前置通知（`before`）、后置通知（`after`）、事后返回通知（`afterReturning`）、异常通知（`afterThrowing`）

织入（`weaving`）：为原有服务（`service`）对象生成代理对象，然后将与切点匹配的连接点拦截，并将各类通知加入约定流程

目标对象（`target`）：被代理对象

# 四.  打造高性能的视频与弹幕系统

视频与弹幕功能开发概要

`FastDFS`文件服务器搭建、相关工具类开发

视频上传、视频处理、视频获取、视频在线播放、视频下载

弹幕系统、数据统计、社交属性（点赞、投币、收藏、评论）

## Fast DFS文件服务器

> Fast DFS文件服务器搭建、相关工具类开发
>
> 视频上传、视频处理、视频获取、视频在线播放、视频下载
>
> 弹幕系统、数据统计、社交属性（点赞、投币、收藏、评论）



### 什么是Fast DFS：

开源的轻量级分布式文件系统，用于解决大数据量存储和负载均衡等问题。

### 优点：

支持HTTP协议传输文件（结合Nginx）；

对文件内容做Hash处理，节约磁盘空间；支持负载均衡、整体性能较佳

适用系统类型：中小型系统

Fast DFS的二个角色：跟踪服务器（Tracker）、存储服务器（Storage）

跟踪服务器：主要做调度工作，起到负载均衡的作用。它是客户端和存储服务器交互的枢纽

存储服务器：主要提供容量和备份服务，存储服务器是以组（Group）为单位，每个组内可以有多台存储服务器，数据互为备份。文件及属性（Meta Data）都保存在该服务器上

Fast DFS架构图

![image-20230312102445191](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312102445191.png)

## Nginx

Nginx是反向代理服务器。代理其实就是中间人，客户端通过代理发送请求到互联网上的服务器，从而获取想要的资源。

Nginx的主要用途：反向代理、负载均衡。

Nginx的主要特点：跨平台、配置简单易上手、高并发、内存消耗小、稳定性高



正向代理的特点：服务端不知道客户端、客户端知道代理端

![image-20230312102909977](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312102909977.png)

反向代理的特点：服务端知道客户端、客户端不知道代理端

![image-20230312103220576](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312103220576.png)

Nginx结合FastDFS实现文件资源HTTP访问

![image-20230312103340206](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312103340206.png)

### Centos7部署单机模式`FastDFS`

#### 1、下载安装参考：

https://github.com/happyfish100/fastdfs/wiki

![image-20230312103842115](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312103842115.png)

#### 2、使用命令：

```shell
# 查看CentOS版本（需要7.x版本）
cat /etc/redhat-release 
```



![image-20230312103900074](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312103900074.png)

执行编译环境指令：

```shell
yum install git gcc gcc-c++ make automake autoconf libtool pcre pcre-devel zlib zlib-devel openssl-devel wget vim -y
```

等待系统完成，如下图：

![image-20230312104021600](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104021600.png)

#### 3、新建`/home/dfs/`目录用来存储数据；切换到`/usr/local/src`下

![image-20230312104042113](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104042113.png)

![image-20230312104049498](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104049498.png)

#### 4、下载`libfastcommon`，使用`git clone`命令：

```shell 
git clone https://github.com/happyfish100/libfastcommon.git --depth 1
```

如果`git clone`无法下载的话可以自行在浏览器下载并上传服务器，如下图：

![image-20230312104121752](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104121752.png)

将下载好的软件包放在`/usr/local/src`下（如果是自行下载上传的需要在解压一下），如图所示：

![image-20230312104336943](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104336943.png)

#### 5、使用命令`cd`进入解压后的文件夹

如图所示：

![image-20230312104345900](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104345900.png)

#### 6、执行

```shell
./make.sh && ./make.sh install 
```

进行编译安装，如下图所示：

![image-20230312104404130](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104404130.png)

#### 7、安装`libfastcommon`完成后，继续安装`FastDFS`，依然可以使用命令：

```shell 
git clone https://github.com/happyfish100/fastdfs.git --depth 1
```

如果git不生效，可以自行下载，如下图所示：

![image-20230312104420134](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104420134.png)

![image-20230312104424440](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104424440.png)

#### 8、下载`FastDFS`完成后，流程同`libfastcommon`，进入响应的文件夹执行编译：

![image-20230312104438151](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104438151.png)

#### 9、准备`FastDFS`配置文件，执行以下命令，如图所示：

![image-20230312104449336](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104449336.png)

执行后的效果如下图所示：

![image-20230312104458843](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104458843.png)

#### 10、安装`fastdfs-nginx-module`

Git命令：

```shell
git clone https://github.com/happyfish100/fastdfs-nginx-module.git --depth 1
```

或自行下载：

![image-20230312104513365](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104513365.png)

执行一下命令：

```
cp /usr/local/src/fastdfs-nginx-module/src/mod_fastdfs.conf /etc/fdfs
```

![image-20230312104605196](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104605196.png)

#### 11、下载安装Nginx

```
下载：wget http://nginx.org/download/nginx-1.15.4.tar.gz
解压：tar -zxvf nginx-1.15.4.tar.gz #解压
```

![image-20230312104632606](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104632606.png)

进入解压后的`nginx`文件夹：

![image-20230312104650096](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104650096.png)

添加fastdfs-nginx-module模块

命令：

```shell
./configure --add-module=/usr/local/src/fastdfs-nginx-module/src/ 
```

![image-20230312104533247](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104533247.png)

#### 12、配置`tracker`，执行命令：

```shell
vim /etc/fdfs/tracker.conf
```

修改

```shell
base_path=/home/dfs
```

![image-20230312104709193](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104709193.png)

#### 13、配置storage

执行命令：

```shell
vim /etc/fdfs/storage.conf
```

![image-20230312104722557](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104722557.png)

修改内容：

```shell
base_path=/home/dfs # 数据和日志文件存储根目录

store_path0=/home/dfs # 第一个存储目录

tracker_server=本机ip:22122 # 本机ip改成自己服务器的ip

http.server_port=8888 # http访问文件的端口(默认8888,看情况修改,和nginx中保持一致)
```

#### 14、启动tracker和storage

```shell
#启动tracker：
fdfs_trackerd /etc/fdfs/tracker.conf
#启动storage：
fdfs_storaged /etc/fdfs/storage.conf
```

#### 15、使用client进行测试

首先通过命令

```shell
vim /etc/fdfs/client.conf
```

修改client配置文件

![image-20230312104740881](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104740881.png)

```shell
#需要修改的内容如下：
base_path=/home/dfs
tracker_server=本机ip:22122    #本机ip修改为服务器ip
```

最后执行命令：

```shell
fdfs_upload_file /etc/fdfs/client.conf /usr/local/src/nginx-1.15.4.tar.gz
#保存后测试,返回ID表示成功 如：group1/M00/00/00/xx.tar.gz
```

![image-20230312104751277](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104751277.png)

#### 15、配置`nginx`访问，具体内容如下：

首先修改`mod_fastdfs.conf`配置文件，使用命令

```shell
vim /etc/fdfs/mod_fastdfs.conf
```

需要修改的内容如下：

```shell
tracker_server=本机ip:22122 #本机ip修改为服务器ip
url_have_group_name=true
store_path0=/home/dfs
```

 接着，修改`nginx`配置，命令为：

```shell
vim /usr/local/nginx/conf/nginx.conf
```

添加如下内容，如图所示：

```shell
server {
  listen    8888;  ## 该端口为storage.conf中的http.server_port相同
  server_name localhost;
  location ~/group[0-9]/ {
    ngx_fastdfs_module;
  }
  error_page  500 502 503 504 /50x.html;
  location = /50x.html {
  root  html;
  }
}
```



![image-20230312104809274](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230312104809274.png)

#### 16、启动`nginx`

使用命令：

```shell
/usr/local/nginx/sbin/nginx
```

在浏览器中访问之前上传的文件地址：`http://本机ip:8888/group1/M00/00/00/xx.tar.gz`测试是否能访问到

### 弹幕系统

场景分析：客户端针对某一视频创建了弹幕，发送后端进行处理，后端需要对所有正在观看该视频的用户推送该弹幕

实现方式：使用短连接进行通信或使用长连接进行通信

短连接实现方案：所有观看视频的客户端不断轮询后端，若有新的弹幕则拉取后进行显示

短连接实现方案的缺点：轮询的效率低，非常浪费资源（因为HTTP协议只能由客户端向服务端发起，故必须不停连接后端）

长连接实现方案：采用WebSocket进行前后端通信

为什么要用`WebSocket：HTTP `协议的通信只能由客户端发起，做不到服务器主动向客户端推送信息。

`WebSocket`简介：WebSocket协议是基于TCP的一种新的网络协议。它实现了浏览器与服务器全双工(Full-Duplex)通信

全双工(`Full-Duplex`)通信：客户端可以主动发送信息给服务端，服务端也可以主动发送信息给客户端

`WebSocket`协议优点：报文体积小、支持长连接

弹幕系统架构设计

![image-20230319133634661](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20230319133634661.png)

# 五.  从全局的角度出发打造你的系统

系统全文搜索：搜索引擎`ElasticSearch`、可视化`Kibana`、`SpringBoot`整合`ElasticSearch`实现全文搜索

内容推荐、观看记录：通过记录观看记录，获取视频内容，通过推荐公式对用户进行个性化内容推荐

弹幕遮罩：处理视频生成人像黑白剪影，实现遮挡弹幕效果

## `ElasticSearch`

传统上，我们以行和列的形式存储数据到关系型数据库中。但是这种存储形式在存储复杂问题的时候会很不灵活。

如果我们可以将对象按对象的方式来存储，这样我们就能更加专注于使用数据，重新利用对象的灵活性。

JSON 是一种以人可读的文本表示对象的方法。 它已经变成 NoSQL 世界交换数据的事实标准。

`ElasticSearch `是一个分布式、可扩展、实时的搜索与数据分析引擎

`Elasticsearch `也是使用 `Java `编写的，建立在一个全文搜索引擎库 `Apache Lucene`基础之上，`Lucene`本身非常复杂

为了使检索变得简单，`ElasticSearch`提供了一套简单一致的 RESTful API

特点：面向文档、JSON

面向文档：意味着存储的是整个对象或文档，而不是类似于`Mysql`的行列数据

`JSON`：使用`JSON`作为文档序列化格式，简单简洁且易于阅读

## `Kibana`

`Kibana`是一个免费且开放的用户界面，它可以对`Elasticsearch `的数据进行视觉探索和实时分析

本项目使用`Elasticsearch `+ `Kibana`的方式实现搜索引擎数据可视化解决方案

## 观看记录

观看记录延伸的统计数据：播放量

播放量计数规则：同一个用户一天1次，游客一天1次（用户退出登录后观看也参与计算播放量）

游客区分规则：操作系统+浏览器+IP

## 内容推荐

使用协同过滤算法来实现内容推荐，程序开发选用`Apache Mahout`来进行具体实现

`Mahout`是一个开源的分布式机器学习算法的算法库，它是一个基于`Java`实现的可扩展的、高效的推荐引擎

`Mahout`常用推荐算法：基于用户的推荐、基于内容的推荐

### 推荐算法步骤

1、收集用户偏好数据，用户的偏好数据可以体现在多种操作行为上，如点赞、收藏、转发、是否购买等

2、数据降噪与归一化处理：不同偏好维度的数据需要进行数据降噪与归一化处理来形成统一的偏好得分

3、算出相似物品或者用户：基于用户的推荐、基于内容的推荐

### 基于用户的推荐

核心思想：推荐和此用户相似的用户喜欢的内容

本质是基于用户对内容的偏好找到相邻的相似用户，然后将邻居用户喜欢的内容推荐给当前用户

举个例子：张三爱看恐怖电影、爱情电影，李四爱看喜剧电影、王五爱看爱情电影、恐怖电影、纪录片，那么张三和王五的相似度比较高，所以推荐张三看纪录片

### 基于物品的推荐

核心思想：推荐和此用户喜欢内容的相似内容给该用户

本质是基于用户对内容的偏好找到相似的内容，然后依据用户的历史行为偏好，推荐相似的内容给用户

举个例子：张三以前买手机，则会把和手机相似度高的其他商品推荐给张三

## 弹幕遮罩

原理：截取视频帧截图，将截图内容进行人像分割，最后将人体轮廓图转换为黑白剪影图，前端通过黑白剪影图覆盖原视频形成遮挡弹幕的效果

关键词：截取视频帧、人像分割、图转换、覆盖

其中，截取视频我们将使用`javacv`提供的功能库，人像分割及图形转换我们通过调用百度提供的免费`api`来实现

# 六.  分布式

Git+Jenkins+Maven实现自动化部署

SpringCloud、Eureka、网关、服务间调用

## 自动化部署

自动化部署：部署的每一步骤都实现自动化

本项目采用`Jenkins`+`Git`+`Maven`的自动化部署方案

步骤：通过`git`获取项目代码，使用`maven`打包成可执行`jar`包，通过`jenkins`推送到应用服务器并执行远程启动脚本

### Jenkins

是一个开源的、基于Java的持续集成工具

Jenkins的主要功能：监控外部调用执行工作、持续的软件版本发布（自动化）

## 分布式开发Spring Cloud

如今互联网开发的普遍要求：高并发、大数据、快响应

应对高并发、大数据、快响应的解决方案：分布式开发

分布式开发研发成本高、周期长，大部分企业很难开发自己的分布式框架

Spring Cloud为企业提供了一套免费且开源的解决方案

Spring Cloud是一套组件，如服务治理和发现、配置中心、消息总线、负载均衡、断路器和数据监控等

服务治理和发现：在Spring Cloud中使用Eureka实现服务治理

客户端负载均衡：微服务间调用时需要使用负载均衡提供一个节点进行响应，负载均衡是分布式必须实施的方案

负载均衡体现了Spring Cloud的高可用特点

Spring Cloud提供了Ribbon来实现负载均衡。但是Ribbon的使用较为繁琐，故Spring Cloud提供了接口式的申明服务调用方式，也就是Feign

断路器：在分布式中，因存在网络延迟或故障，大量的服务调用可能导致调用者和被调用者瘫痪，Spring Cloud引入`Hystrix`来处理这个问题

`Hystrix`会对故障的服务进行处理，这类似于电路负荷过大，保险丝会烧毁从而保障用电安全一样，所以称之为断路器

这样，当消费者长期得不到服务提供者的响应时，就可以进行降级、服务断路、线程和信号隔离、请求缓存或合并处理

API网关：Spring Cloud中的API网关是`Zuul`

API网关有两个作用：一个请求地址映射真实地址，另一个是过滤恶意攻击与无效请求

API网关实际上是实现了服务端的负载均衡及过滤器的作用

