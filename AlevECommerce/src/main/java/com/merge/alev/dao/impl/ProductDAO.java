package com.merge.alev.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.merge.alev.dao.model.Product;

@Component
public class ProductDAO extends AbstractDiscreteDAO<Product> {

	@Override
	public Query getListQuery(Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Criteria getListByCriteria(Session session, Product model) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
