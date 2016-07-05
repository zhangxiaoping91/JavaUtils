package com.zxp.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.zxp.Json.JsonTransformUtils;
import com.zxp.bean.Article;

public class HelloWorld {
	public static void main(String[] args) {
		Article article=new Article("1", "Œ“ «∞≤∑¿");
		System.out.println(JsonTransformUtils.beanToJson(article));
	}
	
	@Test
	public void simdate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date(System.currentTimeMillis())));
	}
}
