#  Docker应用

## 一.  定义

- 官网
- 容器是什么
- 整合程序和环境

## 二.  背景

- 安装软件不容易
- 虚拟机
- 开销问题

## 三.  用途

- 构建--  方便打包
- 分享--  中央仓库
- 运行--  独立

## 四.  特点

- 标准
- 轻量级
- 安全

## 五.  镜像、容器、仓库

![image-20220816215250563](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20220816215250563.png)

镜像类似于Java类，一个镜像可以有多个容器，容器是实例。相互隔离

仓库：集中存储



## 六.  安装

### 1  先有一个Cent OS 7.6系统

这个很重要，不同版本按照的时候是不一样的。

查看CentOS版本 cat /etc/redhat-release 

### 2  用root账户登录进去

### 3  配置国内yum源

```shell
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo

yum clean all

yum makecache
```

### 4  卸载旧版本

较旧的Docker版本称为docker或docker-engine。如果已安装这些程序，请卸载它们以及相关的依赖项。

 ```shell
 yum remove docker \
 docker-client \
 docker-client-latest \
 docker-common \
 docker-latest \
 docker-latest-logrotate \
 docker-logrotate \
 docker-engine
 ```

如果yum报告未安装这些软件包，也没问题。

### 5  更新yum

```shell
yum check-update
yum update
```

### 6  安装所需的软件包

```shell
yum install -y yum-utils
 device-mapper-persistent-data
 lvm2
```

### 7  使用以下命令来设置稳定的存储库。

```shell
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

### 8  查看docker版本

```shell
yum list docker-ce --showduplicates | sort -r
```

### 9  安装指定的版本

```shell
yum install docker-ce-18.09.0 docker-ce-cli-18.09.0 containerd.io
```

### 10  Docker 是服务器----客户端架构。命令行运行docker命令的时候，需要本机有 Docker 服务。用下面的命令启动

```shell
systemctl start docker
```

### 11  安装完成后，运行下面的命令，验证是否安装成功。

```shell
docker version

# 或者

docker info
```

## 七.  生成和运行docker容器

## 八.  后台运行和端口映射

## 九.  dockerfile  