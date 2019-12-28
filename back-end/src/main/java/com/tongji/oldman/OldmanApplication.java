package com.tongji.oldman;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tongji.oldman.mapper")
public class OldmanApplication {

    public static void main(String[] args) {
        SpringApplication.run(OldmanApplication.class, args);
    }

}
