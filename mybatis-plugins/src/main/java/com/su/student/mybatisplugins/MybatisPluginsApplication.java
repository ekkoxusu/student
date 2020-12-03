package com.su.student.mybatisplugins;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.su.student.mybatisplugins.mapper")
public class MybatisPluginsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPluginsApplication.class, args);
    }

}
