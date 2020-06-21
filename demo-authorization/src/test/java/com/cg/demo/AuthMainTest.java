package com.cg.demo;

import com.cg.demo.entity.UserInfo;
import com.cg.demo.utils.JwtUtils;
import com.cg.demo.utils.RsaUtils;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.junit.Before;
import org.junit.Test;

/**
 * @author cg
 * @date 2020/4/16
 */
public class AuthMainTest {

  private static final String pubKeyPath =
      "C:\\always-personal\\code\\demo-root\\demo-authorization\\src\\main\\resources\\rsa.pub";

  private static final String priKeyPath =
      "C:\\always-personal\\code\\demo-root\\demo-authorization\\src\\main\\resources\\rsa.pri";

  private PublicKey publicKey;

  private PrivateKey privateKey;

  /**
   * 生辰龚公钥，私钥
   *
   * @throws Exception
   */
  @Test
  public void testRsa() throws Exception {
    RsaUtils.generateKey(pubKeyPath, priKeyPath, "salt");
  }

  /**
   * 读取公钥，私钥
   *
   * @throws Exception
   */
  @Before
  public void testGetRsa() throws Exception {
    this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
    this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
  }

  /**
   * 生辰token信息
   *
   * @throws Exception
   */
  @Test
  public void testGenerateToken() throws Exception {
    // 生成token
    String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
    System.out.println("token = " + token);
  }

  @Test
  public void testParseToken() throws Exception {
    String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU4NzA0OTE5Mn0.jlhv5WKSKLDcxSS0JxfY14A9VPY5yrS7J9xVI3PLCjOLoxKMNswFBovjzm7sPr6H7ObuXMxeBJvmjwxDfPZpINm3ygnZcs28k_m2uYzZGOrQ66-CM9iM8LZuEEI_7CbjrDk6wb8gRhYHq-TSWgKLY0lrunxBuBbcGphEV7D_ZwA";
    // 解析token
    UserInfo user = JwtUtils.getInfoFromToken("eyJhbGciOiJub25lIn0.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU4NzA1MDYxOH0", publicKey);
    System.out.println("id: " + user.getId());
    System.out.println("userName: " + user.getUsername());
  }
}