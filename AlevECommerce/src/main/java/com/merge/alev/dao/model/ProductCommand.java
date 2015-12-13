package com.merge.alev.dao.model;

import java.io.Serializable;
import java.util.List;

public class ProductCommand extends Product implements Serializable {

	private List<ProductPicture> pictures;

	public List<ProductPicture> getPictures() {
		return pictures;
	}

	public void setPictures(List<ProductPicture> pictures) {
		this.pictures = pictures;
	}
	
}
