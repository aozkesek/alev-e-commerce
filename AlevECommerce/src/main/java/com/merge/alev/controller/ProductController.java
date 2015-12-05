package com.merge.alev.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.merge.alev.dao.model.Product;

@RestController
public class ProductController extends AbstractCRUDController<Product> {

	@Override
	@RequestMapping("/product/create")
	public GenericResponse<Product> create(@RequestBody GenericRequest<Product> requestModel) {
		return operate(Operation.C, requestModel);
	}

	@Override
	@RequestMapping("/product/read")
	@ResponseBody
	public GenericResponse<Product> read(@RequestBody GenericRequest<Product> requestModel) {
		return operate(Operation.R, requestModel);
	}

	@Override
	@RequestMapping("/product/update")
	@ResponseBody
	public GenericResponse<Product> update(@RequestBody GenericRequest<Product> requestModel) {
		return operate(Operation.U, requestModel);
	}

	@Override
	@RequestMapping("/product/delete")
	@ResponseBody
	public GenericResponse<Product> delete(@RequestBody GenericRequest<Product> requestModel) {
		return operate(Operation.D, requestModel);
	}

	@Override
	@RequestMapping("/product/list")
	@ResponseBody
	public GenericResponse<Product> list(@RequestBody GenericRequest<Product> requestModel) {
		return operate(Operation.L, requestModel);
	}


}
