package com.tongji.oldman;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.tongji.oldman.mapper")
@EnableScheduling
public class OldmanApplication {

    public static void main(String[] args) {
        SpringApplication.run(OldmanApplication.class, args);
    }

}
