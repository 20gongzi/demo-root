package com.cg.demo.service;

import com.cg.demo.pojo.UserPOJO;
import java.util.Scanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void insert() throws Exception {
    UserPOJO bo = new UserPOJO();
    bo.setUserName("xxx");
    bo.setUserAge(12);
    userService.insert(bo);
  }

  @Test
  public void select() throws Exception {
    UserPOJO bo = this.userService.select(3L);
    System.out.println(bo);

  }
}