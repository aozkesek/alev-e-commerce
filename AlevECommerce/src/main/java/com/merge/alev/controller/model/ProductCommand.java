package com.merge.alev.controller.model;

import java.io.Serializable;
import java.util.List;

import com.merge.alev.dao.model.Product;
import com.merge.alev.dao.model.ProductPicture;

public class ProductCommand extends Product implements Serializable {

	private List<ProductPicture> pictures;

	public List<ProductPicture> getPictures() {
		return pictures;
	}

	public void setPictures(List<ProductPicture> pictures) {
		this.pictures = pictures;
	}
	
}
