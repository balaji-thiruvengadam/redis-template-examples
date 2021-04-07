/**
 * 
 */
package com.example.demo.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.demo.entity.User;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author balaj
 *
 */
@Configuration
public class RedisConfig {
	
    @Value("${spring.redis.host}")
    private String REDIS_HOSTNAME;
    @Value("${spring.redis.port}")
    private int REDIS_PORT;
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		  JedisPoolConfig poolConfig = new JedisPoolConfig();
		  poolConfig.setMaxTotal(20);
		  poolConfig.setMinIdle(2);
		  poolConfig.setMaxIdle(5);
		  JedisClientConfiguration clientConfig = JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig).build();
		  return new JedisConnectionFactory(new RedisStandaloneConfiguration(REDIS_HOSTNAME,REDIS_PORT), clientConfig);
		}
	
    @Bean
    public RedisTemplate<String, User> redisTemplate() {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }
    
    @PostConstruct
    public void RedisMonitor() {
    	
    	System.out.println("============================================================");
    	System.out.println("Redis Stats");
    	System.out.println(this.jedisConnectionFactory().getConnection().info());
        System.out.println(this.jedisConnectionFactory().getUsePool());
        System.out.println(this.jedisConnectionFactory().getPoolConfig());
        System.out.println("============================================================");
    	
    }
}
