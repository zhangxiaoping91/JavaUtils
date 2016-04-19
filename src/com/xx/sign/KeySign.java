package com.xx.sign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;



public class KeySign {
	private static String key;
	
	private static void getKey()
	{
		File file=new File("config/param.txt");
		if(file.exists())
		{
			try {
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
				key=(String)ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
		}
	}
	}
	public static void main(String[] args) {
		getKey();
		System.out.println(key);
	}

}
