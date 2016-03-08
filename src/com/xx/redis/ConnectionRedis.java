package com.xx.redis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class ConnectionRedis {
	public static void main(String[] args) {
		Jedis jedis=new Jedis("192.168.1.111", 6379);
//		jedis.auth("zhangxp");
//		Jedis jedis=new Jedis("192.168.8.105", 6379);
		System.out.println("connection to server successfully");
		System.out.println("server is running:"+jedis.ping());
		jedis.flushAll();
		//set redis
		jedis.set("redis_name", "redis");
		System.out.println("Stored string in redis："+jedis.get("name"));
		//list 列表
		jedis.lpush("redisList", "2.0");
		jedis.lpush("redisList", "2.6");
		jedis.lpush("redisList", "3.0");
		List<String> redis_list=jedis.lrange("redisList", 0, -1);
		for(String s:redis_list)
		{
			System.out.println(s);
		}
//		System.out.println(jedis.lrange("redisList", 0, -1).toArray());
		//获取数据并输出
		Set<String> rSet= jedis.keys("*");
		Iterator< String> ls=rSet.iterator();
		while(ls.hasNext())
		{
			System.out.println("List of stored keys："+ls.next());
		}
	}

}
