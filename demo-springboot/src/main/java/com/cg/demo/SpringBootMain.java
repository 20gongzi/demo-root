package com.cg.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cd.demo.mapper")
public class SpringBootMain {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootMain.class, args);
  }
}
