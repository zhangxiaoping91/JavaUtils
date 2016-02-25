package com.xx.mongo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.QueryOperators;
import com.mongodb.util.JSON;

public class MongodbLink {
	public static void main(String[] args) {
		Mongo mongo = null;
		DB db = null;
		Map userMap = null;
		try {
			// 连接mongo
			mongo = new Mongo("192.168.8.107", 27017);
			// 选择数据库
			db = mongo.getDB("admin");
			// 校验用户
			// &……
			// 获取集合
			Set<String> colNameSet = db.getCollectionNames();
			Iterator<String> colNameItr = colNameSet.iterator();
			while (colNameItr.hasNext()) {
				System.out.println("数据集合的名称：" + colNameItr.next());
			}
			DBCollection dbCollection = db.getCollection("user");
			if (dbCollection != null) {
				System.out.println("数据集大小：" + dbCollection.getStats().size());
			}
//			queryAll(dbCollection); //查询所有记录
			queryByContent(dbCollection); //按条件查询
//			System.out.println(dbCollection.findAndRemove(new BasicDBObject("age",21)));
//			System.out.println(dbCollection.findAndModify(new BasicDBObject("age",22),new BasicDBObject("name","zhangxiaoping")).toMap());
//			System.out.println(dbCollection.findAndModify(new BasicDBObject("age",24),new BasicDBObject("$set",new BasicDBObject("name","zhangxiaoxiao"))));
//			System.out.println(dbCollection.remove(new BasicDBObject("age",new BasicDBObject("$gte",30))));
			
			/*
			List list=dbCollection.find(new BasicDBObject("age",new BasicDBObject("$gte", 30))).toArray();
			Iterator l=list.iterator();
			while(l.hasNext())
			{
				System.out.println(l.next());
			}
			*/
					

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			mongo.close();
		}
	}

	/**
	 * 按条件查询用户
	 * @param dbCollection
	 */
	private static void queryByContent(DBCollection dbCollection) {
		System.out.println("年龄等于30："+dbCollection.find(new BasicDBObject("age", 30)).toArray());
		System.out.println("年龄大于等于30："+dbCollection.find(new BasicDBObject("age",new BasicDBObject("$gte", 30))).toArray());
		System.out.println("年龄不等于30"+dbCollection.find(new BasicDBObject("age",new BasicDBObject("$ne",30))).toArray());
		System.out.println("年龄等于25 29 30："+dbCollection.find(new BasicDBObject("age",new BasicDBObject(QueryOperators.IN,new int[]{25,29,30}))).toArray());
		System.out.println(dbCollection.find().skip(3).limit(3).toArray());
	}

	/**
	 * 查詢所有記錄
	 * 
	 * @param dbCollection
	 */
	private static void queryAll(DBCollection dbCollection) {
		Map userMap;
		DBCursor dbCursor = dbCollection.find();
		// DBObject query=new BaseDBObject();
		// query.put("age", "30");
		// DBCursor dbCursor = dbCollection.find(query);
		while (dbCursor.hasNext()) {
			userMap = dbCursor.next().toMap();
			System.out.println("name：" + userMap.get("name") + " \t age：" + userMap.get("age"));
		}
		System.out.println(JSON.serialize(dbCursor));
	}
	private static void print(String o)
	{
		System.out.println(o);
	}

}
