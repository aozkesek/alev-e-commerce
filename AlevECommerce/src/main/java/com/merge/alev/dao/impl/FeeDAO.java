package com.merge.alev.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.merge.alev.dao.model.Fee;
import com.merge.base.dao.impl.AbstractDAO;

@Component(value="feeDao")
public class FeeDAO extends AbstractDAO<Fee> {

	@Override
	public Criteria getListCriteria(Session session) {
		return session.createCriteria(Fee.class);
	}

	@Override
	public Criteria getListCriteriaBy(Session session, Fee model) {
		Criteria criteria = session
				.createCriteria(Fee.class)
				.add(Restrictions.like("name", model.getName().concat("%")));
	
		if (model.getName() != null && !model.getName().isEmpty())
			criteria.add(Restrictions.like("name", model.getName().concat("%")));
			
		return criteria;
	}

	
}
