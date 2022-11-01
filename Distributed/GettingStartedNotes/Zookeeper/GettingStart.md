# Zookeeper

## 一.  Zookeeper简介

### 1.  Zookeeper诞生历史

《从 paxos 到 zookeeper》  雅虎的整个分布式系统

- 无单点问题的分布式协调框架，精力集中再处理业务逻辑
- 内部很多项目都是使用动物的名字来命名
- 大型动物管理员

### 2.  为什么需要Zookeeper

- HashMap
- 不可靠
- 高可用，数据一致

官网 [Apache ZooKeeper](https://zookeeper.apache.org/)

Apache ZooKeeper is an effort to develop and maintain an open-source server which enables highly reliable distributed coordination.

- 分布式应用程序的分布式协调服务
- 不因为某一个节点宕机而不可用

### 3.  5 大特点

- 顺序一致性
- 原子性
- 单一系统映像
- 可靠性
- 及时性

### 4.  架构图

![zookeper架构图](https://zookeeper.apache.org/doc/current/images/zkservice.jpg)



### 5.  作用

分布式锁

配置中心



