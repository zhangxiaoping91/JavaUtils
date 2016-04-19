package com.xx.JDKtask;

import java.util.Date;
import java.util.TimerTask;

public class MyTask01 extends TimerTask {

	@Override
	public void run() {
		System.out.println(new Date().toLocaleString()+" 服务进行中……");
	}

}
