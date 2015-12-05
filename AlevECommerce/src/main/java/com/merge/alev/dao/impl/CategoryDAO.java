package com.merge.alev.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.merge.alev.dao.model.Category;

@Component
public class CategoryDAO extends AbstractDiscreteDAO<Category> {

	@Override
	public Query getListQuery(Session session) {
		return session.createQuery("from Category c");
	}

	@Override
	public Criteria getListByCriteria(Session session, Category model) {
		return session
				.createCriteria(Category.class)
				.add(Restrictions.like("categoryName", model.getCategoryName().concat("%")));
	}

	
}
