package com.merge.alev.dao.impl;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.merge.alev.dao.model.Product;
import com.merge.base.dao.impl.AbstractDAO;

@Component
public class ProductDAO extends AbstractDAO<Product> {

	@Override
	public Criteria getListCriteria(Session session) {
		return session.createCriteria(Product.class);
	}

	@Override
	public Criteria getListCriteriaBy(Session session, Product model) {
		Criteria criteria = session.createCriteria(Product.class);
		
		if (model.getCategoryId() != null)
			criteria.add(Restrictions.eq("categoryId", model.getCategoryId()));
	
		if (model.getName() != null && !model.getName().isEmpty())
			criteria.add(Restrictions.like("name", model.getName().concat("%")));

		if (model.getTitle() != null && !model.getTitle().isEmpty())
			criteria.add(Restrictions.like("title", "%".concat(model.getTitle()).concat("%")));

			
		return criteria;
	}

	@Override
	public Product create(Product product) {
		
		Date now = Calendar.getInstance().getTime();
		product.setCreateDate(now);
		product.setUpdateDate(now);
		
		return super.create(product);
	}
	
	@Override
	public Product update(Product product) {
		
		Date now = Calendar.getInstance().getTime();
		product.setUpdateDate(now);
		
		return super.create(product);
	}
	
}
