package com.junyang.template.cache;

import redis.clients.jedis.Jedis;

public class RedisDemo {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.0.107",6379);  
		 jedis.set("mysql", "mysql1");  
         System.out.println(jedis.get("redis"));  

	}

}
