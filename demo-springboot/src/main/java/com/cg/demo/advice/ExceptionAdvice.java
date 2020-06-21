package com.cg.demo.advice;

import com.cg.demo.exception.DemoException;
import com.cg.demo.exception.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler(DemoException.class)
  public ResponseEntity<ExceptionResult> handleException(DemoException e) {
    return ResponseEntity.status(e.getExceptionEnum().getCode())
        .body(new ExceptionResult(e.getExceptionEnum()));
  }
}
