package com.xx.quartz;

import java.text.ParseException;

import org.quartz.SchedulerException;

public class TestJob {
	public static void main(String[] args) throws SchedulerException, ParseException, InterruptedException {
		MyJob job=new MyJob();
		String jobName="my";
		String time="0/2 * * * * ?";
		String time2="0/5 * * * * ?";
		System.out.println("【系统启动】");
		JobManager.addAndRun(jobName, job, time);
		
		Thread.sleep(10000);
		System.out.println("【修改任务】");
//		JobManager.modifyAndRun(jobName, time2);
		
	}

}
