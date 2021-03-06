package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StringExampleRepository {
	
	private static final String KEY = "user";
	  
	  @Autowired
	  private StringRedisTemplate stringRedisTemplate;
	  
	  
	  public void addUserName(String uname) {
		  stringRedisTemplate.opsForValue().setIfAbsent(KEY, uname);
	  }
	  public void updateUserName(String uname) {
		  stringRedisTemplate.opsForValue().set(KEY, uname);
	  }	  
	  public String getUserName() {
		  return stringRedisTemplate.opsForValue().get(KEY);
	  }
	  public void deleteUser() {
		  stringRedisTemplate.delete(KEY);
	  }	  

}
