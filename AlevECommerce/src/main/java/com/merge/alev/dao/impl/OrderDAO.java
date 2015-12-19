package com.merge.alev.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.merge.alev.dao.model.Order;
import com.merge.alev.dao.model.OrderDetail;
import com.merge.alev.dao.model.Product;
import com.merge.base.dao.impl.AbstractDAO;
import com.merge.base.dao.intf.IGenericDAO;

@Component(value="orderDao")
public class OrderDAO extends AbstractDAO<Order> {

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
		return session
				.createCriteria(Order.class);
	}

	@Override
	public Criteria getListCriteriaBy(Session session, Order model) {
		Criteria criteria = session
				.createCriteria(Order.class);
		
//				.add(Restrictions.like("orderNo", model.getOrderNo().concat("%")));
	
			
		return criteria;
	}

	@Override
	public Order beforeCreate(Order order) throws Exception {
		for (OrderDetail orderDetail : order.getDetails()) {
			orderDetail.setProduct(productDao.read(orderDetail.getProduct()));
		}
		return order;
	}
	
	@Override
	public Order beforeUpdate(Order order) throws Exception {
		for (OrderDetail orderDetail : order.getDetails()) {
			orderDetail.setProduct(productDao.read(orderDetail.getProduct()));
		}
		return order;
	}
	
}
