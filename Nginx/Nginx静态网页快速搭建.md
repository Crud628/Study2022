### Linux下的安装

配置源

```shell
vim /etc/yum.repos.d/nginx.repo
```

文件信息

```shell
[nginx-stable]
name=nginx stable repo
baseurl=http://nginx.org/packages/centos/7/$basearch/
gpgcheck=1
enabled=1
gpgkey=https://nginx.org/keys/nginx_signing.key
module_hotfixes=true

[nginx-mainline]
name=nginx mainline repo
baseurl=http://nginx.org/packages/mainline/centos/7/$basearch/
gpgcheck=1
enabled=0
gpgkey=https://nginx.org/keys/nginx_signing.key
module_hotfixes=true
```

查看源

```shell
yum list | grep nginx
```

![image-20220814191458595](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20220814191458595.png)

执行安装

```shell
yum install nginx
```

查看版本

```shell
nginx -v
```

查看目录

```shell
whereis nginx
```

```shell
nginx: /usr/sbin/nginx /usr/lib64/nginx /etc/nginx /usr/share/nginx /usr/share/man/man8/nginx.8.gz
```

### 配置文件解读(可跳过)

Nginx安装成功后，使用

```shell
nginx -t
```

查看配置文件的位置

```shell
# 运行用户
user  nginx;
# 工作进程数，可以和CPU核心数一致
worker_processes  1;
# 错误日志路径
error_log  /var/log/nginx/error.log;
pid      /run/nginx.pid;
# 把对应的配置文件导入
include /usr/share/nginx/modules/*.conf;
events {
# 连接数
    worker_connections  1024;
}
# http服务器的配置
http {
# 访问日志
    access_log  /var/log/nginx/access.log  main;
    sendfile        on;
# keepalive超时时间
    keepalive_timeout  65;
#加载其他各模块的配置文件
include /etc/nginx/conf.d/*.conf;
    server {
#监听端口号
        listen       80;
        listen       [::]:80;
        server_name  _;
    #root路径的位置
        root         /usr/share/nginx/html2;

        # Load configuration files for the default server block.
        include /etc/nginx/default.d/*.conf;

        error_page 404 /404.html;
        location = /404.html {
        }

        error_page 500 502 503 504 /50x.html;
        location = /50x.html {
        }
    }
}
```

### 配置访问

默认路径是

```shell
/usr/share/nginx/html
```

index.html 放到下面即可，其他js，css等路径正确。只是更改文件，无需重启nginx

### 启动

直接输入nginx，默认在80端口



### 停止

```shell
# 立即停止
nginx -s stop

# 优雅停止
nginx -s quit
```

