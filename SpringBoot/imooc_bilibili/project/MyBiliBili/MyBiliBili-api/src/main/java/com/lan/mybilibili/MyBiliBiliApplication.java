package com.lan.mybilibili;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyBiliBiliApplication {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(MyBiliBiliApplication.class, args);
    }
}