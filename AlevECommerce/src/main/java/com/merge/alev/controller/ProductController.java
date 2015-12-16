package com.merge.alev.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.merge.alev.dao.model.Product;
import com.merge.base.controller.GenericCRUDController;
import com.merge.base.controller.GenericRequest;
import com.merge.base.controller.GenericResponse;

@RestController
public class ProductController extends GenericCRUDController<Product> {

	@Override
	@RequestMapping("/product/create")
	@ResponseBody
	public GenericResponse<Product> create(@RequestBody GenericRequest<Product> request) {
		return super.create(request);
	}

	@Override
	@RequestMapping("/product/read")
	@ResponseBody
	@Transactional
	public GenericResponse<Product> read(@RequestBody GenericRequest<Product> request) {
		GenericResponse<Product> response = super.read(request);
		
		if (response.getResponseCode().equals(0)) {
			for (Product p : response.getModel()) {
				p.getCategory().getId();
				p.getPictures().size();
			}
		}
		
		return response;
	}

	@Override
	@RequestMapping("/product/update")
	@ResponseBody
	public GenericResponse<Product> update(@RequestBody GenericRequest<Product> request) {
		return super.update(request);
	}

	@Override
	@RequestMapping("/product/delete")
	@ResponseBody
	public GenericResponse<Product> delete(@RequestBody GenericRequest<Product> request) {
		return super.delete(request);
	}

	@Override
	@RequestMapping("/product/list")
	@ResponseBody
	public GenericResponse<Product> list(@RequestBody GenericRequest<Product> request) {
		return super.list(request);
	}


}
