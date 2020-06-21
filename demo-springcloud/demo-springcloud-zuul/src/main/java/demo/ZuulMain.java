package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringCloudApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulMain {

  public static void main(String[] args) {
    SpringApplication.run(ZuulMain.class, args);
  }
}
