package com.skysea.monitor.domain.support;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseDomain implements Serializable {
	private static final long serialVersionUID = 5510966496699243858L;
	private Pager pager = new Pager();
	private Map<String, Object> map = new HashMap<String, Object> ();

	public Map<String, Object> getMap() {
		return this.map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	public void initPager(int page,int rows){
		this.pager.setPage(page);
		this.pager.setRows(rows);
		this.pager.init();
	}

}
