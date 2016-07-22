package com.skysea.monitor.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skysea.monitor.domain.support.BaseDomain;

public class HeartbeatLog extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Integer id;
	
	private Integer appInstanceId;
	
	private Integer connTime;
	
	private Integer isConn;
	
	private Integer receivedSize;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	private String receivedContent;
	
	public HeartbeatLog() {

	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getAppInstanceId() {
		return appInstanceId;
	}
	
	public void setAppInstanceId(Integer appInstanceId) {
		this.appInstanceId = appInstanceId;
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

	public String getReceivedContent() {
		return receivedContent;
	}

	public void setReceivedContent(String receivedContent) {
		this.receivedContent = receivedContent;
	}
	
}