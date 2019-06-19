package com.sample.service;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class RedisPractice {
	public static void main(String args[]) {
		Jedis jedis = new Jedis();
		jedis.set("c", "1");
		String response = jedis.get("a");
		System.out.println(response);
		
		// List
		jedis.lpush("primeList", "2", "3", "4");
		response = jedis.lpop("primeList");
		System.out.println(response);
		
		// Set
		jedis.sadd("oddSet", "1", "3", "5");
		System.out.println(jedis.smembers("oddSet"));
		System.out.println(jedis.sismember("oddSet", "1"));
		
		// Hash
		jedis.hset("user", "user1", "raj");
		jedis.hset("user", "user2", "kumar");
		jedis.hset("QUOTA", "a:SayHello", "4");
		System.out.println(jedis.hget("user", "user1"));
		System.out.println(jedis.hexists("user", "user3"));
		
		// Sorted set
		Map<String, Double> scores = new HashMap<String, Double>();
		scores.put("raj", 100.0);
		scores.put("kumar", 50.0);
		scores.put("swas", 200.0);
		
		jedis.zadd("scoresMap", scores);
		System.out.println(jedis.zrevrange("scoresMap", 0, 1).iterator().next());
		jedis.zadd("scoresMap", 300.0, "pup");
		System.out.println(jedis.zrevrange("scoresMap", 0, 1).iterator().next());
		
		// Transactions
		Transaction tr = jedis.multi();
		tr.set("c", "3");
		tr.set("d", "4");
		tr.exec();
		System.out.println(jedis.get("d"));
				
		/*
		Jedis jsubscriber = new Jedis();
		jsubscriber.subscribe(new JedisPubSub() {
			public void onMessage(String channel, String message) {
				System.out.println("Read message: " + message);
			}
		}, "channel1");
		*/
		
		Jedis jpublisher = new Jedis();
		jpublisher.publish("channel1", "Mknfsdknsknskdfn k askldnsklnlkflk");
		
		jpublisher.close();
//		jsubscriber.close();
	}
}
