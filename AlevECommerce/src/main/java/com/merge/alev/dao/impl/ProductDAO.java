package com.merge.alev.dao.impl;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.merge.alev.dao.model.Category;
import com.merge.alev.dao.model.Product;
import com.merge.base.dao.impl.AbstractDAO;
import com.merge.base.dao.intf.IGenericDAO;

@Component(value="productDao")
public class ProductDAO extends AbstractDAO<Product> {
	
	@Autowired
	private IGenericDAO<Category> categoryDao;

	public IGenericDAO<Category> getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(IGenericDAO<Category> categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public Criteria getListCriteria(Session session) {
		return session.createCriteria(Product.class);
	}

	@Override
	public Criteria getListCriteriaBy(Session session, Product model) {
		Criteria criteria = session.createCriteria(Product.class);
		
		if (model.getCategory().getId() != null)
			criteria.add(Restrictions.eq("categoryId", model.getCategory().getId()));
	
		if (model.getName() != null && !model.getName().isEmpty())
			criteria.add(Restrictions.like("name", model.getName().concat("%")));

		if (model.getTitle() != null && !model.getTitle().isEmpty())
			criteria.add(Restrictions.like("title", "%".concat(model.getTitle()).concat("%")));

			
		return criteria;
	}

	@Override
	public Product beforeCreate(Product product) throws Exception {
		
		Date now = Calendar.getInstance().getTime();
		product.setCreateDate(now);
		product.setUpdateDate(now);
		//change the category object with a transient one
		product.setCategory(categoryDao.read(product.getCategory()));
		
		return product;
	}
	
	@Override
	public Product beforeUpdate(Product product) throws Exception {
		
		Date now = Calendar.getInstance().getTime();
		product.setUpdateDate(now);
		//change the category object with a transient one
		product.setCategory(categoryDao.read(product.getCategory()));
				
		return product;
	}
	
}
