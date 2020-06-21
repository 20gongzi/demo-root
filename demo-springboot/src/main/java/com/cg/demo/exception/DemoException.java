package com.cg.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoException extends Exception {

  private ExceptionEnum exceptionEnum;
}
