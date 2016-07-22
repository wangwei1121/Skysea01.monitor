package com.skysea.monitor.domain;

import java.io.Serializable;
import java.util.Date;

import com.skysea.monitor.domain.support.BaseDomain;

public class AppInstance extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Integer id;
	
	private Integer monitorAppId;
	
	private String juid;
	
	private String name;
	
	private String url;
	
	private String requestMethod;
	
	private String requestParams;
	
	private String contentType;
	
	private Integer maxConn;
	
	private Integer frequency;
	
	private String email;
	
	private Integer isEnable;
	
	private Integer isDel;
	
	private String remark;
	
	private Date createTime;
	
	private Date updateTime;
	
	public AppInstance() {

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
	
	public String getJuid() {
		return juid;
	}
	
	public void setJuid(String juid) {
		this.juid = juid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getRequestMethod() {
		return requestMethod;
	}
	
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	
	public String getRequestParams() {
		return requestParams;
	}
	
	public void setRequestParams(String requestParams) {
		this.requestParams = requestParams;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public Integer getMaxConn() {
		return maxConn;
	}
	
	public void setMaxConn(Integer maxConn) {
		this.maxConn = maxConn;
	}
	
	public Integer getFrequency() {
		return frequency;
	}
	
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getIsEnable() {
		return isEnable;
	}
	
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	
	public Integer getIsDel() {
		return isDel;
	}
	
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}