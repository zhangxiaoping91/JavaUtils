package com.xx.JDKtask;

import java.util.Timer;

public class TestTask {
	Timer timer;
	public TestTask(int seconds)
	{
		timer=new Timer();
		timer.schedule(new MyTask01(), seconds*1000);
	}
	
	public TestTask(int seconds,int deay)
	{
		timer=new Timer();
		timer.schedule(new MyTask01(), seconds*1000,deay*1000);
	}
	
	public static void main(String[] args) {
		new TestTask(3,5);
	}

}
