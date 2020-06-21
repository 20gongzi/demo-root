package com.cg.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {

  FRIST_EXCEPTION(400, "异常错误");

  private int code;
  private String msg;
}
