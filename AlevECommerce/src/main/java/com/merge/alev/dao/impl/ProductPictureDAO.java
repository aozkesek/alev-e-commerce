package com.merge.alev.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.merge.alev.dao.model.ProductPicture;
import com.merge.base.dao.impl.AbstractDAO;

@Component
public class ProductPictureDAO extends AbstractDAO<ProductPicture> {

	@Override
	public Criteria getListCriteria(Session session) {
		return session.createCriteria(ProductPicture.class);
	}

	@Override
	public Criteria getListCriteriaBy(Session session, ProductPicture model) {
		Criteria criteria = session
				.createCriteria(ProductPicture.class)
				.add(Restrictions.eq("productId", model.getProductId()));

		return criteria;
	}

	
}
