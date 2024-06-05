package com.example.easychatbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAsync
@MapperScan("com.example.easychatbackend.mapper")
@SpringBootApplication(scanBasePackages = {"com.example.easychatbackend"})
@EnableTransactionManagement
@EnableScheduling
public class EasyChatBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyChatBackendApplication.class, args);
    }

}
