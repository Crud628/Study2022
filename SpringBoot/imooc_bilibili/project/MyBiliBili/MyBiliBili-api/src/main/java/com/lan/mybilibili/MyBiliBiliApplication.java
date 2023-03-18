package com.lan.mybilibili;

import com.lan.mybilibili.service.websocket.WebSocketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@EnableAsync
public class MyBiliBiliApplication {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(MyBiliBiliApplication.class, args);
        WebSocketService.setApplicationContext(app);
    }
}