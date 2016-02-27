package com.xx.redis;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.ValueCodecProvider;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {
	private Jedis jedis;	//非切片客户端连接
	private JedisPool jedisPool; 	//非切片连接池
	private ShardedJedis shardedJedis; 	//切片客户端连接
	private ShardedJedisPool shardedJedisPool; 	//切片连接池
	public RedisClient()
	{
		initialPool();
		initialSharedPool();
		shardedJedis=shardedJedisPool.getResource();
		jedis=jedisPool.getResource();
		
	}
	public void show()
	{
		StringOperate();
	}
	//初始化非切片池
	private void initialPool()
	{
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxActive(20);
		config.setMaxIdle(5);
		config.setMaxWait(1000l);
		config.setTestOnBorrow(false);
		jedisPool=new JedisPool(config, "192.168.8.105", 6379);
	}
	
	//初始化切片池
	private void initialSharedPool()
	{
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxActive(20);
		config.setMaxIdle(5);
		config.setMaxWait(1000l);
		config.setTestOnBorrow(false);
		//slave链接
		List<JedisShardInfo> shards=new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("192.168.8.105", 6379));
		shards.add(new JedisShardInfo("192.168.8.105", 7003));
		//构造池
		shardedJedisPool=new ShardedJedisPool(config, shards);
		
	}
	//String  功能
	private  void StringOperate()
	{
		System.out.println("================String_1=================");
		System.out.println("清空库中所有的数据"+jedis.flushDB());
		System.out.println("============增=============");
		jedis.set("key001", "value001");
		jedis.set("key002", "value002");
		jedis.set("key003", "value003");
		System.out.println("已新增的3个键值对如下：");
		System.out.println(jedis.get("key001"));
		System.out.println(jedis.get("key002"));
		System.out.println(jedis.get("key003"));
		System.out.println("============删==============");
		System.out.println("删除key003键值对："+jedis.del("key003"));
		System.out.println("获取key003键对应的值："+jedis.get("key003"));
		System.out.println("============改==============");
		//直接覆盖
		System.out.println("直接覆盖key001原来的数据："+jedis.set("key001", "key-update"));
		System.out.println("获取key001对应的新值："+jedis.get("key001"));
		//追加
		System.out.println("在key002后追加："+jedis.append("key002", "key-append"));
		System.out.println("获取key002对应的新值："+jedis.get("key002"));
		System.out.println("==========增、删、查(多个)============");
		System.out.println("一次性新增key201,key202,key203："+jedis.mset("key201","value201","key202","value202","key203","value203"));
		System.out.println("一次性获取key201,key202,key203："+jedis.mget("key201","key202","key203"));
		System.out.println("一次性删除key201,key202"+jedis.del(new String[]{"key201","key202"}));
		System.out.println("一次性获取key201,key202,key203："+jedis.mget("key201","key202","key203"));
		
		//shardedJedis使用
		System.out.println("=======================String_2======================");
		System.out.println("清空库中所有数据："+jedis.flushDB());
		System.out.println("=================新增键值对时防止覆盖原先值");
		System.out.println("原先key301不存在时，新增key301："+shardedJedis.setnx("key301", "value301"));
		System.out.println("原先key302不存在时，新增key302："+shardedJedis.setnx("key302", "value302"));
		System.out.println("当key302存在时，尝试新增key302："+shardedJedis.setnx("key302","value302_new"));
		System.out.println("获取key301对应的值："+shardedJedis.get("key301"));
		System.out.println("获取key302对应的值："+shardedJedis.get("key302"));
		System.out.println("==================超过有效期键值对删除===============");
		System.out.println("新增key303，并指定过期时间2s："+shardedJedis.setex("key303", 2, "value303"));
		System.out.println("获取key303对应的值："+shardedJedis.get("key303"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("3秒之后，获取key303对应的值："+shardedJedis.get("key303"));
		System.out.println("===============获取原值，更新为新值==================");
		System.out.println("key302原值"+shardedJedis.getSet("key302", "shardeJedis302"));
		System.out.println("key302新值："+shardedJedis.get("key302"));
		System.out.println("================获取字串=============");
		System.out.println("获取key302对应值中的字串："+shardedJedis.getrange("key302", 4, 7));


		
		
		
		

	}
}
