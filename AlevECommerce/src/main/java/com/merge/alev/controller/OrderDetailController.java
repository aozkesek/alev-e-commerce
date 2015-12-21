package com.merge.alev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merge.alev.dao.model.OrderDetail;
import com.merge.base.controller.AbstractCRUDController;
import com.merge.base.dao.intf.IGenericDAO;

@RestController
@RequestMapping("/orderdetail")
public class OrderDetailController extends AbstractCRUDController<OrderDetail> {
	
	@Autowired
	private IGenericDAO<OrderDetail> orderDetailDao;
	
	public IGenericDAO<OrderDetail> getDao() {
		return orderDetailDao;
	}
		
}
