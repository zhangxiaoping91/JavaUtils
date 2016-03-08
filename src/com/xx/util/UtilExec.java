package com.xx.util;

import java.util.ArrayList;
import java.util.List;

public class UtilExec {
	public static void main(String[] args) {
		binarySearch();
		
 	}

	/**
	 * 二分查找
	 */
	private static void binarySearch() {
		int[] array=new int[]{1,2,5,8,9,10};
		int value=9;
		int low=0;
		int high=array.length-1;
		while(low<=high)
		{
			int middle=(low+high)/2;
			if(value==array[middle])
			{
				System.out.println(middle);
				break;
			}
			else if (value<array[middle]) {
				high=middle-1;
			}
			else {
				low=middle+1;
			}
		}
	}

	/**
	 * 获取LIST中的子LIST
	 * @author zhangxp
	 */
	private static void getSubList() {
		List list=new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
 		list.add("6");
		List list2=list.subList(3, 5);
		for (Object o : list2) {
			System.out.println(o);
			
		}
	}

}
