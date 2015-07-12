package com.hexin.core.base;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.hexin.core.exception.ErrorCodeException;


public class DaoHelper extends SqlSessionDaoSupport {
	private  SqlSessionTemplate sqlSessionTemplate;
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public  Object insert(String nameSpace,String sqlID,IEntity entity) throws ErrorCodeException{
		try{
			return (Object)sqlSessionTemplate.insert(getStatement(nameSpace, sqlID), entity);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	public  Object insert(String nameSpace,IEntity entity) throws ErrorCodeException{
		try{
			return insert(nameSpace, "insert", entity);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	public  Object update(String nameSpace,String sqlID,IEntity entity) throws ErrorCodeException{
		try{
			return (Object)sqlSessionTemplate.update(getStatement(nameSpace, sqlID), entity);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	public  Object update(String nameSpace, IEntity entity)throws ErrorCodeException{
		try{
			return update(nameSpace, "update", entity);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	public  int delete(String nameSpace,String sqlID,IEntity entity) throws ErrorCodeException{
		try{
			return sqlSessionTemplate.delete(getStatement(nameSpace, sqlID), entity);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	public  int delete(String nameSpace, IEntity entity) throws ErrorCodeException{
		try{
			return delete(nameSpace, "delete", entity);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	public  <T extends IEntity> T getOne(String nameSpace,String sqlID,IEntity paramObject) throws ErrorCodeException{
		try{
			return sqlSessionTemplate.selectOne(getStatement(nameSpace, sqlID), paramObject);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	public  <T extends IEntity> T getOne(String nameSpace, IEntity paramObject) throws ErrorCodeException{
		try{
			return getOne(nameSpace, "getone", paramObject);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	public  <T extends IEntity> List<T> query(String nameSpace, String sqlID, IEntity paramObject) throws ErrorCodeException{
		try{
			return sqlSessionTemplate.selectList(getStatement(nameSpace, sqlID), paramObject);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	public  <T extends IEntity> List<T> query(String nameSpace, IEntity paramObject) throws ErrorCodeException{
		try{
			return query(nameSpace,"query", paramObject);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	public  <T extends IEntity> List<T> page(String nameSpace, String sqlID, IEntity paramObject) throws ErrorCodeException{
		try{
			return sqlSessionTemplate.selectList(getStatement(nameSpace, sqlID), paramObject);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	public  <T extends IEntity> List<T> page(String nameSpace, IEntity paramObject) throws ErrorCodeException{
		try{
			return page(nameSpace,"page", paramObject);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	public  int count(String nameSpace, String sqlID, IEntity paramObject) throws ErrorCodeException{
		try{
			return Integer.valueOf(String.valueOf(sqlSessionTemplate.selectOne(getStatement(nameSpace, sqlID), paramObject)));
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	public  int count(String nameSpace, IEntity paramObject) throws ErrorCodeException{
		try{
			return count(nameSpace, "count", paramObject);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	public  Object queryObject(String nameSpace, String sqlID, IEntity paramObject) throws ErrorCodeException{
		try{
			return sqlSessionTemplate.selectOne(getStatement(nameSpace, sqlID), paramObject);
		}
		catch(Exception ex){
			throw new ErrorCodeException("DB00001", ex.getMessage(), ex);
		}
	}
	
	private  String getStatement(String nameSpace,String sqlID){
		return nameSpace+"."+sqlID;
	}
}
