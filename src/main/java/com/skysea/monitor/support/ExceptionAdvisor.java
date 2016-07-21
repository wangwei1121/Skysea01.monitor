package com.skysea.monitor.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

public class ExceptionAdvisor implements ThrowsAdvice {
	private static Logger LOG = LoggerFactory.getLogger(ExceptionAdvisor.class);

	public void afterThrowing(Exception e) throws Throwable {
		LOG.debug("##########################exception 来了！");
	}
}
