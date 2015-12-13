package com.merge.alev.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
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
	public Criteria getListByCriteria(Session session, ProductPicture model) {
		Criteria criteria = session
				.createCriteria(ProductPicture.class);
//				.add(Restrictions.like("name", model.getName().concat("%")));
//	
//		if (model.getPostCode() != null && !model.getPostCode().isEmpty())
//			criteria.add(Restrictions.like("postCode", model.getPostCode().concat("%")));
//			
		return criteria;
	}

	
}
