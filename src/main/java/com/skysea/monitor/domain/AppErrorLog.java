package com.skysea.monitor.domain;

import java.io.Serializable;
import java.util.Date;

import com.skysea.monitor.domain.support.BaseDomain;

public class AppErrorLog extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Integer id;
	
	private Integer monitorAppId;
	
	private String log;
	
	private Date createTime;
	
	public AppErrorLog() {

	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getMonitorAppId() {
		return monitorAppId;
	}
	
	public void setMonitorAppId(Integer monitorAppId) {
		this.monitorAppId = monitorAppId;
	}
	
	public String getLog() {
		return log;
	}
	
	public void setLog(String log) {
		this.log = log;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}