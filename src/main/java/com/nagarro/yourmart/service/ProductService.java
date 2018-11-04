package com.nagarro.yourmart.service;

import java.util.List;

import com.nagarro.yourmart.entity.Product;

public interface ProductService {
	
	List<Product> getAllProducts(String sortBy, String searchBy, String searchValue, Integer status, Integer category, Integer id, int offset, int limit);
	List<Product> getAllProducts(String sortBy, String searchBy, String searchValue, Integer status, Integer category, Integer sellerId, String sellerCompanyName, int offset, int limit);
	Product getProductById(int id);
	Product addNewProduct(Product product);
	Product updateProduct(Product product);
	Product deleteProduct(int id);
	Product changeProductStatus(int productId, int status);
	Product addComment(int id, String comment);
	Integer getSellerId(int productId);
}