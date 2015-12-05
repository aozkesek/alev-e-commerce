package com.merge.alev.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.merge.alev.dao.model.Product;

@Component
public class ProductDAO extends AbstractDiscreteDAO<Product> {

	@Override
	public Criteria getListCriteria(Session session) {
		return session.createCriteria(Product.class);
	}

	@Override
	public Criteria getListByCriteria(Session session, Product model) {
		Criteria criteria = session.createCriteria(Product.class);
		
		if (model.getCategoryId() != null)
			criteria.add(Restrictions.eq("categoryId", model.getCategoryId()));
	
		if (model.getName() != null && !model.getName().isEmpty())
			criteria.add(Restrictions.like("name", model.getName().concat("%")));

		if (model.getTitle() != null && !model.getTitle().isEmpty())
			criteria.add(Restrictions.like("title", "%".concat(model.getTitle()).concat("%")));

			
		return criteria;
	}

	
}
