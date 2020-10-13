package com.gupaoedu.live.gupaoedulive.modules.sys.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Maps;
import com.gupaoedu.live.gupaoedulive.modules.sys.mapper.SysUserMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class MailService {

  @Autowired
  private JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String from;


  @Value("${domain.name}")
  private String domainName;


  @Autowired
  private SysUserMapper userMapper;


  private final Cache<String, String> registerCache =
      CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(15, TimeUnit.MINUTES)
          .removalListener(new RemovalListener<String, String>() {

            @Override
            public void onRemoval(RemovalNotification<String, String> notification) {
            }
          }).build();
  
  
  private final Cache<String, String> resetCache =  CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(15, TimeUnit.MINUTES).build();

  @Async
  public void sendMail(String title, String url, String email) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(from);
    message.setSubject(title);
    message.setTo(email);
    message.setText(url);
    mailSender.send(message);
  }

  /**
   * 1.缓存key-email的关系 2.借助spring mail 发送邮件 3.借助异步框架进行异步操作
   * 
   * @param email
   */
  //@Async
  public String registerNotify(String email) {
    boolean isVerify = registerCache.asMap().containsValue(email);
    if(isVerify){
      registerCache.cleanUp();
    }
    String randomKey = RandomStringUtils.randomAlphabetic(4);
    registerCache.put(randomKey, email);
    String message="您的验证码为："+randomKey;
    sendMail("注册激活邮件", message, email);
    return randomKey;
  }
  
  /**
   * 发送重置密码邮件
   * 
   * @param email
   */
  @Async
  public void resetNotify(String email) {
    String randomKey = RandomStringUtils.randomAlphanumeric(4);
    resetCache.put(randomKey, email);
    String content = "您的验证码为：" + randomKey;
    sendMail("注册重置验证码邮件", content, email);
  }

  public String getResetEmail(String key){
    return resetCache.getIfPresent(key);
  }
  
  public void invalidateRestKey(String key){
    resetCache.invalidate(key);
  }

  public boolean enable(String key) {
    String email = registerCache.getIfPresent(key);
    if (StringUtils.isBlank(email)) {
      return false;
    }
    registerCache.invalidate(key);
    return true;
  }


  public String getEmailVerify(String email) {
    Map<String, String> newMap = Maps.newHashMap();
    registerCache.asMap().forEach((k,v) -> {
      if(v!=null && v.equalsIgnoreCase(email))
        newMap.put(email,k);
    });
    return newMap.get(email);
  }
}
