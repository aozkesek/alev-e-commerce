package com.merge.alev.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.merge.alev.dao.model.Category;
import com.merge.base.dao.impl.AbstractDiscreteDAO;

@Component
public class CategoryDAO extends AbstractDiscreteDAO<Category> {

	@Override
	public Criteria getListCriteria(Session session) {
		return session.createCriteria(Category.class);
	}

	@Override
	public Criteria getListByCriteria(Session session, Category model) {
		return session
				.createCriteria(Category.class)
				.add(Restrictions.like("categoryName", model.getCategoryName().concat("%")));
	}

	
}
