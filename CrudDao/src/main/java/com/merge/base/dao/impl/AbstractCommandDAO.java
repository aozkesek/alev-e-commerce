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

public abstract class AbstractCommandDAO<T extends AbstractModel> implements IGenericDAO<T> {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public abstract Criteria getListCriteria(Session session);
	public abstract Criteria getListCriteriaBy(Session session, T model);
	public abstract T createCommand(T model);
	public abstract T readCommand(T model);
	public abstract T updateCommand(T model);
	public abstract T deleteCommand(T model);
	
	protected T operate(CrudEnumeration operation, T model) throws Exception {
		boolean transactionBegun = false;
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			//somebody forgot to begin transaction, take care 
			if (session.getTransaction().getStatus() == TransactionStatus.NOT_ACTIVE) {
				session.getTransaction().begin();
				transactionBegun = true;
			}
			
			switch(operation) {
			case C:
				beforeCreate(model);
				createCommand(model);
				afterCreate(model);
				break;
				
			case R:
				beforeRead(model);
				readCommand(model);
				afterRead(model);
				break;
				
			case U:
				beforeUpdate(model);
				updateCommand(model);
				afterUpdate(model);
				break;
				
			case D:
				beforeDelete(model);
				deleteCommand(model);
				afterDelete(model);
				break;
				
			case Q:
				break;
			}
			
			if (transactionBegun)
				session.getTransaction().commit();
			
			return model;
		}
		catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Could not succeeded", ex);
			if (transactionBegun)
				session.getTransaction().rollback();
			throw ex;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> operateList(T model, Integer firstResult, Integer maxResult) {
		List<T> result = new ArrayList<T>();
		boolean transactionBegun = false;
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			//somebody forgot to begin transaction, take care 
			if (session.getTransaction().getStatus() == TransactionStatus.NOT_ACTIVE) {
				session.getTransaction().begin();
				transactionBegun = true;
			}
			
			Criteria criteria = 
					model == null ? 
							getListCriteria(session) 
							: getListCriteriaBy(session, model);
			
			result = criteria
					.setFirstResult(firstResult)
					.setMaxResults(maxResult)
					.list();
			
			if (transactionBegun)
				session.getTransaction().commit();
			
			return result;
		}
		catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Could not succeeded", ex);
			if (transactionBegun)
				session.getTransaction().rollback();
			throw ex;
		}
		
	}
	
	protected Integer operateMaxResult(T model) {
		Long result;
		boolean transactionBegun = false;
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			//somebody forgot to begin transaction, take care 
			if (session.getTransaction().getStatus() == TransactionStatus.NOT_ACTIVE) {
				session.getTransaction().begin();
				transactionBegun = true;
			}
			
			Criteria criteria = 
					model == null ? 
							getListCriteria(session) 
							: getListCriteriaBy(session, model);
			
			result = (Long) criteria
					.setProjection(Projections.rowCount())
					.uniqueResult();
			
			if (transactionBegun)
				session.getTransaction().commit();
			
			return result.intValue();
		}
		catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Could not succeeded", ex);
			if (transactionBegun)
				session.getTransaction().rollback();
			throw ex;
		}

	}
	
	@Override
	@Transactional
	public T create(T model) throws Exception {
		return operate(CrudEnumeration.C, model);
	}

	@Override
	@Transactional(readOnly=true)
	public T read(T model) throws Exception {
		return operate(CrudEnumeration.R, model);
	}
	
	@Override
	@Transactional
	public T update(T model) throws Exception {
		return operate(CrudEnumeration.U, model);
	}

	@Override
	@Transactional
	public T delete(T model) throws Exception {
		return operate(CrudEnumeration.D, model);
	}

	@Override
	@Transactional(readOnly=true)
	public Integer getListMaxResult() {
		return operateMaxResult(null);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Integer getListMaxResultBy(T model) {
		return operateMaxResult(model);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<T> list(Integer firstResult, Integer maxResult) {
		return operateList(null, firstResult, maxResult);
	}

	@Override
	@Transactional(readOnly=true)
	public List<T> listBy(T model, Integer firstResult, Integer maxResult) {
		return operateList(model, firstResult, maxResult);
	}

	public T beforeCreate(T model) throws Exception {
		return model;
	};
	
	public T afterCreate(T model) throws Exception {
		return model;
	};
	
	public T beforeRead(T model) throws Exception {
		return model;
	};
	
	public T afterRead(T model) throws Exception {
		return model;
	};
	
	public T beforeUpdate(T model) throws Exception {
		return model;
	};
	
	public T afterUpdate(T model) throws Exception {
		return model;
	};
	
	public T beforeDelete(T model) throws Exception {
		return model;
	};
	
	public T afterDelete(T model) throws Exception {
		return model;
	};	
	
}
