package com.nagarro.yourmart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.yourmart.entity.Product;
import com.nagarro.yourmart.repository.ProductRepository;
import com.nagarro.yourmart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts(String sortBy, String searchBy, String searchValue, Integer status,
			Integer category, Integer id, int offset, int limit) {
		return productRepository.getAllProducts(sortBy, searchBy, searchValue, status, category, id, offset, limit);
	}

	@Override
	public List<Product> getAllProducts(String sortBy, String searchBy, String searchValue, Integer status,
			Integer category, Integer sellerId, String sellerCompanyName, int offset, int limit) {
		return productRepository.getAllProducts(sortBy, searchBy, searchValue, status, category, sellerId, sellerCompanyName, offset, limit);
	}

	@Override
	public Product getProductById(int id) {
		return productRepository.getProductById(id);
	}

	@Override
	public Product addNewProduct(Product product) {
		product.setStatus(1);
		product.setComment("none");
		return productRepository.addNewProduct(product);
	}

	@Override
	public Product updateProduct(Product product) {
		product.setStatus(4);
		return productRepository.updateProduct(product);
	}

	@Override
	public Product deleteProduct(int id) {
		return productRepository.deleteProduct(id);
	}

	@Override
	public Product changeProductStatus(int productId, int status) {
		return productRepository.changeProductStatus(productId, status);
	}

	@Override
	public Product addComment(int id, String comment) {
		return productRepository.addComment(id, comment);
	}

	@Override
	public Integer getSellerId(int productId) {
		return productRepository.getSellerId(productId);
	}

	@Override
	public Long getProductsCount() {
		return productRepository.getProductsCount();
	}

}
