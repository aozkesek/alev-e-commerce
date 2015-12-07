package com.merge.alev.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.merge.alev.dao.model.OrderDetail;
import com.merge.base.dao.impl.AbstractDiscreteDAO;

@Component
public class OrderDetailDAO extends AbstractDiscreteDAO<OrderDetail> {

	@Override
	public Criteria getListCriteria(Session session) {
		return session.createCriteria(OrderDetail.class);
	}

	@Override
	public Criteria getListByCriteria(Session session, OrderDetail model) {
		Criteria criteria = session
				.createCriteria(OrderDetail.class);
//				.add(Restrictions.like("name", model.getName().concat("%")));
//	
//		if (model.getPostCode() != null && !model.getPostCode().isEmpty())
//			criteria.add(Restrictions.like("postCode", model.getPostCode().concat("%")));
//			
		return criteria;
	}

	
}
