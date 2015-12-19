package com.merge.alev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.merge.alev.dao.model.OrderDetail;
import com.merge.base.controller.AbstractCRUDController;
import com.merge.base.controller.GenericRequest;
import com.merge.base.controller.GenericResponse;
import com.merge.base.dao.intf.IGenericDAO;

@RestController
public class OrderDetailController extends AbstractCRUDController<OrderDetail> {
	
	@Autowired
	private IGenericDAO<OrderDetail> orderDetailDao;
	
	public IGenericDAO<OrderDetail> getDao() {
		return orderDetailDao;
	}
	
	@Override
	@RequestMapping("/orderdetail/create")
	@ResponseBody
	public GenericResponse<OrderDetail> create(@RequestBody GenericRequest<OrderDetail> request) {
		return super.create(request);
	}

	@Override
	@RequestMapping("/orderdetail/read")
	@ResponseBody
	public GenericResponse<OrderDetail> read(@RequestBody GenericRequest<OrderDetail> request) {
		return super.read(request);
	}

	@Override
	@RequestMapping("/orderdetail/update")
	@ResponseBody
	public GenericResponse<OrderDetail> update(@RequestBody GenericRequest<OrderDetail> request) {
		return super.update(request);
	}

	@Override
	@RequestMapping("/orderdetail/delete")
	@ResponseBody
	public GenericResponse<OrderDetail> delete(@RequestBody GenericRequest<OrderDetail> request) {
		return super.delete(request);
	}	
	
	@Override
	@RequestMapping("/orderdetail/list")
	@ResponseBody
	public GenericResponse<OrderDetail> list(@RequestBody GenericRequest<OrderDetail> request) {
		return super.list(request);
	}	
	
}
