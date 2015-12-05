package com.merge.alev.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.merge.alev.dao.model.Product;

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
	public GenericResponse<Product> read(@RequestBody GenericRequest<Product> request) {
		return super.read(request);
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
