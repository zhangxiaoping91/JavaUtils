package com.xx.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ArrayToList {
	public static void main(String[] args) {
//		Integer[] arr=new Integer[]{1,3,6,4,8,5,2};
//		System.out.println(arr.length);
//		List<Integer>list= Arrays.asList(arr);
//		for (Integer i : list) {
//			System.out.print(i+" ");
//		}
//		System.out.println(list.size());
//		System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		List<String> list=new ArrayList<String>();
		list.add("sfd");
		list.add("efe");
		String[] s= (String[]) list.toArray(new String[list.size()]);
		System.out.println(s[1]);
	}

}
