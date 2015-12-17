package com.merge.base.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.merge.base.dao.intf.IGenericDAO;
import com.merge.base.dao.model.AbstractModel;
import com.merge.base.dao.model.CrudEnumeration;

public class GenericCRUDController<T extends AbstractModel> {

	@Autowired
	private IGenericDAO<T> dao;
	
	public IGenericDAO<T> getDao() {
		return dao;
	}
	
	public void setDao(IGenericDAO<T> dao) {
		this.dao = dao;
	}

	private GenericResponse<T> operate(CrudEnumeration operator, GenericRequest<T> request) {
		
		GenericResponse<T> res = new GenericResponse<T>();
		res.setResponseCode(ResponseCode.OK);
		res.setResponseMesage(new ArrayList<String>());
		res.setModel(new ArrayList<T>());
		
		try {
			
			boolean isModelNullOrEmpty = request == null || request.getModel() == null || request.getModel().isEmpty();
			
			if (operator != CrudEnumeration.Q && isModelNullOrEmpty) {
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
						
						case Q:
							res.setTotalRecordNumber(dao.getListMaxResultBy(m));
							res.setModel(dao.listBy(m, request.getFirstRecordNumber(), request.getMaxRecordNumber()));
							break;
							
						}
	
						oneSucceded = true;
					}
					catch(Exception ex) {
						res.setResponseCode(ResponseCode.ERROR);
						if (ex.getCause() != null)
							res.getResponseMesage().add(ex.getCause().getMessage().concat("\n").concat(m.toString()));
						else	
							res.getResponseMesage().add(ex.getMessage().concat("\n").concat(m.toString()));
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
		return operate(CrudEnumeration.C, request);
	}

	public GenericResponse<T> read(GenericRequest<T> request) {
		return operate(CrudEnumeration.R, request);
	}

	public GenericResponse<T> update(GenericRequest<T> request) {
		return operate(CrudEnumeration.U, request);
	}

	public GenericResponse<T> delete(GenericRequest<T> request) {
		return operate(CrudEnumeration.D, request);
	}

	public GenericResponse<T> list(GenericRequest<T> request) {
		return operate(CrudEnumeration.Q, request);
	}
	
}
