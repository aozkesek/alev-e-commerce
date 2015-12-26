package com.merge.base.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.merge.base.dao.intf.IGenericDAO;
import com.merge.base.dao.model.AbstractModel;
import com.merge.base.dao.model.CrudEnumeration;

public abstract class AbstractCRUDController<T extends AbstractModel> {

	abstract public IGenericDAO<T> getDao();

	private GenericResponse<T> operate(CrudEnumeration operator, GenericRequest<T> request) {
		
		GenericResponse<T> response = new GenericResponse<T>();
		response.setResponseCode(ResponseCode.OK);
		response.setResponseMesage(new ArrayList<String>());
		response.setTotalRecordNumber(0);
		response.setModel(new ArrayList<T>());
		
		try {
			
			boolean isModelNullOrEmpty = request == null || request.getModel() == null || request.getModel().isEmpty();
			
			if (operator != CrudEnumeration.Q && isModelNullOrEmpty) {
				response.setResponseCode(ResponseCode.ERROR);
				response.getResponseMesage().add("ArgumentNull");
				return response;
			}
			
			response.setFirstRecordNumber(request.getFirstRecordNumber());
			
			if (isModelNullOrEmpty) {
				response.setTotalRecordNumber(getDao().getListMaxResult());
				response.setModel(getDao().list(request.getFirstRecordNumber(), request.getMaxRecordNumber()));
			}
			else {
				boolean oneSucceded = false;
				for (T m : request.getModel()) {
					
					try {
	
						switch (operator) {
						case C:
							response.getModel().add(getDao().create(m));
							break;
						
						case R:
							response.getModel().add(getDao().read(m));
							break;
							
						case U:
							response.getModel().add(getDao().update(m));
							break;
						
						case D:
							response.getModel().add(getDao().delete(m));
							break;
						
						case Q:
							//we may be reconsider to take only one item at calling list, 
							//have not been tested in every case yet.  still might need to develop
							int maxResult = getDao().getListMaxResultBy(m);
							response.setTotalRecordNumber(response.getTotalRecordNumber() + maxResult);
							
							if (request.getFirstRecordNumber() > response.getTotalRecordNumber())
								break;
							
							if (request.getFirstRecordNumber() + request.getMaxRecordNumber() <= response.getTotalRecordNumber()) {
								response.getModel()
									.addAll(getDao().listBy(m, request.getFirstRecordNumber(), request.getMaxRecordNumber()));
								break;
							}
							
							response.getModel()
								.addAll(getDao().listBy(
										m
										, response.getModel().size() > 0 ? 0 : request.getFirstRecordNumber()
										, request.getMaxRecordNumber()));
							
							break;
							
						}
	
						oneSucceded = true;
					}
					catch(Exception ex) {
						response.setResponseCode(ResponseCode.ERROR);
						if (ex.getCause() != null)
							response.getResponseMesage().add(ex.getCause().getMessage().concat("\n").concat(m.toString()));
						else	
							response.getResponseMesage().add(ex.getMessage().concat("\n").concat(m.toString()));
					}
					
					if (oneSucceded && response.getResponseCode() == ResponseCode.ERROR)
						response.setResponseCode(ResponseCode.WARNING);
				}
			}
			
			return response;
		}
		catch(Exception ex) {
			response.setResponseCode(ResponseCode.ERROR);
			response.getResponseMesage().add(ex.getMessage());
			return response;
		}
		
	}

	@RequestMapping("/create")
	@ResponseBody
	public GenericResponse<T> create(@RequestBody GenericRequest<T> request) {
		return operate(CrudEnumeration.C, request);
	}

	@RequestMapping("/read")
	@ResponseBody
	public GenericResponse<T> read(@RequestBody GenericRequest<T> request) {
		return operate(CrudEnumeration.R, request);
	}

	@RequestMapping("/update")
	@ResponseBody
	public GenericResponse<T> update(@RequestBody GenericRequest<T> request) {
		return operate(CrudEnumeration.U, request);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public GenericResponse<T> delete(@RequestBody GenericRequest<T> request) {
		return operate(CrudEnumeration.D, request);
	}

	@RequestMapping("/list")
	@ResponseBody
	public GenericResponse<T> list(@RequestBody GenericRequest<T> request) {
		return operate(CrudEnumeration.Q, request);
	}
	
	@RequestMapping("/listTotalRecord")
	@ResponseBody
	public Integer listTotalRecord(@RequestBody GenericRequest<T> request) {
		boolean isModelNullOrEmpty = request == null || request.getModel() == null || request.getModel().isEmpty();
		
		if (isModelNullOrEmpty)
			return getDao().getListMaxResult();
		
		int totalRecord = 0;
		for (T m : request.getModel()) 
			totalRecord += getDao().getListMaxResultBy(m);
		
		return totalRecord;
	}
}
