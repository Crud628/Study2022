package com.lan.eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午4:21:36
 * @TODO
 * 
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerAppliaction {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerAppliaction.class, args);
	}
}
