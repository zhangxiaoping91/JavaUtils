package com.xx.mongo;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import com.sun.xml.internal.ws.util.StringUtils;

public class ConnectionMongodb {
	public static void main(String[] args) {
//		Mongo mongo=new Mongo("192.168.8.107", 27017);
		Mongo mongo=new Mongo("192.168.1.111", 27017);
		DB db=mongo.getDB("admin");
		System.out.println(db.getName());
		JSON json=new JSON();
		String sex;
	    DBCursor dbCursor=db.getCollection("user").find(new BasicDBObject("age",new BasicDBObject("$gte",26))).skip(3).limit(3);
//		DBCursor dbCursor=db.getCollection("user").find("{age:{$gte:30}}");
	    Map useMap=new HashMap();
	    while(dbCursor.hasNext())
	    {
	    	useMap=dbCursor.next().toMap();
	    	if (useMap.get("sex")==null) {
				continue;
			}
	    	
	    	if(useMap.get("sex").toString().equalsIgnoreCase("1.0"))
	    	{
	    		sex="ÄÐ";
	    	}else
	    	{
	    		sex="Å®";
	    	}
	    	System.out.println("name£º"+useMap.get("name")+" \tage£º"+useMap.get("age")+" \tsex£º"+sex);
	    }
	}

}
