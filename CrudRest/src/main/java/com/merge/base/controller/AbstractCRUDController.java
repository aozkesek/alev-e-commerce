package com.merge.base.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;

import com.merge.base.dao.intf.IGenericDAO;
import com.merge.base.dao.model.AbstractModel;
import com.merge.base.dao.model.CrudEnumeration;

public abstract class AbstractCRUDController<T extends AbstractModel> {

	abstract public IGenericDAO<T> getDao();

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
				res.setTotalRecordNumber(getDao().getListMaxResult());
				res.setModel(getDao().list(request.getFirstRecordNumber(), request.getMaxRecordNumber()));
			}
			else {
				boolean oneSucceded = false;
				for (T m : request.getModel()) {
					
					try {
	
						switch (operator) {
						case C:
							res.getModel().add(getDao().create(m));
							break;
						
						case R:
							res.getModel().add(getDao().read(m));
							break;
							
						case U:
							res.getModel().add(getDao().update(m));
							break;
						
						case D:
							res.getModel().add(getDao().delete(m));
							break;
						
						case Q:
							res.setTotalRecordNumber(getDao().getListMaxResultBy(m));
							res.setModel(getDao().listBy(m, request.getFirstRecordNumber(), request.getMaxRecordNumber()));
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

	@RequestMapping("/create")
	public GenericResponse<T> create(GenericRequest<T> request) {
		return operate(CrudEnumeration.C, request);
	}

	@RequestMapping("/read")
	public GenericResponse<T> read(GenericRequest<T> request) {
		return operate(CrudEnumeration.R, request);
	}

	@RequestMapping("/update")
	public GenericResponse<T> update(GenericRequest<T> request) {
		return operate(CrudEnumeration.U, request);
	}

	@RequestMapping("/delete")
	public GenericResponse<T> delete(GenericRequest<T> request) {
		return operate(CrudEnumeration.D, request);
	}

	@RequestMapping("/list")
	public GenericResponse<T> list(GenericRequest<T> request) {
		return operate(CrudEnumeration.Q, request);
	}
	
}
