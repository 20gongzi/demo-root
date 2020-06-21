package com.cg.demo.controller;

import com.cg.demo.exception.DemoException;
import com.cg.demo.exception.ExceptionEnum;
import com.cg.demo.pojo.UserPOJO;
import com.cg.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("User接口")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("user")
  @ApiOperation(value = "insert", notes = "insert")
  @ApiImplicitParam(name = "pojo", required = true, value = "UserPojo")
  public ResponseEntity<Integer> insert(@RequestBody UserPOJO pojo) {
    return ResponseEntity.ok(userService.insert(pojo));
  }

  @GetMapping("user/{userId}")
  public ResponseEntity<UserPOJO> select(@PathVariable("userId") Long id) throws DemoException {
    if (1 < 2) {
      throw new DemoException(ExceptionEnum.FRIST_EXCEPTION);
    }
    return ResponseEntity.ok(userService.select(id));
  }
}
