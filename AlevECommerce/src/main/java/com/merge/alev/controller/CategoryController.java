package com.merge.alev.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.merge.alev.dao.model.Category;

@RestController
public class CategoryController extends AbstractCRUDController<Category> {
	
	@Override
	@RequestMapping("/category/create")
	@ResponseBody
	public GenericResponse<Category> create(@RequestBody GenericRequest<Category> requestModel) {
		return operate(Operation.C, requestModel);
	}

	@Override
	@RequestMapping("/category/read")
	@ResponseBody
	public GenericResponse<Category> read(@RequestBody GenericRequest<Category> requestModel) {
		return operate(Operation.R, requestModel);
	}

	@Override
	@RequestMapping("/category/update")
	@ResponseBody
	public GenericResponse<Category> update(@RequestBody GenericRequest<Category> requestModel) {
		return operate(Operation.U, requestModel);
	}

	@Override
	@RequestMapping("/category/delete")
	@ResponseBody
	public GenericResponse<Category> delete(@RequestBody GenericRequest<Category> requestModel) {
		return operate(Operation.D, requestModel);
	}	
	
	@Override
	@RequestMapping("/category/list")
	@ResponseBody
	public GenericResponse<Category> list(@RequestBody GenericRequest<Category> requestModel) {
		return operate(Operation.L, requestModel);
	}	
	
}
