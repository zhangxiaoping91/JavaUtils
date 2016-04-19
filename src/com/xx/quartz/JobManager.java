package com.xx.quartz;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

public class JobManager {
	private static SchedulerFactory sdf=new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "group1";  
	private static String TRIGGER_GROUP_NAME = "trigger1";  
	/**
	 * 添加定时任务，并且执行 下午6:23:07
	 * 
	 * @throws SchedulerException
	 * @throws ParseException 
	 */
	public static void addAndRun(String JobName,Job job,String time) throws SchedulerException, ParseException {

		 Scheduler scheduler=sdf.getScheduler();
		 JobDetail jobDetail =new JobDetailImpl(JobName,JOB_GROUP_NAME,job.getClass());
		CronTrigger trigger=new CronTriggerImpl(JobName, TRIGGER_GROUP_NAME,time);
		 scheduler.scheduleJob(jobDetail, trigger);
		 if(!scheduler.isShutdown())
		 {
			 scheduler.start();
		 }
	}

	
	/**
	 * 修改定时任务，并且执行 下午6:23:07
	 * 
	 * @throws SchedulerException
	 * @throws ParseException 
	 */
//	public static void modifyAndRun(String JobName,String time) throws SchedulerException, ParseException {
//
//		Scheduler scheduler=sdf.getScheduler();
//		Trigger trigger=scheduler.getTrigger();
//		if(trigger!=null)
//		{
//			scheduler.resumeTrigger(trigger.getKey());
//		}
//	}
	
//	public static void addJob(String jobName, Job job, String time) throws SchedulerException, ParseException {
//		Scheduler sched = sdf.getScheduler();
//		JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, job.getClass());// 任务名，任务组，任务执行类
//		// 触发器
//		CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组
//		trigger.setCronExpression(time);// 触发器时间设定
//		sched.scheduleJob(jobDetail, trigger);
//		// 启动
//		if (!sched.isShutdown())
//			sched.start();
//	}

}
