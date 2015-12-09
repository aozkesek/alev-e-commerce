package com.merge.base.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.merge.base.dao.intf.IGenericDAO;
import com.merge.base.dao.model.AbstractModel;

public abstract class AbstractDAO<T extends AbstractModel> implements IGenericDAO<T> {

	protected enum Operation { C, R, U, D }
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	protected T operate(Operation operation, T model) {
		Session session = getSessionFactory().getCurrentSession();
		
		try {
			switch(operation) {
			case C:
				session.save(model);
				break;
				
			case R:
				model = (T) session.load(model.getClass(), model.getId());
				break;
				
			case U:
				session.update(model);
				break;
				
			case D:
				session.delete(model);
				break;
				
			}
			
			return model;
		}
		catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Could not succeeded", ex);
			throw ex;
		}
		finally {
			
		}		
	}
	
	@Override
	public T create(T model) {
		return operate(Operation.C, model);
	}

	@Override
	public T read(T model) {
		return operate(Operation.R, model);
	}
	
	@Override
	public T update(T model) {
		return operate(Operation.U, model);
	}

	@Override
	public T delete(T model) {
		return operate(Operation.D, model);
	}

	@Transactional
	protected List<T> operateList(T model, Integer firstResult, Integer maxResult) {
		List<T> result = new ArrayList<T>();
		Session session = getSessionFactory().getCurrentSession();

		try {
			
			Criteria criteria = 
					model == null ? 
							getListCriteria(session) 
							: getListByCriteria(session, model);
			
			result = criteria
					.setFirstResult(firstResult)
					.setMaxResults(maxResult)
					.list();
			
			return result;
		}
		catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Could not succeeded", ex);
			throw ex;
		}
		finally {
		}
	}
	
	@Transactional
	protected Integer operateMaxResult(T model) {
		Long result;
		Session session = getSessionFactory().getCurrentSession();
		
		try {
			
			Criteria criteria = 
					model == null ? 
							getListCriteria(session) 
							: getListByCriteria(session, model);
			
			result = (Long) criteria
					.setProjection(Projections.rowCount())
					.uniqueResult();
			
			return result.intValue();
		}
		catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Could not succeeded", ex);
			throw ex;
		}
		finally {
		}
	}
	
	@Override
	public Integer getListMaxResult() {
		return operateMaxResult(null);
	}
	
	@Override
	public Integer getListByMaxResult(T model) {
		return operateMaxResult(model);
	}
	
	@Override
	public List<T> list(Integer firstResult, Integer maxResult) {
		return operateList(null, firstResult, maxResult);
	}

	@Override
	public List<T> listBy(T model, Integer firstResult, Integer maxResult) {
		return operateList(model, firstResult, maxResult);
	}
	
	public abstract Criteria getListCriteria(Session session);
	public abstract Criteria getListByCriteria(Session session, T model);
	
}
