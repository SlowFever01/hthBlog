package com.hth;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//添加包扫描。不然注入不了公共模块的类(bean)
@MapperScan("com.hth.mapper")
@EnableWebSecurity
@EnableScheduling
@EnableSwagger2
public class HTHBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(HTHBlogApplication.class,args);
    }
}
