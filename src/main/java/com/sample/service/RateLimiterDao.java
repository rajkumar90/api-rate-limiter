package com.sample.service;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
public class RateLimiterDao {
	
	// TODO make it singleton
	Jedis jedis = new Jedis();
	
	public String get(String key) {
		return jedis.hget(RateLimiterConstants.COUNT_MAP_NAME, key);
	}
	
	public void put(String key, String value) {
		jedis.hset(RateLimiterConstants.COUNT_MAP_NAME, key, value);
	}
	
	public boolean exists(String key) {
		return jedis.exists(key);
	}
	
	public String hget(String key, String field) {
		return jedis.hget(key, field);
	}
	
	public void hset(String key, String field, String value) {
		jedis.hset(key, field, value);
	}
	
	public void hexists(String key, String field) {
		jedis.hexists(key, field);
	}
	
}
