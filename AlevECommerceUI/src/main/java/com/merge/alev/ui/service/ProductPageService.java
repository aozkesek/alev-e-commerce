package com.merge.alev.ui.service;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.merge.alev.ui.model.Category;
import com.merge.alev.ui.model.Product;
import com.merge.alev.ui.model.ProductPicture;
import com.merge.alev.ui.model.reponse.ProductResponse;
import com.merge.alev.ui.model.request.ProductRequest;

@Component("productPageService")
public class ProductPageService implements IPageService {

	 interface IValueOf<T> {
		T valueOf(String value);
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response, Model model) {
		String contentType = request.getContentType();
		if (contentType == null || !contentType.startsWith("multipart/form-data"))
			return "administration/product";
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, String[]> paramMap = multipartRequest.getParameterMap();	
		Map<String,MultipartFile> files = multipartRequest.getFileMap();
		
		Product product = new Product();
		product.setCategory(new Category());
		product.setPictures(new ArrayList<ProductPicture>());
		try {
			
			product.setActualPrice(isNullOrEmpty(paramMap.get("actualPrice")[0], new IValueOf<Double>() { 
				@Override 
				public Double valueOf(String value) {
					return Double.valueOf(value);
				}
				}, 0.0)
			);
			product.setColors(paramMap.get("colors")[0]);
			product.setDescription(paramMap.get("description")[0]);
			product.setId(isNullOrEmpty(paramMap.get("productId")[0], new IValueOf<Integer>() { 
				@Override 
				public Integer valueOf(String value) {
					return Integer.valueOf(value);
				}
				}, null)
			);
			product.setName(paramMap.get("name")[0]);
			product.setPrice(isNullOrEmpty(paramMap.get("price")[0], new IValueOf<Double>() { 
				@Override 
				public Double valueOf(String value) {
					return Double.valueOf(value);
				}
				}, 0.0)
			);
			product.setSizes(paramMap.get("sizes")[0]);
			product.setTitle(paramMap.get("title")[0]);
			
			product.getCategory().setCategoryName(paramMap.get("categoryName")[0]);
			product.getCategory().setId(isNullOrEmpty(paramMap.get("categoryId")[0], new IValueOf<Integer>() { 
				@Override 
				public Integer valueOf(String value) {
					return Integer.valueOf(value);
				}
				}, null)
			);
			
			for (String picName : paramMap.get("pictures")) {
				ProductPicture picture = new ProductPicture();
				picture.setName(picName);
				picture.setPath("/pictures/product/".concat(product.getName().concat("/")));
//				pictureId settings is not clear 
				product.getPictures().add(picture);
			}
			
			for (MultipartFile rawPic : files.values()) {
				String name = rawPic.getName();
				
				
				ProductPicture picture = new ProductPicture();
				picture.setName(name);
				picture.setPath("/pictures/product/".concat(product.getName().concat("/")));
				product.getPictures().add(picture);
			}
	
			ProductRequest productReq = new ProductRequest();
			productReq.setModel(new ArrayList<Product>());
			productReq.getModel().add(product);
			
			String addUpdate = product.getId() == null ? "/create" : "/update";
			
			ProductResponse productRes = RestProxy.postForObject(ServiceConstants.ProductEndpoint + addUpdate, productReq, ProductResponse.class);
			
			response.sendRedirect("/administration?activeIndex=3");
			
		} catch(Exception ex) {
			
		}
		
		return "administration/index";
	}

	private static <T> T isNullOrEmpty(String value, IValueOf<T> valueOf , T defaultValue) {
		if (value == null || value.isEmpty())
			return defaultValue;
	
		try {
			return valueOf.valueOf(value);
		} catch(Exception ex) {
			return defaultValue;	
		}
		
	}
}
