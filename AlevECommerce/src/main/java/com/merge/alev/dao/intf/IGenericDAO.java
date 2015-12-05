package com.merge.alev.dao.intf;

import java.util.List;

import com.merge.alev.dao.model.AbstractModel;

public interface IGenericDAO<T extends AbstractModel> {
	
	T create(T model);
	T read(T model);
	T update(T model);
	T delete(T model);
	
	Integer getListMaxResult();
	Integer getListByMaxResult(T model);
	List<T> list(Integer firstResult, Integer maxResult);
	List<T> listBy(T model, Integer firstResult, Integer maxResult);

}
