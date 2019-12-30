package com.springboot.fun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Repository;

@SpringBootApplication(exclude = {GsonAutoConfiguration.class})
@MapperScan(annotationClass = Repository.class, basePackages = "com.springboot.fun.dao")
public class FunApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunApplication.class, args);
    }
}
