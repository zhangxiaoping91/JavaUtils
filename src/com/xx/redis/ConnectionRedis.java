package com.xx.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xx.bean.Student;
import com.xx.utils.SerializableUtils;

import redis.clients.jedis.Jedis;

public class ConnectionRedis {
	private static Jedis jedis=new Jedis("123.57.211.130", 6379);

	public static void main(String[] args) {
		Student stu1=new Student();
		stu1.setName("zhangsan");
		stu1.setAge("20");
		stu1.setSex("female");
		
		Student stu2 =new Student();
		stu2.setName("lisi");
		stu2.setAge("16");
		stu2.setSex("male");
		
		Student stu3 =new Student();
		stu3.setName("wangwu");
		stu3.setAge("23");
		stu3.setSex("male");
		
		Map stuMap=new HashMap();
		stuMap.put("stu1".getBytes(), SerializableUtils.serialize(stu1));
		stuMap.put("stu2".getBytes(), SerializableUtils.serialize(stu2));
		stuMap.put("stu3".getBytes(), SerializableUtils.serialize(stu3));
		
		jedis.hmset("stu".getBytes(), stuMap);
//		Map map=jedis.hgetAll("stu".getBytes());

	byte[] student1= jedis.hget("stu".getBytes(), "stu1".getBytes());
//	byte[] student1= map.get("stu1".getBytes()).toString().getBytes();
		Student student= (Student) SerializableUtils.unserialize(student1);
		System.out.println(student.toString());


		
		
//		jedis.set("stu1".getBytes(), SerializableUtils.serialize(student1));
//		byte[] stu= jedis.get("stu1".getBytes());
//		Student student= (Student) SerializableUtils.unserialize(stu);
//		System.out.println(student.toString());
		
//		System.out.println(SerializableUtils.serialize(stu1));
//		System.out.println(SerializableUtils.unserialize("[B@184c9860".getBytes()));
		
	}

	private static void hashOperate() {
		jedis.hset("hfriend", "age", "25");
		List<String> list = jedis.hmget("hfriend", "name" ,"age","sex");
		Set<String> set =jedis.hkeys("hfriend");
		Long len= jedis.hlen("hfriend");
//		System.out.println(len);
//		List<String> list= jedis.hvals("hfriend");
		for(String str:list)
		{
			System.out.println(str);
		}
		
		Iterator< String> iterator=set.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}

	private static void setOperate() {
		//		jedis.sadd("sfriend", "lanlan");
		//		jedis.sadd("sfriend", "yangyang");
		//		jedis.sadd("sfriend", "huahua");
		//		jedis.srem("sfriend", "lanlan");
		//		System.out.println(jedis.spop("sfriend"));
		//		jedis.del("sfriend");
		//		Set<String> set=jedis.sinter("sfriend","efriend");
				Set<String> set=jedis.sunion("sfriend","efriend");
		//		Set<String> set= jedis.smembers("sfriend");
				Iterator<String>it= set.iterator();
				while(it.hasNext())
				{
					System.out.println(it.next());
				}
	}

	private static void listOperate() {
		//		jedis.rpush("friend", "zhangsan");
		//		jedis.lpush("friend", "zhangxiaoping");
				jedis.lpop("friend");
				jedis.rpop("friend");
				List<String> fList= jedis.lrange("friend", 0, -1);
				for(String s:fList)
				{
					System.out.println(s);
				}
	}

	private static void localHostTest() {
		Jedis jedis=new Jedis("123.57.211.130", 6379);
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
