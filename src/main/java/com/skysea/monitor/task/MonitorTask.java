package com.skysea.monitor.task;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class MonitorTask implements InitializingBean{

	private final Logger LOG = LoggerFactory.getLogger(MonitorTask.class);
	
	@PostConstruct
	public void init(){
		LOG.info("..........init..........");
		 try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(),e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOG.info("..........afterPropertiesSet..........");
	}
	
	 @PreDestroy 
	 public void destory(){
		 LOG.info("..........destory..........");
	 }
	
}
