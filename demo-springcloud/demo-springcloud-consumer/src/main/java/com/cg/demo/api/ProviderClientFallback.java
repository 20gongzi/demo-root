package com.cg.demo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProviderClientFallback implements ProviderClient {

  @Override
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("对不起，服务器正忙");
  }
}
