package com.xx.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * ������<br>
 * 2016��7��6�� ����3:54:21
 * 
 * @author zhangxiaoping
 */
public class UtilExec {
	public static void main(String[] args) {
		// binarySearch();
		UtilExec utilExec=new UtilExec();
		long start=System.currentTimeMillis();
		utilExec.chooseSort();
		long end=System.currentTimeMillis();
		System.out.println("ѡ������Ч�ʣ�"+(end-start));
		
		long start2=System.currentTimeMillis();
		utilExec.quickSort();
		long end2=System.currentTimeMillis();
		System.out.println("��������Ч�ʣ�"+(end2-start2));
		
	}

	/**
	 * ���ֲ���
	 */
	@Test
	public void binarySearch() {
		int[] array = new int[] { 1, 2, 5, 8, 9, 10 };
		int value = 2;
		int low = 0;
		int high = array.length - 1;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (value == array[middle]) {
				System.out.println(middle);
				break;
			} else if (value < array[middle]) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
	}

	/**
	 * ��ȡLIST�е���LIST
	 * 
	 * @author zhangxp
	 */
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private static void getSubList() {
		List list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("6");
		List list2 = list.subList(3, 5);
		for (Object o : list2) {
			System.out.println(o);

		}
	}

	/**
	 * ð������ ����4:01:46
	 */
	@Test
	public void maopaoSort() {
		int temp;
		Integer[] arr = new Integer[] { 4, 2, 5, 1, 6, 9, 3, 7 };
		for (int a = 0; a < arr.length; a++) {
			for (int b = 0; b < arr.length - a - 1; b++) {
				if (arr[b] > arr[b + 1]) {
					temp = arr[b];
					arr[b] = arr[b + 1];
					arr[b + 1] = temp;
				}
			}
		}
		for (int a : arr) {
			System.out.print(a + " ");
		}

	}

	/**
	 * ��������<br>
	 * ����4:34:14
	 */
	@Test
	public void quickSort() {
		Integer[] arr = new Integer[] {23,12,43,2,56, 4, 2,11,42,67, 5, 1, 6,13,76,46, 9, 3, 7 };
		_quickSort(arr, 0, arr.length - 1);
		for (int a : arr) {
			System.out.print(a + " ");
		}

	}

	public void _quickSort(Integer[] arr, int low, int high) {
		if (low < high) {
			int middle = getMiddle(arr, low, high); // ��list�������һ��Ϊ��
			_quickSort(arr, low, middle - 1); // �Ե��ֱ���еݹ�����
			_quickSort(arr, middle + 1, high); // �Ը��ֱ���еݹ�����
		}

	}

	public int getMiddle(Integer[] arr, int low, int high) {
		int tmp = arr[low]; // ����ĵ�һ����Ϊ����
		while (low < high) {
			while (low < high && arr[high] >= tmp) {
				high--;
			}
			arr[low] = arr[high]; // ������С�ļ�¼�Ƶ��Ͷ�
			while (low < high && arr[low] <= tmp) {
				low++;
			}
			arr[high] = arr[low]; // �������ļ�¼�Ƶ��߶�
		}
		arr[low] = tmp; // �����¼��β
		return low; // ���������λ��

	}
	
	/**
	 * ѡ������
	 * ����4:59:56
	 */
	@Test
	public void chooseSort(){
		Integer[] arr = new Integer[] {23,12,43,2,56, 4, 2,11,42,67, 5, 1, 6,13,76,46, 9, 3, 7 };
		int temp;
		int max;
		for(int a=0;a<arr.length;a++){
			max=a;
			for(int b=a+1;b<arr.length;b++){
				if(arr[max]>arr[b]){
					max=b;
				}
			}
			if(a!=max){
				temp=arr[a];
				arr[a]=arr[max];
				arr[max]=temp;
			}
		}
		for (int a : arr) {
			System.out.print(a + " ");
		}
	}

}
