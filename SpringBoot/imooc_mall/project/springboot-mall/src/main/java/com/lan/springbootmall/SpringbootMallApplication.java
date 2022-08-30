package com.lan.springbootmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Keason
 * 慕课网Java体系科，单体商城项目
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lan.springbootmall.model.dao")
@EnableSwagger2
@EnableCaching
public class SpringbootMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMallApplication.class, args);
    }

}
