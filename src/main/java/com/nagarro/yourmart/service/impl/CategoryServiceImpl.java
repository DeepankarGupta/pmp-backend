package com.nagarro.yourmart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.yourmart.dto.CategoryCountResponse;
import com.nagarro.yourmart.entity.Category;
import com.nagarro.yourmart.repository.CategoryRepository;
import com.nagarro.yourmart.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAllCategories(int offset, int limit) {
		return categoryRepository.getAllCategories(offset, limit);
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryRepository.getCategoryById(id);
	}

	@Override
	public Category addNewCategory(Category category) {
		return categoryRepository.addNewCategory(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return categoryRepository.updateCategory(category);
	}

	@Override
	public Category deleteCategory(int id) {
		return categoryRepository.deleteCategory(id);
	}

	@Override
	public List<CategoryCountResponse> getAllCategoriesWithCount(int offset, int limit) {
		return categoryRepository.getAllCategoriesWithCount(offset, limit);
	}

}
