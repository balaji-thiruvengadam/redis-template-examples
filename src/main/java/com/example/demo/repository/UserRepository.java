package com.example.demo.repository;

import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public class UserRepository {
	
	private static final String USER = "tenant:user";
	
    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    public UserRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(User user){
        hashOperations.put(USER, user.getId(), user);
    }
    public List findAll(){
        return hashOperations.values(USER);
    }

    public User findById(String id){
        return (User) hashOperations.get(USER, id);
    }

    public void update(User user){
        save(user);
    }

    public void delete(String id){
        hashOperations.delete(USER, id);
    }

}
