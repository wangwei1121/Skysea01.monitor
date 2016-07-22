package com.skysea.monitor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skysea.monitor.domain.HeartbeatLog;
import com.skysea.monitor.domain.HeartbeatLogText;
import com.skysea.monitor.service.HeartbeatLogService;
import com.skysea.monitor.service.HeartbeatLogTextService;

@Service
public class HeartbeatLogServiceImpl extends BaseServiceImpl<HeartbeatLog> implements HeartbeatLogService{

	@Autowired
	private HeartbeatLogTextService heartbeatLogTextService;
	
	@Override
	public Integer insertWitgReceivedContent(HeartbeatLog entity,String receivedContent) {
		Integer id = this.insert(entity);
		HeartbeatLogText heartbeatLogText = new HeartbeatLogText();
		heartbeatLogText.setHeartbeatLogId(id);
		heartbeatLogText.setReceivedContent(receivedContent);
		this.heartbeatLogTextService.insert(heartbeatLogText);
		return id;
	}

}
