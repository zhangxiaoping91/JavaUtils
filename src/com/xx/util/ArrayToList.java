package com.xx.util;

import java.util.Arrays;
import java.util.List;

public class ArrayToList {
	public static void main(String[] args) {
		Integer[] arr=new Integer[]{1,3,6,4,8,5,2};
		System.out.println(arr.length);
		List<Integer>list= Arrays.asList(arr);
		for (Integer i : list) {
			System.out.print(i+" ");
		}
		System.out.println(list.size());
	}

}
