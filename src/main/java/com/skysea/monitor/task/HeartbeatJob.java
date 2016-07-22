package com.skysea.monitor.task;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skysea.monitor.domain.AppInstance;
import com.skysea.monitor.domain.HeartbeatLog;
import com.skysea.monitor.service.AppInstanceService;
import com.skysea.monitor.service.HeartbeatLogService;
import com.skysea.monitor.util.HttpClientUtil;

public class HeartbeatJob implements Job{

	private final Logger LOG = LoggerFactory.getLogger(HeartbeatJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Integer id = (Integer) context.getJobDetail().getJobDataMap().get("id");
		AppInstanceService appInstanceService = (AppInstanceService) context.getJobDetail().getJobDataMap().get("appInstanceService");
		HeartbeatLogService heartbeatLogService = (HeartbeatLogService) context.getJobDetail().getJobDataMap().get("heartbeatLogService");
		LOG.info("........execute HeartbeatJob.." + id + ".......");
		if(null == id || null == appInstanceService || null == heartbeatLogService){
			return;
		}
		try {
			Date now = new Date();
			AppInstance entity = new AppInstance();
			entity.setId(id);
			entity = appInstanceService.get(entity);
			if(null == entity || StringUtils.isBlank(entity.getUrl())){
				return;
			}
			String[] result = HttpClientUtil.doGet(entity.getUrl(), entity.getMaxConn());
			HeartbeatLog heartbeatLog = new HeartbeatLog();
			heartbeatLog.setAppInstanceId(id);
			heartbeatLog.setCreateTime(now);
			if(null != result && result.length == 3){
				heartbeatLog.setConnTime(Integer.parseInt(result[0]));
				heartbeatLog.setReceivedSize(Integer.parseInt(result[1]));
				heartbeatLogService.insertWitgReceivedContent(heartbeatLog, result[2]);
			}else{
				heartbeatLogService.insertWitgReceivedContent(heartbeatLog, null);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}

}
