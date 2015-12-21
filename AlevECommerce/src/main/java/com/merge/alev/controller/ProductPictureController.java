package com.merge.alev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merge.alev.dao.model.ProductPicture;
import com.merge.base.controller.AbstractCRUDController;
import com.merge.base.dao.intf.IGenericDAO;

@RestController
@RequestMapping("/productpicture")
public class ProductPictureController extends AbstractCRUDController<ProductPicture> {
	
	@Autowired
	private IGenericDAO<ProductPicture> productPictureDao;
	
	public IGenericDAO<ProductPicture> getDao() {
		return productPictureDao;
	}
	
}
