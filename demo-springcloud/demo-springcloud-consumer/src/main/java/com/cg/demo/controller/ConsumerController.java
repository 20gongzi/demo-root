package com.cg.demo.controller;

import com.cg.demo.api.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

  @Autowired
  private ProviderClient providerClient;

  @GetMapping("hello")
  public ResponseEntity<String> hello() {
    ResponseEntity<String> responseEntity = providerClient.hello();
    System.out.println(responseEntity);
    return responseEntity;
  }
}
