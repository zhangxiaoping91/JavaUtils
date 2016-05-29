package com.xx.readconfig;

import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.log4j.lf5.util.Resource;

/**
 * 用于读取配置文件
 * 2016年5月3日 下午4:14:30
 * @author zhangxiaoping
 */
public class ReadConfig {
	public static void main(String[] args) {
		Reader reader=new InputStreamReader(Resource.class.getResourceAsStream("log4j.xml"));
		System.out.println(reader);
	}

}
