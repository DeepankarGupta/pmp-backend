package com.nagarro.yourmart.dto;

import java.util.List;

public class ProductListResponse {

	private List<ProductResponse> products;
	private Long productsCount;

	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}

	public Long getProductsCount() {
		return productsCount;
	}

	public void setProductsCount(Long productsCount) {
		this.productsCount = productsCount;
	}

}
