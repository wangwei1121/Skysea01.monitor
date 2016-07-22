package com.skysea.monitor.task;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skysea.monitor.domain.AppInstance;
import com.skysea.monitor.service.AppInstanceService;
import com.skysea.monitor.service.HeartbeatLogService;

@Component
public class HeartbeatTask{

	private final Logger LOG = LoggerFactory.getLogger(HeartbeatTask.class);
	
	private static final String JOB_GROUP = "heart_beat_group";

	@Autowired
	private AppInstanceService appInstanceService;
	
	@Autowired
	private HeartbeatLogService heartbeatLogService;
	
	private Scheduler scheduler;
	
	@PostConstruct
	public void init(){
		LOG.info("..........init..........");
		 try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			List<AppInstance> list = this.appInstanceService.getList(null);
			if(null != list && list.size() > 0){
				for(AppInstance entity:list){
					JobDetail jobDetail = JobBuilder.newJob(HeartbeatJob.class).withIdentity(entity.getId().toString(), JOB_GROUP).build();
					Trigger trigger = TriggerBuilder.newTrigger().withIdentity(entity.getId().toString(), JOB_GROUP).withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(entity.getFrequency()).repeatForever()).build();
					jobDetail.getJobDataMap().put("appInstanceService", this.appInstanceService);
					jobDetail.getJobDataMap().put("heartbeatLogService", this.heartbeatLogService);
					jobDetail.getJobDataMap().put("id", entity.getId());
					scheduler.scheduleJob(jobDetail, trigger);
				}
			}
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(),e);
		}
	}
	
	public void updateJob(AppInstance entity){
		if(null == entity || null == entity.getId() || null == entity.getFrequency()){
			return;
		}
		try {
			JobDetail jobDetail = scheduler.getJobDetail(new JobKey(entity.getId().toString(), JOB_GROUP));
			if(null == jobDetail){
				jobDetail = JobBuilder.newJob(HeartbeatJob.class).withIdentity(entity.getId().toString(), JOB_GROUP).build();
				Trigger trigger = TriggerBuilder.newTrigger().withIdentity(entity.getId().toString(), JOB_GROUP).withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(entity.getFrequency()).repeatForever()).build();
				jobDetail.getJobDataMap().put("appInstanceService", this.appInstanceService);
				jobDetail.getJobDataMap().put("heartbeatLogService", this.heartbeatLogService);
				jobDetail.getJobDataMap().put("id", entity.getId());
				scheduler.scheduleJob(jobDetail, trigger);
			}else{
				TriggerKey triggerKey = new TriggerKey(entity.getId().toString(), JOB_GROUP);
				Trigger trigger = scheduler.getTrigger(new TriggerKey(entity.getId().toString(), JOB_GROUP));
				SimpleScheduleBuilder builder = (SimpleScheduleBuilder)trigger.getScheduleBuilder();
				builder.withIntervalInSeconds(entity.getFrequency());
				scheduler.resumeTrigger(triggerKey);
			}
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(),e);
		}
	}
	
	public void deleteJob(AppInstance entity) {
		if (null == entity || null == entity.getId()) {
			return;
		}
		try {
			JobKey jobKey = new JobKey(entity.getId().toString(), JOB_GROUP);
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			if (null != jobDetail) {
				TriggerKey triggerKey = new TriggerKey(entity.getId().toString(), JOB_GROUP);
				// 停止触发器
				scheduler.pauseTrigger(triggerKey);
				// 移除触发器
				scheduler.unscheduleJob(triggerKey);
				// 删除任务
				scheduler.deleteJob(jobKey);
			}
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(),e);
		}

	}

	 @PreDestroy 
	 public void destory(){
		 LOG.info("..........destory..........");
		 if(null != scheduler){
			 try {
				 scheduler.shutdown();
				 scheduler.clear();
			} catch (SchedulerException e) {
				LOG.error(e.getMessage(),e);
			}
		 }
		 
	 }
	
}
