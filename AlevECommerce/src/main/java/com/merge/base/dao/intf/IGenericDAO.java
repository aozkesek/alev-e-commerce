package com.merge.base.dao.intf;

import java.util.List;

import com.merge.base.dao.model.AbstractModel;

public interface IGenericDAO<T extends AbstractModel> {
	
	T create(T model);
	T read(T model);
	T update(T model);
	T delete(T model);
	
	void setTransactionDiscrete(boolean isTransactionDiscrete);
	
	Integer getListMaxResult();
	Integer getListMaxResultBy(T model);
	List<T> list(Integer firstResult, Integer maxResult);
	List<T> listBy(T model, Integer firstResult, Integer maxResult);

}
