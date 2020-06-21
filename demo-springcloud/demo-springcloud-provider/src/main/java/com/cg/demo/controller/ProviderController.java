package com.cg.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

  @GetMapping("hello")
  public ResponseEntity<String> hello() {
//    int i = 1 / 0;
    System.out.println("service-provider被调用-------------");
    return ResponseEntity.ok("hello world");
  }
}
