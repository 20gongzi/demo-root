package com.cg.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "service-provider", fallback = ProviderClientFallback.class)
public interface ProviderClient {

  @GetMapping("provider/hello")
  public ResponseEntity<String> hello();
}
