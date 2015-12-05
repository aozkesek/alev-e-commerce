package com.merge.alev.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.merge.alev.dao.intf.IGenericDAO;
import com.merge.alev.dao.model.AbstractModel;

public abstract class AbstractDiscreteDAO<T extends AbstractModel> implements IGenericDAO<T> {

	protected enum Operation { C, R, U, D }
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected T operate(Operation operation, T model) {
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			switch(operation) {
			case C:
				session.save(model);
				break;
				
			case R:
				model = (T) session.get(model.getClass(), model.getId());
				break;
				
			case U:
				session.update(model);
				break;
				
			case D:
				session.delete(model);
				break;
				
			}
			
			transaction.commit();
			return model;
		}
		catch (Exception ex) {
			if (transaction != null)
				transaction.rollback();
			LoggerFactory.getLogger(getClass()).error("Could not succeeded", ex);
			throw ex;
		}
		finally {
			if (session.isOpen())
				session.close();
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

	protected List<T> operateList(T model, Integer firstResult, Integer maxResult) {
		List<T> result = new ArrayList<T>();
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Criteria criteria = 
					model == null ? 
							getListCriteria(session) 
							: getListByCriteria(session, model);
			
			result = criteria
					.setFirstResult(firstResult)
					.setMaxResults(maxResult)
					.list();
			
			transaction.commit();
			return result;
		}
		catch (Exception ex) {
			if (transaction != null)
				transaction.rollback();
			LoggerFactory.getLogger(getClass()).error("Could not succeeded", ex);
			throw ex;
		}
		finally {
			if (session.isOpen())
				session.close();
		}
	}
	
	protected Integer operateMaxResult(T model) {
		Long result;
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Criteria criteria = 
					model == null ? 
							getListCriteria(session) 
							: getListByCriteria(session, model);
			
			result = (Long) criteria
					.setProjection(Projections.rowCount())
					.uniqueResult();
			
			transaction.commit();
			return result.intValue();
		}
		catch (Exception ex) {
			if (transaction != null)
				transaction.rollback();
			LoggerFactory.getLogger(getClass()).error("Could not succeeded", ex);
			throw ex;
		}
		finally {
			if (session.isOpen())
				session.close();
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
