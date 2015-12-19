package com.merge.alev.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.merge.alev.dao.model.OrderDetail;
import com.merge.alev.dao.model.Product;
import com.merge.base.dao.impl.AbstractDAO;
import com.merge.base.dao.intf.IGenericDAO;

@Component(value="orderDetailDao")
public class OrderDetailDAO extends AbstractDAO<OrderDetail> {
	
	@Autowired
	private IGenericDAO<Product> productDao;

	public IGenericDAO<Product> getProductDao() {
		return productDao;
	}

	public void setProductDao(IGenericDAO<Product> productDao) {
		this.productDao = productDao;
	}

	@Override
	public Criteria getListCriteria(Session session) {
		return session.createCriteria(OrderDetail.class);
	}

	@Override
	public Criteria getListCriteriaBy(Session session, OrderDetail model) {
		Criteria criteria = session
				.createCriteria(OrderDetail.class);
//				.add(Restrictions.like("name", model.getName().concat("%")));
//			
		return criteria;
	}

	@Override
	public OrderDetail beforeCreate(OrderDetail orderDetail) throws Exception {
		orderDetail.setProduct(productDao.read(orderDetail.getProduct()));
		return orderDetail;
	}
	
	@Override
	public OrderDetail beforeUpdate(OrderDetail orderDetail) throws Exception {
		orderDetail.setProduct(productDao.read(orderDetail.getProduct()));
		return orderDetail;
	}
}
