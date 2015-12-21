package com.merge.alev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merge.alev.dao.model.Product;
import com.merge.base.controller.AbstractCRUDController;
import com.merge.base.dao.intf.IGenericDAO;

@RestController
@RequestMapping("/product")
public class ProductController extends AbstractCRUDController<Product> {
	
	@Autowired
	private IGenericDAO<Product> productDao;
	
	public IGenericDAO<Product> getDao() {
		return productDao;
	}

}
