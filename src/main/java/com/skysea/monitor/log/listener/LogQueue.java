package com.skysea.monitor.log.listener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


@Component
public class LogQueue implements InitializingBean{

	private final Logger LOG = LoggerFactory.getLogger(LogQueue.class);
	
	@PostConstruct 
	public void init(){
		LOG.info("..............init.....................");
	}
	 @PreDestroy  
    public void  dostory(){  
		 LOG.info("..............dostory....................."); 
    }
	 
	@Override
	public void afterPropertiesSet() throws Exception {
		LOG.info("..............afterPropertiesSet.....................");
	}  
	
}
