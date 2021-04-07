/**
 * 
 */
package com.example.demo.repository;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author balaj
 *
 */

@Repository
@Transactional
public class SetsExampleRepository {
	
	private static final String KEY = "cities";
	  
	  @Resource(name="redisTemplate")
	  private SetOperations<String, String> setOps;	  
	  
	  public void addMembers(String... cities) {
		  setOps.add(KEY, cities);  	
	  }
	  public Set<String> getMembers() {
		  return setOps.members(KEY);
	  }
	  public long getNumberOfMembers() {
		  return setOps.size(KEY);
	  }
	  public long removeMembers(String... cities) {
		  return setOps.remove(KEY, (Object[])cities);
	  }	 

}
