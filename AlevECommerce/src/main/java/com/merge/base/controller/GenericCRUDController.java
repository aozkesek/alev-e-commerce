package com.merge.base.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.merge.base.dao.intf.IGenericDAO;
import com.merge.base.dao.model.AbstractModel;

public class GenericCRUDController<T extends AbstractModel> {

	private enum Operation { C, R, U, D, L }
	
	@Autowired
	private IGenericDAO<T> dao;
	
	public IGenericDAO<T> getDao() {
		return dao;
	}
	
	public void setDao(IGenericDAO<T> dao) {
		this.dao = dao;
	}

	private GenericResponse<T> operate(Operation operator, GenericRequest<T> request) {
		
		GenericResponse<T> res = new GenericResponse<T>();
		res.setResponseCode(ResponseCode.OK);
		res.setResponseMesage(new ArrayList<String>());
		res.setModel(new ArrayList<T>());
		
		try {
			
			boolean isModelNullOrEmpty = request == null || request.getModel() == null || request.getModel().isEmpty();
			
			if (operator != Operation.L && isModelNullOrEmpty) {
				res.setResponseCode(ResponseCode.ERROR);
				res.getResponseMesage().add("ArgumentNull");
				return res;
			}
			
			res.setFirstRecordNumber(request.getFirstRecordNumber());
			
			if (isModelNullOrEmpty) {
				res.setTotalRecordNumber(dao.getListMaxResult());
				res.setModel(dao.list(request.getFirstRecordNumber(), request.getMaxRecordNumber()));
			}
			else {
				boolean oneSucceded = false;
				for (T m : request.getModel()) {
					
					try {
	
						switch (operator) {
						case C:
							res.getModel().add(dao.create(m));
							break;
						
						case R:
							res.getModel().add(dao.read(m));
							break;
							
						case U:
							res.getModel().add(dao.update(m));
							break;
						
						case D:
							res.getModel().add(dao.delete(m));
							break;
						
						case L:
							res.setTotalRecordNumber(dao.getListByMaxResult(m));
							res.setModel(dao.listBy(m, request.getFirstRecordNumber(), request.getMaxRecordNumber()));
							break;
							
						}
	
						oneSucceded = true;
					}
					catch(Exception ex) {
						res.setResponseCode(ResponseCode.ERROR);
						res.getResponseMesage().add(ex.getMessage());
					}
					
					if (oneSucceded && res.getResponseCode() == ResponseCode.ERROR)
						res.setResponseCode(ResponseCode.WARNING);
				}
			}
			
			return res;
		}
		catch(Exception ex) {
			res.setResponseCode(ResponseCode.ERROR);
			res.getResponseMesage().add(ex.getMessage());
			return res;
		}
		
	}

	public GenericResponse<T> create(GenericRequest<T> request) {
		return operate(Operation.C, request);
	}

	public GenericResponse<T> read(GenericRequest<T> request) {
		return operate(Operation.R, request);
	}

	public GenericResponse<T> update(GenericRequest<T> request) {
		return operate(Operation.U, request);
	}

	public GenericResponse<T> delete(GenericRequest<T> request) {
		return operate(Operation.D, request);
	}

	public GenericResponse<T> list(GenericRequest<T> request) {
		return operate(Operation.L, request);
	}
	
}
