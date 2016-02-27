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
	private Jedis jedis;	//����Ƭ�ͻ�������
	private JedisPool jedisPool; 	//����Ƭ���ӳ�
	private ShardedJedis shardedJedis; 	//��Ƭ�ͻ�������
	private ShardedJedisPool shardedJedisPool; 	//��Ƭ���ӳ�
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
	//��ʼ������Ƭ��
	private void initialPool()
	{
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxActive(20);
		config.setMaxIdle(5);
		config.setMaxWait(1000l);
		config.setTestOnBorrow(false);
		jedisPool=new JedisPool(config, "192.168.8.105", 6379);
	}
	
	//��ʼ����Ƭ��
	private void initialSharedPool()
	{
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxActive(20);
		config.setMaxIdle(5);
		config.setMaxWait(1000l);
		config.setTestOnBorrow(false);
		//slave����
		List<JedisShardInfo> shards=new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("192.168.8.105", 6379));
		shards.add(new JedisShardInfo("192.168.8.105", 7003));
		//�����
		shardedJedisPool=new ShardedJedisPool(config, shards);
		
	}
	//String  ����
	private  void StringOperate()
	{
		System.out.println("================String_1=================");
		System.out.println("��տ������е�����"+jedis.flushDB());
		System.out.println("============��=============");
		jedis.set("key001", "value001");
		jedis.set("key002", "value002");
		jedis.set("key003", "value003");
		System.out.println("��������3����ֵ�����£�");
		System.out.println(jedis.get("key001"));
		System.out.println(jedis.get("key002"));
		System.out.println(jedis.get("key003"));
		System.out.println("============ɾ==============");
		System.out.println("ɾ��key003��ֵ�ԣ�"+jedis.del("key003"));
		System.out.println("��ȡkey003����Ӧ��ֵ��"+jedis.get("key003"));
		System.out.println("============��==============");
		//ֱ�Ӹ���
		System.out.println("ֱ�Ӹ���key001ԭ�������ݣ�"+jedis.set("key001", "key-update"));
		System.out.println("��ȡkey001��Ӧ����ֵ��"+jedis.get("key001"));
		//׷��
		System.out.println("��key002��׷�ӣ�"+jedis.append("key002", "key-append"));
		System.out.println("��ȡkey002��Ӧ����ֵ��"+jedis.get("key002"));
		System.out.println("==========����ɾ����(���)============");
		System.out.println("һ��������key201,key202,key203��"+jedis.mset("key201","value201","key202","value202","key203","value203"));
		System.out.println("һ���Ի�ȡkey201,key202,key203��"+jedis.mget("key201","key202","key203"));
		System.out.println("һ����ɾ��key201,key202"+jedis.del(new String[]{"key201","key202"}));
		System.out.println("һ���Ի�ȡkey201,key202,key203��"+jedis.mget("key201","key202","key203"));
		
		//shardedJedisʹ��
		System.out.println("=======================String_2======================");
		System.out.println("��տ����������ݣ�"+jedis.flushDB());
		System.out.println("=================������ֵ��ʱ��ֹ����ԭ��ֵ");
		System.out.println("ԭ��key301������ʱ������key301��"+shardedJedis.setnx("key301", "value301"));
		System.out.println("ԭ��key302������ʱ������key302��"+shardedJedis.setnx("key302", "value302"));
		System.out.println("��key302����ʱ����������key302��"+shardedJedis.setnx("key302","value302_new"));
		System.out.println("��ȡkey301��Ӧ��ֵ��"+shardedJedis.get("key301"));
		System.out.println("��ȡkey302��Ӧ��ֵ��"+shardedJedis.get("key302"));
		System.out.println("==================������Ч�ڼ�ֵ��ɾ��===============");
		System.out.println("����key303����ָ������ʱ��2s��"+shardedJedis.setex("key303", 2, "value303"));
		System.out.println("��ȡkey303��Ӧ��ֵ��"+shardedJedis.get("key303"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("3��֮�󣬻�ȡkey303��Ӧ��ֵ��"+shardedJedis.get("key303"));
		System.out.println("===============��ȡԭֵ������Ϊ��ֵ==================");
		System.out.println("key302ԭֵ"+shardedJedis.getSet("key302", "shardeJedis302"));
		System.out.println("key302��ֵ��"+shardedJedis.get("key302"));
		System.out.println("================��ȡ�ִ�=============");
		System.out.println("��ȡkey302��Ӧֵ�е��ִ���"+shardedJedis.getrange("key302", 4, 7));


		
		
		
		

	}
}
