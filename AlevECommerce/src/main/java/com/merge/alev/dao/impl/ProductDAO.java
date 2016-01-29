package com.merge.alev.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.merge.alev.dao.model.Category;
import com.merge.alev.dao.model.Product;
import com.merge.alev.dao.model.ProductPicture;
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
		
		if (model.getCategory().getId() != null) {
			Category category = null;
			
			try { category = categoryDao.read(model.getCategory()); } catch(Exception ex) {}
			
			criteria.add(Restrictions.eq("category", category));
		}
	
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
	
		Product current = read(product);
		List<ProductPicture> currentPictures = current.getPictures();  
		
		current.setActualPrice(product.getActualPrice());
		if (!current.getCategory().getCategoryName().equals(product.getCategory().getCategoryName()))
			current.setCategory(categoryDao.read(product.getCategory()));
		current.setColors(product.getColors());
		current.setDescription(product.getDescription());
		current.setName(product.getName());
		current.setPrice(product.getPrice());
		current.setSizes(product.getSizes());
		current.setTitle(product.getTitle());
		current.setUpdateDate(Calendar.getInstance().getTime());
		
		for (ProductPicture proPic : product.getPictures()) {
			if (proPic.getId() == null) 
				currentPictures.add(proPic);
			else {
				for (ProductPicture updPic : currentPictures)
					if (updPic.getId().equals(proPic.getId())) {
						updPic.setName(proPic.getName());
						updPic.setPath(proPic.getPath());
						break;
					}
			}
		}
		
		if (product.getPictures().size() < currentPictures.size())
			for (ProductPicture proPic : product.getPictures()) {
				if (proPic.getId() == null)
					continue;
				for (ProductPicture curPic : currentPictures) 
					if (curPic.getId().equals(proPic.getId())) {
						currentPictures.remove(curPic);
						break;
					}
						
			}
		return current;
	}
	
	@Override
	public Product beforeDelete(Product product) throws Exception {
		return read(product);
	}
	
}
