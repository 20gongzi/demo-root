package com.cg.demo;

import com.cg.demo.spring.RabbitMQApplication;
import javax.xml.ws.soap.Addressing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author cg
 * @date 2020/4/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQApplication.class)
public class RabbitMQTest {

  @Autowired
  private AmqpTemplate amqpTemplate;

  @Test
  public void testSend() throws Exception {
    String msg = "Hello World";
    this.amqpTemplate.convertAndSend("spring.test.exchange", "a.b", msg);
    // 等待10S结束
    Thread.sleep(10000);
  }
}
