package com.bizconf.vcaasz.task.billing;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

import com.bizconf.vcaasz.component.BaseConfig;
import com.bizconf.vcaasz.logic.impl.CDRReportLogic;

/**
 * @desc 生成CDR文件任务调度器
 * @author Darren
 * @date 2014-10-24
 */
public class CreateCDRExecutor implements Job{

	private static final Log logger = LogFactory.getLog(CreateCDRExecutor.class);

	public static void main(String[] args) {
		
	   try {
		   //触发生成cdr时间
		   	String date = BaseConfig.getInstance().getString("Date","1");
		   	String hour = BaseConfig.getInstance().getString("Hour","3");
		   	String minute = BaseConfig.getInstance().getString("Minute","0");
		   	
			SchedulerFactory schedFact =  new StdSchedulerFactory();
			Scheduler sched = schedFact.getScheduler();
			sched.start();
			JobDetail jobDetail = new JobDetail("myJob", "myJobGroup",CreateCDRExecutor.class);
//			jobDetail.getJobDataMap().put("type", "FULL");
			//创建一个每月触发的Trigger，指明几日几点几分执行
			Trigger trigger = TriggerUtils.makeMonthlyTrigger(Integer.parseInt(date), 
					Integer.parseInt(hour), Integer.parseInt(minute));
//			Trigger trigger = TriggerUtils.makeDailyTrigger(20,47);
//			Trigger trigger = TriggerUtils.makeMinutelyTrigger(30);
			trigger.setGroup("myTriggerGroup");
			trigger.setStartTime(TriggerUtils.getEvenSecondDate(new Date()));
			trigger.setName("myTrigger");
			sched.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("The task is excuting for exception "+e);
		}
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		logger.info("Start to generate cdr file.....");
		long beginTime = System.currentTimeMillis();
		//生成上个月的账单
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		int minDay=calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		int maxDay=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		
		String begin = year+"-"+(month+1)+"-"+minDay;
		String end = year+"-"+(month+1)+"-"+maxDay;
		logger.info("The cdr Start date:"+begin+"  The cdr end date:"+end);
		
		CDRReportLogic.getInstance().PerProcess(begin, end, 300, 1);
		logger.info("generate cdr file end!!");
		
		long endTime = System.currentTimeMillis();
		long minutes = (endTime - beginTime)/(1000 * 60);
		logger.info("The process takes about "+minutes+" minutes!");
	}
	
}
