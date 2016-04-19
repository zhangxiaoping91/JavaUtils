package com.xx.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.xx.bean.Student;
import com.xx.utils.SerializableUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol.Command;
import redis.clients.jedis.SortingParams;

public class ConnectionRedis {
	private static Jedis jedis=new Jedis("123.57.211.130", 6379);

	public static void main(String[] args) {
//		jedis.lpush("score", "78");
//		jedis.lpush("score", "69");
//		jedis.lpush("score", "72");
//		jedis.lpush("score", "60");
//		jedis.lpush("score", "84");
		SortingParams sortingParameters=new SortingParams();
//		sortingParameters.desc();
		sortingParameters.alpha();
//		List<String> list= jedis.sort("score");
		List<String> list=jedis.sort("score", sortingParameters);
//		jedis.sort(key, sortingParameters)
//		List<String> list= jedis.lrange("score", 0, -1);
		for(String str:list)
		{
			System.out.println(str);
		}
//		
//		List<String> stulist= jedis.hvals("stu");
//		List<byte[] > stub= jedis.hvals("stu".getBytes());
//		for(byte[] b:stub)
//		{
//			Student student= (Student)SerializableUtils.unserialize(b);
//			System.out.println(student.toString());
//		}
//		System.out.println(stulist.size());
		Student student=new Student("xiaoxia", "23", "male");
		jedis.set(student.getName(), student.toString());
//		hashStudent();
	}

	private static void hashStudent() {
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
		Map map=jedis.hgetAll("stu".getBytes());
		List<Student> stuList=new ArrayList<Student>();
		for(int i=1;i<4;i++)
		{
			String key="stu";
			key=key+i;
			byte[] student1= jedis.hget("stu".getBytes(), key.getBytes());
			Student student= (Student) SerializableUtils.unserialize(student1);
			stuList.add(student);
//			System.out.println(student.toString());
		}
		java.util.Collections.sort(stuList);
		for(Student s:stuList)
		{
			System.out.println(s.toString());
		}
//	byte[] student1= map.get("stu1".getBytes()).toString().getBytes();


		
		
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
