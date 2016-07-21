package com.skysea.monitor.domain.support;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Pager implements Serializable {
	private static final long serialVersionUID = -2345403372879875867L;
	private Integer first = 0;
	private Long totalCount;
	private Integer page = 1;
	private Integer rows = 10;

	public Pager() {
		
	}
	public Integer getFirst() {
		return first;
	}
	public void setFirst(Integer first) {
		this.first = first;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void init(){
		if(null == this.page || null == this.rows){
			return;
		}
		if(this.page < 1 || this.rows < 0){
			this.first = 0;
		}else{
			this.first = (this.page - 1) * this.rows;
		}
	}
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
