package com.cg.demo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cg.demo.service.UserService;
import org.springframework.stereotype.Component;

@Service
@Component
public class UserServiceImpl implements UserService {

  @Override
  public void hello() {
    System.out.println("服务提供者被调用");
  }
}
