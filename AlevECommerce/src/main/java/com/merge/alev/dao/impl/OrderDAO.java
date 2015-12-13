package com.merge.alev.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.merge.alev.dao.model.Order;
import com.merge.base.dao.impl.AbstractDAO;

@Component
public class OrderDAO extends AbstractDAO<Order> {

	@Override
	public Criteria getListCriteria(Session session) {
		return session
				.createCriteria(Order.class);
	}

	@Override
	public Criteria getListByCriteria(Session session, Order model) {
		Criteria criteria = session
				.createCriteria(Order.class);
		
//				.add(Restrictions.like("orderNo", model.getOrderNo().concat("%")));
	
			
		return criteria;
	}

	
}
