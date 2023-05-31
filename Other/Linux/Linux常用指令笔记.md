# Linux部署实战笔记



> 为学患无疑，疑则有进。小疑则小悟，大疑则大悟。



## 一.  端口相关

### 1.  Linux开放端口

```shell
firewall-cmd --state //查看防火墙状态
systemctl start firewalld.service//如果上一步处于关闭状态，输入命令 开启防火墙.
firewall-cmd --zone=public --add-port=8080/tcp --permanent//开启8080端口  可自定义
systemctl restart firewalld.service//重启防火墙
firewall-cmd --reload//重新载入配置
```



## 二.其他配置相关

### 1.配置MySQL

```shell
# 下载和安装  需要时间
wget http://dev.mysql.com/get/mysql57-community-release-el7-10.noarch.rpm
yum -y install mysql57-community-release-el7-10.noarch.rpm
yum -y install mysql-community-server
# 启动
systemctl start mysqld
# 查看初始密码
grep ”password“ /var/log/mysqld.log
# ..root@localhost:密码
# 连接数据库
mysql -uroot -p
# 修改默认密码  必须包含英文大小写加符号
ALTER USER 'root'@'localhost' IDENTIFIED BY 'NewPassWord1.';
```



### 2.  外部访问MySQL

阿里开放安全组
服务器开放3306端口[打开3306参考](#1.  linux开放端口)
服务器进入MySQL

```shell
mysql mysql -uroot -p
```

输入密码

然后执行（如果是默认密码需要先重置 [参考](#1.配置MySQL)）

```mysql
GRANT ALL ON . TO 'root'@'%' IDENTIFIED BY 'mysql密码' WITH GRANT OPTION;
```



### 3.  配置Redis

```shell
# 如果是boot镜像需要安装wget
yum install wget
cd ~
mkdir soft
cd soft
# 下载redis
wget redis的网址
# 解压redis压缩包
tar xf redis..tar.gz
cd redis-src
# 查看 readme.md 跳过
# 安装
make
# 需要C环境所以需要安装gcc
yum install gcc
# 清空已安装的部分文件
make distclean
make
# 可以看到生成的文件
cd src
cd ..
# 安装在特定路径
make install PREFIX=/opt/service/redis6
# 配置环境变量
vi /etc/profile
# 最后两行添加
export REDIS_HOME=/opt/service/redis6
export PATH=$PATH:$REDIS_HOME/bin
# 退出后重载
source /etc/profile
# 启动一个redis实例 可以执行多次，需要分配不同端口
cd utils
./install_server.sh
# 查看redis进程
ps -fe | grep redis
# 结束服务
systemctl sstart redis_ip号
```



### 4.  配置JDK

通过xftp发送rpm包到服务器

进入对应路径执行

```shell
rpm -ivh jdk...rpm
```





### 5.  配置Docker

```shell
yum install docker
# 输入y确认安装
# 启动docker
systemctl start docker
# 查看版本号
docker -v
# 添加开机启动
systemctl enable docker
# 停止docker
systemcyl stop docker
```



### 6.  Docker常用命令

#### ①基础(MySQL)

```shell
# 查看mysql镜像 需要已经开启docker服务
docker search mysql
# index 序列号   name  名称   description  描述    starts  关注数、单位k official 是否为官方 automated 是否自动配置
# 安装
docker pull mysql
# 按版本安装
docker pull mysql:版本
# 查看已经安装镜像
docker images
# 删除某个镜像
docker rmi image-id
# 配置国内源  
vi /etc/docker/daemon.json
# 输入以下 2021/04/05为止还好用，具体可以看官网
{
  "registry-mirrors": ["https://csnqquuy.mirror.aliyuncs.com"]
}
```

[阿里Docker镜像加速服务](https://cr.console.aliyun.com/cn-shenzhen/instances/mirrors)

#### ②容器操作（Tomcat）

```shell
docker search tomcat
# 拉取镜像
docker pull tomcat
# 根据镜像启动容器   这样的外部不能访问
docker run --name 自己命名 -d tomcat:latest
# 查看运行中的容器
docker ps
# 停止运行中容器
docke stop image_id
# 查看所有容器
docker ps -a
# 启动
docker start image_id
# 删除一个容器（需要停止这个容器）rmi 为删除镜像 rm为删除容器
docker rm image_id
# 外部可以访问的容器启动方法  做了端口映射
docker run -d -p 8888:8080 tomcat
#  -d ： 后台运行
#  -p : 将主机的端口映射到容器的一个端口   主机端口：容器内部端口
#查看日志
docker logs
```

#### ③运行服务容器（MySQL）

```shell
# 需要指定参数来启动，具体可以看官网
docker run -p 3306:3306 --name mysql01 -e MYSQL_ROOT_PASSWORD=123456 -d mysql
```

详细参数见[官方说明](https://registry.hub.docker.com/search?q=&type=image)





## 三.程序相关

### 1.  临时启动Jar包

```shell
java -jar 包名
```

关闭远程连接后，服务也会停止

### 2.  后台挂起

nohup

```shell
nohup java -jar ***.jar > log.file 2>&1 &
```



### 3.  停止jar包运行

```shell
 ps -ef | grep java  //查看jar包运行的PID（第二列）
 kill -9 pid值
```



