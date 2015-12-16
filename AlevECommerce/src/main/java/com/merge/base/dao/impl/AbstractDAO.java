package com.merge.base.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.merge.base.dao.intf.IGenericDAO;
import com.merge.base.dao.model.AbstractModel;
import com.merge.base.dao.model.CrudEnumeration;

public abstract class AbstractDAO<T extends AbstractModel> implements IGenericDAO<T> {

	private boolean isTransactionDiscrete;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setTransactionDiscrete(boolean isTransactionDiscrete) {
		this.isTransactionDiscrete = isTransactionDiscrete;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	protected T operate(CrudEnumeration operation, T model) throws Exception {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			
			if (isTransactionDiscrete || session.getTransaction().getStatus() == TransactionStatus.NOT_ACTIVE)
				transaction = session.beginTransaction();
			
			switch(operation) {
			case C:
				session.save(model);
				if (!model.isValid())
					throw new Exception(AbstractModel.INVALID);
				break;
				
			case R:
				model = (T) session.load(model.getClass(), model.getId());
				if (!model.isValid())
					throw new Exception(AbstractModel.INVALID);
				break;
				
			case U:
				if (!model.isValid())
					throw new Exception(AbstractModel.INVALID);
				session.update(model);
				break;
				
			case D:
				session.delete(model);
				break;
			
			case Q:
				break;
				
			}
			
			if (transaction != null)
				transaction.commit();
			
			return model;
		}
		catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Could not succeeded", ex);
			if (transaction != null)
				transaction.rollback();
			throw ex;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> operateList(T model, Integer firstResult, Integer maxResult) {
		List<T> result = new ArrayList<T>();

		try {
			
			Session session = sessionFactory.openSession();
			
			Criteria criteria = 
					model == null ? 
							getListCriteria(session) 
							: getListCriteriaBy(session, model);
			
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

	}

	protected Integer operateMaxResult(T model) {
		Long result;
		
		try {
			
			Session session = sessionFactory.openSession();
			
			Criteria criteria = 
					model == null ? 
							getListCriteria(session) 
							: getListCriteriaBy(session, model);
			
			result = (Long) criteria
					.setProjection(Projections.rowCount())
					.uniqueResult();
			
			return result.intValue();
		}
		catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Could not succeeded", ex);
			throw ex;
		}
		
	}
	
	@Override
	public T create(T model) throws Exception {
		return operate(CrudEnumeration.C, model);
	}

	@Override
	public T read(T model) throws Exception {
		return operate(CrudEnumeration.R, model);
	}
	
	@Override
	public T update(T model) throws Exception {
		return operate(CrudEnumeration.U, model);
	}

	@Override
	public T delete(T model) throws Exception {
		return operate(CrudEnumeration.D, model);
	}
	
	@Override
	public Integer getListMaxResult() {
		return operateMaxResult(null);
	}
	
	@Override
	public Integer getListMaxResultBy(T model) {
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
	public abstract Criteria getListCriteriaBy(Session session, T model);
	
}
