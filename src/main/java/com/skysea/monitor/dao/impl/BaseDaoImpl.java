package com.skysea.monitor.dao.impl;

import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.skysea.monitor.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Autowired
	protected SqlSession sqlSession;
	
	protected Class<T> elementClass;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDaoImpl() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		this.elementClass = ((Class) params[0]);
	}

	public Integer insert(T t) {
		String statement = this.elementClass.getName() + ".insert";
		return sqlSession.insert(statement, t);
	}

	public Integer update(T t){
		String statement = this.elementClass.getName() + ".update";
		return sqlSession.update(statement, t);
	}

	public Integer delete(T t){
		String statement = this.elementClass.getName() + ".delete";
		return sqlSession.delete(statement, t);
	}

	public T select(T t){
		String statement = this.elementClass.getName() + ".select";
		return sqlSession.selectOne(statement, t);
	}

	public Integer selectCount(T t){
		String statement = this.elementClass.getName() + ".selectCount";
		return sqlSession.selectOne(statement, t);
	}

	public List<T> selectList(T t){
		String statement = this.elementClass.getName() + ".selectList";
		return sqlSession.selectList(statement, t);
	}

	public List<T> selectPageList(T t){
		String statement = this.elementClass.getName() + ".selectPageList";
		return sqlSession.selectList(statement, t);
	}

	@Override
	public Integer insertBatch(List<T> list) {
		String statement = this.elementClass.getName() + ".insertBatch";
		return sqlSession.insert(statement, list);
	}

}
