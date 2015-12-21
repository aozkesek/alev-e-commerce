package com.merge.alev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merge.alev.dao.model.Order;
import com.merge.base.controller.AbstractCRUDController;
import com.merge.base.dao.intf.IGenericDAO;

@RestController
@RequestMapping("/order")
public class OrderController extends AbstractCRUDController<Order> {
	
	@Autowired
	private IGenericDAO<Order> orderDao;
	
	public IGenericDAO<Order> getDao() {
		return orderDao;
	}
		
}
