package com.skysea.monitor.service;

import com.skysea.monitor.domain.HeartbeatLog;

public interface HeartbeatLogService extends BaseService<HeartbeatLog> {
	
	Integer insertWitgReceivedContent(HeartbeatLog entity,String receivedContent);

}