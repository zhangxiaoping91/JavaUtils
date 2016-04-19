package com.xx.memcache;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcacheUtils {
	private static MemCachedClient mclient=new MemCachedClient();
	public static void main(String[] args) throws InterruptedException {
		getConnectConfig();
//		mclient.set("game","LOL");
//		mclient.set("user01", "tom", new Date(System.currentTimeMillis()+10000));
//		System.out.println(mclient.get("user01"));
//		Thread.sleep(11000);
//		System.out.println(mclient.get("user01"));
//		mclient.delete("user01");
		
		//存在替换
//		mclient.replace("game", "XCB");
//		System.out.println(mclient.get("game"));
		
		//不存在替换
//		mclient.replace("hello", "abc");
//		System.out.println(mclient.get("hello"));
		
//		Student student=new Student("zhangsan", "25", "female");
//		mclient.set(student.getName(), student);
//		System.out.println(mclient.get(student.getName()));
		//对象必须序列化
//		User user=new User("tom", "23");
//		mclient.set(user.getName(), user);
//		System.out.println(mclient.get(user.getName()));
		
//		Map<String, Map<String, String>> ms=mclient.statsItems();
//		Map<String, Map<String, String>> ms=mclient.stats();
//		Map<String, Map<String, String>> ms=mclient.statsSlabs();
		Map<String, Map<String, String>> ms=mclient.statsCacheDump(1, 0);
		Set<Entry<String, Map<String, String>>> s=ms.entrySet();
		Iterator<Entry<String, Map<String, String>>> it=s.iterator();
		while(it.hasNext())
		{
			Entry<String, Map<String, String>> entry=it.next();
			System.out.println(entry.getKey());
			Map<String, String> eMap=entry.getValue();
			Set<Entry<String, String>> se= eMap.entrySet();
			Iterator<Entry<String, String>> iter=se.iterator();
			while(iter.hasNext())
			{
				Entry<String, String> entry2=iter.next();
				System.out.print(entry2.getKey()+"   "+entry2.getValue());
			}
//			System.out.println(it.next());
		}
		
//		mclient.stats();
	
	}
	
	public static void getConnectConfig()
	{
		//连接配置
		String[] addr={"123.57.211.130:11211"};
		Integer[]  weights={3};
		SockIOPool pool=SockIOPool.getInstance();
		pool.setServers(addr);
		pool.setWeights(weights);  
        pool.setInitConn(5);  
        pool.setMinConn(5);  
        pool.setMaxConn(200);  
        pool.setMaxIdle(1000*30*30);  
        pool.setMaintSleep(30);  
        pool.setNagle(false);  
        pool.setSocketTO(30);  
        pool.setSocketConnectTO(0);  
        pool.initialize();
//        mclient.setCompressEnable(true);  
//        mclient.setCompressThreshold(1000*1024);
	}
}
