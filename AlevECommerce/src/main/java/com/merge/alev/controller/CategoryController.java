package com.merge.alev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merge.alev.dao.model.Category;
import com.merge.base.controller.AbstractCRUDController;
import com.merge.base.dao.intf.IGenericDAO;

@RestController
@RequestMapping("/category")
public class CategoryController extends AbstractCRUDController<Category> {
	
	@Autowired
	private IGenericDAO<Category> categoryDao;
	
	public IGenericDAO<Category> getDao() {
		return categoryDao;
	}
	
}
