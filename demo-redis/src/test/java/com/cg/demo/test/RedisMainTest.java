package com.cg.demo.test;

import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisMainTest {

  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Test
  public void run01() {
    redisTemplate.opsForValue().set("x01", "x01");
    System.out.println(redisTemplate.opsForValue().get("x01"));
    redisTemplate.boundValueOps("x01").set("y01");
    System.out.println(redisTemplate.opsForValue().get("x01"));
  }

  @Test
  public void run02() {
    stringRedisTemplate.opsForValue().set("x02", "x02");
    System.out.println(stringRedisTemplate.opsForValue().get("x02"));
    stringRedisTemplate.boundValueOps("x02").set("y02");
    System.out.println(stringRedisTemplate.opsForValue().get("x02"));

    BoundHashOperations<String, Object, Object> hash = stringRedisTemplate.boundHashOps("hash");
    hash.put("name", "name");
    hash.put("age", "age");
    Map<Object, Object> map = stringRedisTemplate.boundHashOps("hash").entries();
    map.keySet().forEach(x -> {
      System.out.println("key:" + x);
      System.out.println("value:" + map.get(x));
    });
  }
}
