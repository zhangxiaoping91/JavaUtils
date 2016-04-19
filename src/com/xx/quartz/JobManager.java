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
	 * ��Ӷ�ʱ���񣬲���ִ�� ����6:23:07
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
	 * �޸Ķ�ʱ���񣬲���ִ�� ����6:23:07
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
//		JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, job.getClass());// �������������飬����ִ����
//		// ������
//		CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// ��������,��������
//		trigger.setCronExpression(time);// ������ʱ���趨
//		sched.scheduleJob(jobDetail, trigger);
//		// ����
//		if (!sched.isShutdown())
//			sched.start();
//	}

}
