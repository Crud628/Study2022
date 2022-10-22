package com.lan.course_zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午7:11:25
 * @TODO
 * 
 */
@SpringCloudApplication
@EnableZuulProxy
public class ZuulGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class, args);
	}
}
