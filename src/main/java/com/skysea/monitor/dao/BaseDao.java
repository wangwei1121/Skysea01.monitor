package com.skysea.monitor.dao;

import java.util.List;


public interface BaseDao<T> {

	Integer insert(T t);

	Integer update(T t);

	Integer delete(T t);

	T select(T t);

	Integer selectCount(T t);

	List<T> selectList(T t);

	List<T> selectPageList(T t);
	
	Integer insertBatch(List<T> list);
	
}