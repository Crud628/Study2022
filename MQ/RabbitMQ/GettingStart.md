# RabbitMQ入门

### 理解AMQP协议架构



### 熟悉消息流转流程



### 合理的交换机和队列的设置

交换机的数量不能过多，一般来说同一个业务，或者同一类业务使用同一个交换机

合理设置队列数量，一般来说一个微服务监听一个队列，或者一个微服务的一个业务监听一个队列

合理配置交换机类型，使用topic模式时仔细设置绑定建

### 尽量使用自动化的配置

将创建交换机/队列的操作固化在应用代码中，免去复杂的运维操作，高效且不易出错

一般来说，交换机由双方同时声明，队列由接收方声明并配置绑定关系

交换机/队列的参数一定要由双方开发团队确认，否则重复声明时，若参数不一致，会导致声明失败

### 思考

AMQP为什么要设计Exchange消息流传机制，

## 需求分析与架构设计

一个外卖后端系统，用户可以在线下单外卖

用户下单后，可以实时查询订单进度

系统可以承受短时间的大量并发请求

### 架构设计

使用微服务系统，组件之间充分解耦

使用消息中间件，解耦业务逻辑

使用数据库，持久化业务数据

### 什么是微服务架构

将程序构建为松耦合、可独立部署的一组服务

服务：一个单一的、可独立部署的软件组织，实现了一些有用的功能

松耦合：封装服务的实现细节，通过API调用

### 如何拆分微服务

根据系统操作进行微服务拆分

根据业务能力进行服务拆分（推荐使用）

根据子域进行微服务拆分

### 根据业务能力进行服务拆分

订单获取和履行  订单微服务

供应商和产品管理，商家微服务

送餐、起手管理 骑手微服务

记账与结算，结算微服务

积分管理，积分微服务

<img src="https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20220925115600740.png" alt="image-20220925115600740" style="zoom:50%;" />

<img src="https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20220925120106384.png" alt="image-20220925120106384" style="zoom:50%;" />

### 接口需求

新建订单接口

查询订单接口

接口采用REST风格

### 微服务的数据库设计原则

每个微服务使用自己的数据库

不要使用共享数据库的方式进行通信

不要使用外键，对于数据量非常少的表慎用索引

![image-20220925120433220](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20220925120433220.png)

### 如何搭建示例SpringBoot项目 

https://start.spring.io/ 

输入项目名称、包名 

选择Lombok、SpringWeb、MyBatis、MySQL Driver、Spring for RabbitMQ 插件

### Direct Exchange复习

Message中的Routing Key 如果和Binding Key一致，Direct Exchange则将message发到对应queue中

<img src="https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20220925214124097.png" alt="image-20220925214124097" style="zoom:50%;" />

### REST风格接口

REST风格接口是一种HTTP接口

使用URL代表资源，如com/v1/users/345

使用HTTP方法代表动词，如GET、POST、DELETE

