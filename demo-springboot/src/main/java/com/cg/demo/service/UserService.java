package com.cg.demo.service;

import com.cg.demo.pojo.UserPOJO;
import com.cg.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;

  public int insert(UserPOJO bo) {
    return this.userMapper.insert(bo);
  }

  public UserPOJO select(Long id) {
    return this.userMapper.select(id);
  }
}
