package com.merge.base.dao.intf;

import java.util.List;

import com.merge.base.dao.model.AbstractModel;

public interface IGenericDAO<T extends AbstractModel> {
	
	T create(T model) throws Exception;
	T read(T model) throws Exception;
	T update(T model) throws Exception;
	T delete(T model) throws Exception;
	
	Integer getListMaxResult();
	Integer getListMaxResultBy(T model);
	List<T> list(Integer firstResult, Integer maxResult);
	List<T> listBy(T model, Integer firstResult, Integer maxResult);

}
