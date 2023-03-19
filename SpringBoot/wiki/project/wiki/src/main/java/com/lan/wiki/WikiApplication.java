package com.lan.wiki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class WikiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WikiApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WikiApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        // 端口需要定义，默认的取不到端口号
        // banner 网站：http://patorjk.com/software/taag/
        // yaml 自动转换：https://toyaml.com/index.html
        LOG.info("地址：\thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
