package com.merge.alev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merge.alev.dao.model.Fee;
import com.merge.base.controller.AbstractCRUDController;
import com.merge.base.dao.intf.IGenericDAO;

@RestController
@RequestMapping("/fee")
public class FeeController extends AbstractCRUDController<Fee> {
	
	@Autowired
	private IGenericDAO<Fee> feeDao;
	
	public IGenericDAO<Fee> getDao() {
		return feeDao;
	}
		
}
