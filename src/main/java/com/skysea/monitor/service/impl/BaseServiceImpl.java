package com.skysea.monitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skysea.monitor.dao.BaseDao;
import com.skysea.monitor.service.BaseService;


public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;
	
	@Override
	public Integer insert(T t) {
		return this.baseDao.insert(t);
	}

	@Override
	public Integer update(T t) {
		return this.baseDao.update(t);
	}

	@Override
	public Integer delete(T t) {
		return this.baseDao.delete(t);
	}

	@Override
	public T get(T t) {
		return this.baseDao.select(t);
	}

	@Override
	public Integer getCount(T t) {
		return this.baseDao.selectCount(t);
	}

	@Override
	public List<T> getList(T t) {
		return this.baseDao.selectList(t);
	}

	@Override
	public List<T> getPageList(T t) {
		return this.baseDao.selectPageList(t);
	}

	@Override
	public Integer insertBatch(List<T> list) {
		return this.baseDao.insertBatch(list);
	}

}
