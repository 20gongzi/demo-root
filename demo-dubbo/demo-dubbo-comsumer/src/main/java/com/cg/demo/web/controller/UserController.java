package com.cg.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cg.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Reference
  private UserService userService;

  @GetMapping("hello")
  public String hello() {
      userService.hello();
    System.out.println("服务消费者被调用");
    return "hello world";
  }
}
