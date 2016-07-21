package com.skysea.monitor.domain;

import java.io.Serializable;

import com.skysea.monitor.domain.support.BaseDomain;

public class HeartbeatLogText extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Integer id;
	
	private Integer instanceLogId;
	
	private String receivedContent;
	
	public HeartbeatLogText() {

	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getInstanceLogId() {
		return instanceLogId;
	}
	
	public void setInstanceLogId(Integer instanceLogId) {
		this.instanceLogId = instanceLogId;
	}
	
	public String getReceivedContent() {
		return receivedContent;
	}
	
	public void setReceivedContent(String receivedContent) {
		this.receivedContent = receivedContent;
	}
	
}