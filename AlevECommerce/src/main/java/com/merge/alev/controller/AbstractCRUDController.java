package com.merge.alev.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import com.merge.alev.dao.intf.IGenericDAO;
import com.merge.alev.dao.model.AbstractModel;

public abstract class AbstractCRUDController<T extends AbstractModel> {

	protected enum Operation { C, R, U, D, L }
	
	@Autowired
	private IGenericDAO<T> dao;
	
	public IGenericDAO<T> getDao() {
		return dao;
	}
	
	public void setDao(IGenericDAO<T> dao) {
		this.dao = dao;
	}

	protected GenericResponse<T> operate(Operation operator, GenericRequest<T> model) {
		
		GenericResponse<T> res = new GenericResponse<T>();
		res.setResponseCode(ResponseCode.OK);
		res.setResponseMesage(new ArrayList<String>());
		res.setModel(new ArrayList<T>());
		
		try {
			
			if (operator != Operation.L && (model == null || model.getModel() == null || model.getModel().isEmpty())) {
				res.setResponseCode(ResponseCode.ERROR);
				res.getResponseMesage().add("ArgumentNull");
				return res;
			}
			
			if (model == null || model.getModel() == null || model.getModel().isEmpty()) {
				res.setModel(dao.list(model.getFirstRecordNumber(), model.getMaxRecordNumber()));
			}
			else {
				boolean oneSucceded = false;
				for (T m : model.getModel()) {
					
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
							res.setModel(dao.listBy(m, model.getFirstRecordNumber(), model.getMaxRecordNumber()));
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

	public abstract GenericResponse<T> create(GenericRequest<T> model);

	public abstract GenericResponse<T> read(GenericRequest<T> model);

	public abstract GenericResponse<T> update(GenericRequest<T> model);

	public abstract GenericResponse<T> delete(GenericRequest<T> model);

	public abstract GenericResponse<T> list(GenericRequest<T> model);
	
}
