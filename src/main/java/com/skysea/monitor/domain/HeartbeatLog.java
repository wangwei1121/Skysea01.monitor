package com.skysea.monitor.domain;

import java.io.Serializable;
import java.util.Date;

import com.skysea.monitor.domain.support.BaseDomain;

public class HeartbeatLog extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Integer id;
	
	private Integer instanceId;
	
	private Integer connTime;
	
	private Integer isConn;
	
	private Integer receivedSize;
	
	private Date createTime;
	
	public HeartbeatLog() {

	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getInstanceId() {
		return instanceId;
	}
	
	public void setInstanceId(Integer instanceId) {
		this.instanceId = instanceId;
	}
	
	public Integer getConnTime() {
		return connTime;
	}
	
	public void setConnTime(Integer connTime) {
		this.connTime = connTime;
	}
	
	public Integer getIsConn() {
		return isConn;
	}
	
	public void setIsConn(Integer isConn) {
		this.isConn = isConn;
	}
	
	public Integer getReceivedSize() {
		return receivedSize;
	}
	
	public void setReceivedSize(Integer receivedSize) {
		this.receivedSize = receivedSize;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}