package com.skysea.monitor.domain;

import java.io.Serializable;

import com.skysea.monitor.domain.support.BaseDomain;

public class AppErrorLogText extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Integer id;
	
	private Integer errorLogId;
	
	private String context;
	
	public AppErrorLogText() {

	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getErrorLogId() {
		return errorLogId;
	}
	
	public void setErrorLogId(Integer errorLogId) {
		this.errorLogId = errorLogId;
	}
	
	public String getContext() {
		return context;
	}
	
	public void setContext(String context) {
		this.context = context;
	}
	
}