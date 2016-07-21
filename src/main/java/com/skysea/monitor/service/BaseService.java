package com.skysea.monitor.service;

import java.util.List;


public interface BaseService<T> {

	Integer insert(T t);

	Integer update(T t);

	Integer delete(T t);

	T get(T t);

	Integer getCount(T t);

	List<T> getList(T t);

	List<T> getPageList(T t);
	
	Integer insertBatch(List<T> list);
	
}