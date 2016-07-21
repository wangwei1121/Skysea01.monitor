package com.skysea.monitor.web.entity;

public class UIResult {
	private boolean flag = false;
	private String message;
	private Object result;
	private Integer totalCount = 0;

	public void setResult(Object result) {
		this.flag = true;
		this.result = result;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Object getResult() {
		return this.result;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isFlag() {
		return this.flag;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
}
