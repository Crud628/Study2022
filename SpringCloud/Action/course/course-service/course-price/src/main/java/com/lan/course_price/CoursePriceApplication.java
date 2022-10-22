package com.lan.course_price;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午3:19:42
 * @TODO
 * 
 */
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class CoursePriceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursePriceApplication.class, args);
	}
}
